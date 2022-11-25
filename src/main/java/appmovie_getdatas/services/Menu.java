package appmovie_getdatas.services;

import models.Film;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Interface Menu
 * Accès aux méthodes de gestion de l'affichage du Menu
 */
public interface Menu {

    /**
     * Methode d'Affichage du Menu de l'application et de Renvoi d'une réponse utilisateur
     * @param scan Scanner
     * @return String choiceUser
     */
    static String afficherMenu(Scanner scan) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n***************** MENU *****************\n");
        sb.append("1. Affichage de la filmographie d’un acteur donné\n");
        sb.append("2. Affichage du casting d’un film donné\n");
        sb.append("3. Affichage des films sortis entre 2 années données\n");
        sb.append("4. Affichage des films communs à 2 acteurs/actrices donnés.\n");
        sb.append("5. Affichage des acteurs communs à 2 films donnés\n");
        sb.append("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting\n");
        sb.append("7. Fin de l’application\n");
        System.out.println(sb);

        return scan.next();
    }


    /**
     * Methode de Recuperation de la saisie utilisateur
     * @param scan  Scanner
     * @param sujet String
     * @return String string
     */
    static String saisirUnique(Scanner scan, String sujet){
        System.out.println("Saisir un " + sujet);

        return  scan.nextLine();
    }

    /**
     * Methode de Recuperation de la double saisie utilisateur
     * @param scan  Scanner
     * @param sujet String
     * @return List<String> list
     */
    static List<String> saisirCommun(Scanner scan, String sujet){
        List<String> lstFilm = new ArrayList<>();
        System.out.println("Saisir un premier " + sujet);
        lstFilm.add(scan.nextLine());

        System.out.println("Saisir un second "+ sujet);
        lstFilm.add(scan.nextLine());

        return lstFilm;
    }

    /**
     * Methode d'Affichage des erreurs utilisateurs liés à la saisie
     * @param reponse String
     * @param saisie  String
     */
    static void errorMenu(String reponse, String saisie){
         switch (reponse) {
             case "0" ->
                     System.out.println("Saisie incorrecte: " + saisie + " !!Veuillez saisir un chiffre compris entre 1 et 7!!");
             case "1" -> System.out.println("Acteur introuvable: " + saisie + "");
             case "2" -> System.out.println("Film introuvable: " + saisie + "");
             case "3" -> System.out.println("Dates introuvables: " + saisie + "");
             case "4" -> System.out.println("Acteurs en commun introuvables: " + saisie + "");
             case "5", "6" -> System.out.println("Films en commun introuvables: " + saisie + "");
             default -> System.out.println("Saisie incorrecte: " + saisie + " Vous m'avez cassée =/");
         }
    }


    /**
     * Methode d'Affichage des resultats de film.
     * @param list the list
     */
    static void afficherResultFilm(List<Film> list){
        if(list.isEmpty()){
            Menu.errorMenu("3", list.toString());
        }
        else{
            for (Film film : list){
                System.out.println(film.getNom() + " (" + film.getAnneeSortie()+")");
            }
        }
    }
}
