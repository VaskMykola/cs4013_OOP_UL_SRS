package utilities;

import java.util.*;

/**
 * The `QCACalculator` class provides methods for calculating the Quality Credit Average (QCA)
 * for specific students and studying periods based on their grades.
 */
public class QCACalculator {
    /**
     * Represents the grade scheme data.
     */
    private static final CSVHandler gradeScheme = new CSVHandler(String.format("./csvFiles/%s.csv", "gradeBands"));
    /**
     * Represents the student information data.
     */
    private static final CSVHandler studentInformation = new CSVHandler(String.format("./csvFiles/%s.csv", "StudentModulesTaken"));

    /**
     * Determines the Quality Point Value (QPV) for a given grade.
     *
     * @param grade The grade for which QPV needs to be determined.
     * @return The Quality Point Value (QPV) corresponding to the given grade.
     */
    private static double determineQPVForModuleGivenGrade(String grade) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("Grade", grade);
        return Double.parseDouble(
                gradeScheme.findValueOfSpecificColumnInSpecificRow(
                        gradeScheme.findRowsWithColumnValuesSpecified(attributes).get(
                                gradeScheme.getColumnHeaders().get("Grade")), "QPV"));
    }

    /**
     * Finds the grades for a specific student in a given academic year and semester.
     *
     * @param studentId    The ID of the student.
     * @param academicYear The academic year.
     * @param semester     The semester.
     * @return A map containing module names and their corresponding grades for the specified student.
     */
    public static Map<String, String> findGradesForSpecificStudentForSpecificPeriod(String studentId, String academicYear, String semester) {

        Map<String, String> attributesForStudentDataTable = new HashMap<>();
        attributesForStudentDataTable.put("ID", studentId);
        attributesForStudentDataTable.put("Academic Year", academicYear);
        attributesForStudentDataTable.put("Semester", semester);
        List<String> particularStudentInfo = studentInformation.findRowsWithColumnValuesSpecified(attributesForStudentDataTable);

        Map<String, String> studentGradesMap = new HashMap<>();
        for (String studentRecord : particularStudentInfo) {
            String module = studentInformation.findValueOfSpecificColumnInSpecificRow(studentRecord, "Module");
            CSVHandler moduleInfo = new CSVHandler(String.format("./csvFiles/modulesByAcademicYear/%s_%s.csv", module, academicYear));
            Map<String, String> attributesForModuleDataTable = new HashMap<>();
            attributesForModuleDataTable.put("ID", studentId);
            attributesForStudentDataTable.put("Semester", semester);
            List<String> particularModuleInfo = moduleInfo.findRowsWithColumnValuesSpecified(attributesForModuleDataTable); // supposed to contain only one String as student IDs are unique
            String grade = moduleInfo.findValueOfSpecificColumnInSpecificRow(particularModuleInfo.get(0), "Grade");
            studentGradesMap.put(module, grade);
        }
        return studentGradesMap;
    }

    /**
     * Determines the Quality Point Values (QPV) for a collection of modules and grades.
     *
     * @param gradesForSomeModules A map containing module names and their corresponding grades.
     * @return A map containing module names and their corresponding Quality Point Values (QPV).
     */
    private static Map<String, Double> determineQPVForSeveralModules(Map<String, String> gradesForSomeModules) {
        Map<String, Double> qpvsForTheModules = new HashMap<>();
        for (Map.Entry<String, String> moduleAndGradeForIt : gradesForSomeModules.entrySet()) {
            double qpv = determineQPVForModuleGivenGrade(moduleAndGradeForIt.getValue());
            qpvsForTheModules.put(moduleAndGradeForIt.getKey(), qpv);
        }
        return qpvsForTheModules;
    }

    /**
     * Calculates the Quality Credit Average (QCA) for a specific student in a given academic year and semester.
     *
     * @param studentId    The ID of the student.
     * @param academicYear The academic year.
     * @param semester     The semester.
     * @return The calculated Quality Credit Average (QCA) for the specified student in the given period.
     */
    public static double calculateQCAForSpecificPeriod(String studentId, String academicYear, String semester) {
        Map<String, String> studentModulesTakenAndGradesForThem = findGradesForSpecificStudentForSpecificPeriod(studentId, academicYear, semester);
        Map<String, Double> studentModulesTakenAndQpvsForThem = determineQPVForSeveralModules(studentModulesTakenAndGradesForThem);
        double total = 0;
        for (String module : studentModulesTakenAndQpvsForThem.keySet()) {
            total += studentModulesTakenAndQpvsForThem.get(module);
        }
        return total / studentModulesTakenAndQpvsForThem.size();
    }

    /**
     * Calculates the Quality Credit Average (QCA) for every studying period of a specific student.
     *
     * @param studentId The ID of the student.
     * @return A map containing every studying period and its corresponding QCA for the specified student.
     */
    public static Map<String, Map<String, String>> calculateQCAForEveryStudyingPeriod(String studentId) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("ID", studentId);
        List<String> specificStudentData = studentInformation.findRowsWithColumnValuesSpecified(attributes);
        List<String> allStudyingPeriodsForTheStudent = studentInformation.findDistinctValuesForSeveralSpecificColumns(specificStudentData, new String[]{"Academic Year", "Semester"});
        Map<String, Map<String, String>> everyPeriodAndQCAForThisPeriod = new LinkedHashMap<>();
        double sumOfAllPreviousQCAs = 0;
        int numberOfPeriods = 0;
        for (String studyingPeriod : allStudyingPeriodsForTheStudent) {
            numberOfPeriods++;
            String[] studyingYearAndStudyingSemester = studyingPeriod.split(",");
            String academicYear = studyingYearAndStudyingSemester[0];
            String semester = studyingYearAndStudyingSemester[1];
            double qca = calculateQCAForSpecificPeriod(studentId, academicYear, semester);
            sumOfAllPreviousQCAs += qca;
            double upToDateQCA = sumOfAllPreviousQCAs / numberOfPeriods;

            String key = String.format("Academic Year: %s, Semester: %s", academicYear.replace("_", "/"), semester);
            Map<String, String> values = new LinkedHashMap<>();
            values.put("Session", String.format("%.2f", qca));
            values.put("To-Date", String.format("%.2f", upToDateQCA));
            everyPeriodAndQCAForThisPeriod.put(key, values);
        }
        return everyPeriodAndQCAForThisPeriod;
    }
}

