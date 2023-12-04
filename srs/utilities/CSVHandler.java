package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The `CSVHandler` class provides methods for handling CSV files, including reading data,
 * extracting column headers, and performing queries based on column values.
 */
public class CSVHandler {
    /**
     * The delimiter used to separate values in the CSV file.
     */
    private static final String CSV_DELIMITER = ",";
    /**
     * Represents the data in the CSV file as a list of strings, each string representing a row.
     */
    private final List<String> tableData;
    /**
     * Represents the column headers and their corresponding indices in the CSV file.
     */
    private final Map<String, Integer> columnHeaders;

    /**
     * Constructs a CSVHandler instance and initializes the tableData and columnHeaders fields
     * based on the CSV file at the specified filepath.
     *
     * @param filepath The filepath of the CSV file.
     */
    public CSVHandler(String filepath) {
        tableData = readFile(filepath);
        columnHeaders = extractColumnHeaders(tableData.get(0)); // use the first row from the table to set column headers
        tableData.remove(0); // from the tableInfo ArrayList, remove the first element which is a String of column headers
        Collections.sort(tableData);
    }

    /**
     * Reads the content of a CSV file and returns it as a list of strings.
     *
     * @param filepath The filepath of the CSV file.
     * @return An ArrayList containing each line from the file as a separate String object.
     */
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

    /**
     * Extracts column headers from a row in the CSV file and returns them as a map with their indices.
     *
     * @param columnHeadersRow The row containing column headers.
     * @return A map containing column headers and their corresponding indices.
     */
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

    /**
     * Checks if a specific column in a row has a specified value.
     *
     * @param dataRow       The row of data.
     * @param columnName    The name of the column.
     * @param expectedValue The expected value for the column.
     * @return True if the column has the expected value, false otherwise.
     */
    private boolean hasSpecificColumnValue(String dataRow, String columnName, String expectedValue) {
        String value = findValueOfSpecificColumnInSpecificRow(dataRow, columnName);
        return value != null && value.equals(expectedValue);
    }

    /**
     * Finds the value of a specific column in a given row.
     *
     * @param dataRow    The row of data.
     * @param columnName The name of the column.
     * @return The value of the specified column in the given row.
     */
    public String findValueOfSpecificColumnInSpecificRow(String dataRow, String columnName) {
        String[] rowValues = splitRow(dataRow);
        Integer columnIndex = columnHeaders.get(columnName);
        if (columnIndex == null) {
            return null;
        }
        return rowValues[columnIndex];
    }

    /**
     * Splits a row into an array of values based on the CSV delimiter.
     *
     * @param row The row to be split.
     * @return An array of values from the row.
     */
    private String[] splitRow(String row) {
        return row.split(CSV_DELIMITER);
    }

    /**
     * Finds rows that have specific values for specified columns.
     *
     * @param columnValues A map containing column names and their corresponding values.
     * @return A list of rows that satisfy the specified conditions.
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

    /**
     * Checks if a row has specific values for specified columns.
     *
     * @param dataRow       The row of data.
     * @param columnValues  A map containing column names and their corresponding values.
     * @return True if the row has all specified values for the specified columns, false otherwise.
     */
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

    /**
     * Finds distinct values for a specific column within a list of rows.
     *
     * @param rows       The list of rows.
     * @param columnName The name of the column.
     * @return A list of distinct values for the specified column.
     */
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

    /**
     * Finds distinct combinations of values for several specified columns within a list of rows.
     *
     * @param rows        The list of rows.
     * @param columnNames An array of column names.
     * @return A list of distinct combinations of values for the specified columns.
     */
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

    /**
     * Gets the table data as a list of strings.
     *
     * @return The list of strings representing the rows of the table.
     */
    public List<String> getTableData() {
        return tableData;
    }

    /**
     * Gets the column headers and their corresponding indices as a map.
     *
     * @return A map containing column headers and their corresponding indices.
     */
    public Map<String, Integer> getColumnHeaders() {
        return columnHeaders;
    }

}
