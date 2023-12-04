
import utilities.menu.RoleMainMenuBuilder;

/**
 * Main class of the application.
 * <p>
 * This class is the entry point of the application and is responsible for
 * initiating the login menu process.
 */
public class Main {

    /**
     * The main method that serves as the entry point of the Java application.
     * <p>
     * This method calls the loginMenu method from the RoleMainMenuBuilder class
     * to start the application.
     *
     * @param args command line arguments passed to the application (not used in this application)
     */
    public static void main(String[] args) {
        RoleMainMenuBuilder.loginMenu();
    }

}
