package controls;


public class Controler {
    private static Controler instance = null;

    private Controler(){
        super();
    }

    public static Controler getInstance() {

        if(Controler.instance == null){
            Controler.instance = new Controler();
        }

        return Controler.instance;
    }
}
