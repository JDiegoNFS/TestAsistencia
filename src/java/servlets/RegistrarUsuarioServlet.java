package servlets;

import config.UsuarioDAO;
import models.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistrarUsuarioServlet")
public class RegistrarUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // Obtener los parámetros del formulario
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String contraseña = request.getParameter("contraseña");
        String rolStr = request.getParameter("rol"); // Obtener el rol como String

        // Validar los campos
        if (dni == null || nombre == null || contraseña == null || rolStr == null || 
            dni.trim().isEmpty() || nombre.trim().isEmpty() || contraseña.trim().isEmpty()) {
            request.setAttribute("mensajeError", "Todos los campos son obligatorios y debe seleccionar un rol.");
            request.getRequestDispatcher("registrar-usuario.jsp").forward(request, response);
            return;
        }

        // Convertir rol a int
        int rol = Integer.parseInt(rolStr);

        // Crear un nuevo objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setDni(dni.trim());
        usuario.setNombre(nombre.trim());
        usuario.setContraseña(contraseña.trim());
        usuario.setIdRol(rol);

        // Usar UsuarioDAO para registrar el usuario
        String mensajeError = "";
        try {
            boolean exito = usuarioDAO.registrarUsuario(usuario);
            if (exito) {
                response.sendRedirect("panel-admin.jsp"); // Redirigir a la página de éxito
                return;
            } else {
                mensajeError = "Error al registrar el usuario. Por favor, intenta nuevamente.";
            }
        } catch (Exception e) {
            mensajeError = "Error en el registro: " + e.getMessage(); // Capturar el mensaje de error
        }

        // Si hay un error, enviar al JSP
        request.setAttribute("mensajeError", mensajeError);
        request.setAttribute("datosUsuario", usuario); // Para mostrar los datos ingresados
        request.getRequestDispatcher("registrar-usuario.jsp").forward(request, response);
    }
}
