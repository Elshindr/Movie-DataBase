package appmovie_getdatas.controls;


/**
 * The type App.
 */
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if(Controller.getInstance() != null ){
            Controller.launch();
        }
    }
}
