package appmovie_senddatas.controls;


/**
 * The type Controler.
 */
public class Controler {
    private static Controler instance = null;

    private Controler(){
        super();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Controler getInstance() {

        if(Controler.instance == null){
            Controler.instance = new Controler();
        }

        return Controler.instance;
    }
}
