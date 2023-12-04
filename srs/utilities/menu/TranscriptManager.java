package utilities.menu;

import utilities.CSVHandler;
import utilities.QCACalculator;

import java.util.HashMap;
import java.util.Map;

public class TranscriptManager {

    public static CSVHandler allStudentsData = new CSVHandler("./csvFiles/studentsInformation.csv");

    public static String generateTranscript(String studentID) {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("ID", studentID);
        String studentInfo = allStudentsData.findRowsWithColumnValuesSpecified(arguments).get(0);
        StringBuilder studentPersonalData = new StringBuilder();
        for (Map.Entry<String, Integer> item : allStudentsData.getColumnHeaders().entrySet()) {
            studentPersonalData.append(item.getKey()).append(":").append(allStudentsData.findValueOfSpecificColumnInSpecificRow(studentInfo, item.getKey())).append("\n");
        }
        for (Map.Entry<String, Map<String, Double>> item : QCACalculator.calculateQCAForEveryStudyingPeriod(studentID).entrySet()) {
            System.out.println(item.getKey());
            for (Map.Entry<String, Double> innerItem : item.getValue().entrySet()) {
                System.out.println(innerItem.getKey() + " = " + innerItem.getValue());
            }
        };
        return studentPersonalData.toString();
    }


    public static void main(String[] args) {
        System.out.println(generateTranscript("22310975"));
    }
}