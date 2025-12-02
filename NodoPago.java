public class NodoPago {
    double monto; //monto del pago reaalizado
    String fecha; //fecha en la que se hizo el pago
    NodoPago siguiente; // referencia al siguiente nodo de pago

    public NodoPago(double monto, String fecha){ // constructor que inicializa el pago
        this.monto = monto;
        this.fecha = fecha;
        this.siguiente = null;
    }
    
}
