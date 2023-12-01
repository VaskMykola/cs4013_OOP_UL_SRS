package Menu;

import java.util.*;

public class QCACalculator {
    private static final CSVHandler gradeScheme = new CSVHandler(String.format("./csvFiles/%s.csv", "gradeBands"));
    private static final CSVHandler studentInformation = new CSVHandler(String.format("./csvFiles/%s.csv", "StudentModulesTaken"));

    private static double determineQPVForModuleGivenGrade(String grade) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("Grade", grade);
        return Double.parseDouble(
                gradeScheme.findValueOfSpecificColumnInSpecificRow(
                        gradeScheme.findRowsWithColumnValuesSpecified(attributes).get(
                                gradeScheme.getColumnHeaders().get("Grade")), "QPV"));
    }

    private static Map<String, String> findGradesForSpecificStudentForSpecificPeriod(String studentId, String startYearOfStudies, String semester) {

        Map<String, String> attributesForStudentDataTable = new HashMap<>();
        attributesForStudentDataTable.put("ID", studentId);
        attributesForStudentDataTable.put("Academic Year", startYearOfStudies);
        attributesForStudentDataTable.put("Semester", semester);
        List<String> particularStudentInfo = studentInformation.findRowsWithColumnValuesSpecified(attributesForStudentDataTable);

        Map<String, String> studentGradesMap = new HashMap<>();
        for (String studentRecord : particularStudentInfo) {
            String academicYear = studentInformation.findValueOfSpecificColumnInSpecificRow(studentRecord, "Academic Year");
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

    private static Map<String, Double> determineQPVForSeveralModules(Map<String, String> gradesForSomeModules) {
        Map<String, Double> qpvsForTheModules = new HashMap<>();
        for (Map.Entry<String, String> moduleAndGradeForIt : gradesForSomeModules.entrySet()) {
            double qpv = determineQPVForModuleGivenGrade(moduleAndGradeForIt.getValue());
            qpvsForTheModules.put(moduleAndGradeForIt.getKey(), qpv);
        }
        return qpvsForTheModules;
    }

    public static double calculateQCAForSpecificPeriod(String studentId, String academicYear, String semester) {

        Map<String, String> studentModulesTakenAndGradesForThem = findGradesForSpecificStudentForSpecificPeriod(studentId, academicYear, semester);
//        System.out.println(studentModulesTakenAndGradesForThem);
        Map<String, Double> studentModulesTakenAndQpvsForThem = determineQPVForSeveralModules(studentModulesTakenAndGradesForThem);
//        System.out.println(studentModulesTakenAndQpvsForThem);
        double total = 0;
        for (String module : studentModulesTakenAndQpvsForThem.keySet()) {
            total += studentModulesTakenAndQpvsForThem.get(module);
        }
        return total / studentModulesTakenAndQpvsForThem.size();
    }

    public static Map<String, Map<String, Double>> calculateQCAForEveryStudyingPeriod(String studentId) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("ID", studentId);
        List<String> specificStudentData = studentInformation.findRowsWithColumnValuesSpecified(attributes);
        List<String> allStudyingPeriodsForTheStudent = studentInformation.findDistinctValuesForSeveralSpecificColumns(specificStudentData, new String[]{"Academic Year", "Semester"});
        Map<String, Map<String, Double>> everyPeriodAndQCAForThisPeriod = new TreeMap<>();
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
            Map<String, Double> values = new TreeMap<>();
            values.put("Session", qca);
            values.put("To-Date", upToDateQCA);
            everyPeriodAndQCAForThisPeriod.put(key, values);
        }
        return everyPeriodAndQCAForThisPeriod;
    }

    public static void main(String[] args) {
//        System.out.println(QCACalculator.determineQPVForModuleGivenGrade("F"));
//        System.out.println(QCACalculator.findAllGradesForSpecificStudent("22310975"));
//        System.out.println(QCACalculator.determineQPVForSeveralModules(QCACalculator.findAllGradesForSpecificStudent("22310975")));
        System.out.println(calculateQCAForEveryStudyingPeriod("22331549"));
        System.out.println(calculateQCAForEveryStudyingPeriod("22310975"));
    }

}

