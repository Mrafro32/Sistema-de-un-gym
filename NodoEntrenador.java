import javax.swing.JTextArea;

public class NodoEntrenador {
    String nombre;
    NodoEntrenador siguiente;
    NodoEntrenador anterior;

    public NodoEntrenador(String nombre) {
        this.nombre = nombre;
        this.siguiente = this;
        this.anterior = this;
    }

    public String getNombre(){
    return nombre;
    }
}
class ListaEntrenadores {
    NodoEntrenador cabeza;

    public ListaEntrenadores() {
        cabeza = null;
    }

    public void agregarEntrenador(String nombre) {
        NodoEntrenador nuevo = new NodoEntrenador(nombre);
        if(cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoEntrenador cola = cabeza.anterior;
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
        }
    }

    public NodoEntrenador buscar(String nombre){
        if(cabeza == null) return null;
        NodoEntrenador temp = cabeza;
        do {
            if(temp.nombre.equals(nombre)) return temp;
            temp = temp.siguiente;
        } while(temp != cabeza);
        return null;
    }

    public void mostrarEntrenadoresGUI(JTextArea area){
        if(cabeza == null) return;
        NodoEntrenador temp = cabeza;
        do {
            area.append("Entrenador: " + temp.nombre + "\n");
            temp = temp.siguiente;
        } while(temp != cabeza);
    }

    public void eliminarEntrenador(String nombre) {
        if (cabeza == null) return;
        NodoEntrenador actual = cabeza;
        do {
            if (actual.nombre.equals(nombre)) {
                if (actual == cabeza && cabeza.siguiente == cabeza) {
                    cabeza = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    if (actual == cabeza) cabeza = actual.siguiente;
                }
                return;
            }
            actual = actual.siguiente;
        } while(actual != cabeza);
    }

}


