package users;

import java.io.*;
import java.util.*;

public class Student {


    public static String showTranscript(String studentID) {
        // Todo: implement
        System.out.println("show a transcript selected");
        return "transcript";
    }

    public void calculateQSA(String studentID) {
        // TODO implement
        System.out.println("calculate QSA selected");
    }

    public static String getStudentModules(String studentID) {
        // TODO remove after transferring to CSVHandler
        // TODO transfer to CSVHandler
        String studentModulesFile = "csvFiles/students.csv";
        List<String> modules = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(studentModulesFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] student = line.split(",");
                // Check if the first element (StudentID) matches the provided studentID
                if (student[0].equals(studentID)) {


                    // Assuming the modules are in the 7th column (index 6)
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


        try (Scanner scanner = new Scanner(new File("csvFiles/Courses/LM121_Modules/" + modulesFile))) {
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
