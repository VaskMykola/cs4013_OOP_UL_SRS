package utilities;

import utilities.CSVHandler;
import utilities.QCACalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * The TranscriptManager class provides functionality for displaying student transcripts.
 * It includes methods to show transcripts for specific students.
 */
public class TranscriptManager {

    /**
     * Represents the CSVHandler for all student data.
     */
    public static CSVHandler allStudentsData = new CSVHandler("./csvFiles/studentsInformation.csv");

    /**
     * Displays the transcript for a specific student identified by their student ID.
     *
     * @param studentID The ID of the student for whom the transcript is to be displayed.
     */
    public static void showTranscript(String studentID) {
        // Retrieve student information
        Map<String, String> arguments = new HashMap<>();
        arguments.put("ID", studentID);
        String studentInfo = allStudentsData.findRowsWithColumnValuesSpecified(arguments).get(0);

        // Display student personal data
        StringBuilder studentPersonalData = new StringBuilder();
        for (Map.Entry<String, Integer> item : allStudentsData.getColumnHeaders().entrySet()) {
            studentPersonalData.append(item.getKey())
                    .append(":")
                    .append(allStudentsData.findValueOfSpecificColumnInSpecificRow(studentInfo, item.getKey()))
                    .append("\n");
        }
        System.out.println(studentPersonalData);

        // Display QCA for every studying period
        for (Map.Entry<String, Map<String, String>> item : QCACalculator.calculateQCAForEveryStudyingPeriod(studentID).entrySet()) {
            System.out.println(item.getKey());
            for (Map.Entry<String, String> innerItem : item.getValue().entrySet()) {
                System.out.println(innerItem.getKey() + " = " + innerItem.getValue());
            }
        }
    }
}
