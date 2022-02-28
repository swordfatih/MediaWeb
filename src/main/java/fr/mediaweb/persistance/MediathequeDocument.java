package fr.mediaweb.persistance;

import fr.mediaweb.database.MySQLManager;
import mediatek2022.Document;
import mediatek2022.Utilisateur;

import java.sql.SQLException;

public class MediathequeDocument implements Document {
	private final DataManager bdd;

	private final int id;
    private final String titre;
    private final String auteur;
    private final String type;
    private int emprunt;
    private final String options;

    public MediathequeDocument(int id, String titre, String auteur, String type, int emprunt, String options) {
		this.bdd = new MySQLManager();

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
		if(!disponible())
			throw new MediathequeException("Ce document est déjà emprunté");

		this.emprunt = bdd.emprunterDocument(this.id, utilisateur.name());
    }

    @Override
    public void retour() {
		try {
			bdd.retournerDocument(this.id);
            this.emprunt = -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
