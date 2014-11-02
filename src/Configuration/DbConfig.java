package Configuration;

public class DbConfig {
    public static final String host = "localhost";

    public static final String user = "root";
    public static final String pass = "notSoC0y";

    public static final String dbName = "assignmentTwoDb";
    public static final boolean dropDbIfExistsWhenCreating = true;

    public static final String tableName = "assignmenttwotable";
    public static final boolean dropTableIfExistsWhenCreating = false;

    public static final String artistColumnName = "ARTIST";
    public static final String titleColumnName = "TITTEL";
    public static final String trackColumnName = "SPOR";
    public static final String releasedColumnName = "UTGITT";
    public static final String genreColumnName = "SJANGER";
}
