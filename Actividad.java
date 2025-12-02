public class Actividad {
    ColaUsuario cola; // cola circular para gestionar usuarios inscritos
    NodoEntrenador entrenador; // entrenador asignado a la actividad
    String nombre; //nombre de la actividad
    int capacidad; // capacidad max de participantes

    public Actividad(String nombre, int capacidad){ //constructor para inicializar la actividad con nombre y capacidad
        this.nombre = nombre; 
        this.cola = new ColaUsuario(capacidad);// inicializa la cola con la capacidad
        this.entrenador = null; //inicialmente no tiene entrenador asignado
        this.capacidad = capacidad;
    }
}
