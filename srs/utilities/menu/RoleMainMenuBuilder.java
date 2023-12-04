package utilities.menu;

import users.Department;
import users.Student;
import utilities.UserUtils;

/**
 * RoleMainMenuBuilder is a utility class responsible for creating and displaying
 * different types of menus based on user roles like Student, Department, etc.
 * <p>
 * This class contains static methods to create specific menus for students,
 * departments, and for the login process.
 */
public class RoleMainMenuBuilder {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws IllegalStateException if this constructor is called
     */
    private RoleMainMenuBuilder() {
        throw new IllegalStateException("RoleMainMenuBuilder class");
    }

    /**
     * Creates and displays a menu for student-related actions.
     * <p>
     * The student menu provides options like viewing the student's transcript.
     *
     * @param student the student for whom the menu is being created
     */
    public static void studentMenu(Student student) {
        Menu studentMenu = new Menu("StudentUtils Menu");
        studentMenu.addItem(new MenuItem("View Transcript", () -> {
            student.showTranscript();

        }));

        studentMenu.display();
    }

    /**
     * Creates and displays a menu for department-related actions.
     * <p>
     * The department menu provides options like viewing transcripts of students
     * within the department.
     *
     * @param department the department for which the menu is being created
     */
    public static void departmentMenu(Department department) {
        Menu departmentMenu = new Menu("Department Menu");

        departmentMenu.addItem(new MenuItem("View Transcript of a Specific Student",
                () -> {
                    department.getTranscriptOfStudentFromThisDepartment();

                }));

        departmentMenu.display();
    }

    /**
     * Creates and displays a login menu.
     * <p>
     * The login menu provides options for user authentication.
     */
    public static void loginMenu() {
        Menu loginMenu = new Menu("Login Menu");

        loginMenu.addItem(new MenuItem("Login", UserUtils::userLogin));

        loginMenu.display();
    }
}
