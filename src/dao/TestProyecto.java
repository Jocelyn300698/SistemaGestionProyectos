/*package dao;
import dao.ProyectoDB;
import modelo.Proyecto;

import java.util.Date;
import java.util.List;

public class TestProyecto {
    public static void main(String[] args) {

        ProyectoDB proyectoDB = new ProyectoDB();

        // 1. Agregar un nuevo proyecto
        Proyecto nuevo = new Proyecto(0, "Proyecto UTN", "Sistema de gestión de proyectos", new Date(), new Date());
        boolean agregado = proyectoDB.agregarProyecto(nuevo);
        System.out.println("Proyecto agregado: " + agregado);

        // 2. Obtener todos los proyectos
        List<Proyecto> proyectos = proyectoDB.obtenerProyectos();
        System.out.println("Lista de proyectos:");
        for (Proyecto p : proyectos) {
            System.out.println("ID: " + p.getIdProyecto() + ", Nombre: " + p.getNombre());
        }

        // 3. Actualizar el primer proyecto (si existe)
        if (!proyectos.isEmpty()) {
            Proyecto primero = proyectos.get(0);
            primero.setNombre("Proyecto UTN Modificado");
            boolean actualizado = proyectoDB.actualizarProyecto(primero);
            System.out.println("Proyecto actualizado: " + actualizado);
        }

        // 4. Eliminar el último proyecto (para probar delete)
        if (!proyectos.isEmpty()) {
            int idAEliminar = proyectos.get(proyectos.size() - 1).getIdProyecto();
            boolean eliminado = proyectoDB.eliminarProyecto(idAEliminar);
            System.out.println("Proyecto eliminado (ID " + idAEliminar + "): " + eliminado);
        }
    }
}*/
