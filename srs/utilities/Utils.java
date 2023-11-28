package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Utils {
    private static final String[] ROLES = readRolesFromCSV();

    private static String[] readRolesFromCSV() {
        ArrayList<String> rolesList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("csvFiles/Roles.csv"))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    rolesList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return rolesList.toArray(new String[0]);
    }

    public static boolean updatePassword(String userID, String newPass) {
        return false;
    }

    public static String newUser(String role) {
        return "";
    }

    public static boolean deleteUser(String userID) {
        return false;
    }

    public static boolean checkUser(String userID) {
        return false;
    }

    public static boolean checkUser(String login, String role) {
        return false;
    }

    public static String[] getRoles() {
        return ROLES;
    }


}
