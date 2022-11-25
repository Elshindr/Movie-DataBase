package appmovie_senddatas.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Utilitaires {

    /**
     * Methode de Vérification du format de Date
     * Format attendu : yyyy-MM-dd, différent de 0
     * @param str String
     * @return Boolean
     */
     static boolean localDateChecker(String str){
        String validPattern =   "(0?[^0]{4})-(0?[1-9]|1[012])-(0?[1-9]|[12]\\d|3[01])";
        Pattern pattern = Pattern.compile(validPattern);
        Matcher matcher = pattern.matcher(str);

        return !matcher.find();
    }


    /**
     * Methode de Création de la date de Naissance au format dd/MM/yyyy
     * @param str String
     * @return LocalDate
     */
     static LocalDate dateNaissanceBuilder(String str){

        String strDay = str.substring(str.length() - 2).replace("-", "");
        String strMonth = str.substring(5, 7).replace("-","");

        StringBuilder strDate = new StringBuilder();
        strDate.append(strDay.length() == 2 ? strDay : "0" + strDay).append("/");
        strDate.append(strMonth.length() == 2 ? strMonth : "0" + strMonth).append("/");
        strDate.append(str, 0, 4);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.parse(strDate, formatter);
    }
}
