package fr.mediaweb;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "MediaWeb", value = "/")
public class MediaWeb extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public static ArrayList<String> connect() throws SQLException, ClassNotFoundException {
        String url, user, password, req1;

        Class.forName("com.mysql.cj.jdbc.Driver");

        url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
        user = "vmvo1438_mediaweb";
        password = "mediaweb4568";
        Connection conn = DriverManager.getConnection(url, user, password);

        ArrayList<String> utilisateurs = new ArrayList<>();
        req1 = "SELECT `login` FROM utilisateur";
        Statement st1 = conn.createStatement();
        ResultSet RS1 = st1.executeQuery(req1);

        while(RS1.next()) {
            String login = RS1.getString(1);
            utilisateurs.add(login);
        }

        RS1.close();
        st1.close();

        return utilisateurs;
    }

	protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");

        try {
            request.setAttribute("title", String.join(", ", connect()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("nom", request.getParameter("nom"));

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/accueil.jsp");
        view.forward(request, response);
    }
}
