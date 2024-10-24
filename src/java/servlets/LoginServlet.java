/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import config.LoginDAO;
import models.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private LoginDAO loginDAO = new LoginDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dni = request.getParameter("dni");
        String contraseña = request.getParameter("contraseña");

        if (dni == null || contraseña == null || dni.trim().isEmpty() || contraseña.trim().isEmpty()) {
            request.setAttribute("error", "Por favor, completa todos los campos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Usuario usuario = loginDAO.autenticar(dni.trim(), contraseña.trim());

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            // Usamos id_rol en lugar de nombreRol
            switch (usuario.getIdRol()) {
                case 1: // Admin
                    response.sendRedirect("panel-admin.jsp");
                    break;
                case 2: // Docente
                    response.sendRedirect("panel-docente.jsp");
                    break;
                case 3: // Estudiante
                    response.sendRedirect("panel-estudiante.jsp");
                    break;
                default:
                    session.invalidate();
                    request.setAttribute("error", "Rol no reconocido.");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Credenciales incorrectas. Intenta nuevamente.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
