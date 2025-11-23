<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.inventario.model.InventarioModel" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");
    List<InventarioModel> lista = (List<InventarioModel>) request.getAttribute("listaInventario");
    String criterioBusqueda = (String) request.getAttribute("criterioBusqueda");
    if (criterioBusqueda == null) criterioBusqueda = "";
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario</title>

    <!-- ICONOS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />

    <!-- ESTILOS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">
</head>

<body>

<div class="container">

    <!-- ============ ASIDE ============ -->
    <aside>
        <div class="top">
            <div class="logo">
                <img src="65844.png" alt="logo">
            </div>
            <div class="close" id="close-btn">
                <span class="material-symbols-sharp">close</span>
            </div>
        </div>

        <div class="sidebar">
            <a href="<%= request.getContextPath() %>/dashboard">
                <span class="material-symbols-sharp">dashboard</span>
                <h3>Dashboard</h3>
            </a>

            <a href="<%= request.getContextPath() %>/InventarioController" class="active">
                <span class="material-symbols-sharp">inventory</span>
                <h3>Inventario</h3>
            </a>

            <a href="<%= request.getContextPath() %>/PersonalController">
                <span class="material-symbols-sharp">person</span>
                <h3>Personal</h3>
            </a>

            <a href="logout">
                <span class="material-symbols-sharp">logout</span>
                <h3>Logout</h3>
            </a>
        </div>
    </aside>

    <!-- ============ MAIN ============ -->
    <main>

        <h1>Inventario</h1>
        
        <div class="header-actions">
            <a href="<%= request.getContextPath() %>/InventarioController?accion=nuevo" 
            class="btn btn-primary">
             + Agregar Producto
            </a>
            <form method="get" action="<%= request.getContextPath() %>/InventarioController" class="search-form">
                <div class="search-container">
                    <input type="text" 
                           name="buscar" 
                           placeholder="Buscar por código, descripción, línea o proveedor..." 
                           value="<%= criterioBusqueda %>"
                           class="search-input">
                    <button type="submit" class="search-btn" title="Buscar">
                        <span class="material-symbols-sharp">search</span>
                    </button>
                    <% if (criterioBusqueda != null && !criterioBusqueda.isEmpty()) { %>
                        <a href="<%= request.getContextPath() %>/InventarioController" class="clear-search-btn" title="Limpiar búsqueda">
                            <span class="material-symbols-sharp">close</span>
                        </a>
                    <% } %>
                </div>
            </form>
        </div>

        <br><br>

        <div class="table-container">
            <table class="styled-table" style="border-collapse: separate !important; border-spacing: 0 !important;">
                <thead>
                <tr>
                    <th>Código</th>
                    <th>Descripción</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Stock Mínimo</th>
                    <th>Unidad</th>
                    <th>Línea</th>
                    <th>Proveedor</th>
                    <th style="text-align: center;">Acciones</th>
                </tr>
                </thead>

                <tbody>
                <% if (lista == null || lista.isEmpty()) { %>
                    <tr>
                        <td colspan="9" class="no-records">
                            <% if (criterioBusqueda != null && !criterioBusqueda.isEmpty()) { %>
                                No se encontraron productos que coincidan con "<%= criterioBusqueda %>"
                            <% } else { %>
                                No hay registros...
                            <% } %>
                        </td>
                    </tr>
                <% } else {
                       for (InventarioModel prod : lista) { %>
                    <tr>
                        <td class="code-cell"><%= prod.getCODIGO_PRODUCTO()%></td>
                        <td class="desc-cell"><%= prod.getDESCRIPCION_PRODUCTO()%></td>
                        <td class="number-cell"><%= prod.getSACO_PRODUCTO()%></td>
                        <td class="price-cell">$<%= String.format("%.2f", prod.getPRECIO_PRODUCTO())%></td>
                        <td class="number-cell"><%= prod.getMINIMO_STOCK()%></td>
                        <td class="unit-cell"><%= prod.getUNIDAD_PRODUCTO()%></td>
                        <td class="line-cell"><%= prod.getLINEA_PRODUCTO()%></td>
                        <td class="supplier-cell">
                            <% 
                                String proveedores = prod.getNOMBRE_PROVEEDOR() != null ? prod.getNOMBRE_PROVEEDOR() : "Sin proveedor";
                                if (proveedores.contains(",")) {
                                    String[] provs = proveedores.split(",");
                                    for (int i = 0; i < provs.length; i++) {
                                        String prov = provs[i].trim();
                            %>
                                        <span class="supplier-badge"><%= prov %></span>
                            <% 
                                    }
                                } else {
                            %>
                                <span class="supplier-badge"><%= proveedores %></span>
                            <% } %>
                        </td>
                        <td class="action-buttons">
                            <a class="icon-btn edit-btn" 
                               href="editarProducto.jsp?id=<%= prod.getCODIGO_PRODUCTO() %>"
                               title="Editar producto">
                                <span class="material-symbols-sharp">edit</span>
                            </a>
                            <a class="icon-btn delete-btn"
                               href="InventarioController?accion=eliminar&id=<%= prod.getCODIGO_PRODUCTO() %>"
                               onclick="return confirm('¿Eliminar este producto?')"
                               title="Eliminar producto">
                                <span class="material-symbols-sharp">delete</span>
                            </a>
                        </td>
                    </tr>
                <% } } %>
                </tbody>
            </table>
        </div>

    </main>

    <!-- ============ RIGHT PANEL ============ -->
    <div class="right">

        <div class="top">
            <button id="menu-btn">
                <span class="material-symbols-sharp">menu</span>
            </button>

            <div class="theme-toggler">
                <span class="material-symbols-sharp active">light_mode</span>
                <span class="material-symbols-sharp">dark_mode</span>
            </div>

            <div class="profile">
                <div class="info">
                    <p>Hola, <b><%= usuario.getNOMBRE1() %></b></p>
                    <small class="text-muted">Admin</small>
                </div>
            </div>
        </div>

    </div>

</div>

</body>
</html>
