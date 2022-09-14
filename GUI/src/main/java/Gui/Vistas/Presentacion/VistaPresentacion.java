package Gui.Vistas.Presentacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;

/* (no-Javadoc) La vista presentación depende de la lista de expositores y la lista de asistentes 
 * valores que debería recibir como parámetros. */

public class VistaPresentacion extends GridPane implements Initializable {


    @FXML private ComboBox<Persona> expositorEntrada;
    @FXML private TextField nombreEntrada;
    @FXML private DatePicker fechaEntrada;
    @FXML private Button submit;
    private Registro registro;

    private Presentacion p = null;


    /* @param expositores InmutableList 
     * @param asistentes InmutableList 
     */

    public VistaPresentacion(Registro r)  {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
            "/vistas/presentacion.fxml"));
        fxmlLoader.setController(this);
        this.registro = r;
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        p = new Presentacion();
        ObservableList<Persona> items = expositorEntrada.getItems();
        for (Persona e : registro.getExpositores()) {

            items.add(e);
        }
    }




    public Presentacion getValue() {
        return this.p;
    }

    public void guardar() {
        p = null;
        String nombre = nombreEntrada.getText();
        if (!nombre.equals("")) {
            p = new Presentacion(nombre);
        } else {
            return;
        }
        LocalDate fecha = fechaEntrada.getValue();
        p.setFecha(fecha);

        Persona e = expositorEntrada.getValue();
        p.setExpositor(e);
    }
}
