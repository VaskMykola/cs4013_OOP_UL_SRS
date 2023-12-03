package utilities.menu;

import users.Student;
import utilities.Utils;

public class RoleMainMenuBuilder {
    private RoleMainMenuBuilder() {
        throw new IllegalStateException("RoleMainMenuBuilder class");
    }

    public static void studentMenu(String studentLogin) {
        Menu studentMenu = new Menu("Student Menu");

        studentMenu.addItem(new MenuItem("show a transcript", () -> System.out.println(Student.showTranscript(studentLogin))));

        studentMenu.addItem(new MenuItem("View programme details", () -> System.out.println(Student.viewProgrammeDetails(studentLogin))));

        studentMenu.addItem(new MenuItem("View student modules", () -> System.out.println(Student.getStudentModules(studentLogin))));

        studentMenu.display();

    }


    public static void departmentMenu(String departmentLogin) {
        System.out.println("Department Menu still in development...");
    }

    public static void facultyMenu(String facultyLogin) {
        System.out.println("Faculty Menu still in development...");
    }

    public static void loginMenu() {

        Menu loginMenu = new Menu("Login Menu");

        loginMenu.addItem(new MenuItem("Login", Utils::userLogin));

        loginMenu.display();
    }

    public static void adminMenu() {
        System.out.println("Admin Menu still in development...");
    }
}