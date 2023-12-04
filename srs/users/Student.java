package users;

import utilities.StudentUtils;

/**
 * Represents a student in an educational institution.
 * This class encapsulates student-specific information and actions.
 */
public class Student {
    private final String studentLogin;

    /**
     * Constructs a new Student instance.
     *
     * @param studentLogin The unique login ID of the student.
     */
    public Student(String studentLogin) {
        this.studentLogin = studentLogin;
    }

    /**
     * Retrieves the academic transcript of this student.
     * Delegates to StudentUtils to obtain the transcript based on the student's login ID.
     */
    public void showTranscript() {
        StudentUtils.showTranscript(this.studentLogin);
    }
}
