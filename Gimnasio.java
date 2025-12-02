import java.util.HashMap;
import java.util.Map;

public class Gimnasio {
    private Usuario[] usuarios;
    private int contadorUsuarios = 0;
    private double[][] pagos;
    private ListaEntrenadores entrenadores;
    private Map<String, Actividad> actividades;

    public Gimnasio(int maxUsuarios){
        usuarios = new Usuario[maxUsuarios];
        pagos = new double[maxUsuarios][12];
        entrenadores = new ListaEntrenadores();
        actividades = new HashMap<>();
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void agregarUsuarios(Usuario u){
        if(contadorUsuarios < usuarios.length){
            usuarios[contadorUsuarios] = u;
            contadorUsuarios++;
        } else {
            System.out.println("Capacidad maxima de usuarios alcanzada");
        }
    }

    public boolean registrarPago(int id, double monto, int mes) {
        int idx = Usuario.buscarUsuario(usuarios, id);
        if(idx == -1){
            System.out.println("Usuario no encontrado");
            return false;
        }
        if(mes < 0 || mes > 11){
            System.out.println("Mes invalido");
            return false;
        }
        pagos[idx][mes] = monto;
        return true;
    }
    public void verHistorialPagosGUI(int id, javax.swing.JTextArea area) {
        int idx = Usuario.buscarUsuario(usuarios, id);
        if (idx != -1) {
            Usuario u = usuarios[idx];
            area.append("Historial de pagos de " + u.getNombre() + ":\n");
            for (int m = 0; m < 12; m++) {
                if (pagos[idx][m] > 0) {
                    area.append("Mes " + m + ": $" + pagos[idx][m] + "\n");
                }
            }
        } else {
            area.append("Usuario no encontrado.\n");
        }
    }

    public void mostrarInscritosGUI(String nombreActividad, javax.swing.JTextArea area) {
        Actividad act = actividades.get(nombreActividad);
        if (act == null) {
            area.append("La actividad no existe.\n");
            return;
        }
        area.append("Inscritos en " + nombreActividad + ":\n");
        act.cola.mostrarColaGUI(area);
    }

    public void mostrarEntrenadoresGUI(javax.swing.JTextArea area) {
        entrenadores.mostrarEntrenadoresGUI(area);
    }

    public void registrarActividad(String nombre, int capacidad) {
        if (!actividades.containsKey(nombre)) {
            actividades.put(nombre, new Actividad(nombre, capacidad));
            System.out.println("Actividad registrada: " + nombre);
        } else {
            System.out.println("La actividad ya existe.");
        }
    }

    public Usuario inscribirEnActividad(int id, String nombreActividad) {
        Usuario u = buscarUsuario(id);
        if(u == null){
            System.out.println("Usuario con el ID: " + id + " no existe");
            return null;
        }
        Actividad act = actividades.get(nombreActividad);
        if(act == null){
            System.out.println("Actividad no encontrada");
            return null;
        }

        boolean agregado = act.cola.enqueue(u);

        if(agregado){
            System.out.println("Usuario: " + u.getNombre() + " inscrito en: " + nombreActividad);
            return u;
        } else {
            System.out.println("No se pudo inscribir, actividad llena");
            return null;
        }
    }

    public void registrarEntrenador(String nombre){
        entrenadores.agregarEntrenador(nombre);
        System.out.println("Entrenador registrado: " + nombre);
    }

    public void asignarEntrenador(String actividadNombre, String entrenadorNombre){
        Actividad act = actividades.get(actividadNombre);

        if(act == null){
            System.out.println("Actividad no encontrada");
            return;
        }

        NodoEntrenador temp = entrenadores.buscar(entrenadorNombre);
        if(temp == null){
            System.out.println("Entrenador no encontrado");
            return;
        }

        act.entrenador = temp;
        System.out.println("Entrenador: " + entrenadorNombre + " asignado a la actividad: " + actividadNombre);
    }
    private Usuario buscarUsuario(int id) {
        for (int i = 0; i < contadorUsuarios; i++) {
            if (usuarios[i].getId() == id) {
                return usuarios[i];
            }
        }
        return null;
    }

    public void deshacer(PilaAcciones pila){
        String accion = pila.pop();
        if(accion == null){
            System.out.println("No hay acciones por deshacer");
            return;
        }

        String[] partes = accion.split(":");
        String tipo = partes[0];

        switch(tipo){
            case "agregar_usuario" -> {
                int id = Integer.parseInt(partes[1].trim());
                eliminarUsuario(id);
                System.out.println("Deshecho: Usuario eliminado");
            }
            case "pago" -> {
                int idPago = Integer.parseInt(partes[1].trim());
                int mes = Integer.parseInt(partes[2].trim());
                revertirPago(idPago, mes);
                System.out.println("Deshecho: Pago eliminado");
            }
            case "actividad" -> {
                String act = partes[1].trim();
                actividades.remove(act);
                System.out.println("Deshecho: Actividad eliminada");
            }
            case "inscribir" -> {
                String act = partes[1].trim();
                int idUsuario = Integer.parseInt(partes[2].trim());
                Actividad actividad = actividades.get(act);
                if(actividad != null){
                    actividad.cola.eliminarUsuario(idUsuario);
                }
                System.out.println("Deshecho: Inscripción eliminada");
            }
            case "registrar_entrenador" -> {
                String nombre = partes[1].trim();
                entrenadores.eliminarEntrenador(nombre);
                System.out.println("Deshecho: Entrenador eliminado");
            }
            case "asignar_entrenador" -> {
                String act = partes[1].trim();
                Actividad actividad = actividades.get(act);
                if(actividad != null){
                    actividad.entrenador = null;
                }
                System.out.println("Deshecho: Entrenador desasignado");
            }
            default -> System.out.println("Acción desconocida: " + tipo);
        }
    }
    private void eliminarUsuario(int id){
        for(int i = 0; i < contadorUsuarios; i++ ){
            if(usuarios[i].getId() == id){
                for(int j = i; j < contadorUsuarios - 1; j++){
                    usuarios[j]  = usuarios[j + 1];
                }
                contadorUsuarios--;
                return;
            }
        } 
    }
    
    public boolean asignarEntrenadorGUI(String actividadNombre, String entrenadorNombre){
        Actividad act = actividades.get(actividadNombre);
        if(act == null) return false;

        NodoEntrenador temp = entrenadores.buscar(entrenadorNombre);
        if(temp == null) return false;

        act.entrenador = temp;
        return true;
    }


    private void revertirPago(int id, int mes){
        int idx = Usuario.buscarUsuario(usuarios, id);
        if(idx != -1){
            pagos[idx][mes] = 0;
        }
    }
}
