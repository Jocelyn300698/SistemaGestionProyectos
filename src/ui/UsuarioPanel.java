
package ui;

import dao.UsuarioDB;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioPanel extends JPanel {

    private JTextField txtNombre, txtCorreo, txtRol;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private UsuarioDB usuarioDB;

    public UsuarioPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255)); // fondo azul claro

        usuarioDB = new UsuarioDB();

        // Título
        JLabel titulo = new JLabel("👤 Gestión de Usuarios");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(new Color(0, 70, 140));
        titulo.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.PAGE_START);

        // Panel del formulario
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelForm.setBackground(new Color(240, 248, 255));

        txtNombre = new JTextField();
        txtCorreo = new JTextField();
        txtRol = new JTextField();

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Correo:"));
        panelForm.add(txtCorreo);
        panelForm.add(new JLabel("Rol:"));
        panelForm.add(txtRol);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(240, 248, 255));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        // Estilo de botones
        Dimension btnSize = new Dimension(120, 30);
        Color texto = Color.WHITE;

        btnAgregar.setPreferredSize(btnSize);
        btnAgregar.setBackground(new Color(0, 153, 76));
        btnAgregar.setForeground(texto);

        btnActualizar.setPreferredSize(btnSize);
        btnActualizar.setBackground(new Color(255, 153, 0));
        btnActualizar.setForeground(texto);

        btnEliminar.setPreferredSize(btnSize);
        btnEliminar.setBackground(new Color(204, 0, 0));
        btnEliminar.setForeground(texto);

        btnLimpiar.setPreferredSize(btnSize);
        btnLimpiar.setBackground(new Color(102, 102, 255));
        btnLimpiar.setForeground(texto);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Correo", "Rol"}, 0);
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setRowHeight(25);
        tablaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaUsuarios.getTableHeader().setBackground(new Color(0, 70, 140));
        tablaUsuarios.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de Usuarios"));

        // Armado de paneles
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(240, 248, 255));
        centro.add(panelForm, BorderLayout.NORTH);
        centro.add(panelBotones, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);

        cargarUsuarios();

        // Acciones
        btnAgregar.addActionListener(e -> {
            Usuario u = new Usuario(0,
                    txtNombre.getText(),
                    txtCorreo.getText(),
                    txtRol.getText());
            if (usuarioDB.agregarUsuario(u)) {
                JOptionPane.showMessageDialog(this, "Usuario agregado");
                cargarUsuarios();
                limpiarCampos();
            }
        });

        btnActualizar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila >= 0) {
                int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                Usuario u = new Usuario(
                        id,
                        txtNombre.getText(),
                        txtCorreo.getText(),
                        txtRol.getText());
                if (usuarioDB.actualizarUsuario(u)) {
                    JOptionPane.showMessageDialog(this, "Usuario actualizado");
                    cargarUsuarios();
                    limpiarCampos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila >= 0) {
                int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (usuarioDB.eliminarUsuario(id)) {
                        JOptionPane.showMessageDialog(this, "Usuario eliminado");
                        cargarUsuarios();
                        limpiarCampos();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Llenar campos al seleccionar fila
        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila >= 0) {
                txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtCorreo.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtRol.setText(modeloTabla.getValueAt(fila, 3).toString());
            }
        });
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0);
        List<Usuario> lista = usuarioDB.obtenerUsuarios();
        for (Usuario u : lista) {
            modeloTabla.addRow(new Object[]{
                    u.getIdUsuario(),
                    u.getNombre(),
                    u.getCorreo(),
                    u.getRol()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtRol.setText("");
        tablaUsuarios.clearSelection();
    }
}
