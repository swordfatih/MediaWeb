package fr.mediaweb.persistance;

import mediatek2022.Utilisateur;

public class MediathequeUtilisateur implements Utilisateur {
    private final int id;
	private final String nom;
    private final String login;
    private final String mdp;
    private final String type;

    public MediathequeUtilisateur(int id, String nom, String login, String mdp, String type) {
        this.id = id;
    	this.nom = nom;
        this.login = login;
        this.mdp = mdp;
        this.type = type;
    }

    public int getID() {
        return id;
    }

    @Override
    public String name() {
        return nom;
    }
    
    @Override
    public boolean isBibliothecaire() {
        return type.equals("bibliothecaire");
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }
}
