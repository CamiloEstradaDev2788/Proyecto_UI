<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.personal.model.PersonalModel" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");
    List<PersonalModel> lista = (List<PersonalModel>) request.getAttribute("listaPersonal");
    List<PersonalModel> menosVentas = (List<PersonalModel>) request.getAttribute("menosVentas"); // esta lista debe venir del controller
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Personal</title>

    <!-- ICONOS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />
    <!-- ESTILOS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">
</head>
<body>

<div class="container">

    <!-- ASIDE -->
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
            <a href="<%= request.getContextPath() %>/InventarioController">
                <span class="material-symbols-sharp">inventory</span>
                <h3>Inventario</h3>
            </a>
            <a href="<%= request.getContextPath() %>/PersonalController" class="active">
                <span class="material-symbols-sharp">person</span>
                <h3>Personal</h3>
            </a>
            <a href="<%= request.getContextPath() %>/ClientesController">
                <span class="material-symbols-sharp">groups</span>
                <h3>Clientes</h3>
            </a>
            <a href="<%= request.getContextPath() %>/DistritoController">
                <span class="material-symbols-sharp">location_city</span>
                <h3>Distritos</h3>
            </a>
            <a href="logout">
                <span class="material-symbols-sharp">logout</span>
                <h3>Logout</h3>
            </a>
        </div>
    </aside>

    <!-- MAIN -->
    <main>
        <h1>Personal de la Empresa</h1>

        <!-- BOTÓN AGREGAR PERSONAL -->
        <div style="margin-bottom: 15px; text-align: right;">
            <a href="<%= request.getContextPath() %>/PersonalController?accion=agregar">
                <button type="button" class="btn-add">
                    <span class="material-symbols-sharp">person_add</span> Agregar Personal
                </button>
            </a>
        </div>

        <!-- TABLA PRINCIPAL DE PERSONAL -->
        <div class="table-container">
            <table class="styled-table" style="border-collapse: separate !important; border-spacing: 0 !important;">
                <thead>
                    <tr>
                        <th>Nombre Completo</th>
                        <th>Cargo</th>
                        <th>Código Vendedor</th>
                        <th>Salario</th>
                        <th>Tipo Contrato</th>
                        <th>Estado</th>
                        <th style="text-align:center;">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (lista == null || lista.isEmpty()) { %>
                        <tr>
                            <td colspan="7" class="no-records">No hay personal registrado...</td>
                        </tr>
                    <% } else {
                           for (PersonalModel p : lista) {
                               String nombreCompleto = 
                                   (p.getNOMBRE1() != null ? p.getNOMBRE1() : "") + " " +
                                   (p.getNOMBRE2() != null ? p.getNOMBRE2() : "") + " " +
                                   (p.getAPELLIDO1() != null ? p.getAPELLIDO1() : "") + " " +
                                   (p.getAPELLIDO2() != null ? p.getAPELLIDO2() : "");
                    %>
                        <tr>
                            <td class="name-cell"><%= nombreCompleto.trim().replaceAll("\\s+", " ") %></td>
                            <td class="role-cell"><span class="role-badge"><%= p.getID_ROL() %></span></td>
                            <td>
                                <% if (p.isES_VENDEDOR() && p.getCODIGO_VENDEDOR() != null) { %>
                                    <span class="vendedor-badge"><%= p.getCODIGO_VENDEDOR() %></span>
                                <% } else { %>
                                    <span class="no-vendedor">—</span>
                                <% } %>
                            </td>
                            <td class="salary-cell">$<%= String.format("%.2f", p.getSALARIO()) %></td>
                            <td class="contract-cell"><%= p.getTIPO_CONTRATO() %></td>
                            <td class="status-cell">
                                <span class="status-badge <%= p.isESTADO() ? "active" : "inactive" %>">
                                    <%= p.isESTADO() ? "Activo" : "Inactivo" %>
                                </span>
                            </td>
                            <td class="action-buttons">
                                <a class="icon-btn edit-btn"
                                   href="<%= request.getContextPath() %>/PersonalController?accion=editar&id=<%= p.getID_USER() %>">
                                    <span class="material-symbols-sharp">edit</span>
                                </a>
                                <a class="icon-btn delete-btn"
                                   onclick="return confirm('¿Eliminar este registro de personal?')"
                                   href="<%= request.getContextPath() %>/PersonalController?accion=eliminar&id=<%= p.getID_USER() %>">
                                    <span class="material-symbols-sharp">delete</span>
                                </a>
                                <% if (p.isES_VENDEDOR() && p.getCODIGO_VENDEDOR() != null) { %>
                                    <a class="icon-btn sales-btn"
                                       href="<%= request.getContextPath() %>/PersonalController?accion=verVentas&idVendedor=<%= p.getID_USER() %>">
                                        <span class="material-symbols-sharp">trending_up</span>
                                    </a>
                                <% } else { %>
                                    <span class="icon-btn disabled-btn" title="No es vendedor">
                                        <span class="material-symbols-sharp">block</span>
                                    </span>
                                <% } %>
                            </td>
                        </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>

        <!-- TABLA 3 VENDEDORES CON MENOS VENTAS -->
        <div class="table-container" style="margin-top:40px;">
            <h2>3 Vendedores con Menos Ventas</h2>
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>Nombre Completo</th>
                        <th>Código Vendedor</th>
                        <th>Total Ventas</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (menosVentas == null || menosVentas.isEmpty()) { %>
                        <tr>
                            <td colspan="3" class="no-records">No hay vendedores registrados...</td>
                        </tr>
                    <% } else {
                           for (PersonalModel p : menosVentas) {
                               String nombreCompleto = 
                                   (p.getNOMBRE1() != null ? p.getNOMBRE1() : "") + " " +
                                   (p.getNOMBRE2() != null ? p.getNOMBRE2() : "") + " " +
                                   (p.getAPELLIDO1() != null ? p.getAPELLIDO1() : "") + " " +
                                   (p.getAPELLIDO2() != null ? p.getAPELLIDO2() : "");
                    %>
                        <tr>
                            <td><%= nombreCompleto.trim().replaceAll("\\s+", " ") %></td>
                            <td><%= p.getCODIGO_VENDEDOR() %></td>
                            <td>$<%= String.format("%.2f", p.getTOTAL_VENTAS()) %></td>
                        </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>

    </main>

</div>

</body>
</html>
