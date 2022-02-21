package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class MediathequeDocument implements Document {
    private String titre;
    private String auteur;
    private String type;
    private int emprunt;
    private String options;

    public MediathequeDocument(String titre, String auteur, String type, int emprunt, String options) {
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

    }

    @Override
    public void retour() {

    }
}
