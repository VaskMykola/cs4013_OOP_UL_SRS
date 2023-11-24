package Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CSVHandler {
    private final ArrayList<String> tableData;
    private final HashMap<String, Integer> columnHeaders;


    public CSVHandler(String filepath) {
        tableData = readFile(filepath);
        columnHeaders = extractColumnHeaders(tableData.get(0)); // use the first row from the table to set column headers
        tableData.remove(0); // from the tableInfo ArrayList, remove the first element which is a String of column headers
    }
    /**
     * @param filepath of the csv file
     * @return an ArrayList containing each line from the file as a separate String object
     */
    // it was decided to leave all rows as String for efficiency: not all rows will be used for data manipulation, so it's redundant to convert all of them to arrayList
    private ArrayList<String> readFile(String filepath) {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filepath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isBlank()) { // makes sure that there are no lines without any data (for example, somebody created a few new lines at the end of a file)
                    fileData.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileData;
    }

    private HashMap<String, Integer> extractColumnHeaders(String columnHeadersRow) {
        String[] columnHeadersArray = columnHeadersRow.split(",");
        HashMap<String, Integer> columnHeadersMap = new HashMap<>();
        int columnIndex = 0;
        for (String columnHeader : columnHeadersArray) {
            columnHeadersMap.put(columnHeader, columnIndex);
            columnIndex++;
        }
        return columnHeadersMap;
    }

    /**
     * @param columnName name of a column
     * @param value that needs to be found in that column
     * @return String representing the row containing a specific value for a specific column
     */
    public String findRowWithSpecificColumnValue(String columnName, String value) {
        for (String dataRow : tableData) {
            if (findValueOfSpecificColumn(dataRow, columnName).equals(value)) {
                return dataRow;
            }
        }
        return null; // identifies that there is no row with the specified value for the specified column
    }

    public String findValueOfSpecificColumn(String dataRow, String columnName) {
        return dataRow.split(",")[columnHeaders.get(columnName)];
    }

//    public static int determineColumnNumberByItsName(String columnHeader, String columnHeaderRow) {
//        String[] columnHeadersArray = columnHeaderRow.split(",");
//        for (int i = 0; i < columnHeadersArray.length; i++) {
//            if (columnHeadersArray[i].equals(columnHeader)) {
//                return i + 1;
//            }
//        }
//        return -1; // The specified columnHeader is not in the table
//    }

    public ArrayList<String> getTableData() {
        return tableData;
    }

    public HashMap<String, Integer> getColumnHeaders() {
        return columnHeaders;
    }

    public static void main(String[] args) {
        CSVHandler et4012 = new CSVHandler("./csvFiles/%s.csv".formatted("CS4013"));
        System.out.println(et4012.getColumnHeaders());
        System.out.println(et4012.getTableData());
        System.out.println(et4012.findRowWithSpecificColumnValue("Grade", "C3"));
    }
}