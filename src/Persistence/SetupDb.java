package Persistence;

import Configuration.Config;
import Configuration.DbConfig;
import Parsing.CSVParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SetupDb {
    /**
     * Sets up the database and table if not already set up/existing.
     *
     * @param connection The connection to the DB host.
     * @throws Exception
     */
    public static void setUp(Connection connection) throws Exception {
        // Instantiate connection and convenience class.
        DbUtils dbUtils = new DbUtils(connection);
        CSVParser csvParser = new CSVParser(Config.getFilePath());

        if (dbUtils.dbExists(DbConfig.dbName) && dbUtils.tableExists(DbConfig.tableName)) {
            return;
        }

        // If set to drop db upon init, don't bother checking whether it exists.
        if (DbConfig.dropDbIfExistsWhenCreating) {
            dbUtils.dropDb(DbConfig.dbName);
        }

        if (DbConfig.dropDbIfExistsWhenCreating || !dbUtils.dbExists(DbConfig.dbName)) {
            dbUtils.createDb(DbConfig.dbName);
        }

        // Update pointers.
        connection = new ConnectToDB(DbConfig.host, DbConfig.dbName, DbConfig.user, DbConfig.pass).getConnection();
        dbUtils = new DbUtils(connection);

        if (!DbConfig.dropDbIfExistsWhenCreating &&
                DbConfig.dropTableIfExistsWhenCreating && dbUtils.tableExists(DbConfig.tableName)) {
            dbUtils.dropTable(DbConfig.tableName);
        }

        if (!dbUtils.tableExists(DbConfig.tableName)) {
            dbUtils.createTable(DbConfig.tableName, csvParser.getCsvHeaderColumns());
        }

        populateTable(DbConfig.tableName, dbUtils, csvParser);
    }

    private static void populateTable(String tableName, DbUtils dbUtils, CSVParser csv) throws SQLException {
        for (String csvLine : csv.getCsvFileContent()) {
            ArrayList<String> items = new ArrayList<>();
            Arrays.asList(csvLine.split(",")).forEach(item -> items.add(String.format("'%s'", item.replace("'", "\\'"))));
            dbUtils.insertInto(tableName, items.toArray(new String[items.size()]));
        }
    }
}
