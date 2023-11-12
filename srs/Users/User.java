package Users;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class User {
    private final static String[] roles = {"student", "faculty", "admin", "department"};
    private final String login;
    private String password;
    private final String role;
    private String userID;


    public User() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Possible roles: ");
        for (String role : roles) System.out.print(role + " ");
        System.out.println("Enter role: ");
        this.role = scanner.nextLine();
        System.out.println("Enter login: ");
        this.login = scanner.nextLine();
        System.out.println("Enter password: ");
        this.password = scanner.nextLine();
    }


    public boolean checkUser() {
        try (Scanner scanner = new Scanner(Paths.get("srs/users.csv").toFile())) {
            // read all fields in user.csv file and find this user (user.csv format: login, password, role)
            String DELIMITER = ",";
            scanner.useDelimiter(DELIMITER);
            while (scanner.hasNext()) {
                String[] fields = scanner.nextLine().split(DELIMITER);

                if (fields[0].equals(this.login) && fields[1].equals(this.password) && fields[2].equals(this.role)) {
                    this.userID = fields[3];
                    return true;
                }
            }


        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return true;
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
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter old password: ");
            String oldPassword = scanner.nextLine();

            System.out.println("Enter new password: ");
            String newPassword = scanner.nextLine();

            System.out.println("Enter new password again: ");
            String newPasswordAgain = scanner.nextLine();

            if (!newPassword.equals(newPasswordAgain)) {
                System.out.println("Passwords are not equal");
                continue;
            }

            if (oldPassword.equals(this.password)) {
                this.password = newPassword;
                System.out.println("Password changed successfully");

                break;
            } else {
                System.out.println("Wrong password");
            }
        }
    }

    public void showProfileInfo() {
        // TODO show profile info, needs more like name, surname, email, phone number, address, bank details but does each role have all of this?
        System.out.println("Profile info:");
        System.out.println("Login: " + this.login);
        System.out.println("Password: " + this.password); // TODO should i level this here?
        System.out.println("Role: " + this.role);
        System.out.println("User ID: " + this.userID);
    }


//    test main
//    TODO delete this main after testing
//    public static void main(String[] args) {
//        User user = new User("student");
//        System.out.println(user.checkUser());
//    }
}
