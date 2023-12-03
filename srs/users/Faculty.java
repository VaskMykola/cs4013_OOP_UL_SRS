package users;


import utilities.CSVHandler;

public class Faculty {
    private static final String FACULTIES_CSV = "csvFiles/csvForRoles/faculties.csv";
    public static String viewFacultyInformation(String facultyLogin) {
        CSVHandler faculties = new CSVHandler(FACULTIES_CSV);
        String facultyName = faculties.findValueOfSpecificColumnInSpecificRow(facultyLogin, "FacultyLogin");
        return "Faculty Name: " + facultyName + "\n";
    }

    public static void main(String[] args) {
        System.out.println(viewFacultyInformation("fce"));
    }

}