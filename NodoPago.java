public class NodoPago {
    double monto;
    String fecha;
    NodoPago siguiente;

    public NodoPago(double monto, String fecha){
        this.monto = monto;
        this.fecha = fecha;
        this.siguiente = null;
    }
    
}
