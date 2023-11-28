package utilities;

import Menu.CSVHandler;

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

    public static boolean checkUser(String login, String password, String role) {
        // check if user exists in the users.csv file
        CSVHandler userSCV = new CSVHandler("csvFiles/Users.csv");

        return false;
    }

    public static String[] getRoles() {
        return ROLES;
    }

    public static String[] getLoginInfoFromUser() {
        Scanner scanner = new Scanner(System.in);
        String[] loginInfo = new String[3]; // Array to store login, password, and role

        System.out.println("Enter login: ");
        loginInfo[0] = scanner.nextLine();

        System.out.println("Enter password: ");
        loginInfo[1] = scanner.nextLine();

        System.out.println("Select your role (choose from the following): ");
        String[] roles = getRoles();
        for (int i = 0; i < roles.length; i++) {
            System.out.println((i + 1) + ". " + roles[i]);
        }

        int roleChoice;
        while (true) {
            System.out.print("Enter the number corresponding to your role: ");
            try {
                roleChoice = Integer.parseInt(scanner.nextLine());
                if (roleChoice >= 1 && roleChoice <= roles.length) {
                    loginInfo[2] = roles[roleChoice - 1]; // Store the selected role
                    break; // Exit the loop if a valid role is selected
                } else {
                    System.out.println("Invalid role selection. Please choose a valid role.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to your role.");
            }
        }

        return loginInfo; // Return the array containing login, password, and role
    }

    public static boolean userLogin() {
        String[] loginInfo = getLoginInfoFromUser();
        if (!checkUser(loginInfo[0], loginInfo[1], loginInfo[2])) {
            return false;
        }

        return true;
    }
}
