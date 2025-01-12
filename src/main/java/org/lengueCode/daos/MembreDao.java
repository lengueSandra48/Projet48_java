package org.lengueCode.daos;

import org.lengueCode.Connection.DataBaseConnection;
import org.lengueCode.entites.Livre;
import org.lengueCode.entites.Membre;

import java.sql.*;
import java.time.LocalDate;

public class MembreDao {
    //Etablir la connection avec la base de donnees
    Connection connection = DataBaseConnection.getConnection();

    //Sauvegarder un membre dans la base de donnees
    public void saveMember(Membre membre){
        try {
            PreparedStatement checkedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM membre WHERE id = ?");
            checkedStatement.setLong(1, membre.getId());
            ResultSet rs = checkedStatement.executeQuery();
            rs.next();
            boolean membreExists = rs.getInt(1) > 0;
            PreparedStatement statement;

            if (membreExists){
                statement = connection.prepareStatement(
                        "UPDATE membre SET nom = ? WHERE id = ?");

                statement.setString(1, membre.getNom());
                statement.setLong(2, membre.getId());

                int rowUpdated = statement.executeUpdate();
                if (rowUpdated > 0){
                    System.out.println("le nom a ete mise a jour avec succes");
                }else {
                    System.out.println("Echec de la mise a jour");
                }
            }else {
                statement = connection.prepareStatement(
                        "INSERT INTO membre(id, nom, prenom, email, date_adhesion) VALUES(?, ?, ?, ?, ?)");
                statement.setLong(1, membre.getId());
                statement.setString(2,membre.getNom());
                statement.setString(3, membre.getPrenom());
                statement.setString(4, membre.getEmail());
                statement.setDate(5, Date.valueOf(membre.getDateAdhesion()));

            }
            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0){
                System.out.println(membre.getNom()+" est inserer dans base de donnees ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rechercher un membre par son nom
    public Membre rechercherMembreParNom(String nom){
        Membre membre = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM membre WHERE nom = ?");
            statement.setString(1, nom);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                Long id = rs.getLong("id");
                String nomMembre = rs.getString("nom");
                String prenomMembre = rs.getString("prenom");
                String emailMembre = rs.getString("email");
                LocalDate dateAdhesion = rs.getDate("date_adhesion").toLocalDate();


                membre = new Membre(id, nomMembre, prenomMembre, emailMembre, dateAdhesion);

            }else {
                System.out.println("Aucun membre trouver avec le nom : "+ nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membre;
    }

    //Supprimer un membre par son id
    public void supprimerMembre(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM membre WHERE id = ?");
            statement.setLong(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Le membre avec l'id " + id + " a été supprimé avec succès.");
            } else {
                System.out.println("Aucun membre trouvé avec l'id " + id + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
