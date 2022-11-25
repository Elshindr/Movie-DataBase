package appmovie_getdatas.dao;

import appmovie_getdatas.services.Menu;
import models.Film;
import models.jsonschema.beans.Acteur;

import javax.persistence.*;
import java.util.List;

/**
 * Class Dao
 * Gestion des requetes JPQL vers la base de données
 */
public class Dao {

    private final EntityManager em;
    private static Dao instance = null;

    /**
     * Get one instance dao.
     *
     * @return the dao instance
     */
    public static Dao getInstance(){
        if(Dao.instance == null){
            Dao.instance = new Dao();
        }
        return Dao.instance;
    }

    private Dao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("movie-database-getdatas");
        this.em = factory.createEntityManager();
    }


    /**
     * Methode de Récupération de la filmographie d'un acteur donnée
     * Envoie d'un requete JPQL
     * @param nomActeur the nom acteur
     * @param em        the em
     */
    public static void getFilmoActeur(String nomActeur, EntityManager em){
        String str = "SELECT DISTINCT f FROM Film f JOIN f.lstActeur a WHERE a.nom = :nom";
        TypedQuery<Film> query = em.createQuery(str, Film.class);
        query.setParameter("nom", nomActeur);
        List<Film> lstFilm= query.getResultList();

        Menu.afficherResultFilm(lstFilm);
    }

    /**
     * Methode de Récupération des acteurs d'un film donnée
     * Envoie d'un requete JPQL
     * @param nomFilm the nom film
     * @param em      the em
     */
    public static void getFilmCasting(String nomFilm, EntityManager em){
        String str = "SELECT DISTINCT a FROM Acteur a JOIN a.lstFilm f WHERE f.nom = :nom";
        TypedQuery<Acteur> query = em.createQuery(str, Acteur.class);
        query.setParameter("nom", nomFilm);
        List<Acteur> lstActeur= query.getResultList();

        if(lstActeur.isEmpty()){
            Menu.errorMenu("2", nomFilm);
        }
        else{
            for (Acteur acteur : lstActeur){
                System.out.println(acteur.getNom());
            }
        }
    }

    /**
     * Film annees.
     *
     * @param lstAnnees the lst annees
     * @param em        the em
     */
    public static void filmAnnees(List<String> lstAnnees, EntityManager em){

        String str = "SELECT DISTINCT f FROM Film f  WHERE f.anneeSortie BETWEEN :anneeDebut AND :anneeFin" ;
        TypedQuery<Film> query = em.createQuery(str, Film.class);
        query.setParameter("anneeDebut", lstAnnees.get(0));
        query.setParameter("anneeFin", lstAnnees.get(1));
        List<Film> lstFilm= query.getResultList();

        if(lstFilm.isEmpty()){
            Menu.errorMenu("3", lstAnnees.toString());
        }
        else{
            for (Film film : lstFilm){
                System.out.println(film.getNom() + " (" + film.getAnneeSortie()+")");
            }
        }
    }

    /**
     * Methode de Récupération des films ayant en commun deux acteurs donnés
     * Envoie d'un requete JPQL
     * @param lstActeur the lst acteur
     * @param em        the em
     */
    public static void filmCommunActeur(List<String> lstActeur, EntityManager em){
        String str = "SELECT DISTINCT f FROM Film f JOIN f.lstActeur a WHERE a.nom = :acteurPremier";
        TypedQuery<Film> queryActeurPremier = em.createQuery(str, Film.class);
        queryActeurPremier.setParameter("acteurPremier", lstActeur.get(0));
        List<Film> lstActeurPremier= queryActeurPremier.getResultList();

        str = "SELECT DISTINCT f FROM Film f JOIN f.lstActeur a WHERE a.nom = :acteurSecond";
        TypedQuery<Film> queryActeurSecond = em.createQuery(str, Film.class);
        queryActeurSecond.setParameter("acteurSecond", lstActeur.get(1));
        List<Film> lstActeurSecond= queryActeurSecond.getResultList();

        if(lstActeurPremier.isEmpty() || lstActeurSecond.isEmpty()){
            Menu.errorMenu("4", lstActeur.toString());
        }
        else{
            for (Film filmPr : lstActeurPremier){
                for(Film filmSd : lstActeurSecond){
                    if(filmPr.getNom().equals(filmSd.getNom())){
                        System.out.println(filmSd.getNom() + " (" + filmSd.getAnneeSortie()+")");
                    }
                }

            }
        }
    }

    /**
     * Methode de Récupération des acteurs ayant en commun deux film donnés
     * Envoie d'un requete JPQL
     * @param lstFilm the lst film
     * @param em      the em
     */
    public static void acteurCommunFilm(List<String> lstFilm, EntityManager em){
        String str = "SELECT DISTINCT a FROM Acteur a JOIN a.lstFilm f WHERE f.nom = :filmPremier";
        TypedQuery<Acteur> queryFilmPremier = em.createQuery(str, Acteur.class);
        queryFilmPremier.setParameter("filmPremier", lstFilm.get(0));
        List<Acteur> lstFilmPremier= queryFilmPremier.getResultList();

        str = "SELECT DISTINCT a FROM Acteur a JOIN a.lstFilm f WHERE f.nom =  :filmSecond";
        TypedQuery<Acteur> queryFilmSecond = em.createQuery(str, Acteur.class);
        queryFilmSecond.setParameter("filmSecond", lstFilm.get(1));
        List<Acteur> lstFilmSecond= queryFilmSecond.getResultList();

        if(lstFilmPremier.isEmpty() || lstFilmSecond.isEmpty()){
            Menu.errorMenu("5", lstFilm.toString());
        }
        else{
            for (Acteur filmPr : lstFilmPremier){
                for(Acteur filmSd : lstFilmSecond){
                    if(filmPr.getNom().equals(filmSd.getNom())){
                        System.out.println(filmSd.getNom());
                    }
                }
            }
        }
    }

    /**
     * Methode de Récupération des films existant entre deux années ayant un acteur donnée
     * Envoie d'un requete JPQL
     * @param lstAnnees the lst annees
     * @param acteur    the acteur
     * @param em        the em
     */
    public static void filmAnneesActeur(List<String> lstAnnees, String acteur, EntityManager em){
        String str = "SELECT DISTINCT f FROM Film f JOIN f.lstCastingP a WHERE a.nom = :acteur AND f.anneeSortie BETWEEN :anneeDebut AND :anneeFin " ;
        TypedQuery<Film> query = em.createQuery(str, Film.class);
        query.setParameter("acteur", acteur);
        query.setParameter("anneeDebut", lstAnnees.get(0));
        query.setParameter("anneeFin", lstAnnees.get(1));
        List<Film> lstFilm= query.getResultList();

        Menu.afficherResultFilm(lstFilm);
    }

    /**
     * Gets em.
     *
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }
}
