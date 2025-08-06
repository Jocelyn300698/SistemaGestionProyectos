package ui;

import javax.swing.*;

public class MainSistema {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("📊 Sistema de Gestión de Proyectos - UTN");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(1000, 750);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);

            // Crear el contenedor de pestañas
            JTabbedPane pestañas = new JTabbedPane();

            // Agregar cada panel
            pestañas.addTab("🗂 Proyectos", new JScrollPane(new ProyectoPanel()));
            pestañas.addTab("👤 Usuarios", new JScrollPane(new UsuarioPanel()));
            pestañas.addTab("✅ Tareas", new JScrollPane(new TareaPanel()));

            // Añadir las pestañas a la ventana
            ventana.add(pestañas);
            ventana.setVisible(true);
        });
    }
}
