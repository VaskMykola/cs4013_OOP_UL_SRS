import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Grade {
    private String grade;
    private String gradeDescription;
    private double QPV;
    private boolean creditsAwarded;

    public Grade(String grade, String gradeDescription, double QPV, boolean creditsAwarded) {
        this.grade = grade;
        this.gradeDescription = gradeDescription;
        this.QPV = QPV;
        this.creditsAwarded = creditsAwarded && QPV >= 2;  // QPV should be more than 2.00 for it to be awarded
    }

    public String toString() {
        return "Grade: " + grade +
                "\nGrade Description: " + gradeDescription +
                "\nQPV: " + QPV +
                "\nCredits Awarded: " + creditsAwarded;
    }

    public static String calculateGrade(int percentage, String filePath) {
        List<Grade> gradeList = readGradeDataFromCSV(filePath);

        for (Grade gradeObj : gradeList) {
            if (percentage >= Integer.parseInt(gradeObj.getGradeDescription())) {
                return gradeObj.toString() +
                        "\nPercentage: " + percentage;
            }
        }

        return "No matching grade found for this percentage.";
    }

    private static List<Grade> readGradeDataFromCSV(String filePath) {
        List<Grade> gradeList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skipping the header line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Grade gradeObj = new Grade(parts[0], parts[1], Double.parseDouble(parts[2]), true);
                gradeList.add(gradeObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gradeList;
    }

    public String getGrade() {
        return grade;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public double getQPV() {
        return QPV;
    }

    public boolean isCreditsAwarded() {
        return creditsAwarded;
    }

}
