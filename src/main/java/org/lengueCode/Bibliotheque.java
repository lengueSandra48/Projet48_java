package org.lengueCode;

import org.lengueCode.daos.EmpruntDao;
import org.lengueCode.daos.LivreDao;
import org.lengueCode.daos.MembreDao;
import org.lengueCode.entites.Emprunt;
import org.lengueCode.entites.Livre;
import org.lengueCode.entites.Membre;
import org.lengueCode.enums.StatusEmprunt;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Stream;

public class Bibliotheque {
    public static void main(String[] args) {
        MembreDao membreDao = new MembreDao();
        LivreDao livreDao = new LivreDao();
        EmpruntDao empruntDao = new EmpruntDao();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichage du menu
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Inscrire un membre");
            System.out.println("4. Enregistrer un emprunt");
            System.out.println("5. Afficher les emprunts en retard");
            System.out.println("6. Quitter");
            System.out.print("Veuillez entrer votre choix : ");

            // Lecture du choix de l'utilisateur
            choice = scanner.nextInt();

            // Gestion des choix
            switch (choice) {
                case 1:
                    System.out.println("Option 1 sélectionnée : Ajouter un livre");
                    // code pour ajouter un livre

                    System.out.println("Entrez l'id du livre : ");
                    Long id = scanner.nextLong();

                    System.out.println("Entrez le titre du livre : ");
                    String titre = scanner.next();

                    System.out.println("Entrez l'auteur du livre :");
                    String auteur = scanner.next();

                    System.out.println("Entrez la categorie du livre");
                    String categorie = scanner.next();


                    System.out.println("Entrez le nombres d'exemplaires du livre");
                    int nombreExemplaires = scanner.nextInt();


                    Livre livre = new Livre(id, titre, auteur, categorie, nombreExemplaires);
                    livreDao.ajouterLivre(livre);
                    //System.out.println("Le livre a ete ajouter avec succes");


                    break;
                case 2:
                    System.out.print("Entrez le titre du livre à rechercher : ");
                    String titreLivre = scanner.next().trim();
                    Livre livreTrouve = livreDao.rechercherLivreParTitre(titreLivre);
                    if (livreTrouve != null) {
                        System.out.println("Livre trouvé : ");
                        System.out.println("Titre : " + livreTrouve.getTitre());
                        System.out.println("Auteur : " + livreTrouve.getAuteur());
                        System.out.println("Catégorie : " + livreTrouve.getCategorie());
                        System.out.println("Nombre d'exemplaires : " + livreTrouve.getNombreExemplaires());
                    } else {
                        System.out.println("Le livre est introuvable.");
                    }
                    break;

                case 3:
                    System.out.println("Option 3 sélectionnée : Inscrire un membre");
                    // code pour inscrire un membre
                    System.out.println("Entrez l'id du membre : ");
                    Long idMembre = scanner.nextLong();

                    System.out.println("Entrez le nom du membre :");
                    String nom = scanner.next();
                    System.out.println("Entrez le prenom du membre :");
                    String prenom = scanner.next();
                    System.out.println("Entrez l'email du membre :");
                    String email = scanner.next();
                    System.out.println("Entrez la date d'adhésion du membre (format: yyyy-MM-dd) : ");
                    String dateAdhesionInput = scanner.next().trim();
                    LocalDate dateAdhesion = LocalDate.parse(dateAdhesionInput);

                    Membre membre = new Membre(idMembre, nom, prenom, email, dateAdhesion);
                    membreDao.saveMember(membre);

                    break;
                case 4:
                    System.out.println("Option 4 sélectionnée : Enregistrer un emprunt");
                    // code pour enregistrer un emprunt
                    System.out.println("Entrez l'id de l'emprunt : ");
                    Long idEmprunt = scanner.nextLong();
                    System.out.println("Entrez l'id du membre qui souhaite effectuer l'emprunt : ");
                    Long membreid = scanner.nextLong();
                    System.out.println("Entrez l'id du livre : ");
                    Long livreid = scanner.nextLong();
                    System.out.println("Entrez la date de l'emprunt (format: yyyy-MM-dd) : ");
                    String dateEmpruntInput = scanner.next().trim();
                    LocalDate dateEmprunt = LocalDate.parse(dateEmpruntInput);
                    System.out.println("Entrez la date de retour prevu (format: yyyy-MM-dd) : ");
                    String dateRetourPrevInput = scanner.next().trim();
                    LocalDate dateRetourPrev = LocalDate.parse(dateRetourPrevInput);
                    System.out.println("Entrez la date de retour effective (format: yyyy-MM-dd) : ");
                    String dateRetourEffInput = scanner.next().trim();
                    LocalDate dateRetourEff = LocalDate.parse(dateRetourEffInput);
                    System.out.println("Entrez le status de l'emprunt : ");
                    StatusEmprunt statusEmprunt = StatusEmprunt.valueOf(scanner.next());

                    empruntDao.enregistrerEmprunt(new Emprunt(idEmprunt,dateEmprunt,dateRetourPrev,dateRetourEff,membreid,livreid,statusEmprunt));


                    break;
                case 5:

                    List<Emprunt> empruntsEnRetard = empruntDao.afficherEmpruntEnRetard();

                    if (empruntsEnRetard.isEmpty()) {
                        System.out.println("Aucun emprunt en retard.");
                    } else {
                        System.out.println("Liste des emprunts en retard :");
                        for (Emprunt emprunt : empruntsEnRetard) {
                            System.out.println("ID Emprunt : " + emprunt.getIdEmprunt());
                            System.out.println("ID Membre : " + emprunt.getMembreId());
                            System.out.println("ID Livre : " + emprunt.getLivreId());
                            System.out.println("Date d'emprunt : " + emprunt.getDateEmprunt());
                            System.out.println("Date de retour prévue : " + emprunt.getDateRetourPrev());
                            System.out.println("Statut : " + emprunt.getStatus());

                            // Calcul des jours de retard
                            long joursDeRetard = ChronoUnit.DAYS.between(emprunt.getDateRetourPrev(), LocalDate.now());
                            System.out.println("Jours de retard : " + joursDeRetard);

                            // Calcul de la pénalité (exemple : 100FCFA par jour de retard)
                            double penalite = joursDeRetard * 100;
                            System.out.println("Pénalité : " + penalite + " FCFA");
                            System.out.println("----------------------------");
                        }
                    }
                    break;

                case 6:
                    System.out.println("Quitter le programme...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

            System.out.println("######################################");
        } while (choice != 6);

        scanner.close();
    }

}