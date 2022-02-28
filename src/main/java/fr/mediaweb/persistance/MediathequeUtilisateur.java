package fr.mediaweb.persistance;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import java.util.stream.Collectors;

public class MediathequeUtilisateur implements Utilisateur {
    private final int id;
	private final String nom;
    private final String type;

    public MediathequeUtilisateur(int id, String nom, String type) {
        this.id = id;
    	this.nom = nom;
        this.type = type;
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
        Object[] donnees = new Object[1];

        donnees[0] = Mediatheque.getInstance().tousLesDocumentsDisponibles().stream().map(d -> (MediathequeDocument) d).filter(d -> d.getEmprunt() == id).collect(Collectors.toList());

        return donnees;
    }
}
