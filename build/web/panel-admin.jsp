<%-- 
    Document   : panel-admin
    Created on : 19 oct. 2024, 6:09:42 a. m.
    Author     : AsturiasNFS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Administración</title>
    <!-- Referencia a la hoja de estilos -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h1>Bienvenido, Administrador dfdf</h1>

    <!-- Botones para las diferentes acciones -->
    <div class="admin-buttons">
        <button onclick="window.location.href='mostrar-docentes.jsp'">Mostrar Docentes</button>
        <button onclick="window.location.href='mostrar-estudiantes.jsp'">Mostrar Estudiantes</button>
        <button onclick="window.location.href='registrar-usuario.jsp'">Registrar Nuevo Usuario</button>
    </div>
</body>
</html>
