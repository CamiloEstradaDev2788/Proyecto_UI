<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>
<%@ page import="com.bodegapp.inventario.model.InventarioModel" %>
<%@ page import="java.util.List" %>

<%
    UsuarioModel vendedor = (UsuarioModel) session.getAttribute("usuario");
    if (vendedor == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<InventarioModel> inventario = (List<InventarioModel>) request.getAttribute("listaInventario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario Vendedor</title>
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
            <a href="dashboardVendedor">
                <span class="material-icons-sharp">dashboard</span>
                <h3>Dashboard</h3>
            </a>
            <a href="#" class="active">
                <span class="material-icons-sharp">inventory_2</span>
                <h3>Inventario</h3>
            </a>
            <a href="logout">
                <span class="material-icons-sharp">logout</span>
                <h3>Cerrar sesión</h3>
            </a>
        </div>
    </aside>

    <!-- Main -->
    <main>
        <h1>Inventario</h1>

        <div class="table-container">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Descripción</th>
                        <th>Stock</th>
                        <th>Stock Mínimo</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (inventario == null || inventario.isEmpty()) { %>
                        <tr>
                            <td colspan="5">No hay productos disponibles</td>
                        </tr>
                    <% } else {
                        for (InventarioModel prod : inventario) { %>
                            <tr>
                                <td><%= prod.getCODIGO_PRODUCTO() %></td>
                                <td><%= prod.getDESCRIPCION_PRODUCTO() %></td>
                                <td><%= prod.getSACO_PRODUCTO() %></td>
                                <td><%= prod.getMINIMO_STOCK() %></td>
                                <td>
                                    <form method="post" action="InventarioVendedorController">
                                        <input type="hidden" name="codigoProducto" value="<%= prod.getCODIGO_PRODUCTO() %>">
                                        <input type="number" name="cantidadSolicitada" min="1" placeholder="Cantidad" required>
                                        <button type="submit">Solicitar</button>
                                    </form>
                                </td>
                            </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>
    </main>

</div>
</body>
</html>
