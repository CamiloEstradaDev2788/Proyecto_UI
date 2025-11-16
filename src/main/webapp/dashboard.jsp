<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    HttpSession session = request.getSession(false);
    Usuario u = (Usuario) session.getAttribute("usuario");

    if (u == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Dashboard - BodegApp</title>
</head>
<body>
<h1>Bienvenido, <%= u.getNombre() %> 👋</h1>

<p>Tu rol: <%= u.getRol() %></p>

<a href="logout">Cerrar sesión</a>
</body>
</html>
