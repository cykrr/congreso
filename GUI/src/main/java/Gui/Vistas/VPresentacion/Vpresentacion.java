package Gui.Vistas.VPresentacion;

import java.io.IOException;
import java.util.LinkedList;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Gui.Alerta;
import Gui.EventoPresentacion;
import Gui.Vistas.PopUp;
import Gui.Vistas.Vista;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Vpresentacion extends Vista implements Vista.Manipulable {
    @FXML Text txtNombre, txtExpositor, txtDescripcion, txtFecha, txtHora, txtDuracion, txtTotalAsistentes, txtAsistentes;
    
    private Presentacion p;
    
    public Vpresentacion(Presentacion p, Registro registro, Stage stage, Dashboard dashboard) {
        super(registro, stage, dashboard, "/vistas/vPresentacion.fxml");      
        this.p = p;
    	
        txtNombre.setText(p.getNombre());
        txtExpositor.setText(p.getExpositor().getNombre());
        txtDescripcion.setText(p.getDescripcion());
        txtFecha.setText(p.getStringFecha());
        txtHora.setText(p.getStringHora());
        txtDuracion.setText(Integer.toString(p.getDuracion()) + " minutos");
        
        LinkedList<Persona> asistentes = p.getAsistentes();
        txtTotalAsistentes.setText(Integer.toString(asistentes.size()));
        
        if(asistentes.size() == 0) {
        	txtAsistentes.setText("Ninguno");
        } else {     
	        String str = "";
	        for(int i = 0; i < asistentes.size()-1; i++) {
	        	str += asistentes.get(i);
	        	str += ", ";
	        }
	        str += asistentes.get(asistentes.size()-1);
	        txtAsistentes.setText(str);
        }
    }
    
    @Override
    public void editar() {
    	LeerPresentacion lp = new LeerPresentacion(getRegistro(), p);
    	lp.setHeader("Editando presentacion");
    	
    	PopUp popup = new PopUp(getStage(), lp);
    	popup.setTitle("Editar presentación");
            
        Presentacion retorno = (Presentacion)popup.showDialog();
        if (retorno != null) {
        	getRegistro().editarPresentacion(p, retorno);
            getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.EDITAR_PRESENTACION, p, retorno));
        }
    }
    
    @Override
    public void eliminar() {
    	boolean opcion = Alerta.mostrarAlertaConfirmacion("¿Desea eliminar la presentación \"" + p.getNombre() + "\"?");
    	if(opcion) {
    		getRegistro().eliminarPresentacion(p);
    		getDashboard().fireEvent(new EventoPresentacion(EventoPresentacion.ELIMINAR_PRESENTACION, p));
    	}
    }
}
