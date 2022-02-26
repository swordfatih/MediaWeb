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
        response.setContentType("text/html");
        
        MediathequeUtilisateur utilisateur = (MediathequeUtilisateur) request.getSession().getAttribute("utilisateur");
        request.setAttribute("nom_u", utilisateur.name());
        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/" + (utilisateur.isBibliothecaire() ? "bibliothecaire" : "abonne") + ".jsp");
        view.forward(request, response);
    }
}
