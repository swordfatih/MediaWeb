package fr.mediaweb.database;

import fr.mediaweb.persistance.DataManager;
import fr.mediaweb.persistance.MediathequeDocument;
import fr.mediaweb.persistance.MediathequeUtilisateur;
import mediatek2022.Document;
import mediatek2022.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLManager implements DataManager {
    private static final String url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
    private static final String user = "vmvo1438_mediaweb";
    private static final String password = "mediaweb4568";

    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Document> tousLesDocumentsDisponibles() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT `id_d`, `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document;");

        List<Document> documents = new ArrayList<>();
        while (res.next()) documents.add(new MediathequeDocument(res.getInt(1), res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getInt(5),
                res.getString(6)));

        res.close();
        stmt.close();

        return documents;
    }

    @Override
    public Utilisateur getUser(String login, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT `id_u`, `nom_u`, `login`, `mdp`, `type_u` FROM utilisateur WHERE `login`=? AND `mdp`=?;");
        stmt.setString(1, login);
        stmt.setString(2, password);

        return getUtilisateur(stmt);
    }

    @Override
    public Utilisateur getUser(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT `id_u`, `nom_u`, `login`, `mdp`, `type_u` FROM utilisateur WHERE `id_u`=?;");
        stmt.setInt(1, id);

        return getUtilisateur(stmt);
    }

    private Utilisateur getUtilisateur(PreparedStatement stmt) throws SQLException {
        ResultSet res = stmt.executeQuery();

        MediathequeUtilisateur utilisateur = res.next() ? new MediathequeUtilisateur(res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5)) : null;

        res.close();
        stmt.close();

        return utilisateur;
    }

    @Override
    public Integer getUserID(String nomUtilisateur) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT `id_u` FROM utilisateur WHERE `nom_u`=?;");
        stmt.setString(1, nomUtilisateur);

        ResultSet res = stmt.executeQuery();

        Integer id = res.next() ? res.getInt(1) : null;

        res.close();
        stmt.close();

        return id;
    }

    @Override
    public Document getDocument(int numDocument) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT `id_d`, `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document WHERE `id_d`=?;");
        stmt.setInt(1, numDocument);

        ResultSet res = stmt.executeQuery();

        MediathequeDocument document = res.next() ? new MediathequeDocument(res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getInt(5),
                res.getString(6)) : null;

        res.close();
        stmt.close();

        return document;
    }

    @Override
    public void ajoutDocument(int type, Object... args) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO document (`titre_d`, `auteur_d`, `type_d`) VALUES (?, ?, ?);");
        stmt.setString(1, (String) args[0]);
        stmt.setString(2, (String) args[1]);
        stmt.setString(3, (String) args[2]);

        stmt.execute();
        stmt.close();
    }

    @Override
    public int emprunterDocument(int numDocument, String nomUtilisateur) throws SQLException {
        int id_u = getUserID(nomUtilisateur);

        // UPDATE document, utilisateur SET `emprunt_d`=`id_u` WHERE `nom_u`=? AND `id_d`=?;

        PreparedStatement stmt = conn.prepareStatement("UPDATE documen SET `emprunt_d`=? WHERE `id_d`=?;");
        stmt.setInt(1, id_u);
        stmt.setInt(2, numDocument);

        stmt.execute();
        stmt.close();

        return id_u;
    }

    @Override
    public void retournerDocument(int numDocument) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE document SET `emprunt_d`=-1 WHERE `id_d`=?;");
        stmt.setInt(1, numDocument);

        stmt.execute();
        stmt.close();
    }
}
