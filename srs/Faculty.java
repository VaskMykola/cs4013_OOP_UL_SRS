import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String facultyName;
    private String department;
    private List<Course> assignedCourses;

    public Faculty(String facultyName, String department) {
        this.facultyName = facultyName;
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }

    public void viewCourseInformation(Course course) {
        System.out.println("Faculty Name: " + facultyName);
        System.out.println("Department: " + department);
        System.out.println("Assigned Courses:");

        for (Course assignedCourse : assignedCourses) {
            System.out.println("Course Code: " + assignedCourse.getCourseCode());
            System.out.println("Course Name: " + assignedCourse.getCourseName());
            System.out.println("Semesters: " + assignedCourse.getSemester());
            System.out.println("--------------------");
        }
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
        System.out.println("Course " + course.getCourseCode() + " assigned to " + facultyName);
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getDepartment() {
        return department;
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }
}