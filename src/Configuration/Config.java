package Configuration;

public class Config {
    public static final String fileItemsSeparator = ",";
    public static final String generatedResults = "genreresults";
    public static final String generatedResultsJsp = generatedResults + ".jsp";
    public static final String generatedResultsServlet = "/" + generatedResults;
    private static final String filePath = "C:\\Users\\Jonas\\Desktop\\";
    private static final String fileName = "musikk";
    private static final String fileExtension = "csv";

    public static String getFilePath() {
        return String.format("%s%s.%s", filePath, fileName, fileExtension);
    }
}
