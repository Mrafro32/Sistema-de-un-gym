public class PilaAcciones {
    private String []acciones;
    private int top;
    private int max;

    public PilaAcciones(int tamaño){
        max = tamaño;
        acciones = new String[max];
        top = -1;
    }
    public void push(String accion){
        if(top < max - 1){
            acciones[++top] = accion;
        }else{
            System.out.println("Pila llena");
        }
    }
    public String pop(){
        if(top >= 0){
            return acciones[top--];
        }else{
            System.out.println("Pila vacia");
            return null;
        }
    }
    public void mostrarPila(){
        for(int i = top; i >= 0; i--){
            System.out.println(acciones[i]);
        }
    }

}
