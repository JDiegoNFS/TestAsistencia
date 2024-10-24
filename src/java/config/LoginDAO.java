/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    // Método para autenticar al usuario
    public Usuario autenticar(String dni, String contraseña) {
        Usuario usuario = null;
        Connection con = null;

        try {
            con = Conexion.getConnection(); // Usamos la clase Conexion para obtener la conexión
            String query = "SELECT * FROM usuarios WHERE dni = ? AND contraseña = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dni);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setDni(rs.getString("dni"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setIdRol(rs.getInt("id_rol")); // Usamos id_rol
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close(); // Cerramos la conexión si está abierta
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }
}
