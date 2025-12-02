public class NodoClase {
    String nombre; //nombre de la clase
    NodoClase siguiente; //referencia al siguiente nodo de la lista

    public NodoClase(String nombre){
        this.nombre = nombre;
        this.siguiente = null;
    }   
}
