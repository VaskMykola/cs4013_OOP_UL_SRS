package users;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class User {
    private static final String CSV_FILE_PATH = "srs/users.csv";
    private static final String CSV_DELIMITER = ",";
    private static final String[] ROLES = {"student", "faculty", "admin", "department"};

    private String login;
    private String password;
    private String role;
    private String userID;

    public User() {
        initializeUser();
    }

    private void initializeUser() {
        this.role = chooseRole();
        System.out.println("Enter login: ");
        this.login = getUserInput();
        System.out.println("Enter password: ");
        this.password = getUserInput();
    }

    private static String chooseRole() {
        while (true) {
            System.out.println("Possible roles: " + Arrays.toString(ROLES));
            System.out.println("Enter role: ");
            String inputRole = getUserInput();
            if (Arrays.asList(ROLES).contains(inputRole)) {
                return inputRole;
            } else {
                System.out.println("Invalid role. Please choose from the list.");
            }
        }
    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean checkUser() {
        try (Scanner scanner = new Scanner(Paths.get(CSV_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String[] fields = scanner.nextLine().split(CSV_DELIMITER);
                if (fields.length == 4 && fields[0].equals(this.login) && fields[2].equals(this.role)) {
                    this.userID = fields[3];
                    return true;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getRole() {
        return this.role;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void changePassword() {
        System.out.println("Password changing...");
        while (true) {
            System.out.println("Enter old password: ");
            String oldPassword = getUserInput();
            System.out.println("Enter new password: ");
            String newPassword = getUserInput();
            System.out.println("Enter new password again: ");
            String newPasswordAgain = getUserInput();

            if (!newPassword.equals(newPasswordAgain)) {
                System.out.println("Passwords do not match.");
                continue;
            }

            if (oldPassword.equals(this.password)) {
                this.password = newPassword;
                System.out.println("Password changed successfully.");
                updatePasswordInCSV(newPassword);
                break;
            } else {
                System.out.println("Wrong password.");
            }
        }
    }

    private void updatePasswordInCSV(String newPassword) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CSV_FILE_PATH));
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] fields = line.split(CSV_DELIMITER);
                if (fields.length == 4 && fields[0].equals(this.login) && fields[2].equals(this.role)) {
                    fields[1] = newPassword;
                    lines.set(i, String.join(CSV_DELIMITER, fields));
                    break;
                }
            }
            Files.write(Paths.get(CSV_FILE_PATH), lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error updating password in CSV file: " + ex.getMessage());
        }
    }

    public void showProfileInfo() {
        System.out.println("Profile info:");
        System.out.println("Login: " + this.login);
        System.out.println("Role: " + this.role);
        System.out.println("User ID: " + this.userID);
        // Add more profile information here as needed
    }
}
