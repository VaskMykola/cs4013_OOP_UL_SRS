package users;

import java.io.*;
import java.util.*;

public class Student {
    private static final String ALL_STUDENTS_FILE_LOCATION = "csvFiles/csvForRoles/students.csv";
    private static final String MODULES_FILES_DIR_LOCATION = "csvFiles/csvForRoles/FacultyDepartments/departmentsCourses/CourseModules/";








    public static String showTranscript(String studentID) {
        System.out.println("show a transcript selected");
        return "transcript";
    }

    public void calculateQSA(String studentID) {
        System.out.println("calculate QSA selected");
    }

    public static String getStudentModules(String studentLogin) {
        List<String> modules = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(ALL_STUDENTS_FILE_LOCATION))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] student = line.split(",");
                if (student[1].equals(studentLogin)) {
                    String modulesFile = student[6];
                    modules.addAll(readModules(modulesFile));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = String.join("\n", modules);
        return result;
    }

    private static List<String> readModules(String modulesFile) {

        List<String> modules = new ArrayList<>();


        try (Scanner scanner = new Scanner(new File(MODULES_FILES_DIR_LOCATION + modulesFile))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String moduleCode = line.split(",")[0];
                    String moduleName = line.split(",")[1];
                    String moduleSemester = line.split(",")[2];
                    modules.add(moduleCode + " - " + moduleName + " - Sem:" + moduleSemester);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return modules;
    }


    public static String viewProgrammeDetails(String studentID) {
        System.out.println("view programme details selected");
        return "programme details";
    }




}
