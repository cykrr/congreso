package Gui.Vistas.LeerPresentacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.text.Text;
import Congreso.Expositor;
import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;
import Gui.Alerta;
import Gui.Vistas.PopUp;

/* (no-Javadoc) La vista presentación depende de la lista de expositores y la lista de asistentes 
 * valores que debería recibir como parámetros. */

public class LeerPresentacion extends GridPane implements Initializable, PopUp.PopAble {
	
    // Elementos XML
    @FXML private ComboBox<Expositor> comboExpositor;
    @FXML private ComboBox<Persona> comboAsistentes;
    @FXML private ComboBox<Persona> comboAsistentesConfirmados;
    @FXML private TextField tfNombre, tfHora, tfDuracion, tfDescripcion, tfAsistentes;
    @FXML private DatePicker dpFecha;
    @FXML private Button submit;
    @FXML private Text txtHeader;

    // Referencia al registro principal
    private Registro registro;

    // Valor de retorno
    private Presentacion p = null;

    /* Constructor de la vista
     * Requiere el registro para utilizarlo
     * en la inicialización */
    public LeerPresentacion(Registro r)  {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/leerPresentacion.fxml"));
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

    public LeerPresentacion(Registro r, Presentacion p) {
		this(r);
		tfNombre.setText(p.getNombre());
		dpFecha.setValue(p.getFecha());
		tfHora.setText(p.getStringHora());
		tfDuracion.setText(Integer.toString(p.getDuracion()));
		tfDescripcion.setText(p.getDescripcion());
		comboExpositor.setValue(p.getExpositor());
		comboAsistentes.setValue(null);
	}

	@Override
    public void initialize(URL url, ResourceBundle resources) {
        ObservableList<Expositor> itemsExpositores = comboExpositor.getItems();
        for (Expositor e : registro.getExpositores()) {
        	itemsExpositores.add(e);
        }
        ObservableList<Persona> itemsAsistentes = comboAsistentes.getItems();
        for (Persona e : registro.getAsistentes()) {
        	itemsAsistentes.add(e);
        }
        
        /*ObservableList<Persona> itemsAsistentesGuardados = comboAsistentesConfirmados.getItems();
        for (Persona e : p.getAsistentes()) {
        	itemsAsistentesGuardados.add(e);
        }*/
    }

    @Override
    public Object getValue() {
        return this.p;
    }

    @Override
    public boolean guardar() {
        String nombre = tfNombre.getText().trim();
        String strFecha = dpFecha.getEditor().getText().trim();
        String strHora = tfHora.getText().trim();
        String strDuracion = tfDuracion.getText().trim();
        String descripcion = tfDescripcion.getText().trim();
        Expositor expositor = comboExpositor.getValue();
        Persona asistente = comboAsistentes.getValue();
        
        
        if(nombre.isEmpty() || strFecha.isEmpty() || strHora.isEmpty() 
           || strDuracion.isEmpty() || descripcion.isEmpty()) {
        	Alerta.mostrarAlertaAdvertencia("No pueden quedar campos vacíos");
        	return false;
        }
        
        LocalDate fecha = Util.parseDate(strFecha);
        if(fecha == null) {
        	Alerta.mostrarAlertaAdvertencia("La fecha ingresada no es válida");
        	return false;
        }
        
        LocalTime hora = Util.parseTime(strHora);
        if(hora == null) {
        	Alerta.mostrarAlertaAdvertencia("La hora ingresada no es válida");
        	return false;
        }
        
        if(!Util.isNumeric(strDuracion)) {
        	Alerta.mostrarAlertaAdvertencia("La duración ingresada no es válida");
        	return false;
        }
 
        if(expositor == null) {
        	Alerta.mostrarAlertaAdvertencia("Seleccione un expositor");
        	return false;
        }
        
        if(asistente != null) {
        	if(asistente.getNombre().isEmpty() || asistente.getCorreo().isEmpty() || 
        	   Integer.toString(asistente.getEdad()).isEmpty() ||
        	   Long.toString(asistente.getFono()).isEmpty()) {
        		Alerta.mostrarAlertaAdvertencia("No esta bien");
        		return false;
        	}
        	/* 
        	 * Lo hice para probar si tenia todos los datos correspondientes a persona
        	 * if(!asistente.getNombre().isEmpty()) {
        		Alerta.mostrarAlertaAdvertencia(Long.toString(asistente.getFono()));
        		return false;
        	}*/
        }
        
        int duracion = Integer.parseInt(strDuracion);
        
        if(asistente != null) {
        	p = new Presentacion(nombre, expositor, asistente, fecha, hora, duracion, descripcion);
        }
        else {
        	p = new Presentacion(nombre, expositor, fecha, hora, duracion, descripcion);
        }
        return true;
    }
    
    public void setHeader(String text) {
    	txtHeader.setText(text);
    }
}
