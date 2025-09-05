<%-- 
    Document   : index
    Created on : 4/09/2025, 4:53:22 p. m.
    Author     : Esteban
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>BodegApp - Inicio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f4f4f9;
        }
        header {
            background-color: #2c3e50;
            color: white;
            padding: 20px;
            text-align: center;
        }
        header img {
            height: 60px;
            margin-right: 15px;
            vertical-align: middle;
        }
        h1 {
            display: inline;
        }
        nav {
            background-color: #34495e;
            overflow: hidden;
        }
        nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }
        nav a:hover {
            background-color: #1abc9c;
        }
        main {
            padding: 20px;
            text-align: center;
        }
        footer {
            background-color: #2c3e50;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        
        .image1{
            width: 200px;
            height: 200px;
            margin: 0;
        }
    </style>
</head>
<body>

<header>
    <!-- Aquí podrías reemplazar con el logo real -->
    <img src="images/logo.png" alt="Logo BodegApp" class="image1">
    <h1>BodegApp</h1>
    <p>Desarrollado por: Yefry Ávila y Camilo Estrada</p>
</header>

<nav>
    <a href="index.jsp">Inicio</a>
    <a href="login.jsp">Iniciar Sesión</a>
    <!-- comment <a href="inventario.jsp">Gestión de Inventario</a>
    <a href="ventas.jsp">Gestión de Ventas</a>
    <a href="reportes.jsp">Reportes</a>  -->
    <a href="contacto.jsp">Contacto</a>
</nav>

<main>
    <h2>Bienvenido a BodegApp</h2>
    <p>Una solución para pequeñas empresas que necesitan llevar un control eficiente de su inventario y ventas.</p>
    <p>Seleccione una opción del menú para continuar.</p>
</main>

<footer>
    &copy; 2025 BodegApp - Proyecto académico
</footer>

</body>
</html>
