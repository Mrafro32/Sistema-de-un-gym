import javax.swing.JTextArea;

public class ColaUsuario {
    private Usuario[] cola;
    private int frente, fin, tamaño, capacidad;

    public ColaUsuario(int capacidad){
        this.capacidad = capacidad;
        cola = new Usuario[capacidad];
        frente = 0;
        fin = 0;
        tamaño = 0;
    }

    public boolean enqueue(Usuario u){
        if(tamaño == capacidad) return false;
        cola[fin] = u;
        fin = (fin + 1) % capacidad;
        tamaño++;
        return true;
    }

    public void eliminarUsuario(int id){
        if(tamaño == 0) return;
        int cont = 0;
        Usuario[] nueva = new Usuario[capacidad];
        int index = 0;
        for(int i = 0; i < tamaño; i++){
            Usuario u = cola[(frente + i) % capacidad];
            if(u.getId() != id){
                nueva[index++] = u;
                cont++;
            }
        }
        cola = nueva;
        frente = 0;
        fin = cont;
        tamaño = cont;
    }

    public void mostrarColaGUI(JTextArea area){
        if(tamaño == 0){
            area.append("No hay inscritos.\n");
            return;
        }
        for(int i = 0; i < tamaño; i++){
            Usuario u = cola[(frente + i) % capacidad];
            area.append("ID: " + u.getId() + ", Nombre: " + u.getNombre() + "\n");
        }
    }
}
