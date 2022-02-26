package fr.mediaweb.persistance;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import mediatek2022.*;

// classe mono-instance dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	private static final String url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
	private static final String user = "vmvo1438_mediaweb";
	private static final String password = "mediaweb4568";
	private Connection conn;

	static {
		try {
			Mediatheque.getInstance().setData(new MediathequeData());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private MediathequeData() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.conn = DriverManager.getConnection(url, user, password);
	}

	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		List<Document> documents = new ArrayList<>();

		String req = "SELECT `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document;";

		try {
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(req);

			while (res.next()) {
				documents.add(new MediathequeDocument(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5)));
			}

			res.close();
			stmt.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return documents;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		MediathequeUtilisateur utilisateur = null;
		
		String req = "SELECT `nom_u`, `login`, `mdp`, `type_u` FROM utilisateur WHERE `login`=? AND `mdp`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(req);
			stmt.setString(1, login);
			stmt.setString(2, password);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next())
				utilisateur = new MediathequeUtilisateur(res.getString(1), res.getString(2), res.getString(3), res.getString(4));

			res.close();
			stmt.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return utilisateur;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		MediathequeDocument document = null;
		
		String req = "SELECT `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document WHERE `id_d`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(req);
			stmt.setInt(1, numDocument);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next())
				document = new MediathequeDocument(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5));

			res.close();
			stmt.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return document;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document

		String req = "INSERT INTO document ()`titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d`) VALUES (?, ?, ?, ?, ?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(req);
			stmt.setString(1, (String) args[0]);
			stmt.setString(2, (String) args[1]);
			stmt.setString(3, (String) args[2]);
			stmt.setString(4, (String) args[3]);
			stmt.setString(5, (String) args[4]);
			
			stmt.execute();
			
			stmt.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}
