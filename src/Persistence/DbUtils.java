package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * MySQL CRUD convenience methods.
 */
public class DbUtils {
    private Connection connection;

    public DbUtils(Connection connection) {
        this.connection = connection;
    }

    public int createDb(String dbName) throws SQLException {
        return connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + dbName).executeUpdate();
    }

    public int createTable(String tableName, TableHeaderColumn[] columns) throws SQLException {
        final StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " ( ");
        Arrays.asList(columns).forEach(item -> stringBuilder
                .append(item.getName()).append(" ")
                .append(item.getType().getValue()).append("(").append(item.getSize()).append(")").append(", "));

        String query = stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).append(")").toString();

        return connection.prepareStatement(query).executeUpdate();
    }

    public boolean dbExists(String dbName) throws SQLException {
        ResultSet catalogs = connection.getMetaData().getCatalogs();
        while (catalogs.next()) {
            if (catalogs.getString(1).equalsIgnoreCase(dbName)) {
                return true;
            }
        }

        return false;
    }

    public boolean tableExists(String tableName) throws SQLException {
        return connection.getMetaData().getTables(null, null, tableName, null).next();
    }

    public ResultSet executeQuery(String query, String... paramsToPrepare) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < paramsToPrepare.length; i++) {
            preparedStatement.setString(i + 1, paramsToPrepare[i]);
        }

        return preparedStatement.executeQuery();
    }

    public boolean insertInto(String table, String[] values) throws SQLException {
        return connection.prepareStatement(String.format("INSERT INTO %s VALUES (%s)", table,
                Arrays.toString(values).substring(1, Arrays.toString(values).length() - 1))).executeUpdate() > 0;
    }

    /**
     * Generates a "DELETE FROM" SQL statement and executes it.
     *
     * @param table       The table that the generated query will be run against.
     * @param whereValues Sets of Keys and Values, used in the WHERE-clause of the generated statement.
     *                    Multiple key/value-pairs will be appended to the generated statement.
     *                    <p>
     *                    Example: (String table = "testTable")
     *                    whereValues contains pair "GENRE", "exampleGenre"
     *                    resulting query: "DELETE FROM testTable WHERE GENRE = 'exampleGenre'"
     *                    <p>
     *                    whereValues contain pairs "ARTIST", "testArtist" and "GENRE", "testGenre"
     *                    resulting query: "DELETE FROM testTable WHERE ARTIST = 'testArtist' AND GENRE = 'testGenre'"
     * @return True if the query was successfully executed (> 0 rows affected), false otherwise.
     * @throws SQLException
     */
    public boolean deleteFrom(String table, HashMap<String, String> whereValues) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder("DELETE FROM " + table + " WHERE ");
        final int[] counter = {0};
        whereValues.forEach((key, value) -> stringBuilder
                .append(key).append(" = '").append(value).append("'")
                .append(whereValues.size() > ++counter[0] ? " AND " : ""));

        return connection.prepareStatement(stringBuilder.toString()).executeUpdate() > 0;
    }

    public int dropDb(String dbName) throws SQLException {
        return connection.prepareStatement("DROP DATABASE IF EXISTS " + dbName).executeUpdate();
    }

    public int dropTable(String dbName) throws SQLException {
        return connection.prepareStatement("DROP TABLE IF EXISTS " + dbName).executeUpdate();
    }
}