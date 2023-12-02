package users;

import java.util.ArrayList;
import java.util.List;

public class Student{


    public static String showTranscript(String studentID) {
        // Todo: implement
        System.out.println("show a transcript selected");
        return "transcript";
    }

    public void calculateQSA(String studentID) {
        // TODO implement
        System.out.println("calculate QSA selected");
    }

    public static List<String> showStudentModules(String studentID) {
       List<String> modules = new ArrayList<>();
        System.out.println("show modules selected");
        return modules;
    }

    public static String viewProgrammeDetails(String studentID) {
        System.out.println("view programme details selected");
        return "programme details";
    }




}
