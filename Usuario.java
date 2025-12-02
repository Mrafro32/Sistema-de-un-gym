public class Usuario {
    private String nombre; //nombre del usuario    
    private int edad; // edad del usuario
    private int id; // ID del usuario

    public Usuario(String nombre, int edad, int id){  // constructor para inicializar un usuario
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
    }

    public String getNombre(){ return nombre; } //getters
    public int getId(){ return id; }

    @Override
    public String toString(){ //representacion en texto del usuario
        return "ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad;
    }

    public static int buscarUsuario(Usuario[] usuarios, int id){ // metodo pestatico para buscar un usuario por su ID en un arreglo
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null && usuarios[i].getId() == id) return i;
        }
        return -1;
    }
}

