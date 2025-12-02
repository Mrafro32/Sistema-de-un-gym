public class PilaAcciones {
    private String []acciones; //arreglo que almacena las acciones 
    private int top; //indice del tope de la pila 
    private int max; //capacidad maxima

    public PilaAcciones(int tamaño){
        max = tamaño;
        acciones = new String[max];
        top = -1; //inicia vacia
    }
    public void push(String accion){ //agrega accion a la pila
        if(top < max - 1){
            acciones[++top] = accion;
        }else{
            System.out.println("Pila llena");
        }
    }
    public String pop(){ // quitar y retorna la ultima accion
        if(top >= 0){ 
            return acciones[top--];
        }else{ //checa si la pila esta vacia
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
