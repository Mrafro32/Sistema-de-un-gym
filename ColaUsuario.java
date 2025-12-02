import javax.swing.JTextArea;

public class ColaUsuario {
    private Usuario[] cola; //arreglo que representa la cola
    private int frente, fin, tamaño, capacidad; //variable de control de la cola

    public ColaUsuario(int capacidad){ //constructor que defina la capacidad max de la cola
        this.capacidad = capacidad;
        cola = new Usuario[capacidad];
        frente = 0;
        fin = 0;
        tamaño = 0;
    }

    public boolean enqueue(Usuario u){ // agregar un usuario al final de la cola
        if(tamaño == capacidad) return false; // no se puede agregar si la cola esta llena 
        cola[fin] = u;
        fin = (fin + 1) % capacidad; //mueve el indice de fin circularmente 
        tamaño++;
        return true;
    }

    public void eliminarUsuario(int id){ // elimina el usuario por ID
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

    public void mostrarColaGUI(JTextArea area){ //muestra los usuarios en la GUI
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
