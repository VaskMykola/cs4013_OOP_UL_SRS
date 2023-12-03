package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentModuleController {
    private static final String ALL_STUDENTS_FILE_LOCATION = "csvFiles/csvForRoles/students.csv";
    private static final String MODULES_FILES_DIR_LOCATION = "csvFiles/csvForRoles/studentModules/";

    private static final String COURSES_MODULES_FILE_LOCATION = "csvFiles/csvForRoles/FacultyDepartments/departmentsCourses/CourseModules/";

    private static final String[] TEMPORARY = {"ITCS", "1", "2", "mandatory"};


    public static String getMandatoryModules(String courseCode, String year, String semester, String mandatoryOrOptional){
        List<String> modules = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(COURSES_MODULES_FILE_LOCATION + "modules_"
                + courseCode + "_" + year + "_" + semester + "_"+ mandatoryOrOptional + ".csv"))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String moduleCode = line.split(",")[0];
                    modules.add(moduleCode);
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
        }
        return String.join("\n", modules);
    }

    public static void createStudentModulesFile(String studentLogin) {
        //create file studentLogin_modules.csv
        try(FileWriter writer = new FileWriter(MODULES_FILES_DIR_LOCATION + studentLogin + "_modules.csv")) {
            writer.write("ModuleCode\n");
            writer.write(getMandatoryModules(TEMPORARY[0], TEMPORARY[1], TEMPORARY[2], TEMPORARY[3]));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void addModuleToStudent(String studentLogin, String moduleCode) {
        // TODO
    }

    public static void removeModuleFromStudent(String studentLogin, String moduleCode) {
        // TODO
    }

    public static void checkIfStudentShouldChooseOptionalModules(String studentLogin) {
        // TODO
    }

    public static void main(String[] args) {
        createStudentModulesFile("s1234567");
        System.out.println(getStudentModules("s1234567"));
    }
}
