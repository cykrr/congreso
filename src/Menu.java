import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalTime;

import java.io.BufferedReader;
import java.io.IOException;

public class Menu {
    static private BufferedReader br;

    static private Map<String,Presentacion> nombre_presentaciones;
    // static private HashMap<Integer, Presentacion> id_presentaciones;

    /* Presentación debería almacenar la hora y la fecha por separado */

    // static private HashMap<LocalTime,Presentacion> fecha_presentaciones;

    Menu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        nombre_presentaciones = new HashMap<String, Presentacion>();
    }

    public String getLine() throws IOException {
        return br.readLine();
    }
    public int getInt() throws IOException {
        return Integer.parseInt(getLine());
    }
    public char getChar() throws IOException {
        char ret = (char)br.read();
        br.read();
        return ret;
    }

    public void mostrar() {
        System.out.println("Presentaciones del congreso internacional:");
        System.out.println("a: Crear presentación");
        System.out.println("e: Editar presentación");
        // System.out.println("x: eliminar presentación");
        System.out.println("l: Listar presentaciones");
        // System.out.println("b: buscar presentación");
        System.out.println("i: Importar presentaciones");
        System.out.println("s: Salir");
        System.out.print("--- ");
    }

    public void crearPresentacion() throws IOException {
    	String nombre = "";
        System.out.println("Ingrese el nombre de la presentación:");
        
        while(nombre.equals("")) 
        	nombre = getLine();
        
        Presentacion p  = new Presentacion(nombre);
        nombre_presentaciones.put(p.getNombre(), p);
        System.out.println("---");
    }
    
    public void editarPresentacion() throws IOException {
    	String nombre = "";
        System.out.println("Ingrese el nombre de la presentación a editar:");
        
        while(nombre.equals("")) 
        	nombre = getLine();
        
        Presentacion p = buscarPorNombre(nombre);
        if(p == null) {
        	System.out.println("Error: Presentación no encontrada");
          char c;
          do 
            System.out.println("Desea ver las presentaciones disponibles? (S/n)");
          while ( !((c = getChar()) != 0 ||
                     c != '\n' ||
                     c != 's'  ||
                     c != 'S'  ||
                     c != 'n'  ||
                     c != 'N')
              );

          switch(c) { 
            case 'S':
            case 's':
            case '\n':
              mostrarPresentaciones();
              editarPresentacion();
          }
        	return;
        }
        
        MenuEditar submenu = new MenuEditar(p);
        
        char c = '\0';
        
        while (c != '6') {
        	submenu.mostrar();
            c = getChar();
            
            switch(c) {
            case '1':
                submenu.editarExpositor();
                break;
            case '2':
                submenu.editarDescripcion();
                break;
            case '3':
                submenu.editarFecha();
                break;
            case '4':
            	submenu.editarHora();
            	break;
            case '5':
                submenu.editarDuracion();
                break;
            }
        }
    }

    public void mostrarPresentaciones() throws IOException {
        for (Map.Entry<String, Presentacion> p: nombre_presentaciones.entrySet())
            p.getValue().mostrar();
        if (nombre_presentaciones.size() == 0)

          System.out.println("No se encontraron " +
              "presentaciones");
        else 
          System.out.println("Mostrando presentaciones:\n---");
        
        System.out.println("---");
    }
    
    private Presentacion buscarPorNombre(String nombre) {
        Presentacion busqueda = nombre_presentaciones.get(nombre);
        return busqueda;
    }

    public void buscarPorNombre() throws IOException {
        String in = "";
        while (in.equals("")) {
            System.out.println("Ingrese el nombre a buscar:");
            in = getLine();
        }
        System.out.println("---");
    }

    public void buscarPorId() throws IOException {
        String in = "";
        while (in.equals("")) {
            System.out.println("Ingrese el ID a buscar:");
            in = getLine();
        }
        System.out.println("---");
    }

    public void buscarPorFecha () throws IOException {
        String in = "";
        while (in.equals("")) {
            System.out.println("Ingrese la fecha a buscar:");
            in = getLine();
        }
        System.out.println("---");
    }

    public void buscarPresentacion() throws IOException {
        char n = '\0';
        while (n != 'n' && n != 'i' && n != 'f') {
            System.out.println("n: Buscar por nombre");
            System.out.println("i: Buscar por ID");
            System.out.println("f: Buscar por fecha");
            System.out.println("Seleccione una opción:");
            n = getChar();
        }
        switch (n) {
            case 'n':
                buscarPorNombre();
                break;
            case 'i':
                buscarPorId();
                break;
            case 'f':
                buscarPorFecha();
                break;
        }
        
    }

	public void importarPresentaciones() {
		
	}
}
