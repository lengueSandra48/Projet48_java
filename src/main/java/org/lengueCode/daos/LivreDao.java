package org.lengueCode.daos;

import org.lengueCode.Connection.DataBaseConnection;
import org.lengueCode.entites.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDao {
    Connection connection = DataBaseConnection.getConnection();
    //Ajouter un livre
    public void ajouterLivre(Livre livre){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM livre WHERE id = ?");
            statement.setLong(1, livre.getId());
            ResultSet rs = statement.executeQuery();
            rs.next();
            boolean livreExiste = rs.getInt(1) > 0;
            if (livreExiste){
                PreparedStatement statement1 = connection.prepareStatement(
                        "UPDATE livre SET nombre_exemplaires = ? WHERE id = ?");
                statement1.setInt(1, livre.getNombreExemplaires());
                statement1.setLong(2, livre.getId());

                int rowsUpdated = statement1.executeUpdate(); // Exécute la requête
                if (rowsUpdated > 0) {
                    System.out.println("Le nombre d'exemplaires du livre a été mis à jour.");
                } else {
                    System.out.println("Aucune mise à jour n'a été effectuée.");
                }
            }else {
                PreparedStatement statement1 = connection.prepareStatement(
                        "INSERT INTO livre(id, titre, auteur, categorie, nombre_exemplaires) VALUES(?, ?, ?, ?, ?)");
                statement1.setLong(1, livre.getId());
                statement1.setString(2, livre.getTitre());
                statement1.setString(3, livre.getAuteur());
                statement1.setString(4, livre.getCategorie());
                statement1.setInt(5, livre.getNombreExemplaires());

                int rowAffected = statement1.executeUpdate();
                if (rowAffected > 0){
                    System.out.println("Le livre est bien enregistrer dans la base de donnee");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Rechercher un livre par titre
    public Livre
    rechercherLivreParTitre(String titre){
        Livre livre = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM livre WHERE LOWER(titre) = LOWER(?)");
            statement.setString(1, titre);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Long id = resultSet.getLong("id");
                String titreLivre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String categorie = resultSet.getString("categorie");
                int nombreExemplaire = resultSet.getInt("nombre_exemplaires");

                livre = new Livre(id, titreLivre, auteur, categorie, nombreExemplaire);
            }else {
                System.out.println("Le livre est introuvable");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livre;
    }

    // Afficher tous les livres disponibles

    public List<Livre> afficherTousLesLivres(){

        List<Livre> livres = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM livre");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Livre livre = new Livre();
                Long id = resultSet.getLong("id");
                String titreLivre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String categorie = resultSet.getString("categorie");
                int nombreExemplaire = resultSet.getInt("nombre_exemplaires");

                livre = new Livre(id, titreLivre, auteur, categorie, nombreExemplaire);
                livres.add(livre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return livres;
    }
}
