package fr.mediaweb.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Authentification", value = "/")
public class Authentification extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");

        // request.setAttribute("nom", request.getParameter("nom"));

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/authentification.jsp");
        view.forward(request, response);
    }
}


/*
    public static ArrayList<String> connect() throws SQLException, ClassNotFoundException {


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
 */