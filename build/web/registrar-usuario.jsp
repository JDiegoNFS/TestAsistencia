<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Usuario</title>
</head>
<body>
<% if (request.getAttribute("mensajeError") != null) { %>
    <div style="color: red;">
        <%= request.getAttribute("mensajeError") %>
    </div>
<% } %>
<form action="RegistrarUsuarioServlet" method="post">
    <label for="dni">DNI:</label>
    <input type="text" name="dni" id="dni" required><br>
    
    <label for="nombre">Nombre:</label>
    <input type="text" name="nombre" id="nombre" required><br>
    
    <label for="contraseña">Contraseña:</label>
    <input type="password" name="contraseña" id="contraseña" required><br>
    
    <label for="rol">Rol:</label><br>
    <input type="radio" name="rol" value="1" id="admin" required>
    <label for="admin">Admin</label><br>
    <input type="radio" name="rol" value="2" id="docente" required>
    <label for="docente">Docente</label><br>
    <input type="radio" name="rol" value="3" id="estudiante" required>
    <label for="estudiante">Estudiante</label><br><br>

    <input type="submit" value="Registrar Usuario">
</form>
</body>
</html>
