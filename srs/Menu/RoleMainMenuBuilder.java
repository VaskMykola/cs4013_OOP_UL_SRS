package Menu;

import users.Student;
import utilities.Utils;

public class RoleMainMenuBuilder {

    public static void studentMenu(String studentLogin) {
        Menu studentMenu = new Menu("Student Menu");

        studentMenu.addItem(new MenuItem("show a transcript", () -> System.out.println(Student.showTranscript(studentLogin))));

        studentMenu.addItem(new MenuItem("View programme details", () -> System.out.println(Student.viewProgrammeDetails(studentLogin))));

        studentMenu.addItem(new MenuItem("View student modules", () -> System.out.println(Student.getStudentModules(studentLogin))));

        studentMenu.display();

    }


    public static void departmentMenu(String departmentLogin) {
        Menu departmentMenu = new Menu("users.Department Menu");
        Menu manageFacultyAndStuffMenu = new Menu("Manage faculty and stuff Menu");

        departmentMenu.addItem(new MenuItem("View department information",
                // TODO function to view department information
                () -> System.out.println("View department information selected")));
        departmentMenu.addItem(new MenuItem("Manage faculty and stuff", manageFacultyAndStuffMenu));
        departmentMenu.addItem(new MenuItem("Management",
                // TODO function to management
                () -> System.out.println("Management selected")));


        manageFacultyAndStuffMenu.addItem(new MenuItem("View faculty and stuff information",
                // TODO function to view faculty and stuff information
                () -> System.out.println("View faculty and stuff information selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("Add faculty",
                // TODO function to add faculty
                () -> System.out.println("Add faculty selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("Add stuff",
                // TODO function to add stuff
                () -> System.out.println("Add stuff selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("Remove faculty",
                // TODO function to remove faculty
                () -> System.out.println("Remove faculty selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("Remove stuff",
                // TODO function to remove stuff
                () -> System.out.println("Remove stuff selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("Change faculty information",
                // TODO function to change faculty information
                () -> System.out.println("Change faculty information selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("Change stuff information",
                // TODO function to change stuff information
                () -> System.out.println("Change stuff information selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("Assign Roles and Responsibilities",
                // TODO function to assign roles and responsibilities
                () -> System.out.println("Assign Roles and Responsibilities selected")));
        manageFacultyAndStuffMenu.addItem(new MenuItem("View Roles and Responsibilities",
                // TODO function to view roles and responsibilities
                () -> System.out.println("View Roles and Responsibilities selected")));


    }

    public static void facultyMenu(String facultyLogin) {
        Menu facultyMenu = new Menu("users.Faculty Menu");
        facultyMenu.addItem(new MenuItem("View faculty information",
                //TODO: function to view faculty information
                () -> System.out.println("View faculty information selected")));

    }

    public static void loginMenu() {

        Menu loginMenu = new Menu("Login Menu");

        loginMenu.addItem(new MenuItem("Login", Utils::userLogin));

        loginMenu.display();

    }

    public static void adminMenu() {
        Menu adminMenu = new Menu("Admin Menu");
        adminMenu.addItem(new MenuItem("manage departments",
                // TODO function to manage departments
                () -> System.out.println("manage departments selected")));
        adminMenu.addItem(new MenuItem("manage faculties",
                // TODO function to manage faculties
                () -> System.out.println("manage faculties selected")));
        adminMenu.addItem(new MenuItem("manage students",
                // TODO function to manage students
                () -> System.out.println("manage students selected")));
        adminMenu.addItem(new MenuItem("audit logs",
                // TODO function to audit logs
                () -> System.out.println("audit logs selected")));
        adminMenu.addItem(new MenuItem("Backup and Restore",
                // TODO function to backup and restore
                () -> System.out.println("Backup and Restore selected")));
        adminMenu.display();
    }

}