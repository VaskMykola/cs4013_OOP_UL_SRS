package users;

import java.io.*;
import java.util.*;

public class Student {
    private static final String ALL_STUDENTS_FILE_LOCATION = "csvFiles/csvForRoles/students.csv";
    private static final String MODULES_FILES_DIR_LOCATION = "csvFiles/csvForRoles/studentModules/";








    public static String showTranscript(String studentLogin) {
        System.out.println("show a transcript selected");
        return "transcript";
    }

    public void calculateQSA(String studentID) {
        System.out.println("calculate QSA selected");
    }

    public static String getStudentModules(String studentLogin) {

        List<String> modules = new ArrayList<>();


        try (Scanner scanner = new Scanner(new File(MODULES_FILES_DIR_LOCATION + studentLogin+ "_modules.csv"))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String moduleCode = line.split(",")[0];
                    //String moduleName = line.split(",")[1];
                    //String moduleSemester = line.split(",")[2];
                    modules.add(moduleCode);//+ " - " + moduleName + " - Sem:" + moduleSemester);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        String result = String.join("\n", modules);
        return result;
    }


    public static String viewProgrammeDetails(String studentLogin) {
        System.out.println("view programme details selected");
        return "programme details";
    }




}
