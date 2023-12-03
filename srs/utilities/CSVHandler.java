package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVHandler {
    private static final String CSV_DELIMITER = ",";
    private final List<String> tableData;
    private final Map<String, Integer> columnHeaders;


    public CSVHandler(String filepath) {
        tableData = readFile(filepath);
        columnHeaders = extractColumnHeaders(tableData.get(0)); // use the first row from the table to set column headers
        tableData.remove(0); // from the tableInfo ArrayList, remove the first element which is a String of column headers
        Collections.sort(tableData);
    }

    /**
     * @param filepath of the csv file
     * @return an ArrayList containing each line from the file as a separate String object
     */
    // it was decided to leave all rows as String for efficiency: not all rows will be used for data manipulation, so it's redundant to convert all of them to arrayList
    private List<String> readFile(String filepath) {
        List<String> fileData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filepath))) {
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


    private Map<String, Integer> extractColumnHeaders(String columnHeadersRow) {
        String[] columnHeadersArray = splitRow(columnHeadersRow);
        Map<String, Integer> columnHeadersMap = new LinkedHashMap<>();
        int columnIndex = 0;
        for (String columnHeader : columnHeadersArray) {
            columnHeadersMap.put(columnHeader, columnIndex);
            columnIndex++;
        }
        return columnHeadersMap;
    }

    private boolean hasSpecificColumnValue(String dataRow, String columnName, String expectedValue) {
        String value = findValueOfSpecificColumnInSpecificRow(dataRow, columnName);
        return value != null && value.equals(expectedValue);
    }

    public String findValueOfSpecificColumnInSpecificRow(String dataRow, String columnName)  {
        String[] rowValues = splitRow(dataRow);
        Integer columnIndex = columnHeaders.get(columnName);
        if (columnIndex == null) {
            return null;
        }
        return rowValues[columnIndex];
    }

    private String[] splitRow(String row) {
        return row.split(CSV_DELIMITER);
    }

    /**
     * @param columnValues a map containing column names and their corresponding values
     * @return String representing the row containing specific values for specified columns
     */
    public List<String> findRowsWithColumnValuesSpecified(Map<String, String> columnValues) {
        ArrayList<String> foundRows = new ArrayList<>();
        for (String row : tableData) {
            if (hasAllColumnValues(row, columnValues)) {
                foundRows.add(row);
            }
        }

        return foundRows.isEmpty() ? null : foundRows; // null means that there is no row with the specified values for the specified columns
    }


    private boolean hasAllColumnValues(String dataRow, Map<String, String> columnValues) {
        for (Map.Entry<String, String> entry : columnValues.entrySet()) {
            String columnName = entry.getKey();
            String expectedValue = entry.getValue();

            if (!hasSpecificColumnValue(dataRow, columnName, expectedValue)) {
                return false; // at least one condition is not satisfied
            }
        }
        return true; // all conditions are satisfied
    }

    public List<String> findDistinctValuesForSpecificColumn(List<String> rows, String columnName) {
        List<String> distinctValuesForTheColumn = new ArrayList<>();
        for (String row : rows) {
            String value = findValueOfSpecificColumnInSpecificRow(row, columnName);
            if (!distinctValuesForTheColumn.contains(value)) {
                distinctValuesForTheColumn.add(value);
            }
        }
        return distinctValuesForTheColumn;
    }

    public List<String> findDistinctValuesForSeveralSpecificColumns(List<String> rows, String[] columnNames) {
        List<String> distinctValuesForSeveralSpecificColumns = new ArrayList<>();
        for (String row : rows) {
            StringBuilder combinationOfValues = new StringBuilder();
            for (String columnName : columnNames) {
                combinationOfValues.append(findValueOfSpecificColumnInSpecificRow(row, columnName)).append(",");
            }
            if (!distinctValuesForSeveralSpecificColumns.contains(combinationOfValues.toString().trim())) {
                distinctValuesForSeveralSpecificColumns.add(combinationOfValues.toString().trim());
            }
        }
        return distinctValuesForSeveralSpecificColumns;
    }

    public List<String> getTableData() {
        return tableData;
    }

    public Map<String, Integer> getColumnHeaders() {
        return columnHeaders;
    }



//    public static void main(String[] args) {
//        CSVHandler moduleStudentGradesTable = new CSVHandler(String.format("./csvFiles/%s.csv", "gradeBands"));
//        Map<String, String> value = new HashMap<>();
//        value.put("Grade", "A1");
////        System.out.println(moduleStudentGradesTable.determineGradeForModule("A1"));
//        System.out.println(moduleStudentGradesTable.findRowsWithColumnValuesSpecified(value));
//    }
}
