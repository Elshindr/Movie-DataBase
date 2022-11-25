package appmovie_getdatas.controls;

import appmovie_getdatas.dao.Dao;
import appmovie_getdatas.services.Menu;
import java.util.List;
import java.util.Scanner;

/**
 * Class Controller
 * Récupération de l'instance de la Classe Dao
 * Gestion des retours d'affichage
 */
public class Controller {
    private static Controller instance = null;
    private static final Dao dao = Dao.getInstance();
    private static final Scanner scan = new Scanner(System.in);


    private Controller(){
        super();
        scan.useDelimiter("\n");
    }


    /**
     * Gets one Controller instance.
     *
     * @return the instance
     */
    public static Controller getInstance() {

        if(Controller.instance == null){
            Controller.instance = new Controller();
        }

        return Controller.instance;
    }


    /**
     * Methode de Lancement du Menu de l'application
     *
     */
    public static void launch()  {
        boolean running = true;

        while (running) {
            String choiceUser = Menu.afficherMenu(scan);
            scan.nextLine();  // discard newline
            switch (choiceUser) {
                case "1":
                    String choiceActeur = Menu.saisirUnique(scan, "acteur");
                    Dao.getFilmoActeur(choiceActeur, dao.getEm());
                    break;
                case "2":
                    String choiceFilm = Menu.saisirUnique(scan, "film");
                    Dao.getFilmCasting(choiceFilm, dao.getEm());
                    break;
                case "3":
                    List<String> lstAnnee = Menu.saisirCommun(scan, "annee");
                    Dao.filmAnnees(lstAnnee, dao.getEm());
                    break;
                case "4":
                    List<String> lstActeur = Menu.saisirCommun(scan, "acteur");
                    Dao.filmCommunActeur(lstActeur, dao.getEm());
                    break;
                case "5":
                    List<String> lstFilm = Menu.saisirCommun(scan, "film");
                    Dao.acteurCommunFilm(lstFilm, dao.getEm());
                    break;
                case "6":
                    lstAnnee = Menu.saisirCommun(scan, "annee");
                    String acteur = Menu.saisirUnique(scan, "acteur");
                    Dao.filmAnneesActeur(lstAnnee, acteur, dao.getEm());
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    Menu.errorMenu("0", choiceUser);
                    break;
            }
        }

        scan.close();
    }

}
