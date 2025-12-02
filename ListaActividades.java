public class ListaActividades {
    NodoActividad cabeza;
    NodoActividad cola;

    public ListaActividades(){
        cabeza = null;
        cola = null;

    }
    public void agregarActividad(String nombreClase, String fecha){
        NodoActividad nuevo = new NodoActividad(nombreClase, fecha);
        if(cabeza == null){
            cabeza = cola = nuevo;
        }else{
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }
    public void mostrarActividades(){
        NodoActividad temp = cabeza;
        while(temp != null){
            System.out.println("Clase: " + temp.nombreClase + "Fecha: " + temp.fecha);
            temp = temp.siguiente;
        }
    }
}
