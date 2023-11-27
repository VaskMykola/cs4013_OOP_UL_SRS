package Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QCACalculator {
    private static final CSVHandler gradeScheme = new CSVHandler(String.format("./csvFiles/%s.csv", "gradeBands"));
    private static final CSVHandler studentInformation = new CSVHandler(String.format("./csvFiles/%s.csv", "StudentModules"));

    public static void askForExtraInformation() {
        Map<String, String> values = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the student id:");
        String studentId = scanner.nextLine();
        values.put("ID", studentId);
        System.out.println("Enter the year:");
        String year = scanner.nextLine();
        values.put("Start Year", year);
        System.out.println("Enter the semester:");
        String semester = scanner.nextLine();
        values.put("Semester", semester);
        studentInformation.findRowsWithColumnValuesSpecified(values).forEach(System.out::println);
    }

    public static void main(String[] args) {
        askForExtraInformation();
    }
}

//class MarkingSchemeReader {
//    private static final ArrayList<Grade> gradeArrayList = new ArrayList<>(); // put this in a different class (like MarkingSchemeReader)
//
//    public static ArrayList<Grade> readMarkingScheme(String filepath) {
//
//        try {
//            Scanner scanner = new Scanner(new File(filepath));
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] lineData = line.split(",");
//                gradeArrayList.add(new Grade(
//                        lineData[0],
//                        lineData[1],
//                        lineData[2].equals("–") ? -1 : Double.parseDouble(lineData[2]),
//                        lineData[3].equals("Yes")));
//            }
//            gradeArrayList.forEach(System.out::println);
//
//
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        }
//        return gradeArrayList;
//    }
//}


class TranscriptManager {
//    public static boolean getStudentGrades(String studentId) {
//        ArrayList<String> allStudentData = CSVHandler.readFile("./csvFiles/StudentModulesTaken.csv");
//        for (String studentDataRow : allStudentData) {
//            String[] studentIdAndTakenModules = studentDataRow.split(",", 2); // splits the information so that the first element of a new array is ID, and the second is a String with all modules taken
//            String currentStudentId = studentIdAndTakenModules[0]; // studentId from the file
//
//            if (currentStudentId.equals(studentId)) { // check if a student with the specified ID is found
//                if (studentIdAndTakenModules.length == 1) { // check if a record contains anything except student ID (this is the case when length > 1)
//                    System.out.printf("The student %s has not got any grades yet.%n", studentId);
//                    return false;
//                }
//                String[] takenModules = studentIdAndTakenModules[1].split(",");
//
//
//                for (String module : takenModules) {
//                    System.out.printf("%s: %s%n", module, determineGradeForModule(studentId, module));
//                }
//                System.out.println(Arrays.toString(takenModules));
//                return true;
//            }
//        }
//        System.out.printf("The student with id %s is not in the system.%n", studentId);
//        return false;
//    }

//    private static String determineGradeForModule(String studentID, String moduleName) {
//        ArrayList<String> moduleStudentGradesTable = CSVHandler.readFile("./csvFiles/%s.csv".formatted(moduleName));
//        return CSVHandler.findValueOfSpecificColumn(Objects.requireNonNull(CSVHandler.findRowWithSpecificColumnValue(moduleStudentGradesTable, 1, studentID)), 2);
//
//    }

}

record Grade(String grade, String gradeDescription, double QPV, boolean creditsAwarded) {


    public static void main(String[] args) {
//        CSVFileReader.readFile("./csvFiles/AcademicGrades.csv").forEach(System.out::println);
//        TranscriptManager.getStudentGrades("22310975");

    }
}