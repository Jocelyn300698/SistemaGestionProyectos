package dao;

import conexion.ConexionBD;
import modelo.Proyecto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDB {

    // Agregar un nuevo proyecto
    public boolean agregarProyecto(Proyecto proyecto) {
        String sql = "INSERT INTO proyecto(nombre, descripcion, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proyecto.getNombre());
            ps.setString(2, proyecto.getDescripcion());
            ps.setDate(3, new java.sql.Date(proyecto.getFechaInicio().getTime()));
            ps.setDate(4, new java.sql.Date(proyecto.getFechaFin().getTime()));
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar proyecto: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los proyectos
    public List<Proyecto> obtenerProyectos() {
        List<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyecto";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proyecto p = new Proyecto();
                p.setIdProyecto(rs.getInt("id_proyecto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFechaInicio(rs.getDate("fecha_inicio"));
                p.setFechaFin(rs.getDate("fecha_fin"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener proyectos: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar un proyecto
    public boolean actualizarProyecto(Proyecto proyecto) {
        String sql = "UPDATE proyecto SET nombre=?, descripcion=?, fecha_inicio=?, fecha_fin=? WHERE id_proyecto=?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proyecto.getNombre());
            ps.setString(2, proyecto.getDescripcion());
            ps.setDate(3, new java.sql.Date(proyecto.getFechaInicio().getTime()));
            ps.setDate(4, new java.sql.Date(proyecto.getFechaFin().getTime()));
            ps.setInt(5, proyecto.getIdProyecto());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar proyecto: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un proyecto
    public boolean eliminarProyecto(int idProyecto) {
        String sql = "DELETE FROM proyecto WHERE id_proyecto=?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProyecto);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar proyecto: " + e.getMessage());
            return false;
        }
    }
}
