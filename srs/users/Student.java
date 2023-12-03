package users;

import utilities.StudentModuleController;

import java.io.*;
import java.util.*;

public class Student {
    private Student() {
        throw new IllegalStateException("Student class");
    }
    private static final String ALL_STUDENTS_FILE_LOCATION = "csvFiles/csvForRoles/students.csv";
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
        return getStudentField(studentLogin, 4) + "\n"
                + getStudentField(studentLogin, 5) + "\n"
                + getStudentField(studentLogin, 6);
    }

    public static String getStudentField(String studentLogin, int fieldIndex) {
        String course = "";
        try (Scanner scanner = new Scanner(new File(ALL_STUDENTS_FILE_LOCATION))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String studentLoginFromFile = line.split(",")[1];
                    if (studentLoginFromFile.equals(studentLogin)) {
                        course = line.split(",")[fieldIndex];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return course;
    }



}































