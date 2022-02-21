package fr.mediaweb.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Gestionnaire", value = "/gest")
public class Gestionnaire extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/gestionnaire.jsp");
        view.forward(request, response);
    }
}
