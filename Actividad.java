public class Actividad {
    public ColaUsuario cola;
    public NodoEntrenador entrenador;
    public String nombre;

    public Actividad(String nombre, int capacidad){
        this.nombre = nombre;
        this.cola = new ColaUsuario(capacidad);
        this.entrenador = null;
    }
}
