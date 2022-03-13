package fr.mediaweb.persistance;

import java.util.List;

import mediatek2022.*;

// classe mono-instance dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	static {
		// Initialisation de mediatheque
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private final DataManager bdd;

	private MediathequeData() {
		bdd = new MySQLManager();
	}

	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		synchronized (bdd) {
			return bdd.tousLesDocumentsDisponibles();
		}
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		synchronized (bdd) {
			return bdd.getUser(login, password);
		}
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		synchronized (bdd) {
			return bdd.getDocument(numDocument);
		}
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		synchronized (bdd) {
			bdd.ajoutDocument(type, args);;
		}
	}
}
