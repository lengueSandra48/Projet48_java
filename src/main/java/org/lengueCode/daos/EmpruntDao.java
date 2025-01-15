package org.lengueCode.daos;

import org.lengueCode.Connection.DataBaseConnection;
import org.lengueCode.entites.Emprunt;
import org.lengueCode.enums.StatusEmprunt;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDao {
    Connection connection = DataBaseConnection.getConnection();

    //Enregistrer un emprunt
    public void enregistrerEmprunt(Emprunt emprunt){
        try {
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM emprunt where id_emprunt = ?");
            checkStatement.setLong(1, emprunt.getIdEmprunt());
            ResultSet rs = checkStatement.executeQuery();
            rs.next();
            boolean empruntExiste = rs.getInt(1) > 0;

            if (empruntExiste){
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE emprunt SET status = ? WHERE id_emprunt = ?");
                statement.setString(1, emprunt.getStatus().name() );
                statement.setLong(2, emprunt.getIdEmprunt());

                int rowUpdated = statement.executeUpdate();
                if (rowUpdated > 0){
                    System.out.println("Le status est modifier avec succes");

                }else {
                    System.out.println("echec de la modification du status");
                }
            }else {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO emprunt(id_emprunt, membre_id, livre_id, date_emprunt, date_retour_prev, date_retour_eff, status) VALUES(?, ?, ?, ?, ?, ?, ?)");
                statement.setLong(1, emprunt.getIdEmprunt());
                statement.setLong(2, emprunt.getMembreId());
                statement.setLong(3, emprunt.getLivreId());
                statement.setDate(4, Date.valueOf(emprunt.getDateEmprunt()));
                statement.setDate(5, Date.valueOf(emprunt.getDateRetourPrev()));
                if (emprunt.getDateRetourEff() != null) {
                    statement.setDate(6, Date.valueOf(emprunt.getDateRetourEff()));
                } else {
                    statement.setNull(6, java.sql.Types.DATE); // Définit la valeur NULL pour la base de données
                }

                statement.setString(7, emprunt.getStatus().name());

                int rowAffected = statement.executeUpdate();
                if (rowAffected > 0 ){
                    System.out.println("L'emprunt est bien enregistrer dans la base de donnees.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Emprunt> afficherEmpruntEnRetard() {
        List<Emprunt> empruntsEnRetard = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM emprunt WHERE date_retour_eff IS NULL");
           // statement.setDate(1, Date.valueOf(LocalDate.now())); // Compare avec la date actuelle
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Emprunt emprunt = new Emprunt();
                emprunt.setIdEmprunt(rs.getLong("id_emprunt"));
                emprunt.setMembreId(rs.getLong("membre_id"));
                emprunt.setLivreId(rs.getLong("livre_id"));
                emprunt.setDateEmprunt(rs.getDate("date_emprunt").toLocalDate());
                emprunt.setDateRetourPrev(rs.getDate("date_retour_prev").toLocalDate());
                if (rs.getDate("date_retour_eff") != null) {
                    emprunt.setDateRetourEff(rs.getDate("date_retour_eff").toLocalDate());
                }
                emprunt.setStatus(StatusEmprunt.valueOf(rs.getString("status")));
                empruntsEnRetard.add(emprunt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empruntsEnRetard;
    }
}
