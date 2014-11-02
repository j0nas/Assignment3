package Parsing;

import Configuration.Config;
import Persistence.DataType;
import Persistence.TableHeaderColumn;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVParser {
    private String filePath;
    private String headerLine;
    private String[] csvFileContent;
    private TableHeaderColumn[] csvHeaderColumns;

    public CSVParser(final String filePath) throws Exception {
        this.filePath = filePath;
        csvFileContent = readCSVfile();
    }

    public TableHeaderColumn[] getCsvHeaderColumns() throws Exception {
        if (csvHeaderColumns == null) {
            csvHeaderColumns = parseCSVHeadersAsColumns();
        }

        return csvHeaderColumns;
    }

    public String[] getCsvFileContent() {
        return csvFileContent;
    }

    private String[] readCSVfile() throws Exception {
        File csvFile = new File(filePath);
        if (!csvFile.canRead()) {
            throw new FileNotFoundException("readCSVfile: the specified file was not found, or is inaccessible.");
        }

        ArrayList<String> resultCollector = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            headerLine = bufferedReader.readLine(); // Read and do away with first line of file
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                resultCollector.add(nextLine);
                nextLine = bufferedReader.readLine();
            }

            return resultCollector.toArray(new String[resultCollector.size()]);
        } catch (IOException e) {
            throw new Exception("readCSVfile: failed to parse file!");
        }

    }

    private TableHeaderColumn[] parseCSVHeadersAsColumns() {
        ArrayList<TableHeaderColumn> tableHeaderColumns = new ArrayList<>();
        Arrays.asList(headerLine.split(Config.fileItemsSeparator))
                .forEach(columnName -> tableHeaderColumns.add(new TableHeaderColumn(columnName, DataType.VARCHAR)));

        return tableHeaderColumns.toArray(new TableHeaderColumn[tableHeaderColumns.size()]);
    }
}
