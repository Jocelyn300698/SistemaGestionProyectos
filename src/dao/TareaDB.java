package dao;

import conexion.ConexionBD;
import modelo.Tarea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDB {

    // Agregar una nueva tarea
    public boolean agregarTarea(Tarea tarea) {
        String sql = "INSERT INTO tarea(id_proyecto, id_usuario, titulo, descripcion, estado, fecha_inicio, fecha_fin, porcentaje_avance) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tarea.getIdProyecto());
            ps.setInt(2, tarea.getIdUsuario());
            ps.setString(3, tarea.getTitulo());
            ps.setString(4, tarea.getDescripcion());
            ps.setString(5, tarea.getEstado());
            ps.setDate(6, new java.sql.Date(tarea.getFechaInicio().getTime()));
            ps.setDate(7, new java.sql.Date(tarea.getFechaFin().getTime()));
            ps.setInt(8, tarea.getPorcentajeAvance());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar tarea: " + e.getMessage());
            return false;
        }
    }

    // Obtener todas las tareas
    public List<Tarea> obtenerTareas() {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarea";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tarea t = new Tarea();
                t.setIdTarea(rs.getInt("id_tarea"));
                t.setIdProyecto(rs.getInt("id_proyecto"));
                t.setIdUsuario(rs.getInt("id_usuario"));
                t.setTitulo(rs.getString("titulo"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setEstado(rs.getString("estado"));
                t.setFechaInicio(rs.getDate("fecha_inicio"));
                t.setFechaFin(rs.getDate("fecha_fin"));
                t.setPorcentajeAvance(rs.getInt("porcentaje_avance"));
                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener tareas: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar una tarea
    public boolean actualizarTarea(Tarea tarea) {
        String sql = "UPDATE tarea SET id_proyecto=?, id_usuario=?, titulo=?, descripcion=?, estado=?, " +
                     "fecha_inicio=?, fecha_fin=?, porcentaje_avance=? WHERE id_tarea=?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tarea.getIdProyecto());
            ps.setInt(2, tarea.getIdUsuario());
            ps.setString(3, tarea.getTitulo());
            ps.setString(4, tarea.getDescripcion());
            ps.setString(5, tarea.getEstado());
            ps.setDate(6, new java.sql.Date(tarea.getFechaInicio().getTime()));
            ps.setDate(7, new java.sql.Date(tarea.getFechaFin().getTime()));
            ps.setInt(8, tarea.getPorcentajeAvance());
            ps.setInt(9, tarea.getIdTarea());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e.getMessage());
            return false;
        }
    }

    // Eliminar una tarea
    public boolean eliminarTarea(int idTarea) {
        String sql = "DELETE FROM tarea WHERE id_tarea=?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTarea);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar tarea: " + e.getMessage());
            return false;
        }
    }
}
