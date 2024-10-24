package config;

import models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    // Logger para manejar las excepciones
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

    // Método para obtener usuarios por su rol
    public List<Usuario> obtenerUsuariosPorRol(int idRol) {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios WHERE id_rol = ?";

        // Usamos try-with-resources para cerrar automáticamente los recursos
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, idRol);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setDni(rs.getString("dni"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setContraseña(rs.getString("contraseña")); // Asegúrate de que esto se ajuste a tu modelo
                    usuario.setIdRol(rs.getInt("id_rol")); // Asegúrate de que esto se ajuste a tu modelo
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener usuarios por rol", e);
        }

        return usuarios;
    }

    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) throws Exception {
    // Validaciones básicas
    if (usuario == null || usuario.getDni() == null || usuario.getNombre() == null || 
        usuario.getContraseña() == null || usuario.getIdRol() <= 0) {
        logger.log(Level.WARNING, "Datos de usuario inválidos para registrar");
        throw new Exception("Datos de usuario inválidos."); // Lanzar una excepción
    }

    String query = "INSERT INTO usuarios (dni, nombre, contraseña, id_rol) VALUES (?, ?, ?, ?)";

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {
         
        ps.setString(1, usuario.getDni());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getContraseña());
        ps.setInt(4, usuario.getIdRol());

        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            logger.log(Level.INFO, "Usuario registrado exitosamente");
            return true; // Devuelve true si se registró exitosamente
        } else {
            logger.log(Level.WARNING, "No se pudo registrar el usuario, filas afectadas: " + rowsInserted);
            throw new Exception("No se pudo registrar el usuario."); // Lanzar una excepción
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error en la consulta SQL al registrar usuario: " + e.getMessage(), e);
        throw new Exception("Error en la consulta SQL: " + e.getMessage()); // Lanzar una excepción
    } catch (Exception e) {
        logger.log(Level.SEVERE, "Error al registrar usuario: " + e.getMessage(), e);
        throw new Exception("Error al registrar usuario: " + e.getMessage()); // Lanzar una excepción
    }
}

}
