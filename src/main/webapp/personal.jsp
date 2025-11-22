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

            <a href="<%= request.getContextPath() %>/InventarioController">
                <span class="material-symbols-sharp">inventory</span>
                <h3>Inventario</h3>
            </a>

            <a href="<%= request.getContextPath() %>/PersonalController" class="active">
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
        <h1>Personal de la Empresa</h1>

        <div class="table-container">
            <table class="styled-table" style="border-collapse: separate !important; border-spacing: 0 !important;">
                <thead>
                <tr>
                    <th>Nombre Completo</th>
                    <th>Cargo</th>
                    <th>Salario</th>
                    <th>Tipo Contrato</th>
                    <th>Estado</th>
                    <th style="text-align: center;">Acciones</th>
                </tr>
                </thead>

                <tbody>
                <% if (lista == null || lista.isEmpty()) { %>
                    <tr>
                        <td colspan="6" class="no-records">No hay personal registrado...</td>
                    </tr>
                <% } else {
                       for (PersonalModel personal : lista) { %>
                    <tr>
                        <td class="name-cell"><%= personal.getNOMBRE_COMPLETO()%></td>
                        <td class="role-cell">
                            <span class="role-badge"><%= personal.getCARGO()%></span>
                        </td>
                        <td class="salary-cell">$<%= String.format("%.2f", personal.getSALARIO())%></td>
                        <td class="contract-cell"><%= personal.getTIPO_CONTRATO()%></td>
                        <td class="status-cell">
                            <span class="status-badge <%= personal.isESTADO() ? "active" : "inactive" %>">
                                <%= personal.isESTADO() ? "Activo" : "Inactivo" %>
                            </span>
                        </td>
                        <td class="action-buttons">
                            <% if (personal.isES_VENDEDOR() && personal.getCODIGO_VENDEDOR() != null) { %>
                                <a class="icon-btn sales-btn" 
                                   href="<%= request.getContextPath() %>/PersonalController?accion=verVentas&idVendedor=<%= personal.getID_USER() %>"
                                   title="Ver ventas">
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

