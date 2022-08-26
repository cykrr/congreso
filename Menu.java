import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Menu {
    static private BufferedReader br;
    static private LinkedList<Presentacion> presentaciones;

    Menu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        presentaciones = new LinkedList<Presentacion>();
    }

    public String getLine() throws IOException {
        return br.readLine();
    }
    public int getInt() throws IOException {
        return Integer.parseInt(getLine());
    }
    public char getChar() throws IOException {
        return getLine().charAt(0);
    }

    public void mostrar() {
        System.out.println("Presentaciones del congreso internacional:");
        System.out.println("a: crear presentación");
        System.out.println("x: eliminar presentación");
        System.out.println("l: listar presentaciones");
        System.out.println("b: buscar presentación");
        System.out.println("---");
    }

    public void crearPresentacion() throws IOException {
        System.out.println("Ingrese el nombre de la presentación:");
        String nombre = "";
        while (nombre.equals("")) nombre = getLine();
        Presentacion p  = new Presentacion(nombre, presentaciones.size());
        presentaciones.add(p);
        System.out.println("---");
    }

    public void mostrarPresentaciones() throws IOException {
        for (Presentacion p: presentaciones)
            p.mostrar();
        System.out.println("---");
    }

    public void buscarPorNombre() {

    }

    public void buscarPorId() {

    }

    public void buscarPorFecha () {

    }

    public void buscarPresentacion() throws IOException {
        char n = '\0';
        while (n != 'n' || n != 'i' || n != 'f') {
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
}
