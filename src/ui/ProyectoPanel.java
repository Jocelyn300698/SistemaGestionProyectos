package ui;

import dao.ProyectoDB;
import modelo.Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class ProyectoPanel extends JPanel {

    private JTextField txtNombre, txtDescripcion, txtFechaInicio, txtFechaFin;
    private JTable tablaProyectos;
    private DefaultTableModel modeloTabla;
    private ProyectoDB proyectoDB;

    public ProyectoPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255)); // fondo azul claro

        proyectoDB = new ProyectoDB();

        // Título
        JLabel titulo = new JLabel("📋 Gestión de Proyectos");
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
        txtDescripcion = new JTextField();
        txtFechaInicio = new JTextField();  // formato: yyyy-MM-dd
        txtFechaFin = new JTextField();

        panelForm.add(new JLabel("Nombre del Proyecto:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Descripción del Proyecto:"));
        panelForm.add(txtDescripcion);
        panelForm.add(new JLabel("Fecha Inicio (yyyy-MM-dd):"));
        panelForm.add(txtFechaInicio);
        panelForm.add(new JLabel("Fecha Fin (yyyy-MM-dd):"));
        panelForm.add(txtFechaFin);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(240, 248, 255));
        JButton btnAgregar = new JButton("Guardar"); // antes decía Agregar

        btnAgregar.setPreferredSize(new Dimension(120, 30));
        btnAgregar.setBackground(new Color(0, 153, 76));
        btnAgregar.setForeground(Color.WHITE);

        panelBotones.add(btnAgregar);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción", "Inicio", "Fin"}, 0);
        tablaProyectos = new JTable(modeloTabla);
        tablaProyectos.setRowHeight(25);
        tablaProyectos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaProyectos.getTableHeader().setBackground(new Color(0, 70, 140));
        tablaProyectos.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollTabla = new JScrollPane(tablaProyectos);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de Proyectos"));

        // Armado de paneles
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(240, 248, 255));
        centro.add(panelForm, BorderLayout.NORTH);
        centro.add(panelBotones, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);

        cargarProyectos();

        // Acción: Guardar
        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String descripcion = txtDescripcion.getText().trim();
                Date fechaInicio = Date.valueOf(txtFechaInicio.getText().trim());
                Date fechaFin = Date.valueOf(txtFechaFin.getText().trim());

                Proyecto nuevo = new Proyecto(0, nombre, descripcion, fechaInicio, fechaFin);
                if (proyectoDB.agregarProyecto(nuevo)) {
                    JOptionPane.showMessageDialog(this, "✅ Proyecto guardado exitosamente");
                    cargarProyectos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "⚠️ No se pudo guardar el proyecto");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        // Autorellenar campos al seleccionar fila
        tablaProyectos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaProyectos.getSelectedRow();
            if (fila >= 0) {
                txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtDescripcion.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtFechaInicio.setText(modeloTabla.getValueAt(fila, 3).toString());
                txtFechaFin.setText(modeloTabla.getValueAt(fila, 4).toString());
            }
        });
    }

    private void cargarProyectos() {
        modeloTabla.setRowCount(0);
        List<Proyecto> lista = proyectoDB.obtenerProyectos();
        for (Proyecto p : lista) {
            modeloTabla.addRow(new Object[]{
                    p.getIdProyecto(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getFechaInicio(),
                    p.getFechaFin()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        tablaProyectos.clearSelection();
    }
}
