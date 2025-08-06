/*package dao;
import dao.UsuarioDB;
import modelo.Usuario;

import java.util.List;

public class TestUsuario {
    public static void main(String[] args) {

        UsuarioDB usuarioDB = new UsuarioDB();

        // 1. Agregar un nuevo usuario
        Usuario nuevo = new Usuario(0, "Carlos Carballo", "carlos@email.com", "Administrador");
        boolean agregado = usuarioDB.agregarUsuario(nuevo);
        System.out.println("Usuario agregado: " + agregado);

        // 2. Obtener todos los usuarios
        List<Usuario> usuarios = usuarioDB.obtenerUsuarios();
        System.out.println("Lista de usuarios:");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getIdUsuario() + ", Nombre: " + u.getNombre() + ", Correo: " + u.getCorreo());
        }

        // 3. Actualizar el primer usuario (si existe)
        if (!usuarios.isEmpty()) {
            Usuario primero = usuarios.get(0);
            primero.setNombre("Carlos Modificado");
            boolean actualizado = usuarioDB.actualizarUsuario(primero);
            System.out.println("Usuario actualizado: " + actualizado);
        }

        // 4. Eliminar el último usuario (para probar delete)
        if (!usuarios.isEmpty()) {
            int idAEliminar = usuarios.get(usuarios.size() - 1).getIdUsuario();
            boolean eliminado = usuarioDB.eliminarUsuario(idAEliminar);
            System.out.println("Usuario eliminado (ID " + idAEliminar + "): " + eliminado);
        }
    }
}*/
