package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

import java.util.List;

public interface DataManager {
    List<Document> tousLesDocumentsDisponibles();
    Utilisateur getUser(String login, String password);
    Document getDocument(int numDocument);
    void ajoutDocument(int type, Object... args);
    int emprunterDocument(int numDocument, String nomUtilisateur);
    void retournerDocument(int numDocument);
}
