package appmovie_senddatas.controls;
import appmovie_senddatas.manager.ManagerJSON;

import java.io.IOException;

/**
 * Class App.
 * Entry point of application
 */
public class App {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        try{
            if(Controler.getInstance() != null){
                ManagerJSON.main();
            }
        }
        catch(IOException ioException){
            System.out.println("=================oups=================");
        }

    }
}
