<%@page import="com.bodegapp.vendedor.model.VentaRecienteModel"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    UsuarioModel vendedor = (UsuarioModel) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Vendedor</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
    <link rel="stylesheet" href="/assest/styles/vendedorStyle.css">
</head>

<body>

<div class="container">

    <!-- Sidebar -->
    <aside>
        <div class="top">
            <div class="logo">
                <span class="material-icons-sharp">storefront</span>
                <h2>Bodeg<span class="primary">App</span></h2>
            </div>
        </div>

        <div class="sidebar">
            <a href="#" class="active">
                <span class="material-icons-sharp">dashboard</span>
                <h3>Dashboard</h3>
            </a>

            <a href="ventas">
                <span class="material-icons-sharp">shopping_cart</span>
                <h3>Registrar Venta</h3>
            </a>

            <a href="logout">
                <span class="material-icons-sharp">logout</span>
                <h3>Cerrar sesión</h3>
            </a>
        </div>
    </aside>

    <!-- ===== RIGHT ===== -->
    <main>

        <!-- Títulos -->
        <h1>Bienvenido, <%= vendedor.getNOMBRE1() %> <%= vendedor.getAPELLIDO1() %></h1>
        <small class="role">Vendedor</small>

        <!-- CARDS SUPERIORES -->
        <div class="insights">

            <!-- Ventas del día -->
            <div class="sales">
                <span class="material-icons-sharp icon">shopping_cart</span>
                <div class="middle">
                    <div class="left">
                        <h3>Ventas Totales</h3>
                        <h1>$<%= request.getAttribute("ventasTotales") %></h1>
                    </div>
                </div>
            </div>

            <!-- Productos vendidos -->
            <div class="expenses">
                <span class="material-icons-sharp icon">inventory_2</span>
                <div class="middle">
                    <div class="left">
                        <h3>Productos vendidos</h3>
                        <h1><%= request.getAttribute("productosTotales") %></h1>
                    </div>
                </div>
            </div>

            <!-- Clientes atendidos -->
            <div class="income">
                <span class="material-icons-sharp icon">group</span>
                <div class="middle">
                    <div class="left">
                        <h3>Clientes atendidos</h3>
                        <h1><%= request.getAttribute("clientesTotales") %></h1>
                    </div>
                </div>
            </div>

        </div>

        <!-- VENTAS RECIENTES -->
        <div class="recent-orders">
            <h2>Ventas recientes</h2>

            <table>
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Unidades</th>
                        <th>Precio</th>
                        <th>Fecha</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                    List<VentaRecienteModel> ventas = (List<VentaRecienteModel>) request.getAttribute("ventasRecientes");
                     if (ventas != null) {
                    for (VentaRecienteModel v : ventas) {
                    %>
                    <tr>
    <td><%= v.getProducto() %></td>
    <td><%= v.getCantidad() %></td>
    <td>$<%= v.getCantidad() * v.getPrecio()%></td>
    <td><%= v.getFecha() %></td>
</tr>

<%
        }
    } else {
%>
<tr>
    <td colspan="4">No hay ventas recientes.</td>
</tr>
<%
    }
%>
                </tbody>
            </table>
        </div>

        <!-- ALERTAS -->
        <div class="alerts">
            <h2>Alertas importantes</h2>

            <div class="alert-card">
                <span class="material-icons-sharp">priority_high</span>
                <p><b>Yogur Fresa</b> está por agotarse (5 unidades).</p>
            </div>

            <div class="alert-card">
                <span class="material-icons-sharp">priority_high</span>
                <p><b>Queso</b> vence en 3 días.</p>
            </div>
        </div>

    </main>

</div>

</body>
</html>
