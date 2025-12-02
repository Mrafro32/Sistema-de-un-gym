import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GimnasioGUI extends JFrame{
    private Gimnasio gimnasio;
    private PilaAcciones pilaAcciones;
    private JTextArea outputArea;

    public GimnasioGUI(){
        gimnasio = new Gimnasio(200);
        pilaAcciones = new PilaAcciones(200);

        setTitle("Sistema de gimnasio");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        
        JPanel panelBotones = new JPanel(new GridLayout(12, 1, 5, 5));
        JButton btnRegUsuario = new JButton("Registrar usuario");
        JButton btnMostrarUsuarios = new JButton("Mostrar usuarios");
        JButton btnRegPago = new JButton("Registrar pago");
        JButton btnVerHistorial = new JButton("Ver historial de pagos");
        JButton btnRegActividad = new JButton("Registrar actividad");
        JButton btnInscribir = new JButton("Inscribir usuario a actividad");
        JButton btnMostrarInscritos = new JButton("Mostrar inscritos");
        JButton btnDeshacer = new JButton("Deshacer ultima accion");
        JButton btnRegEntrenador = new JButton("Registrar entrenador");
        JButton btnMostrarEntrenadores = new JButton("Mostrar entrenadores");
        JButton btnAsignarEntrenador = new JButton("Asignar entrenador a actividad");
        JButton btnLimpiar = new JButton("Limpiar la pantalla");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnRegUsuario);
        panelBotones.add(btnMostrarUsuarios);
        panelBotones.add(btnRegPago);
        panelBotones.add(btnVerHistorial);
        panelBotones.add(btnRegActividad);
        panelBotones.add(btnInscribir);
        panelBotones.add(btnMostrarInscritos);
        panelBotones.add(btnDeshacer);
        panelBotones.add(btnRegEntrenador);
        panelBotones.add(btnMostrarEntrenadores);
        panelBotones.add(btnAsignarEntrenador);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);

        panelPrincipal.add(panelBotones, BorderLayout.WEST);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        add(panelPrincipal);
        setVisible(true);

        btnRegUsuario.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre del usuario:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));
            Usuario u = new Usuario(nombre, edad, id);
            gimnasio.agregarUsuarios(u);
            pilaAcciones.push("agregar_usuario:" + id);
            outputArea.append("Usuario registrado: " + nombre + "\n");
        });

        btnMostrarUsuarios.addActionListener(e -> {
            outputArea.append("Lista de usuarios:\n");
            for (int i = 0; i < 100; i++) {
                Usuario u = i < gimnasio.getUsuarios().length ? gimnasio.getUsuarios()[i] : null;
                if (u != null) {
                    outputArea.append(u.toString() + "\n");
                }
            }
        });
        btnRegPago.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario:"));
            double monto = Double.parseDouble(JOptionPane.showInputDialog("Monto del pago:"));
            int mes = Integer.parseInt(JOptionPane.showInputDialog("Mes (0-11):"));
            if (gimnasio.registrarPago(id, monto, mes)) {
                pilaAcciones.push("pago:" + id + ":" + mes);
                outputArea.append("Pago registrado.\n");
            } else {
                outputArea.append("Error al registrar pago.\n");
            }
        });

        btnVerHistorial.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario:"));
            outputArea.append("Historial de pagos del usuario " + id + ":\n");
            gimnasio.verHistorialPagosGUI(id, outputArea);
        });

        btnRegActividad.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre de la actividad:");
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Capacidad maxima:"));
            gimnasio.registrarActividad(nombre, capacidad);
            pilaAcciones.push("actividad:" + nombre);
            outputArea.append("Actividad registrada: " + nombre + "\n");
        });

        btnInscribir.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario:"));
            String actividad = JOptionPane.showInputDialog("Nombre de la actividad:");
            Usuario u = gimnasio.inscribirEnActividad(id, actividad);
            if (u != null) {
                pilaAcciones.push("inscribir:" + actividad + ":" + id);
                outputArea.append("Usuario inscrito en actividad: " + actividad + "\n");
            }else{
                outputArea.append("No se pudo inscribir el usuario");
            }
        });

        btnMostrarInscritos.addActionListener(e -> {
            String actividad = JOptionPane.showInputDialog("Nombre de la actividad:");
            outputArea.append("Inscritos en " + actividad + ":\n");
            gimnasio.mostrarInscritosGUI(actividad, outputArea);
        });

        btnDeshacer.addActionListener(e -> {
            gimnasio.deshacer(pilaAcciones);
            outputArea.append("Última acción deshecha.\n");
        });

        btnRegEntrenador.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre del entrenador:");
            gimnasio.registrarEntrenador(nombre);
            pilaAcciones.push("registrar_entrenador:" + nombre);
            outputArea.append("Entrenador registrado: " + nombre + "\n");
        });

        btnMostrarEntrenadores.addActionListener(e -> {
            outputArea.append("Lista de entrenadores:\n");
            gimnasio.mostrarEntrenadoresGUI(outputArea);
        });

        btnAsignarEntrenador.addActionListener(e -> {
            String actividad = JOptionPane.showInputDialog("Nombre de la actividad:");
            String entrenador = JOptionPane.showInputDialog("Nombre del entrenador:");
            boolean asignado = gimnasio.asignarEntrenadorGUI(actividad, entrenador);
            if (asignado) {
                pilaAcciones.push("asignar_entrenador:" + actividad + ":" + entrenador);
                outputArea.append("Entrenador asignado a la actividad\n");
            } else {
                outputArea.append("No se pudo asignar entrenador\n");
            }

        });

        btnLimpiar.addActionListener(e -> outputArea.setText(""));
        btnSalir.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GimnasioGUI::new);
    }
}
