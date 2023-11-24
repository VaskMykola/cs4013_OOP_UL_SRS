package users;

public class Student extends User {
    String group;
    String programme;
    String year;

    String semester;


    String[] myModules;

    Student() {
        super();
    }


    public void showTranscript() {
        // TODO function to generate a student transcript
        System.out.println("show a transcript selected");
    }

    public void calculateQSA() {
        // TODO function to calculate QSA
        System.out.println("calculate QSA selected");
    }

    public void showStudentModules() {
        // TODO function to show modules
        System.out.println("show modules selected");
    }


}
