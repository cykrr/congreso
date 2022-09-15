package Gui;

import java.io.IOException;

import Congreso.Registro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;


public class Gui extends Application {

    private     Stage       stage;
    private     VBox        root;
    private     Controlador controlador;
    
    /* Punto de inicio del GUI
     * @see javafx.application.Application#start(javafx.stage.Stage)
     * Carga los ajustes del usuario usando la clase Ajustes.
     * Crea un controlador que se encargará de configurar la ventana
     * con la información correspondiente. Guarda el Stage en el
     * controlador y carga el archivo raíz (res/vistas/root.xml)
     */
    @Override
    public void start(Stage s) {
        System.out.println("Congreso GUI");

        System.out.println("Inicializando controlador");
        this.controlador = new Controlador(s);

        this.stage = s;
        inflar();
    }

    /** 
     * Carga la Vista raíz con los componentes
     * encontrados en el archivo /vista/root.xml
     */
    private void inflar() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("/vistas/root.fxml"));
        loader.setController(controlador);
        // System.out.println((Gui.class.getResource("/vistas/root.fxml")));
        try {
            root = (VBox)loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: No se encontró el recurso");
            System.exit(1);
        }

    }
}
