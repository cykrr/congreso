import java.util.HashMap;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/* Almacena toda la información correspondiente al
 * runtime: Mapas para rápido acceso a las presentaciones
 * por distintos atributos. */
public class Registro {

    private HashMap<String, Presentacion> nombre_presentaciones;
    private HashMap<Integer, Presentacion> id_presentaciones;
    private HashMap<Integer, Persona> id_personas;

    /* Cuenta de las presentaciones importadas */
    private int count = 0; 

    Registro() {
        nombre_presentaciones = new HashMap<String, Presentacion>();
        id_presentaciones = new HashMap<Integer, Presentacion>();
    }

    /** Añade una presentación al registro, se encarga de revisar
     * que la presentación a ser añadida no tenga un nombre
     * repetido y de que no choque temporalmente con otra
     * presentación 
     * @param p presentación a ser añadida
     * */
    public void insertarPresentacion(Presentacion p) {
        Presentacion busqueda_nombre, busqueda_id;
        busqueda_nombre = nombre_presentaciones.get(p.getNombre());
        if (busqueda_nombre != null) {
            System.err.println("ADVERTENCIA: La presentación ya existe");    
            p.mostrar();
        }
    }

    public void insertarPersona(Persona p) {
        Persona busqueda_id = id_personas.get(p.getId());
    }
    }

    /* Dado un nombre de archivo abre e infla los contenidos del
     * registro. Se asegura de no importar información
     * inconsistente, como por ejemplo errores de síntax o
     * elementos faltantes. 
     * @param p nombre del archivo a abrir */
    public void importar(String nombre_archivo) throws FileNotFoundException, IOException {
        BufferedReader file = new BufferedReader (
                new FileReader(nombre_archivo)
                );
        String line;
        while ( (line = file.readLine()) != null) {
            LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
        if (lineArray.size() != 9) {
            System.err.println("Error: Se esperaba una linea con" +
                    "9 campos. Recibidos: " +lineArray.size());
            System.exit(1);
        }

// TO-DO constructor ()
        Presentacion p;
        Persona presentador;
        int i = 0;
        for (String s: lineArray)  {
            switch (i) {
                case 0:
                    p = new Presentacion(s);
                    break;
                case 1:
                    presentador = new Persona(s, 0, 0);
                    break;
                case 2:
                    presentador.setEdad(Integer.parseInt(s));
                    break;
                case 3:
                    p.setDia(Integer.parseInt(s));
                    break;
                case 4:
                    p.setMes(Integer.parseInt(s));
                    break;
                case 5:
                    p.setAno(Integer.parseInt(s));
                    break;
                case 6:
                    p.setDuracion(Integer.parseInt(s));
                    break;
                case 7:
                    p.setDescripcion(s);
                    break;
                case 8:
                    p.setAsistentes(s, id_personas);

            }

        }
        file.close();

    }


    /* Dado un nombre de archivo abre y guarda los contenidos del
     * registro. 
     * @param p nombre del archivo a guardar */
    public void exportar(String nombre_archivo) {

    }

}