package Users;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class User {
    private String login;
    private String password;
    private String role;

    public User(String role) {
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Enter login: ");
        this.login = Scanner.nextLine();
        System.out.println("Enter password: ");
        this.password = Scanner.nextLine();
        this.role = role;
    }

    // TODO checkUser() not verified
    public boolean checkUser() {
        try (Scanner scanner = new Scanner(Paths.get("srs/src/users.csv").toFile())) {
            // CSV file delimiter
            String DELIMITER = ",";
            // set comma as delimiter
            scanner.useDelimiter(DELIMITER);
            // read all fields and find this user (user.csv format: login, password, role)
            while (scanner.hasNext()) {
                String[] fields = scanner.nextLine().split(DELIMITER);
                if (fields[0].equals(this.login) && fields[1].equals(this.password) && fields[2].equals(this.role)) {
                    return true;
                }
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
