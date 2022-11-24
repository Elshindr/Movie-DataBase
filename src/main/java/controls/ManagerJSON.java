package controls;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import models.jsonschema.beans.Acteur;
import models.jsonschema.beans.CastingPrincipal;
import models.jsonschema.beans.Naissance;
import models.jsonschema.beans.RoleDto;
import utils.Utilitaires;
import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * The type Manager json.
 */
public final class ManagerJSON {

    private static final Logger logger = Logger.getLogger(ManagerJSON.class);

    private ManagerJSON() {
    }


    /**
     * Main
     *
     * @throws IOException the io exception
     */
    public static void main() throws IOException {
        
        // Get Json Objects
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(".\\src\\main\\resources\\films.json");
        Film[] lstFilms = mapper.readValue(new File(path.toString()), Film[].class);
        
        // Get JPA Connexion & Instances
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("movie-database");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        recupAllPays(em, tr, lstFilms);
        recupAllFilm(em, tr, lstFilms);

    }

    /**
     * Methode de Récupération de l'ensemble des Pays
     * @param em EntityManager
     * @param tr EntityTransaction
     * @param lstFilm Film[]
     */
    public static void recupAllFilm(EntityManager em, EntityTransaction tr, Film[] lstFilm){
        for (Film unFilm : lstFilm) {
            tr.begin();
            Film film = new Film();

            Pays pays = recupPays(em, unFilm.getPays());
            Lieu lieu = recupLieu(em, unFilm.getLieuTournage());

            List<Acteur> lstActeurCast = recupLstActeurCasting(em, unFilm);
            List<Role> lstRoles = recupLstRoles(em, unFilm);
            List<Realisateur> lstRealisateur = recupLstRealisateur(em, unFilm);
            List<Genre> lstGenre = recupLstGenre(em, unFilm);

            if (lieu != null) {
                lieu.getLstFilm().add(film);
                lieu.getLstActeur().addAll(lstActeurCast);
            } else {
                Lieu lieuVide = new Lieu();
                lieuVide.getLstFilm().add(film);
            }

            if (pays != null) {
                pays.getSetLieu().add(lieu);
                pays.getSetFilm().add(film);
            }

            film.setId(unFilm.getId());
            film.setNom(unFilm.getNom());
            film.setLangue(unFilm.getLangue());
            film.setUrl(unFilm.getUrl());
            film.setPlot(unFilm.getPlot());
            film.setAnneeSortie(unFilm.getAnneeSortie());
            film.setPays(pays);
            film.setLieuTournage(lieu);

            film.getLstRealisateur().addAll(lstRealisateur);
            film.getLstGenres().addAll(lstGenre);
            film.getLstCastingP().addAll(lstActeurCast);
            film.getLstRole().addAll(lstRoles);

            em.persist(film);
            tr.commit();
        }
    }

    /**
     * Methode de Récupération de l'ensemble des Pays
     * @param em EntityManager
     * @param tr EntityTransaction
     * @param lstFilm Film[]
     */
    public static void recupAllPays(EntityManager em, EntityTransaction tr, Film[] lstFilm) {

        for (Film film : lstFilm) {

            if(film.getPays() != null ){
                tr.begin();
                TypedQuery<Pays> query = em.createQuery("SELECT DISTINCT p FROM pays p WHERE nom = :nom", Pays.class);
                query.setParameter("nom", film.getPays().getNom().toUpperCase().trim());
                List<Pays> lstPays = query.getResultList();

                if(lstPays.isEmpty()){
                    Pays unPays = new Pays();
                    unPays.setNom(film.getPays().getNom().toUpperCase().trim());
                    unPays.setUrl(film.getPays().getUrl());
                    em.persist(unPays);
                }

                tr.commit();
            }
        }
    }


    /**
     * Methode de Récupération d'un Pays en Json et de son Envoie vers la DB
     * @param em   the em
     * @param pays the pays
     * @return Pays pays
     */
    public static Pays recupPays(EntityManager em, Pays pays) {
        Pays paysFinal;

        if(pays == null || pays.getNom() == null){
            return null;
        }

        TypedQuery<Pays> queryPays = em.createQuery("SELECT DISTINCT p FROM pays p WHERE nom = :nom", Pays.class);
        queryPays.setParameter("nom", pays.getNom().toUpperCase().trim());
        List<Pays> lstPays = queryPays.getResultList();

        if(lstPays.isEmpty()){
            Pays unPays = new Pays();
            unPays.setNom(pays.getNom().toUpperCase().trim());
            unPays.setUrl(pays.getUrl());
            em.persist(unPays);

            paysFinal = unPays;
        }
        else if (lstPays.size() == 1){
            paysFinal = em.find(Pays.class, lstPays.get(0).getId());
        }
        else{
            logger.error("Error in lstPays");
            return null;
        }

        return paysFinal;
    }


    /**
     * Methode de Récupération d'un LieuTournage en JSON et de son Envoie vers la DB
     * @param em   the em
     * @param lieu the lieu
     * @return Lieu lieu
     */
    public static Lieu recupLieu(EntityManager em, Lieu lieu) {
        Lieu lieuFinal;
        List<Lieu> lstLieu;

        if(lieu == null){
            return null;
        }

        TypedQuery<Lieu> queryLieu = em.createQuery("SELECT DISTINCT p FROM Lieu p WHERE ville = :ville", Lieu.class);
        queryLieu.setParameter("ville", lieu.getVille());
        lstLieu = queryLieu.getResultList();

        if(lstLieu == null){
            lieuFinal = new Lieu();
        }
        else if(lstLieu.isEmpty()){
            lieuFinal = new Lieu();

            lieuFinal.setEtatDept(lieu.getEtatDept());
            lieuFinal.setVille(lieu.getVille());

            Pays paysNew = new Pays();
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            // Lieu de Naissance Acteur Casting
            if(stackTrace[2].toString().contains("recupLstActeurCasting") || stackTrace[2].toString().contains("recupActeurRole")){
                paysNew.setNom(lieu.getPays() == null ? null : lieu.getPays().getNom());
            }
            // Lieu de Tournage
            else{
                paysNew.setNom(lieu.getPaysJSON());
            }

            Pays paysRecup = recupPays(em, paysNew);
            lieuFinal.setPays(paysRecup);
            em.persist(lieuFinal);

        }
        else if(lstLieu.size() == 1){
            lieuFinal = lstLieu.get(0);
        }
        else{
            logger.error("Error: lstLieu");
            return null;
        }

        return lieuFinal;
    }


    /**
     * Methode de Récupération des Genres en JSON et de leur Envoie vers la DB
     * @param em EntityManager
     * @param film Film
     * @return List<Genre>
     */
    private static List<Genre> recupLstGenre(EntityManager em, Film film) {
        Genre genre;
        List<Genre> lstGenre= new ArrayList<>();

        if(film.getGenres() == null){
            return Collections.emptyList();
        }

        for(String strGenre: film.getGenres()){
            TypedQuery<Genre> query = em.createQuery("SELECT DISTINCT p FROM Genre p WHERE libelle = :libelle", Genre.class);
            query.setParameter("libelle", strGenre);
            List<Genre> lstGenreFind = query.getResultList();

            if(lstGenreFind.isEmpty() ){
                genre = new Genre();
                genre.setLibelle(strGenre);
                em.persist(genre);
            }
            else if(lstGenreFind.size() == 1){
                genre = lstGenreFind.get(0);
            }
            else{
                logger.error("Error: lstGenreFind");
                return Collections.emptyList();
            }
            lstGenre.add(genre);
        }

        return lstGenre;
    }


    /**
     * Methode de Récupération des Realisateurs en JSON et de leur Envoie vers la DB
     * @param em EntityManager
     * @param film Film
     * @return List<Realisateur>
     */
    private static List<Realisateur> recupLstRealisateur(EntityManager em, Film film) {
        Realisateur realisateur;
        List<Realisateur> lstRealisateur= new ArrayList<>();

        if(film.getLstRealisateur() == null){
            return Collections.emptyList();
        }

        for(Realisateur unReal: film.getLstRealisateur()){
            TypedQuery<Realisateur> query = em.createQuery("SELECT DISTINCT p FROM Realisateur p WHERE nom = :nom", Realisateur.class);
            query.setParameter("nom", unReal.getNom());
            List<Realisateur> lstRealisateurFind = query.getResultList();

            if(lstRealisateurFind.isEmpty()){
                realisateur = new Realisateur();
                realisateur.setNom(unReal.getNom());
                realisateur.setUrl(unReal.getUrl());
                em.persist(realisateur);
            }
            else if(lstRealisateurFind.size() == 1){
                realisateur = lstRealisateurFind.get(0);
            }
            else{
                logger.error("Error: lstRealisateurFind");
                return Collections.emptyList();
            }
            lstRealisateur.add(realisateur);
        }

        return lstRealisateur;
    }


    /**
     * Methode de Récupération des Acteurs en CastingPrincipal et de leur Envoie vers la DB
     * @param em EntityManager
     * @param film Film
     * @return List<Acteur>
     */
    private static List<Acteur> recupLstActeurCasting(EntityManager em, Film film) {
        Acteur acteur;
        List<Acteur> lstActeur= new ArrayList<>();

        if(film.getCastingPrincipal() == null){
            return null;
        }

        for(CastingPrincipal unActeurCasting: film.getCastingPrincipal()){
            TypedQuery<Acteur> query = em.createQuery("SELECT DISTINCT p FROM Acteur p WHERE nom = :nom", Acteur.class);
            query.setParameter("nom", unActeurCasting.getIdentite());
            List<Acteur> lstActeurFind = query.getResultList();

            if(lstActeurFind.isEmpty()){
                acteur = new Acteur();
                acteur.setId(unActeurCasting.getId());
                acteur.setNom(unActeurCasting.getIdentite());
                acteur.setUrl(unActeurCasting.getUrl());

                // Enregistrement de la Date de Naissance
                LocalDate dateFinal;

                if(Utilitaires.localDateChecker(unActeurCasting.getNaissance().getDateNaissance())){
                    dateFinal = null;
                }
                else{
                     dateFinal = Utilitaires.dateNaissanceBuilder(unActeurCasting.getNaissance().getDateNaissance());
                }

                // Enregistrement du lieu de Naissance
                acteur.setLieuNaissance(recupLieu(em, recupActeurLieu(em, unActeurCasting.getNaissance())));
                acteur.setDateNaissance(dateFinal);

                em.persist(acteur);
            }
            else if(lstActeurFind.size() == 1){
                acteur = lstActeurFind.get(0);
            }
            else{
                logger.error("Error: lstActeurFind");
                return null;
            }
            lstActeur.add(acteur);
        }

        return lstActeur;
    }


    /**
     * Methode de Récupération des Roles et de leur Envoie vers la DB
     * @param em EntityManager
     * @param film Film
     * @return List<Role>
     */
    private static List<Role> recupLstRoles(EntityManager em, Film film) {
        Role role;
        List<Role> lstRole = new ArrayList<>();

        if(film.getRoles() == null){
            return null;
        }

        for(RoleDto roleDto: film.getRoles()){
            TypedQuery<Role> query = em.createQuery("SELECT DISTINCT p FROM Role p WHERE charactername = :character", Role.class);
            query.setParameter("character", roleDto.getCharacterName());
            List<Role> lstRoleFind = query.getResultList();

            if(lstRoleFind.isEmpty()){
                role = new Role();
                role.setCharacterName(roleDto.getCharacterName());

                // Récupération de l'Acteur de ce role, dans ce film
                recupActeurRole(em, roleDto, role);
                em.persist(role);
            }
            else if(lstRoleFind.size() == 1){
                role = lstRoleFind.get(0);
            }
            else{
                logger.error("Error: lstRoleFind");
                return null;
            }
            lstRole.add(role);

        }

        return lstRole;
    }


    /**
     * Methode de Récupération du lieu de naissance d'un Acteur
     * @param em EntityManager
     * @param naissance Naissance
     * @return Lieu
     */
    private static Lieu recupActeurLieu(EntityManager em, Naissance naissance){
        Lieu lieuFinal = new Lieu();
        List<String> list = new ArrayList<>();

        if(naissance == null){
             return null;
        }
        else {
            list.addAll(Arrays.asList(naissance.getLieuNaissance().split(", ")));
        }

        lieuFinal.setVille(list.get(0));

        if(naissance.getLieuNaissance().equals("")){
            lieuFinal.setPays(null);
        }
        else if(list.size()==1){
            Pays pays = new Pays();
            pays.setNom(list.get(0));
            lieuFinal.setPays(recupPays(em, pays));
        }
        else if(list.size()<3){
            Pays pays = new Pays();
            pays.setNom(list.get(1));
            lieuFinal.setPays(recupPays(em, pays));
        }
        else if(list.get(2) != null && !list.get(2).equals("")){
            Pays pays = new Pays();
            pays.setNom(list.get(2));
            lieuFinal.setPays(recupPays(em, pays));
            lieuFinal.setEtatDept(list.get(1));
        }

        return lieuFinal;
    }

    /**
     * Methode de Récupération de l'Acteur pour un Role
     * @param em EntityManager
     * @param roleDto RoleDto
     * @param role Role
     */
    private static Acteur recupActeurRole(EntityManager em, RoleDto roleDto, Role role){
        Acteur acteur ;
        TypedQuery<Acteur> queryActeur = em.createQuery("SELECT DISTINCT p FROM Acteur p WHERE nom = :nom", Acteur.class);
        queryActeur.setParameter("nom", roleDto.getActeur().getNom());
        List<Acteur> lstActeurFind = queryActeur.getResultList();

        if(lstActeurFind.isEmpty()){
            acteur = new Acteur();
            acteur.setId(roleDto.getActeur().getId());
            acteur.setNom(roleDto.getActeur().getNom());
            acteur.setUrl(roleDto.getActeur().getUrl());
            acteur.setLieuNaissance(recupLieu(em, recupActeurLieu(em, roleDto.getActeur().getNaissance())));

            LocalDate date;
            if(roleDto.getActeur().getNaissance() == null || Utilitaires.localDateChecker(roleDto.getActeur().getNaissance().getDateNaissance())){
                date = null;
            }
            else{
                String str = roleDto.getActeur().getNaissance().getDateNaissance();
                date = Utilitaires.dateNaissanceBuilder(str);
            }

            acteur.setDateNaissance(date);
            em.persist(acteur);
        }
        else if(lstActeurFind.size() == 1){
            acteur = lstActeurFind.get(0);
        }
        else{
            logger.error("Error: lstActeurFind");
            return null;
        }
        role.getLstActeur().add(acteur);
        return acteur;
    }

}