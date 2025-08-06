/* quitar el comentariado para correr este main
package ui;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Sistema de Gestión de Proyectos - UTN");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(900, 700); // Aumentado el alto
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);

            ProyectoPanel panel = new ProyectoPanel();
            JScrollPane scroll = new JScrollPane(panel);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            ventana.add(scroll);
            ventana.setVisible(true);
        });
    }
}
*/
