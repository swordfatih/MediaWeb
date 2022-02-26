package fr.mediaweb.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import java.io.IOException;

@WebServlet(name = "Authentification", value = "/")
public class Authentification extends HttpServlet {
	static {
	    try {
			Class.forName("fr.mediaweb.persistance.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/authentification.jsp");
        view.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");

        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        
        Utilisateur utilisateur = Mediatheque.getInstance().getUser(login, mdp);
        
        if(utilisateur != null) {
        	RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/gestionnaire.jsp");
            view.forward(request, response);
        }
        
        request.setAttribute("erreur", true);
        
        doGet(request, response);
    }
}