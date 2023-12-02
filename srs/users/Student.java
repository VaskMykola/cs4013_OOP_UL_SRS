package users;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {


    public static String showTranscript(String studentID) {
        System.out.println("show a transcript selected");
        return "transcript";
    }

    public void calculateQSA() {
        System.out.println("calculate QSA selected");
    }

    public static List<String> showStudentModules(String studentID) {
       List<String> modules = new ArrayList<String>();
        System.out.println("show modules selected");
        return modules;
    }

    public static String viewProgrammeDetails(String studentID) {
        System.out.println("view programme details selected");
        return "programme details";
    }




}
