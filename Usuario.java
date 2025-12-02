public class Usuario {
    private String nombre;
    private int edad;
    private int id;

    public Usuario(String nombre, int edad, int id){
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
    }

    public String getNombre(){ return nombre; }
    public int getId(){ return id; }

    @Override
    public String toString(){
        return "ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad;
    }

    public static int buscarUsuario(Usuario[] usuarios, int id){
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null && usuarios[i].getId() == id) return i;
        }
        return -1;
    }
}

