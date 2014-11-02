package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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

    public int dropDb(String dbName) throws SQLException {
        return connection.prepareStatement("DROP DATABASE IF EXISTS " + dbName).executeUpdate();
    }

    public int dropTable(String dbName) throws SQLException {
        return connection.prepareStatement("DROP TABLE IF EXISTS " + dbName).executeUpdate();
    }
}
