package appmovie_getdatas.dao;

import models.Film;
import models.jsonschema.beans.Acteur;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de Test Dao
 */
class DaoTest {

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("movie-database-getdatas");
    private final EntityManager em = factory.createEntityManager();

    /**
     * Methode de Test de récupération de la filmographie d'un acteur
     */
    @org.junit.jupiter.api.Test
    void getFilmoActeurTest() {

        TypedQuery<Film> query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.lstActeur a WHERE a.nom = :nom", Film.class);
        query.setParameter("nom", "Fred Thompson");
        List<Film> films = query.getResultList();

        assertEquals(3, films.size());
    }

    /**
     * Methode de Test de récupération du casting d'un film
     */
    @org.junit.jupiter.api.Test
    void getFilmCastingTest() {
        TypedQuery<Acteur> query = em.createQuery( "SELECT DISTINCT a FROM Acteur a JOIN a.lstFilm f WHERE f.nom = :nom", Acteur.class);
        query.setParameter("nom", "Garfield");
        List<Acteur> lstActeur= query.getResultList();

        assertEquals(3, lstActeur.size());
    }

    /**
     * Methode de Test de récupération des films sortis entre 2 années données
     */
    @org.junit.jupiter.api.Test
    void filmAnneesTest() {
        TypedQuery<Film> query = em.createQuery("SELECT DISTINCT f FROM Film f  WHERE f.anneeSortie BETWEEN :anneeDebut AND :anneeFin", Film.class);
        query.setParameter("anneeDebut", "2021");
        query.setParameter("anneeFin", "2023");
        List<Film> lstFilm = query.getResultList();

        assertEquals(3, lstFilm.size());
    }

    /**
     * Methode de Test de récupération des films communs à 2 acteurs/actrices donnés
     */
    @org.junit.jupiter.api.Test
    void filmCommunActeurTest() {
        String str = "SELECT DISTINCT f FROM Film f JOIN f.lstActeur a WHERE a.nom = :acteurPremier";
        TypedQuery<Film> queryActeurPremier = em.createQuery(str, Film.class);
        queryActeurPremier.setParameter("acteurPremier", "Samuel L. Jackson");
        List<Film> lstActeurPremier= queryActeurPremier.getResultList();

        str = "SELECT DISTINCT f FROM Film f JOIN f.lstActeur a WHERE a.nom = :acteurSecond";
        TypedQuery<Film> queryActeurSecond = em.createQuery(str, Film.class);
        queryActeurSecond.setParameter("acteurSecond", "Chris Pratt");
        List<Film> lstActeurSecond = queryActeurSecond.getResultList();

        List<Film> lstFinal = new ArrayList<>();
        for (Film filmPr : lstActeurPremier){
            for(Film filmSd : lstActeurSecond){
                if(filmPr.getNom().equals(filmSd.getNom())){
                   lstFinal.add(filmPr);
                }
            }
        }

        assertEquals(1, lstFinal.size());
    }

    /**
     * Methode de Test de récupération des acteurs communs à 2 films donnés
     */
    @org.junit.jupiter.api.Test
    void acteurCommunFilmTest() {
        String str = "SELECT DISTINCT a FROM Acteur a JOIN a.lstFilm f WHERE f.nom = :filmPremier";
        TypedQuery<Acteur> queryFilmPremier = em.createQuery(str, Acteur.class);
        queryFilmPremier.setParameter("filmPremier", "Garfield");
        List<Acteur> lstFilmPremier= queryFilmPremier.getResultList();

        str = "SELECT DISTINCT a FROM Acteur a JOIN a.lstFilm f WHERE f.nom =  :filmSecond";
        TypedQuery<Acteur> queryFilmSecond = em.createQuery(str, Acteur.class);
        queryFilmSecond.setParameter("filmSecond", "Cowboy Ninja Viking");
        List<Acteur> lstFilmSecond= queryFilmSecond.getResultList();

        List<Acteur> lstFinal = new ArrayList<>();
        for (Acteur acteur : lstFilmPremier){
            for(Acteur acteur1 : lstFilmSecond){
                if(acteur.getNom().equals(acteur1.getNom())){
                    lstFinal.add(acteur);
                }
            }
        }

        assertEquals(2, lstFinal.size());
    }

    /**
     * Methode de Test de récupération des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting
     */
    @org.junit.jupiter.api.Test
    void filmAnneesActeur() {
        String str = "SELECT DISTINCT f FROM Film f JOIN f.lstCastingP a WHERE a.nom = :acteur AND f.anneeSortie BETWEEN :anneeDebut AND :anneeFin " ;
        TypedQuery<Film> query = em.createQuery(str, Film.class);
        query.setParameter("acteur", "Fred Thompson");
        query.setParameter("anneeDebut", "1890");
        query.setParameter("anneeFin", "2022");
        List<Film> lstFilm = query.getResultList();

        assertEquals(3, lstFilm.size());

    }
}