package enumsForCSV;
// TODO AUTOGEN ENUMS FIELD NAMES FROM CSV FILES
// IF THE CSV FILE IS CHANGED, THE ENUMS WILL BE AUTOMATICALLY UPDATED
// IF NEW FIELD IS ADDED TO THE CSV FILE, THE ADMINISTRATOR WILL BE CALLED FOR CODE CHANGES
public enum studentsSCVFields {
    STUDENTLOGIN("StudentLogin"),
    STUDENTNAME("StudentName"),

    STUDENTCOURSECODE("CourseCode"),

    STUDENTDEPARTMENTCODE("DepartmentCode"),

    STUDENTFACULTYCODE("FacultyCode"),

    STUDENTYEAR("Year"),

    STUDENTSEMESTER("Semester"),

    STUDENTPHONE("Phone"),

    STUDENTEMAIL("Email"),

    STUDENTADDRESS("Adress");

    private String field;

    studentsSCVFields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
