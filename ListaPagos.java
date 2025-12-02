public class ListaPagos {
    NodoPago cabeza;
    
    public ListaPagos(){
        cabeza = null;
    }
    public void agregarPago(double monto, String fecha){
        NodoPago nuevo = new NodoPago(monto, fecha);
        if(cabeza == null){
            cabeza = nuevo;
        }else{
            NodoPago temp = cabeza;
            while(temp.siguiente != null){
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }
    public void mostrarPago(){
        NodoPago temp = cabeza;
        while(temp != null){
            System.out.println("Monto: " + temp.monto + "Fecha: " + temp.fecha);
            temp = temp.siguiente;
        }
    }
}
