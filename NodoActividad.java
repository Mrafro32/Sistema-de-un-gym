public class NodoActividad {
    String nombreClase;
    String fecha;
    NodoActividad siguiente;
    NodoActividad anterior;

    public NodoActividad(String nombreClase, String fecha){
        this.nombreClase = nombreClase;
        this.fecha = fecha;
        this.siguiente = null;
        this.anterior = null;
    }
    
}
