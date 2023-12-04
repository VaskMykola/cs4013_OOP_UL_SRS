
package utilities;

import users.Department;
import users.Student;
import utilities.menu.RoleMainMenuBuilder;

import java.util.*;

/**
 * The `UserUtils` class provides utility methods for user authentication and login,
 * as well as functions to open user-specific menus based on their roles.
 * It interacts with user data stored in a CSV file.
 */
public class UserUtils {

    private UserUtils() {
        throw new IllegalStateException("Utility class");
    }
    private static final String[] ROLES = {"student", "department"};

    private static final String USR_FILE_LOCATION = "csvFiles/users.csv";

    /**
     * Checks if a user with the given login, password, and role exists in the CSV data.
     *
     * @param login    The user's login.
     * @param password The user's password.
     * @param role     The user's role (e.g., "student" or "department").
     * @return True if the user exists; false otherwise.
     */
    public static boolean checkUser(String login, String password, String role) {

        CSVHandler userCSV = new CSVHandler(USR_FILE_LOCATION);

        Map<String, String> user = new HashMap<>();
        user.put("Login", login);
        user.put("Password", password);
        user.put("Role", role);
        List<String> result = userCSV.findRowsWithColumnValuesSpecified(user);
        return result.size() == 1;
    }

    /**
     * Prompts the user to enter login, password, and role and returns them as an array.
     *
     * @return An array containing login, password, and role.
     */
    public static String[] getLoginInfoFromUser() {
        Scanner scanner = new Scanner(System.in);
        String[] loginInfo = new String[3]; // Array to store login, password, and role

        System.out.println("Enter login: ");
        loginInfo[0] = scanner.nextLine();

        System.out.println("Enter password: ");
        loginInfo[1] = scanner.nextLine();

        System.out.println("Select your role (choose from the following): ");
        for (int i = 0; i < ROLES.length; i++) {
            System.out.println((i + 1) + ". " + ROLES[i]);
        }

        int roleChoice;
        while (true) {
            System.out.print("Enter the number corresponding to your role: ");
            try {
                roleChoice = Integer.parseInt(scanner.nextLine());
                if (roleChoice >= 1 && roleChoice <= ROLES.length) {
                    loginInfo[2] = ROLES[roleChoice - 1]; // Store the selected role
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

    /**
     * Performs the user login process, including authentication and menu access.
     */
    public static void userLogin() {
        String[] loginInfo = getLoginInfoFromUser();
        if (!checkUser(loginInfo[0], loginInfo[1], loginInfo[2])) {
            System.out.println("Invalid login information. Please try again.");
            return;
        }
        System.out.println("Login successful.");
        openUserMenu(loginInfo);
    }

    /**
     * Opens the user-specific menu based on their role after a successful login.
     *
     * @param loginInfo An array containing login, password, and role.
     */
    public static void openUserMenu(String[] loginInfo) {
        String role = loginInfo[2];
        String login = loginInfo[0];
        switch (role) {
            case "student":
                Student student = new Student(login);
                RoleMainMenuBuilder.studentMenu(student);
                break;
            case "department":
                Department department = new Department(login);
                RoleMainMenuBuilder.departmentMenu(department);
                break;
            default:
                break;
        }
    }
}
