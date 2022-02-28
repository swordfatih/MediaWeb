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
        final String req = "SELECT `id_d`, `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document;";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(req);

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
        final String req = "SELECT `id_u`, `nom_u`, `login`, `mdp`, `type_u` FROM utilisateur WHERE `login`=? AND `mdp`=?;";

        PreparedStatement stmt = conn.prepareStatement(req);
        stmt.setString(1, login);
        stmt.setString(2, password);

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
    public Document getDocument(int numDocument) throws SQLException {
        final String req = "SELECT `id_d`, `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document WHERE `id_d`=?;";

        PreparedStatement stmt = conn.prepareStatement(req);
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
        final String req = "INSERT INTO document (`titre_d`, `auteur_d`, `type_d`) VALUES (?, ?, ?);";

        PreparedStatement stmt = conn.prepareStatement(req);
        stmt.setString(1, (String) args[0]);
        stmt.setString(2, (String) args[1]);
        stmt.setString(3, (String) args[2]);

        stmt.execute();
        stmt.close();
    }

    @Override
    public void emprunterDocument(int numDocument, String nomUtilisateur) throws SQLException {
        String req = "UPDATE document, utilisateur SET `emprunt_d`=`id_u` WHERE `nom_u`=? AND `id_d`=?;";

        PreparedStatement stmt = conn.prepareStatement(req);
        stmt.setString(1, nomUtilisateur);
        stmt.setInt(2, numDocument);

        stmt.execute();
        stmt.close();
    }

    @Override
    public void retournerDocument(int numDocument) throws SQLException {
        String req = "UPDATE document SET `emprunt_d`=-1 WHERE `id_d`=?;";

        PreparedStatement stmt = conn.prepareStatement(req);
        stmt.setInt(1, numDocument);

        stmt.execute();
        stmt.close();
    }
}
