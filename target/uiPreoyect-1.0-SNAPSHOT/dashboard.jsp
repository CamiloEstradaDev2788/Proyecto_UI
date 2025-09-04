<%-- 
    Document   : dashboard
    Created on : 4/09/2025, 5:46:33 p. m.
    Author     : Esteban
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Simulamos usuario en sesión
    String usuario = request.getParameter("usuario");
    if(usuario == null || usuario.isEmpty()){
        usuario = "Administrador";
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>BodegApp - Dashboard</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }
        header {
            background-color: #2c3e50;
            color: white;
            padding: 15px;
            text-align: center;
        }
        .container {
            display: flex;
        }
        nav {
            width: 220px;
            background-color: #34495e;
            height: 100vh;
            padding-top: 20px;
            position: fixed;
        }
        nav a {
            display: block;
            padding: 12px 20px;
            color: white;
            text-decoration: none;
        }
        nav a:hover {
            background-color: #1abc9c;
        }
        main {
            margin-left: 220px;
            padding: 20px;
            flex-grow: 1;
        }
        .card {
            background: #fff;
            padding: 20px;
            margin-bottom: 15px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .logout {
            background: #e74c3c;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .logout:hover {
            background: #c0392b;
        }
    </style>
</head>
<body>

<header>
    <h1>Dashboard - BodegApp</h1>
    <p>Bienvenido, <strong><%= usuario %></strong></p>
    <form action="index.jsp" method="get" style="display:inline;">
        <button type="submit" class="logout">Cerrar Sesión</button>
    </form>
</header>

<div class="container">
    <!-- Menú lateral -->
    <nav>
        <a href="#inventario">Inventario</a>
        <a href="#ventas">Ventas</a>
        <a href="#reportes">Reportes</a>
    </nav>

    <!-- Contenido -->
    <main>
        <!-- Inventario -->
        <section id="inventario" class="card">
            <h2>Inventario</h2>
            <table border="1" width="100%" cellpadding="5" cellspacing="0">
                <tr>
                    <th>ID</th><th>Producto</th><th>Cantidad</th><th>Precio</th>
                </tr>
                <tr><td>1</td><td>Arroz 5kg</td><td>20</td><td>$25,000</td></tr>
                <tr><td>2</td><td>Azúcar 1kg</td><td>50</td><td>$4,500</td></tr>
                <tr><td>3</td><td>Aceite 1L</td><td>15</td><td>$12,000</td></tr>
            </table>
        </section>

        <!-- Ventas -->
        <section id="ventas" class="card">
            <h2>Últimas Ventas</h2>
            <ul>
                <li>Venta #101 - Arroz 5kg (x2) - Total: $50,000</li>
                <li>Venta #102 - Azúcar 1kg (x5) - Total: $22,500</li>
                <li>Venta #103 - Aceite 1L (x1) - Total: $12,000</li>
            </ul>
        </section>

        <!-- Reportes -->
        <section id="reportes" class="card">
            <h2>Reportes</h2>
            <p><strong>Productos con bajo stock:</strong></p>
            <ul>
                <li>Aceite 1L - 15 unidades restantes</li>
            </ul>
            <p><strong>Total ventas del día:</strong> $84,500</p>
        </section>
    </main>
</div>

</body>
</html>
