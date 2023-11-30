import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Department {
    private String departmentName;
    private String departmentCode;
    private String contactDetails;
    private String description;
    private List<Faculty> facultyMembers;

    public Department(String departmentName, String departmentCode, String contactDetails, String description) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.contactDetails = contactDetails;
        this.description = description;
        this.facultyMembers = new ArrayList<>();
    }

    private void manageFacultyAndStaffMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nManage Faculty and Staff MENU:");
            System.out.println("1. View Faculty and Staff List");
            System.out.println("2. Add Faculty or Staff Member");
            System.out.println("3. Edit Faculty or Staff Information");
            System.out.println("4. Remove Faculty or Staff Member");
            System.out.println("5. Assign Department Head");
            System.out.println("6. Assign Roles and Responsibilities");
            System.out.println("7. View Faculty and Staff Roles");
            System.out.println("8. Search Faculty and Staff");
            System.out.println("9. Back to Department Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewFacultyAndStaffList();
                    break;
                case 2:
                    addFacultyOrStaffMember();
                    break;
                case 3:
                    editFacultyOrStaffInformation();
                    break;
                case 4:
                    removeFacultyOrStaffMember();
                    break;
                case 5:
                    assignDepartmentHead();
                    break;
                case 6:
                    assignRolesAndResponsibilities();
                    break;
                case 7:
                    viewFacultyAndStaffRoles();
                    break;
                case 8:
                    searchFacultyAndStaff();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addFacultyOrStaffMember() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAdding Faculty or Staff Member:");

        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();

        System.out.print("Enter member ID: ");
        String memberID = scanner.nextLine();

        System.out.print("Enter member role: ");
        String memberRole = scanner.nextLine();

        Faculty newMember = new Faculty(memberName, memberID, memberRole);

        facultyMembers.add(newMember);

        System.out.println("Faculty or Staff Member added successfully.");
    }

    private void removeFacultyOrStaffMember() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nRemoving Faculty or Staff Member:");

        System.out.print("Enter member ID to remove: ");
        String memberIDToRemove = scanner.nextLine();

        Faculty memberToRemove = findFacultyOrStaffMemberByID(memberIDToRemove);

        if (memberToRemove != null) {
            facultyMembers.remove(memberToRemove);
            System.out.println("Faculty or Staff Member removed successfully.");
        } else {
            System.out.println("Faculty or Staff Member with the given ID not found.");
        }
    }

    private Faculty findFacultyOrStaffMemberByID(String memberID) {
        for (Faculty member : facultyMembers) {
            if (member.getMemberID().equals(memberID)) {
                return member;
            }
        }
        return null;
    }

    private void assignDepartmentHead() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAssigning Department Head:");

        System.out.print("Enter member ID of the new department head: ");
        String newHeadID = scanner.nextLine();

        Faculty newHead = findFacultyOrStaffMemberByID(newHeadID);

        if (newHead != null) {
            removeCurrentDepartmentHead();
            newHead.setDepartmentHead(true);

            System.out.println("Department head assigned successfully.");
        } else {
            System.out.println("Faculty or Staff Member with the given ID not found.");
        }
    }

    private void removeCurrentDepartmentHead() {
        for (Faculty member : facultyMembers) {
            if (member.isDepartmentHead()) {
                member.setDepartmentHead(false);
                System.out.println("Previous department head removed.");
                return;
            }
        }
    }

    private void assignRolesAndResponsibilities() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter member ID to assign roles and responsibilities: ");
        String memberID = scanner.nextLine();

        Faculty faculty = findFacultyOrStaffMemberByID(memberID);

        if (faculty != null) {
            System.out.println("Assigning roles and responsibilities for " + faculty.getFacultyName());

            System.out.print("Enter roles and responsibilities (separated by commas): ");
            String rolesAndResponsibilities = scanner.nextLine();

            faculty.setRolesAndResponsibilities(rolesAndResponsibilities);

            System.out.println("Roles and responsibilities assigned successfully.");
        } else {
            System.out.println("Faculty or Staff Member with the given ID not found.");
        }
    }

    private void editFacultyOrStaffInformation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEditing Faculty or Staff Information:");

        System.out.print("Enter member ID to edit: ");
        String memberIDToEdit = scanner.nextLine();

        Faculty memberToEdit = findFacultyOrStaffMemberByID(memberIDToEdit);

        if (memberToEdit != null) {
            System.out.println("Editing information for " + memberToEdit.getFacultyName() + ":");

            System.out.print("Enter new name (or press Enter to keep the current name): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                memberToEdit.setFacultyName(newName);
            }

            System.out.print("Enter new role (or press Enter to keep the current role): ");
            String newRole = scanner.nextLine();
            if (!newRole.isEmpty()) {
                memberToEdit.setRole(newRole);
            }

            System.out.println("Faculty or Staff Member information updated successfully.");
        } else {
            System.out.println("Faculty or Staff Member with the given ID not found.");
        }
    }

    public void viewFacultyAndStaffRoles() {
        System.out.println("Faculty and Staff Roles:");
        for (Faculty faculty : facultyMembers) {
            System.out.println("Name: " + faculty.getFacultyName() + "\tRole: " + faculty.getRole());
        }
    }

    public List<Faculty> searchFacultyAndStaffByName(String name) {
        List<Faculty> searchResults = new ArrayList<>();
        for (Faculty faculty : facultyMembers) {
            if (faculty.getFacultyName().toLowerCase().contains(name.toLowerCase())) {
                searchResults.add(faculty);
            }
        }
        return searchResults;
    }

    private void viewFacultyAndStaffList() {
        System.out.println("Faculty and Staff List:");
        for (Faculty faculty : facultyMembers) {
            System.out.println("Name: " + faculty.getFacultyName() + "\tID: " + faculty.getMemberID());
        }
    }

    private void searchFacultyAndStaff() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name to search for: ");
        String nameToSearch = scanner.nextLine();

        List<Faculty> searchResults = searchFacultyAndStaffByName(nameToSearch);

        if (!searchResults.isEmpty()) {
            System.out.println("Search results:");
            for (Faculty faculty : searchResults) {
                System.out.println("Name: " + faculty.getFacultyName() + "\tID: " + faculty.getMemberID());
            }
        } else {
            System.out.println("No matching results found.");
        }
    }
}
