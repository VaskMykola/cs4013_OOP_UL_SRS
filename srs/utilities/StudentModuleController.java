package utilities;

import users.Student;
import utilities.menu.ChooseOptionMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentModuleController {

    private StudentModuleController() {
        throw new IllegalStateException("StudentModuleController class");
    }


    // private static final String ALL_STUDENTS_FILE_LOCATION = "csvFiles/csvForRoles/students.csv";
    private static final String MODULES_FILES_DIR_LOCATION = "csvFiles/csvForRoles/studentModules/";

    private static final String COURSES_MODULES_FILE_LOCATION = "csvFiles/csvForRoles/FacultyDepartments/departmentsCourses/CourseModules/";


    // TODO: remove this temporary variable, it's only for testing. replace each usage of it with the actual values,NEED to implement Year and Semester selection in the menu or other way how to deal with it
    private static final String[] TEMPORARY = {"ITCS", "1", "2", "mandatory"};


    public static String getModulesFromCMfile(String courseCode, String year, String semester, String mandatoryOrOptional) {
        List<String> modules = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(COURSES_MODULES_FILE_LOCATION + "modules_"
                + courseCode + "_" + year + "_" + semester + "_" + mandatoryOrOptional + ".csv"))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    modules.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return String.join("\n", modules);
    }

    public static String getStudentModules(String studentLogin) {

        List<String> modules = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(MODULES_FILES_DIR_LOCATION + studentLogin + "_modules.csv"))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    modules.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("we didn't find your modules file, so we will create it for you");
            createStudentModulesFile(studentLogin, TEMPORARY[1], TEMPORARY[2]);
            return getStudentModules(studentLogin); // TODO possible infinite loop, TEST IT billion times !!
        }
        return String.join("\n", modules);
    }

    public static void createStudentModulesFile(String studentLogin, String year, String semester) {
        //create file studentLogin_modules.csv
        try (FileWriter writer = new FileWriter(MODULES_FILES_DIR_LOCATION + studentLogin + "_modules.csv")) {
            writer.write("ModuleCode\n");
            writer.write(getModulesFromCMfile(TEMPORARY[0], TEMPORARY[1], TEMPORARY[2], "mandatory"));
            if (checkIfStudentShouldChooseOptionalModules(studentLogin, TEMPORARY[1], TEMPORARY[2])) {
                System.out.println("You should choose optional modules");
                List<String> chosen = ChooseOptionMenu.chooseManyOptionsMenu("choose module by module that you want to add, then exit",
                        getModulesFromCMfile(TEMPORARY[0], TEMPORARY[1], TEMPORARY[2], "optional")
                                .split("\n"));
                for (String module : chosen) {
                    writer.write(module + "\n");
                }
            }
            System.out.println("Your modules file was created successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while creating your modules file");
            System.out.println(e.getMessage());
        }


    }

    public static boolean checkIfStudentShouldChooseOptionalModules(String studentLogin, String year, String semester) {
        try {
            File file = new File(COURSES_MODULES_FILE_LOCATION + "modules_"
                    + Student.getStudentField(studentLogin, 4) + "_" + year + "_" + semester + "_optional.csv");
            return file.exists();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

//    public static void main(String[] args) {
//        createStudentModulesFile("s1234567");
//        System.out.println(getStudentModules("s1234567"));
//    }
}
