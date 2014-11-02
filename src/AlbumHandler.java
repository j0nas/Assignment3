import Configuration.DbConfig;
import Persistence.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumHandler {
    public static ArrayList<Album> getAlbums(DbUtils dbUtils, String genre) throws SQLException {
        ResultSet resultSet = dbUtils.executeQuery(String.format("SELECT * FROM %s WHERE %s = ?",
                DbConfig.tableName, DbConfig.genreColumnName), genre);

        ArrayList<Album> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(new Album(
                    resultSet.getString(DbConfig.titleColumnName),
                    resultSet.getString(DbConfig.artistColumnName),
                    resultSet.getString(DbConfig.genreColumnName),
                    resultSet.getInt(DbConfig.trackColumnName),
                    resultSet.getInt(DbConfig.releasedColumnName)
            ));
        }
        return results;
    }
}
