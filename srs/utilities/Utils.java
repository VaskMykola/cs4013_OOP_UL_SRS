package utilities;

import Menu.CSVHandler;
import Menu.MenuBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        CSVHandler userSCVcheck = new CSVHandler("csvFiles/Users.csv");
        //one way but Alex made function "hasSpecificColumnValue" private so we can't use it
//        for (String row : userSCVcheck.getTableData()) {
//            if (userSCVcheck.hasSpecificColumnValue(row, "login", login) &&
//                    userSCVcheck.hasSpecificColumnValue(row, "password", password) &&
//                    userSCVcheck.hasSpecificColumnValue(row, "role", role)) {
//                return true;
//            }
//        }
        //another way
        Map<String, String> user = new HashMap<>();
        user.put("Login", login);
        user.put("Password", password);
        user.put("Role", role);
        List<String> result = userSCVcheck.findRowsWithColumnValuesSpecified(user);
        //TODO: remove this
        System.out.println("==============LOG: checkUser()================");
        System.out.println("result.size() = " + result.size());
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("==============LOG: checkUser()================");
        return result.size() == 1;
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
            System.out.println("Invalid login information. Please try again.");
            return false;
        }
        System.out.println("Login successful.");
        openUserMenu(loginInfo[2]);
        return true;
    }

    public static void openUserMenu(String role){
        switch (role) {
            case "admin":
                MenuBuilder.adminMenu();
                break;
            case "student":
                MenuBuilder.studentMenu();
                break;
            case "department":
                MenuBuilder.departmentMenu();
                break;
            case "faculty":
                MenuBuilder.facultyMenu();
                break;
            default:
                break;
        }

    }
}
