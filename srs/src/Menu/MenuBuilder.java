package Menu;

public class MenuBuilder {
    public static void buildMenu() {
        Menu mainMenu = new Menu("Menu.MenuBuilder Menu");
        Menu subMenu1 = new Menu("SubMenu 1");

        mainMenu.addItem(new MenuItem("Option 1", () -> System.out.println("Option 1 selected")));
        mainMenu.addItem(new MenuItem("SubMenu", subMenu1));

        subMenu1.addItem(new MenuItem("Sub option 1", () -> System.out.println("Sub option 1 selected")));

        mainMenu.display();
    }

    public static void studentMenu() {
        Menu mainMenu = new Menu("Student Menu");
        mainMenu.addItem(new MenuItem("show a transcript",
                // TODO function to generate a student transcript
                () -> System.out.println("show a transcript selected")));
        mainMenu.addItem(new MenuItem("View programme details",
                // TODO function to view programme details
                () -> System.out.println("View programme details selected")));
        mainMenu.addItem(new MenuItem("Profile settings",
                // TODO function to view profile settings
                () -> System.out.println("Profile settings selected")));
        mainMenu.addItem(new MenuItem("View academic calendar",
                // TODO function to view accademic calendar
                () -> System.out.println("View academic calendar selected")));
        mainMenu.addItem(new MenuItem("Module selection and other",
                // TODO function to view module selection and other
                () -> System.out.println("Module selection and other selected")));

        // TODO subMenus

    }
    // the same for all roles i guess so, i decided to take it as separate menu
    public static void profileSettingMenu(){ // TODO  we will need to add a parameters to this function (ID, role...)
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

    public static void departmentMenu(){
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

    public static void facultyMenu(){
        Menu facultyMenu = new Menu("Faculty Menu");
        facultyMenu.addItem(new MenuItem("View faculty information",
                // TODO function to view faculty information
                () -> System.out.println("View faculty information selected")));
        facultyMenu.addItem(new MenuItem("Display teaching assignments",
                // TODO function to display teaching assignments
                () -> System.out.println("Display teaching assignments selected")));
        // TODO subMenus, and add more functionality to this one
    }

//    public static void run() {  maibe we will need it
//        mainMenu.display();
//    }
}