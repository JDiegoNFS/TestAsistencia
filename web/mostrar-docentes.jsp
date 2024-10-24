<%-- 
    Document   : mostrar-docentes
    Created on : 20 oct. 2024, 11:05:55 a. m.
    Author     : AsturiasNFS
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.Usuario" %>
<%@ page import="config.UsuarioDAO" %>

<%
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Usuario> docentes = usuarioDAO.obtenerUsuariosPorRol(2); // id_rol para docente es 2
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Docentes</title>
</head>
<body>
    <h1>Lista de Docentes</h1>
    <table border="1">
        <thead>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (docentes.isEmpty()) {
            %>
            <tr>
                <td colspan="2">No hay docentes registrados.</td>
            </tr>
            <%
                } else {
                    for (Usuario docente : docentes) {
            %>
            <tr>
                <td><%= docente.getDni() %></td>
                <td><%= docente.getNombre() %></td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <a href="panel-admin.jsp">Volver al Panel de Administración</a>
</body>
</html>
