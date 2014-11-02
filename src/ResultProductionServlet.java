import Configuration.Config;
import Configuration.DbConfig;
import Persistence.ConnectToDB;
import Persistence.DbUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(Config.generatedResultsServlet)
public class ResultProductionServlet extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection dbConnection = new ConnectToDB(DbConfig.host, DbConfig.dbName,
                    DbConfig.user, DbConfig.pass).getConnection();
            DbUtils dbUtils = new DbUtils(dbConnection);

            request.setAttribute("albums", AlbumHandler.getAlbums(dbUtils, request.getParameter("genre")));
            RequestDispatcher view = request.getRequestDispatcher(Config.generatedResultsJsp);
            view.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
