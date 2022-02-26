package fr.mediaweb.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class MediathequeDocument implements Document {
	private int id;
    private String titre;
    private String auteur;
    private String type;
    private int emprunt;
    private String options;

    public MediathequeDocument(int id, String titre, String auteur, String type, int emprunt, String options) {
        this.id = id;
    	this.titre = titre;
        this.auteur = auteur;
        this.type = type;
        this.emprunt = emprunt;
        this.options = options;
    }

    @Override
    public boolean disponible() {
        return emprunt == -1;
    }

    @Override
    public void emprunt(Utilisateur utilisateur) throws Exception {
    	String url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
    	String user = "vmvo1438_mediaweb";
    	String password = "mediaweb4568";

    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection conn = DriverManager.getConnection(url, user, password);
    	
		String req = "UPDATE document, utilisateur SET `emprunt_d`=`id_u` WHERE `nom_u`=? AND `id_d`=?;";
		
		PreparedStatement stmt = conn.prepareStatement(req);
		stmt.setString(1, utilisateur.name());
		stmt.setInt(2, id);
		
		stmt.execute();

		stmt.close();
    }

    @Override
    public void retour() {
    	String url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
    	String user = "vmvo1438_mediaweb";
    	String password = "mediaweb4568";

    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection conn = DriverManager.getConnection(url, user, password);
	    	
			String req = "UPDATE document SET `emprunt_d`=-1 WHERE `id_d`=?;";
			
			PreparedStatement stmt = conn.prepareStatement(req);
			stmt.setInt(1, id);
			
			stmt.execute();

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
