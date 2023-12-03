package utilities;

import Menu.CSVHandler;
import Menu.MenuBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utils {
    private static final String[] ROLES = readRolesFromCSV();
    private static final String ROLES_FILE_LOCATION = "csvFiles/csvForRoles/roles.csv";
    private static final String USR_FILE_LOCATION = "csvFiles/csvForRoles/users.csv";



    private static String[] readRolesFromCSV() {
        ArrayList<String> rolesList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(ROLES_FILE_LOCATION))) {
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

    public static String[] getRoles() {
        return ROLES;
    }


    public static boolean checkUser(String login, String password, String role) {

        CSVHandler userSCVcheck = new CSVHandler(USR_FILE_LOCATION);

        Map<String, String> user = new HashMap<>();
        user.put("Login", login);
        user.put("Password", password);
        user.put("Role", role);
        List<String> result = userSCVcheck.findRowsWithColumnValuesSpecified(user);
        return result.size() == 1;
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
        openUserMenu(loginInfo);
        return true;
    }

    public static void openUserMenu(String[] loginInfo) {
        String role = loginInfo[2];
        String login = loginInfo[0];
        switch (role) {
            case "admin":
                MenuBuilder.adminMenu();
                break;
            case "student":
                MenuBuilder.studentMenu(login);
                break;
            case "department":
                MenuBuilder.departmentMenu(login);
                break;
            case "faculty":
                MenuBuilder.facultyMenu(login);
                break;
            default:
                break;
        }

    }

}
