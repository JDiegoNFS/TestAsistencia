<%-- 
    Document   : mostrar-estudiantes
    Created on : 20 oct. 2024, 11:06:50 a. m.
    Author     : AsturiasNFS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.Usuario" %>
<%@ page import="config.UsuarioDAO" %>

<%
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Usuario> estudiantes = usuarioDAO.obtenerUsuariosPorRol(3); // 3 es el id_rol para estudiantes
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mostrar Estudiantes</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Lista de Estudiantes</h1>
    <table>
        <tr>
            <th>DNI</th>
            <th>Nombre</th>
        </tr>
        <%
            for (Usuario estudiante : estudiantes) {
        %>
            <tr>
                <td><%= estudiante.getDni() %></td>
                <td><%= estudiante.getNombre() %></td>
            </tr>
        <%
            }
        %>
    </table>
    <button onclick="window.location.href='panel-admin.jsp'">Volver al Panel de Administración</button>
</body>
</html>
