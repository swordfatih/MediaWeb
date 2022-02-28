package fr.mediaweb.persistance;

import java.sql.SQLException;
import java.util.List;

import fr.mediaweb.database.MySQLManager;
import mediatek2022.*;

// classe mono-instance dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	static { // Initialisation de mediatheque
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private DataManager bdd;

	private MediathequeData() {
		bdd = new MySQLManager();
	}

	// renvoie la liste de tous les documents disponibles de la m�diath�que
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		try {
			return bdd.tousLesDocumentsDisponibles();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		try {
			return bdd.getUser(login, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		try {
			return bdd.getDocument(numDocument);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		try {
			bdd.ajoutDocument(type, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
