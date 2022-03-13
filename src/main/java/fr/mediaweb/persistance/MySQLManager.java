package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLManager implements DataManager {
    private static final String URL = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
    private static final String USER = "vmvo1438_mediaweb";
    private static final String PASSWORD = "mediaweb4568";

    private static final String QUERY_TOUS_DOCUMENTS = "SELECT `id_d`, `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document;";
    private static final String QUERY_RECUPERER_USER = "SELECT `id_u`, `nom_u`, `type_u` FROM utilisateur WHERE `login`=? AND `mdp`=?;";
    private static final String QUERY_RECUPERER_USER_ID = "SELECT `id_u` FROM utilisateur WHERE `nom_u`=?;";
    private static final String QUERY_RECUPERER_DOCUMENT = "SELECT `id_d`, `titre_d`, `auteur_d`, `type_d`, `emprunt_d`, `options_d` FROM document WHERE `id_d`=?;";
    private static final String QUERY_AJOUTER_DOCUMENT = "INSERT INTO document (`titre_d`, `auteur_d`, `type_d`) VALUES (?, ?, ?);";
    private static final String QUERY_EMPRUNTER_DOCUMENT = "UPDATE document SET `emprunt_d`=? WHERE `id_d`=?;";
    private static final String QUERY_RETOURNER_DOCUMENT = "UPDATE document SET `emprunt_d`=-1 WHERE `id_d`=?;";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public List<Document> tousLesDocumentsDisponibles() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(QUERY_TOUS_DOCUMENTS);) {

            List<Document> documents = new ArrayList<>();

            while (res.next()) {
                documents.add(new MediathequeDocument(res.getInt(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getInt(5),
                    res.getString(6)));
            }

            return documents;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Utilisateur getUser(String login, String password) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_RECUPERER_USER);) {
            stmt.setString(1, login);
            stmt.setString(2, password);

            try (ResultSet res = stmt.executeQuery();) {
                return res.next() ? new MediathequeUtilisateur(res.getInt(1),
                    res.getString(2),
                    res.getString(3)) : null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    private Integer getUserID(String nomUtilisateur) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_RECUPERER_USER_ID);) {
            stmt.setString(1, nomUtilisateur);

            try (ResultSet res = stmt.executeQuery();) {
                return res.next() ? res.getInt(1) : null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Document getDocument(int numDocument) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_RECUPERER_DOCUMENT);) {
            stmt.setInt(1, numDocument);

            try (ResultSet res = stmt.executeQuery();) {
                return res.next() ? new MediathequeDocument(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getInt(5),
                        res.getString(6)) : null;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void ajoutDocument(int type, Object... args) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_AJOUTER_DOCUMENT);) {
            stmt.setString(1, (String) args[0]);
            stmt.setString(2, (String) args[1]);
            stmt.setString(3, (String) args[2]);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int emprunterDocument(int numDocument, String nomUtilisateur) {
        int id_u = getUserID(nomUtilisateur);

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_EMPRUNTER_DOCUMENT);) {
            stmt.setInt(1, id_u);
            stmt.setInt(2, numDocument);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id_u;
    }

    @Override
    public void retournerDocument(int numDocument) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_RETOURNER_DOCUMENT);) {
            stmt.setInt(1, numDocument);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
