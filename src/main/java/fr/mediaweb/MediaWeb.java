package fr.mediaweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "MediaWeb", value = "/")
public class MediaWeb extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");

	    request.setAttribute("title", "bonjour jeff");
        request.setAttribute("nom", request.getParameter("nom"));

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/accueil.jsp");
        view.forward(request, response);
    }
}
