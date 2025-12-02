public class ListaPagos {
    NodoPago cabeza; //primer nodo de la lista
    
    public ListaPagos(){ // constructor vacio
        cabeza = null;
    }
    public void agregarPago(double monto, String fecha){ // metodo para agregar un nuevo pago al final de lista
        NodoPago nuevo = new NodoPago(monto, fecha);
        if(cabeza == null){
            cabeza = nuevo; // si la lista esta vacia, el nuevo nodo es la cabeza
        }else{
            NodoPago temp = cabeza;
            while(temp.siguiente != null){
                temp = temp.siguiente; // recorre hasta el ultimo nodo
            }
            temp.siguiente = nuevo; // agrega el nuevo nodo al final
        }
    }
    public void mostrarPago(){ // metodo para mostrar pagos de la lista
        NodoPago temp = cabeza;
        while(temp != null){
            System.out.println("Monto: " + temp.monto + "Fecha: " + temp.fecha);
            temp = temp.siguiente;
        }
    }
}
