package fr.mediaweb.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import fr.mediaweb.persistance.MediathequeUtilisateur;

import java.io.IOException;

@WebServlet(name = "Gestionnaire", value = "/gest")
public class Gestionnaire extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	MediathequeUtilisateur utilisateur = (MediathequeUtilisateur) request.getSession().getAttribute("utilisateur");
    	
    	if(utilisateur == null) {
    		response.sendRedirect("/MediaWeb/");
        	return;
    	}
    	
        response.setContentType("text/html");
        
        request.setAttribute("nom_u", utilisateur.name());
        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/" + (utilisateur.isBibliothecaire() ? "bibliothecaire" : "abonne") + ".jsp");
        view.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	if(request.getParameter("deconnexion") != null) {
    		request.getSession().setAttribute("utilisateur", null);
    	}
    	
    	doGet(request, response);
    }
}
