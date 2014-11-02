import Configuration.DbConfig;
import Persistence.ConnectToDB;
import Persistence.DbUtils;
import Persistence.SetupDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet("/")
public class MusicChoiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection dbConnection;
        try {
            dbConnection = new ConnectToDB().getConnection();
            SetupDb.setUp(dbConnection);
            // Update pointer after setup.
            dbConnection = new ConnectToDB(DbConfig.host, DbConfig.dbName, DbConfig.user, DbConfig.pass).getConnection();

            DbUtils dbUtils = new DbUtils(dbConnection);
            ResultSet resultSet = dbUtils.executeQuery(String.format("SELECT DISTINCT %s FROM %s",
                    DbConfig.genreColumnName, DbConfig.tableName));

            ArrayList<String> genres = new ArrayList<>();
            while (resultSet.next()) {
                genres.add(resultSet.getString(1));
            }

            request.setAttribute("genres", genres);
            RequestDispatcher view = request.getRequestDispatcher("musicchoice.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
