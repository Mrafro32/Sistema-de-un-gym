public class ListaClasesCirculares {
    NodoClase cabeza;

    public ListaClasesCirculares(){
        cabeza = null;
    }
    public void agregarClase(String nombre){
        NodoClase  nuevo = new NodoClase(nombre);
        if(cabeza == null){
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
        }else{
            NodoClase temp = cabeza;
            while(temp.siguiente != cabeza){
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
            nuevo.siguiente = cabeza;
        }
    }
    public void mostrarClases(int vueltas){
        NodoClase temp = cabeza;
        if(cabeza == null) return;
        for(int i = 0; i < vueltas; i++){
            do{
                System.out.println("Clase: " + temp.nombre);
                temp = temp.siguiente;
            } while(temp != cabeza);
        }
    }
}
