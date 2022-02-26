package fr.mediaweb.persistance;

import mediatek2022.Utilisateur;

public class MediathequeUtilisateur implements Utilisateur {
    private String nom;
    private String login;
    private String mdp;
    private String type;

    public MediathequeUtilisateur(String nom, String login, String mdp, String type) {
        this.nom = nom;
        this.login = login;
        this.mdp = mdp;
        this.type = type;
    }

    @Override
    public String name() {
        return nom;
    }

    @Override
    public boolean isBibliothecaire() {
        return type == "bibliothecaire";
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }
}
