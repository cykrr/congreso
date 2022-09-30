package Congreso;
import java.time.LocalTime;
import java.time.LocalDate;

import java.util.LinkedList;

import Congreso.excepciones.InvalidDuracionException;
import Congreso.excepciones.InvalidNombreException;
import Congreso.excepciones.NullExpositorException;

/* Presentación a ser almacenada en la base de datos del congreso.
 */
public class Presentacion {
    /*! Nombre de la presentación */
	private String nombre; 

    /*! Descripción de la presentación */
	private String descripcion; 

    /*! Expositor de la presentación */
	private Expositor expositor; 

    /*! Fecha de la presentación */
	private LocalDate fecha = LocalDate.of(1993,01, 01); 

    /*! Hora de la presentación */
	private LocalTime hora = LocalTime.of(0, 0); 

    /*! duración de la presentación */
	private int duracion;

    /*! Lista de asistentes de la presentación */
	private LinkedList<Persona> asistentes;
	
	private Persona asistenteToRemove;

    /** Constructor principal, inicializa la lista de
     * asistentes (sin asistentes).
     * @see Congreso.Persona
      */
    public Presentacion() {
    	this.asistentes = new LinkedList<Persona>();
    	this.asistenteToRemove = null;
    }
    
    /** Constructor general para el tipo presentación
     * inicializa todos los atributos.
     * 
     * @param nombre Nombre de la presentación
     * @param expositor Referencia al expositor de la presentación
     * @param fecha Fecha de la presentación
     * @param hora Hora de la presentación
     * @param duracion Duración de la presentación
     * @param descripcion Descripción de la presentación
     * @throws InvalidNombreException 
     * @throws NullExpositorException 
     * @throws InvalidDuracionException 
     * 
     * @see Congreso.Expositor
     * @see Congreso.Persona
     */
    public Presentacion(String nombre, Expositor expositor, LocalDate fecha, LocalTime hora, int duracion, String descripcion) 
    		throws InvalidNombreException, NullExpositorException, InvalidDuracionException {
    	setNombre(nombre);
    	setExpositor(expositor);
    	setFecha(fecha);
    	setHora(hora);
    	setDuracion(duracion);
    	setDescripcion(descripcion);
    	
    	this.asistentes = new LinkedList<Persona>();
    	this.asistenteToRemove = null;
    }
    
    /** Establece el nombre de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     * @param n Nombre de la presentación.
     */
    public void setNombre(String nombre) throws InvalidNombreException {
    	if(!Util.isAlphaOrSpace(nombre))
    		throw new InvalidNombreException(nombre);
    	
        this.nombre = nombre;
    }
    
    /** Establece el Expositor de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     * @param expositor Referencia al expositor de la presentación
     */
    public void setExpositor(Expositor expositor) throws NullExpositorException {
    	if(expositor == null)
    		throw new NullExpositorException(nombre);
    	
    	this.expositor = expositor;
    }
    
    /** Actualiza la fecha de la presentación 
     * @param fecha Fecha a establecer
     * @see LocalDate
    */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    /** Actualiza la hora de la presentación
     * @param hora Hora a establecer
     * @see LocalTime
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    /** Establece la duración de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     */
    public void setDuracion(int duracion) throws InvalidDuracionException {
    	if(duracion < 1 || duracion > 300)
    		throw new InvalidDuracionException(duracion);
    	
    	this.duracion = duracion;
    }
    
    /** Dado un string, establece la descripción de la presentación
     * @param descripcion Descripción a establecer.
     */
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }

    /** Establece la lista de asistentes de la presentación. Utilizado al importar archivos 
     * al Registro.
     * 
     * @see Registro
     * @param asistentes Nueva lista de asistentes.
     */
    public void setAsistentes(LinkedList<Persona> asistentes) {
        this.asistentes = asistentes;
    }
    
    /** Agrega un asistente de tipo Persona a la presentación insertándolo en
     * su lista de asistentes.
     * @param asistente Asistente a ser añadido.
     */
    public void agregarAsistente(Persona asistente){
    	this.asistentes.add(asistente);
    }
    
    /** Elimina un asistente de tipo Persona en la presentación insertándolo .
     * @param asistente Asistente a ser eliminado.
     */
    public void removerAsistente(Persona asistente){
    	this.asistentes.remove(asistente);
    }
    
    /** Agrega un asistente de tipo Persona a eliminar de la presentación insertándolo en
     *  la variable asistenteToRemove.
     * @param asistente Asistente a ser añadido.
     */
    public void agregarAsistenteToRemove(Persona asistente){
    	this.asistenteToRemove = asistente;
    }
    
    /** Dada una referencia a un asistente, lo elimina de la lista de
     *  asistentes.
     *  @param asistente Asistente a ser eliminado.
     */
    public void eliminarAsistente(Persona asistente) {
      this.asistentes.remove(asistente);
	  }

    /** Dado el nombre de un asistente, revisa si este asistirá a la
     *  presentación.
     *  @param nombre Nombre del asistente a buscar
     *  @return Referencia al asistente || null
     */
    public Persona buscarAsistente(String nombre) {
		for(int i = 0; i < asistentes.size(); i++) {
			Persona persona = asistentes.get(i);
			if(nombre.equals(persona.getNombre()))
				return persona;
		}
		return null;
    }
    
    /** Muestra la presentación actual
     * -- Método desactualizado. Sólo se usa en CLI. --
     */
    public void mostrar() {
    	System.out.println("Nombre: " + nombre);
    	System.out.println("Expositor: " + (expositor != null ? expositor.getNombre() : "No asignado"));
    	System.out.println("Descripción: " + (descripcion != null ? descripcion : "No asignada"));
    	
    	System.out.println("Fecha: " + (fecha != null ? fecha : "No asignada"));
    	System.out.println("Hora: " + (hora != null ? hora : "No asignada"));

    	System.out.println("Duración: " + (duracion != 0 ? duracion : "No asignada"));
    	if(asistentes.size() < 1) {
    		System.out.println("Asistencia: Nadie ha confirmado su asistencia");
    	}
    	else {
    		System.out.println("Asistencia: ");
    		for(int i = 0; i < asistentes.size(); i++) {
    			System.out.println("	"+ asistentes.get(i).getNombre());
    		}
    	}
    }

    /** Muestra los asistentes de la presentación
     *  -- Método desactualizado. Sólo se utiliza en CLI --
     */

    public void mostrarAsistentes() {
    	int cantidadAsistentes = asistentes.size();
    	if(cantidadAsistentes < 1) {
    		System.out.println("No hay asistentes para mostrar");
    		return;
    	}
    	
    	System.out.println(String.format("%-20s %-20s %-20s", "Nombre", "Edad", "Teléfono"));
    	for(int i = 0; i < cantidadAsistentes; i++) {
    		Persona p = asistentes.get(i);
    		System.out.println(String.format("%-20s %-20s %-20s", p.getNombre(), p.getEdad(), p.getFono()));
    	}    	
    	
    	System.out.println("Total de asistentes: " + cantidadAsistentes);
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    public String getNombre() {
    	return nombre;
    }
    
    public Expositor getExpositor() {
    	return expositor;
    }
    
    public Persona getAsistenteToRemove() {
    	return this.asistenteToRemove;
    }
    
    public Persona getAsistenteToRemove(Presentacion p) {
    	return p.getAsistenteToRemove();
    }
    
    public int getDia() {
    	return fecha.getDayOfMonth();
    }
    
    public int getMes() {
    	return fecha.getMonthValue();
    }
    
    public int getAño() {
    	return fecha.getYear();
    }
    
    public LocalDate getFecha() {
    	return fecha;
    }
    
    public String getStringFecha() {
    	return Util.dateFormatterOutput.format(fecha);
    }
    
    public LocalTime getHora() {
    	return hora;
    }
    
    public String getStringHora() {
    	return Util.timeFormatterOutput.format(hora);
    }
    
    public int getHoraInicio() {
    	return 	hora.getHour();
    }
    public int getMinutoInicio() {
    	return 	hora.getMinute();
    }
    
    public int getDuracion() {
    	return duracion;
    }
    
    public String getDescripcion() {
    	return descripcion;
    }
    
    public LinkedList<Persona> getAsistentes() {
    	return asistentes;
    }
}
