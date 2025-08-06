package ui;

import javax.swing.*;

public class MainUsuarios {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("👤 Gestión de Usuarios - UTN");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(800, 600);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);

            UsuarioPanel panel = new UsuarioPanel();
            JScrollPane scroll = new JScrollPane(panel);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            ventana.add(scroll);
            ventana.setVisible(true);
        });
    }
}
