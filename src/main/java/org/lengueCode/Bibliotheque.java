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

public class Bibliotheque {
    public static void main(String[] args) {
        MembreDao membreDao = new MembreDao();
        LivreDao livreDao = new LivreDao();
        EmpruntDao empruntDao = new EmpruntDao();

        Membre membre1 = new Membre(109876L, "Hassan", "Salif", "hassansalif@gmail.com", LocalDate.now());
        Livre livre1 = new Livre(12345L, "Java programming", "Bande de codeurs", "Learning", 15);
        Livre livre2 = new Livre(12346L, "python programming", "Zone code", "Learning", 55);
        Livre livre3 = new Livre(12347L, "JavaScript programming", "Pierre Simo", "Learning", 20);
        Emprunt emprunt = new Emprunt(1234L, LocalDate.now(), LocalDate.of(2025, 1, 25), LocalDate.of(2025,2, 06), 109876L, 12347L, StatusEmprunt.EN_COURS );
        Emprunt emprunt1 = new Emprunt(1235L, LocalDate.now(), LocalDate.of(2025, 1, 25), null, 109876L, 12346L, StatusEmprunt.EN_COURS );

        empruntDao.enregistrerEmprunt(emprunt1);
        //livreDao.ajouterLivre(livre1);
        //livreDao.ajouterLivre(livre2);
        //livreDao.ajouterLivre(livre3);

        //System.out.println(livreDao.afficherTousLesLivres());


        //membreDao.saveMember(membre1);
        //membreDao.rechercherMembreParNom("Lengue");

        //System.out.println(membreDao.rechercherMembreParNom("Hassan"));
        //membreDao.supprimerMembre(109876L);
        //System.out.println(membreDao.rechercherMembreParNom("Hassan"));



    }
}