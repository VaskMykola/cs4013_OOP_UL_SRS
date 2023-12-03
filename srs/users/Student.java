package users;


import enumsForCSV.studentsSCVFields;
import utilities.CSVHandler;
import utilities.StudentModuleController;

import java.util.Map;

public class Student {
    private Student() {
        throw new IllegalStateException("Student class");
    }

    private static final String ALL_STUDENTS_FILE_LOCATION = "csvFiles/csvForRoles/students.csv";
    private static final CSVHandler allStudents = new CSVHandler(ALL_STUDENTS_FILE_LOCATION);
    private static final String MODULES_FILES_DIR_LOCATION = "csvFiles/csvForRoles/studentModules/";


    public static String showTranscript(String studentLogin) {
        System.out.println("show a transcript selected");
        return "transcript";
    }

    public static String calculateQSA(String studentID) {
        System.out.println("calculate QSA selected");
        return null;
    }

    public static String getStudentModules(String studentLogin) {
        return StudentModuleController.getStudentModules(studentLogin);
    }


    public static String viewProgrammeDetails(String studentLogin) {

        return getStudentFaculty(studentLogin) + "\n" + getStudentDepartment(studentLogin) + "\n" + getStudentCourse(studentLogin);
    }


    public static String getStudentInfo(String studentLogin, studentsSCVFields field) {
        String studentRow = allStudents.findRowsWithColumnValuesSpecified(Map.of(studentsSCVFields.STUDENTLOGIN.getField(), studentLogin)).toString();
        return allStudents.findValueOfSpecificColumnInSpecificRow(studentRow, field.getField());
    }

    // Usage examples
    public static String getStudentFaculty(String studentLogin) {
        return getStudentInfo(studentLogin, studentsSCVFields.STUDENTFACULTYCODE);
    }

    public static String getStudentDepartment(String studentLogin) {
        return getStudentInfo(studentLogin, studentsSCVFields.STUDENTDEPARTMENTCODE);
    }

    public static String getStudentCourse(String studentLogin) {
        return getStudentInfo(studentLogin, studentsSCVFields.STUDENTCOURSECODE);
    }

    public static String getStudentYear(String studentLogin) {
        return getStudentInfo(studentLogin, studentsSCVFields.STUDENTYEAR);
    }

    public static String getStudentSemester(String studentLogin) {
        return getStudentInfo(studentLogin, studentsSCVFields.STUDENTSEMESTER);
    }


    public static void main(String[] args) {
        System.out.println(getStudentModules("s1"));
    }
}































