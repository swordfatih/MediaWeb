package fr.mediaweb.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import java.io.IOException;

@WebServlet(name = "Gestionnaire", value = "/gest")
public class Gestionnaire extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
    	
    	if(utilisateur == null) {
    		response.sendRedirect("/MediaWeb/");
        	return;
    	}
    	
        response.setContentType("text/html");
        
        request.setAttribute("nom_u", utilisateur.name());
        
        RequestDispatcher view = request.getRequestDispatcher("view/" + (utilisateur.isBibliothecaire() ? "bibliothecaire" : "abonne") + ".jsp");
        view.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	if(request.getParameter("deconnexion") != null) {
    		request.getSession().setAttribute("utilisateur", null);
    	} else if(request.getParameter("ajouter") != null) {
			try {
				Mediatheque.getInstance().ajoutDocument(0, request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("type"));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(request.getParameter("emprunt") != null) {
    		try {
    			Document document = Mediatheque.getInstance().getDocument(Integer.parseInt(request.getParameter("id_d")));
				Mediatheque.getInstance().emprunt(document, (Utilisateur) request.getSession().getAttribute("utilisateur"));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(request.getParameter("retour") != null) { 
			try {
				Document document = Mediatheque.getInstance().getDocument(Integer.parseInt(request.getParameter("id_d")));
				Mediatheque.getInstance().retour(document, (Utilisateur) request.getSession().getAttribute("utilisateur"));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	doGet(request, response);
    }
}
