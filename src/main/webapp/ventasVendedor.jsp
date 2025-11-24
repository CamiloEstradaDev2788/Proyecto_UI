<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.personal.model.PersonalModel" %>
<%@ page import="com.bodegapp.personal.model.VentaVendedorModel" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");
    PersonalModel vendedor = (PersonalModel) request.getAttribute("vendedor");
    List<VentaVendedorModel> ventas = (List<VentaVendedorModel>) request.getAttribute("ventas");
    Double totalVentas = (Double) request.getAttribute("totalVentas");
    if (totalVentas == null) totalVentas = 0.0;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ventas del Vendedor</title>

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
            <a href="<%= request.getContextPath() %>/dashboard.jsp">
                <span class="material-symbols-sharp">dashboard</span>
                <h3>Dashboard</h3>
            </a>

            <a href="<%= request.getContextPath() %>/InventarioController">
                <span class="material-symbols-sharp">inventory</span>
                <h3>Inventario</h3>
            </a>

            <a href="<%= request.getContextPath() %>/PersonalController" class="active">
                <span class="material-symbols-sharp">person</span>
                <h3>Personal</h3>
            </a>

            <a href="#">
                <span class="material-symbols-sharp">package_2</span>
                <h3>Proveedores</h3>
            </a>

            <a href="#">
                <span class="material-symbols-sharp">order_approve</span>
                <h3>Ventas</h3>
            </a>

            <a href="#">
                <span class="material-symbols-sharp">finance_mode</span>
                <h3>Analíticas</h3>
            </a>

            <a href="logout">
                <span class="material-symbols-sharp">logout</span>
                <h3>Logout</h3>
            </a>
        </div>
    </aside>

    <!-- ============ MAIN ============ -->
    <main>
        <div class="header-actions">
            <h1>Ventas de <%= vendedor != null ? vendedor.getNOMBRE1()+" "+vendedor.getAPELLIDO1() : "Vendedor" %></h1>
            <a href="<%= request.getContextPath() %>/PersonalController" class="btn btn-primary">
                <span class="material-symbols-sharp" style="font-size: 1rem; vertical-align: middle;">arrow_back</span>
                Volver a Personal
            </a>
        </div>

        <% if (vendedor != null) { %>
        <div class="vendedor-info">
            <div class="info-card">
                <h3>Información del Vendedor</h3>
                <p><strong>Cargo:</strong> <%= vendedor.getID_ROL() %></p>
                <p><strong>Código Vendedor:</strong> <%= vendedor.getCODIGO_VENDEDOR() %></p>
                <p><strong>Total de Ventas:</strong> <span class="total-sales">$<%= String.format("%.2f", totalVentas) %></span></p>
            </div>
        </div>
        <% } %>

        <div class="table-container">
            <table class="styled-table" style="border-collapse: separate !important; border-spacing: 0 !important;">
                <thead>
                <tr>
                    <th>Número Factura</th>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>Cantidad Productos</th>
                    <th style="text-align: right;">Total Venta</th>
                </tr>
                </thead>

                <tbody>
                <% if (ventas == null || ventas.isEmpty()) { %>
                    <tr>
                        <td colspan="5" class="no-records">No hay ventas registradas para este vendedor...</td>
                    </tr>
                <% } else {
                       for (VentaVendedorModel venta : ventas) { %>
                    <tr>
                        <td class="code-cell"><%= venta.getNUMERO_FACTURA() != null ? venta.getNUMERO_FACTURA() : "N/A"%></td>
                        <td class="date-cell"><%= venta.getFECHA_FACTURA() != null ? sdf.format(venta.getFECHA_FACTURA()) : "N/A"%></td>
                        <td class="name-cell"><%= venta.getNOMBRE_CLIENTE() != null ? venta.getNOMBRE_CLIENTE() : "Cliente General"%></td>
                        <td class="number-cell"><%= venta.getCANTIDAD_PRODUCTOS()%></td>
                        <td class="price-cell">$<%= String.format("%.2f", venta.getTOTAL_VENTA())%></td>
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

