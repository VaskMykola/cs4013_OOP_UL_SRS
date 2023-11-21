package Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QCACalculator {
    private final ArrayList<Grade> gradeSchemeArrayList =
            MarkingSchemeReader.readMarkingScheme("/Users/oleksandrkardash/IdeaProjects/cs4013_OOP_UL_SRS/srs/academicGrades.csv");

}

class MarkingSchemeReader {
    private static final ArrayList<Grade> gradeArrayList = new ArrayList<>(); // put this in a different class (like MarkingSchemeReader)
    public static ArrayList<Grade> readMarkingScheme(String filepath) {

        try {
            Scanner scanner = new Scanner(new File(filepath));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineData = line.split(",");
                gradeArrayList.add(new Grade(
                        lineData[0],
                        lineData[1],
                        lineData[2].equals("–") ? -1 : Double.parseDouble(lineData[2]),
                        lineData[3] == "Yes"));
            }
            gradeArrayList.forEach(System.out::println);


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return gradeArrayList;
    }
}

class Grade {
    private final String grade;
    private final String gradeDescription;
    private final double QPV;
    private final boolean creditsAwarded;

    public Grade(String grade, String gradeDescription, double QPV, boolean creditsAwarded) {
        this.grade = grade;
        this.gradeDescription = gradeDescription;
        this.QPV = QPV;
        this.creditsAwarded = creditsAwarded;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "grade='" + grade + '\'' +
                ", gradeDescription='" + gradeDescription + '\'' +
                ", QPV=" + QPV +
                ", creditsAwarded=" + creditsAwarded +
                '}';
    }

    public static void main(String[] args) {
        QCACalculator qcaCalculator = new QCACalculator("/Users/oleksandrkardash/IdeaProjects/cs4013_OOP_UL_SRS/srs/academicGrades.csv");
    }
}
