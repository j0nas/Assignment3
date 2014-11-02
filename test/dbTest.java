import Configuration.DbConfig;
import Persistence.ConnectToDB;
import Persistence.DbUtils;
import Persistence.SetupDb;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class dbTest {
    Connection dbConnection;

    @Before
    public void setUp() throws Exception {
        dbConnection = new ConnectToDB(DbConfig.host, DbConfig.user, DbConfig.pass).getConnection();
        SetupDb.setUp(dbConnection);
        dbConnection = new ConnectToDB(DbConfig.host, DbConfig.dbName, DbConfig.user, DbConfig.pass).getConnection();
    }

    @Test
    public void testDbConnection() throws SQLException {
        assertFalse("Expects a valid and open connection to the specified database.", dbConnection.isClosed());
    }

    @Test
    public void testCanInsertIntoTable() throws SQLException {
        DbUtils utils = new DbUtils(dbConnection);
        assertTrue("The table should be written to.",
                utils.insertInto(DbConfig.tableName,
                        new String[]{"'tArtist'", "'tTitle'", "'tTrack'", "'tReleased'", "'tGenre'"}));

        assertTrue("The table should be written to.",
                utils.insertInto(DbConfig.tableName,
                        new String[]{"'tArtist2'", "'tTitle2'", "'tTrack2'", "'tReleased2'", "'tGenre2'"}));
    }
}
