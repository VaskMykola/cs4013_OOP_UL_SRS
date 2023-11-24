package Menu;


public class MenuBuilder {

    public static void studentMenu() {
        Menu studentMenu = new Menu("Student Menu");

        studentMenu.addItem(new MenuItem("show a transcript",
                // TODO function to generate a student transcript
                () -> System.out.println("show a transcript selected")));

        studentMenu.addItem(new MenuItem("View programme details",
                // TODO function to view programme details
                () -> System.out.println("View programme details selected")));

        studentMenu.addItem(new MenuItem("Profile info & settings",
                // TODO function to view profile settings
                () -> System.out.println("Profile settings selected")));

        studentMenu.addItem(new MenuItem("View academic calendar",
                // TODO function to view academic calendar
                () -> System.out.println("View academic calendar selected")));

        studentMenu.addItem(new MenuItem("Module selection and other",
                // TODO function to view module selection and other
                () -> System.out.println("Module selection and other selected")));

        // TODO subMenus


        studentMenu.display();

    }

    public static void profileSettingMenu() {
        // TODO  we will need to add a parameters to this function (ID, role...)
        // i think that all roles should have profile setting so for now it will be separate menu


        Menu profileSettingsMenu = new Menu("Profile Settings Menu");
        profileSettingsMenu.addItem(new MenuItem("Change password",
                // TODO function to change password
                () -> System.out.println("Change password selected")));
        profileSettingsMenu.addItem(new MenuItem("Change email",
                // TODO function to change email
                () -> System.out.println("Change email selected")));
        profileSettingsMenu.addItem(new MenuItem("Change phone number",
                // TODO function to change phone number
                () -> System.out.println("Change phone number selected")));
        profileSettingsMenu.addItem(new MenuItem("Change address",
                // TODO function to change address
                () -> System.out.println("Change address selected")));
        profileSettingsMenu.addItem(new MenuItem("Change bank details",
                // TODO function to change bank details
                () -> System.out.println("Change bank details selected")));
    }

    public static void departmentMenu() {
        Menu departmentMenu = new Menu("Department Menu");
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

    public static void facultyMenu() {
        Menu facultyMenu = new Menu("Faculty Menu");
        facultyMenu.addItem(new MenuItem("View faculty information",
                //TODO: function to view faculty information
                () -> System.out.println("View faculty information selected")));
        facultyMenu.addItem(new MenuItem("Display teaching assignments",
                //TODO function to display teaching assignments
                () -> System.out.println("Display teaching assignments selected")));
        // TODO subMenus, and add more functionality to this one
    }

    public static void loginMenu() {
        // TODO create additional fucntion to check data with data from db
        Menu loginMenu = new Menu("Login Menu");
        loginMenu.addItem(new MenuItem("Login", () -> System.out.println("todo")));
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