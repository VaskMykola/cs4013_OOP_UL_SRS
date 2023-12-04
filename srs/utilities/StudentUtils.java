package utilities;


import utilities.menu.ChooseOptionMenu;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class provides utilities to manage student information and academic records.
 * It is designed to work with CSV files that store student data.
 */
public class StudentUtils {

    private static final CSVHandler allStudents = new CSVHandler("csvFiles/studentsInformation.csv");

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private StudentUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Shows the academic transcript for a specified student.
     *
     * @param studentLogin The unique login ID of the student.
     */
    public static void showTranscript(String studentLogin) {
        TranscriptManager.showTranscript(studentLogin);
    }

    /**
     * Retrieves a list of modules taken by a student.
     *
     * @param studentLogin The unique login ID of the student.
     * @return A List of strings representing the modules taken by the student.
     */
    public static List<String> getModules(String studentLogin) {
        String faculty = getStudentFaculty(studentLogin);
        String department = getStudentDepartment(studentLogin);
        String course = getStudentCourse(studentLogin);

        String pathToModules = String.format("csvFiles/Faculties/%s/%s/%s/StudentModulesTaken.csv", faculty, department, course);
        CSVHandler modulesHandler = new CSVHandler(pathToModules);

        List<String> studentRows = modulesHandler.findRowsWithColumnValuesSpecified(Map.of("ID", studentLogin));
        List<String> moduleList = new ArrayList<>();
        for (String studentRow : studentRows) {
            String module = modulesHandler.findValueOfSpecificColumnInSpecificRow(studentRow, "Module");
            moduleList.add(module);
        }
        return moduleList;
    }

    /**
     * Retrieves specific information about a student.
     *
     * @param studentLogin The unique login ID of the student.
     * @param field        The field of information to retrieve (e.g., "Faculty", "Department").
     * @return The requested information about the student as a String.
     */
    public static String getStudentInfo(String studentLogin, String field) {
        List<String> studentRowList = allStudents.findRowsWithColumnValuesSpecified(Map.of("ID", studentLogin));
        if (studentRowList.isEmpty()) {
            return null; // or throw an exception
        }
        String studentRow = studentRowList.get(0);
        String value = allStudents.findValueOfSpecificColumnInSpecificRow(studentRow, field);
        if (value != null) {
            value = value.replace("]", ""); // Remove any ']' characters
        }
        return value;
    }

    /**
     * Retrieves the faculty to which a student belongs.
     *
     * @param studentLogin The unique login ID of the student.
     * @return The faculty of the student as a String.
     */
    public static String getStudentFaculty(String studentLogin) {
        return getStudentInfo(studentLogin, "Faculty");
    }

    /**
     * Retrieves the department to which a student belongs.
     *
     * @param studentLogin The unique login ID of the student.
     * @return The department of the student as a String.
     */
    public static String getStudentDepartment(String studentLogin) {
        return getStudentInfo(studentLogin, "Department");
    }

    /**
     * Retrieves the course in which a student is enrolled.
     *
     * @param studentLogin The unique login ID of the student.
     * @return The course of the student as a String.
     */
    public static String getStudentCourse(String studentLogin) {
        return getStudentInfo(studentLogin, "Course");
    }

    /**
     * Retrieves all student logins based on a specified field and its value.
     *
     * @param from The field to filter students by (e.g., "Department", "Course", "Faculty").
     * @param code The value of the field to filter the students (e.g., specific department name).
     * @return An array of student login IDs matching the specified criteria.
     */
    public static String[] getAllStudentsFrom(String from, String code) {
        List<String> studentRows = allStudents.findRowsWithColumnValuesSpecified(Map.of(from, code));
        String[] students = new String[studentRows.size()];
        for (int i = 0; i < studentRows.size(); i++) {
            String studentRow = studentRows.get(i);
            String studentLogin = allStudents.findValueOfSpecificColumnInSpecificRow(studentRow, "ID");
            students[i] = studentLogin;
        }
        return students;
    }

    /**
     * Retrieves all student logins from a specific department.
     *
     * @param department The name of the department.
     * @return An array of student login IDs who are part of the specified department.
     */
    public static String[] getAllStudentsFromDepartment(String department) {
        return getAllStudentsFrom("Department", department);
    }

    /**
     * Retrieves all student logins enrolled in a specific course.
     *
     * @param course The name of the course.
     * @return An array of student login IDs who are enrolled in the specified course.
     */
    public static String[] getAllStudentsFromCourse(String course) {
        return getAllStudentsFrom("Course", course);
    }

    /**
     * Retrieves all student logins who are part of a specific faculty.
     *
     * @param faculty The name of the faculty.
     * @return An array of student login IDs who are part of the specified faculty.
     */
    public static String[] getAllStudentsFromFaculty(String faculty) {
        return getAllStudentsFrom("Faculty", faculty);
    }

    /**
     * Creates a menu where you need to choose one student.
     *
     * @param students The list of students to choose from.
     * @return one student that was chosen.
     */
    public static String selectStudentFromList(String[] students){
        return ChooseOptionMenu.chooseOneOptionMenu("Select a student", students);
    }

    /**
     * Main method for testing purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String studentLogin = "22310975";
        List<String> modules = getModules(studentLogin);
        System.out.println("Modules taken by the student: " + modules);
    }
}
