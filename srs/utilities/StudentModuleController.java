// TODO fucn to delete empty files, or files with only header
// TODO fucn to catch exeptions if file in CM not exist or empty
package utilities;

import users.Student;
import utilities.menu.ChooseOptionMenu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class StudentModuleController {

    private StudentModuleController() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static final String MODULES_FILES_DIR_LOCATION = "csvFiles/csvForRoles/studentModules/";
    private static final String COURSES_MODULES_FILE_LOCATION = "csvFiles/csvForRoles/FacultyDepartments/departmentsCourses/CourseModules/";

    public static String getModulesFromCMfile(String courseCode, String year, String semester, String mandatoryOrOptional) {
        String filePath = COURSES_MODULES_FILE_LOCATION + "modules_" + courseCode + "_" + year + "_" + semester + "_" + mandatoryOrOptional + ".csv";
        return readFromFile(filePath);
    }

    public static String getStudentModules(String studentLogin) {
        String filePath = MODULES_FILES_DIR_LOCATION + studentLogin + "_modules.csv";
        String content = readFromFile(filePath);

        if (content.isEmpty()) {
            createStudentModulesFile(studentLogin);
            content = readFromFile(filePath);
        }
        return content;
    }

    private static String readFromFile(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .skip(1) // Skip header line
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath + ". " + e.getMessage());
            return "";
        }
    }

    public static void createStudentModulesFile(String studentLogin) {
        String filePath = MODULES_FILES_DIR_LOCATION + studentLogin + "_modules.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ModuleCode\n");
            writeStudentModulesToFile(writer, studentLogin);
        } catch (IOException e) {
            System.out.println("Error creating modules file: " + e.getMessage());
        }
    }

    private static void writeStudentModulesToFile(FileWriter writer, String studentLogin) throws IOException {
        String studentYear = Student.getStudentYear(studentLogin);
        String studentSemester = Student.getStudentSemester(studentLogin);
        String studentCourse = Student.getStudentCourse(studentLogin);

        writer.write(getModulesFromCMfile(studentCourse, studentYear, studentSemester, "mandatory"));
        if (checkIfStudentShouldChooseOptionalModules(studentLogin, studentYear, studentSemester)) {
            System.out.println("You should choose optional modules");
            ChooseOptionMenu.chooseManyOptionsMenu("choose module by module that you want to add, then exit",
                            getModulesFromCMfile(studentCourse, studentYear, studentSemester, "optional").split("\n"))
                    .forEach(module -> writeModule(writer, module));
        }
    }

    private static void writeModule(FileWriter writer, String module) {
        try {
            writer.write(module + "\n");
        } catch (IOException e) {
            System.out.println("Error writing module to file: " + e.getMessage());
        }
    }

    public static boolean checkIfStudentShouldChooseOptionalModules(String studentLogin, String year, String semester) {
        String filePath = COURSES_MODULES_FILE_LOCATION + "modules_" + Student.getStudentCourse(studentLogin) + "_" + year + "_" + semester + "_optional.csv";
        return new File(filePath).exists();
    }

    public static void main(String[] args) {
        String result = getModulesFromCMfile("ITCS", "1", "2", "mandatory");
        System.out.println(result);
    }
}
