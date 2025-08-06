package ui;

import dao.ProyectoDB;
import dao.TareaDB;
import dao.UsuarioDB;
import modelo.Proyecto;
import modelo.Tarea;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class TareaPanel extends JPanel {

    private JTextField txtTitulo, txtDescripcion, txtFechaInicio, txtFechaFin;
    private JComboBox<Proyecto> comboProyecto;
    private JComboBox<Usuario> comboUsuario;
    private JComboBox<String> comboEstado;
    private JSlider sliderAvance;
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;
    private TareaDB tareaDB;
    private ProyectoDB proyectoDB;
    private UsuarioDB usuarioDB;

    public TareaPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        tareaDB = new TareaDB();
        proyectoDB = new ProyectoDB();
        usuarioDB = new UsuarioDB();

        JLabel titulo = new JLabel("Gestión de Tareas");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(new Color(0, 70, 140));
        titulo.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.PAGE_START);

        // Formulario
        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBackground(new Color(240, 248, 255));
        form.setBorder(new EmptyBorder(10, 20, 10, 20));

        txtTitulo = new JTextField();
        txtDescripcion = new JTextField();
        txtFechaInicio = new JTextField(); // yyyy-MM-dd
        txtFechaFin = new JTextField();
        comboProyecto = new JComboBox<>();
        comboUsuario = new JComboBox<>();
        comboEstado = new JComboBox<>(new String[]{"Pendiente", "En progreso", "Completada"});
        sliderAvance = new JSlider(0, 100, 0);

        form.add(new JLabel("Título:")); form.add(txtTitulo);
        form.add(new JLabel("Descripción:")); form.add(txtDescripcion);
        form.add(new JLabel("Proyecto:")); form.add(comboProyecto);
        form.add(new JLabel("Usuario asignado:")); form.add(comboUsuario);
        form.add(new JLabel("Estado:")); form.add(comboEstado);
        form.add(new JLabel("Fecha inicio (yyyy-MM-dd):")); form.add(txtFechaInicio);
        form.add(new JLabel("Fecha fin (yyyy-MM-dd):")); form.add(txtFechaFin);

        JPanel avancePanel = new JPanel(new BorderLayout());
        avancePanel.add(new JLabel("Porcentaje de avance:"), BorderLayout.NORTH);
        avancePanel.add(sliderAvance, BorderLayout.CENTER);

        // Botón guardar
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar Tarea");
        btnGuardar.setBackground(new Color(0, 153, 76));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setPreferredSize(new Dimension(150, 30));
        panelBotones.setBackground(new Color(240, 248, 255));
        panelBotones.add(btnGuardar);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Título", "Proyecto", "Usuario", "Estado", "Inicio", "Fin", "%"}, 0);
        tablaTareas = new JTable(modeloTabla);
        tablaTareas.setRowHeight(25);
        tablaTareas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaTareas.getTableHeader().setBackground(new Color(0, 70, 140));
        tablaTareas.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollTabla = new JScrollPane(tablaTareas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Tareas"));

        // Ensamblar
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(240, 248, 255));
        centro.add(form, BorderLayout.NORTH);
        centro.add(avancePanel, BorderLayout.CENTER);
        centro.add(panelBotones, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);

        cargarCombos();
        cargarTareas();

        // Acción: guardar
        btnGuardar.addActionListener(e -> {
            try {
                Proyecto p = (Proyecto) comboProyecto.getSelectedItem();
                Usuario u = (Usuario) comboUsuario.getSelectedItem();

                Tarea t = new Tarea(0,
                        p.getIdProyecto(),
                        u.getIdUsuario(),
                        txtTitulo.getText(),
                        txtDescripcion.getText(),
                        comboEstado.getSelectedItem().toString(),
                        Date.valueOf(txtFechaInicio.getText()),
                        Date.valueOf(txtFechaFin.getText()),
                        sliderAvance.getValue()
                );

                if (tareaDB.agregarTarea(t)) {
                    JOptionPane.showMessageDialog(this, "Tarea guardada exitosamente.");
                    cargarTareas();
                    limpiarCampos();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar tarea: " + ex.getMessage());
            }
        });
    }

    private void cargarCombos() {
        comboProyecto.removeAllItems();
        comboUsuario.removeAllItems();
        for (Proyecto p : proyectoDB.obtenerProyectos()) {
            comboProyecto.addItem(p);
        }
        for (Usuario u : usuarioDB.obtenerUsuarios()) {
            comboUsuario.addItem(u);
        }
    }

    private void cargarTareas() {
        modeloTabla.setRowCount(0);
        List<Tarea> tareas = tareaDB.obtenerTareas();
        for (Tarea t : tareas) {
            modeloTabla.addRow(new Object[]{
                    t.getIdTarea(),
                    t.getTitulo(),
                    t.getIdProyecto(),
                    t.getIdUsuario(),
                    t.getEstado(),
                    t.getFechaInicio(),
                    t.getFechaFin(),
                    t.getPorcentajeAvance()
            });
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        sliderAvance.setValue(0);
    }
}
