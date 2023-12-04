package users;

import utilities.StudentUtils;

/**
 * The `Department` class represents a department in the university.
 * It stores information about the department's code and a list of students
 * associated with this department.
 */
public class Department {
    private final String[] studentList;

    /**
     * Constructs a new `Department` object with the specified department code.
     *
     * @param departmentCode The unique code representing the department.
     */
    public Department(String departmentCode) {
        this.studentList = StudentUtils.getAllStudentsFromDepartment(departmentCode);
    }

    /**
     * Retrieves the transcript of a student from this department.
     */
    public void getTranscriptOfStudentFromThisDepartment() {
        StudentUtils.showTranscript(StudentUtils.selectStudentFromList(this.studentList));
    }
}
