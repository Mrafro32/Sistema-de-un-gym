public class ListaClasesCirculares {
    NodoClase cabeza; //referencia al primer nodo de la lista

    public ListaClasesCirculares(){
        cabeza = null; //inicialmente la lista esta vacia
    }
    public void agregarClase(String nombre){ // metodo para agregar una nueva clase a la lista circular
        NodoClase  nuevo = new NodoClase(nombre);
        if(cabeza == null){ // si la lista esta vacia, el nuevo nodo apunta a si mismo
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
        }else{ // si hay nodos, se recirre hasta el ultimo y se inserta el nuevo al final 
            NodoClase temp = cabeza;
            while(temp.siguiente != cabeza){ //recorrer hasta llegarl al ultimo nodo
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo; //apuntar el ultimo nodo al nuevo
            nuevo.siguiente = cabeza; //el nuevo nodo cierra el ciruclo apuntando a la cabeza
        }
    }
    public void mostrarClases(int vueltas){ //metodo para mostrar clases en la lista circular
        NodoClase temp = cabeza;
        if(cabeza == null) return; // si la lista esta vacia no se hace nada
        for(int i = 0; i < vueltas; i++){
            do{
                System.out.println("Clase: " + temp.nombre);
                temp = temp.siguiente;
            } while(temp != cabeza); //recorre hsata voler al nodo inicial
        }
    }
}
