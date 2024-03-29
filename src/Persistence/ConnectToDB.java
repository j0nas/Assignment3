package Persistence;

import Configuration.DbConfig;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectToDB {
    private Connection connection;
    private MysqlDataSource dataSource = new MysqlDataSource();

    public ConnectToDB(String hostname, String username, String password) {
        dataSource.setServerName(hostname);
        dataSource.setUser(username);
        dataSource.setPassword(password);
    }

    public ConnectToDB() {
        this(DbConfig.host, DbConfig.user, DbConfig.pass);
    }

    public ConnectToDB(String hostname, String dbName, String username, String password) {
        this(hostname, username, password);
        dataSource.setDatabaseName(dbName);
    }


    public Connection getConnection() throws SQLException {
        if (!(this.connection != null && !this.connection.isClosed())) {
            this.connection = this.dataSource.getConnection();
        }

        return this.connection;
    }
}
