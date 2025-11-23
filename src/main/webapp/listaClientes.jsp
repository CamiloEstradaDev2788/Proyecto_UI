<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.clientes.model.ClienteModel" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");
    List<ClienteModel> lista = (List<ClienteModel>) request.getAttribute("listaClientes");
    String criterio = (String) request.getAttribute("criterioBusqueda");

    if (criterio == null) criterio = "";
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>

    <!-- ICONOS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />

    <!-- ESTILOS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">
</head>

<body>

<div class="container">

    <!-- ===== ASIDE ===== -->
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

            <a href="<%= request.getContextPath() %>/PersonalController">
                <span class="material-symbols-sharp">person</span>
                <h3>Personal</h3>
            </a>
            <a href="<%= request.getContextPath() %>/ClientesController" class="active">
                <span class="material-symbols-sharp">groups</span>
                <h3>Clientes</h3>
            </a>

            <a href="logout">
                <span class="material-symbols-sharp">logout</span>
                <h3>Logout</h3>
            </a>
        </div>
    </aside>

    <!-- ===== MAIN ===== -->
    <main>

        <h1>Clientes</h1>

        <div class="header-actions">
            <a href="<%= request.getContextPath() %>/ClientesController?accion=nuevo"
               class="btn btn-primary">
                + Nuevo Cliente
            </a>

            <form method="get" action="<%= request.getContextPath() %>/ClientesController" class="search-form">
                <div class="search-container">
                    <input type="text"
                           name="buscar"
                           placeholder="Buscar por código, nombre, teléfono o RUC..."
                           value="<%= criterio %>"
                           class="search-input">

                    <button type="submit" class="search-btn" title="Buscar">
                        <span class="material-symbols-sharp">search</span>
                    </button>

                    <% if (criterio != null && !criterio.isEmpty()) { %>
                        <a href="<%= request.getContextPath() %>/ClientesController"
                           class="clear-search-btn" title="Limpiar búsqueda">
                            <span class="material-symbols-sharp">close</span>
                        </a>
                    <% } %>
                </div>
            </form>
        </div>

        <br><br>

        <div class="table-container">
            <table class="styled-table">
                <thead>
                <tr>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Teléfono</th>
                    <th>RUC</th>
                    <th style="text-align:center;">Acciones</th>
                </tr>
                </thead>

                <tbody>
                <% if (lista == null || lista.isEmpty()) { %>
                    <tr>
                        <td colspan="5" class="no-records">
                            <% if (!criterio.isEmpty()) { %>
                                No se encontraron clientes que coincidan con "<%= criterio %>"
                            <% } else { %>
                                No hay registros...
                            <% } %>
                        </td>
                    </tr>
                <% } else {
                       for (ClienteModel c : lista) { %>
                    <tr>
                        <td class="code-cell"><%= c.getCODIGO_CLIENTE() %></td>
                        <td class="desc-cell"><%= c.getNOMBRE_CLIENTE() %></td>
                        <td class="number-cell"><%= c.getTELEFONO_CLIENTE() %></td>
                        <td class="number-cell"><%= c.getRUC_CLIENTE() %></td>

                        <td class="action-buttons">
                            <a class="icon-btn edit-btn"
                               href="ClientesController?accion=editar&id=<%= c.getCODIGO_CLIENTE() %>"
                               title="Editar">
                                <span class="material-symbols-sharp">edit</span>
                            </a>

                            <a class="icon-btn delete-btn"
                               href="ClientesController?accion=eliminar&id=<%= c.getCODIGO_CLIENTE() %>"
                               onclick="return confirm('¿Eliminar este cliente?')"
                               title="Eliminar">
                                <span class="material-symbols-sharp">delete</span>
                            </a>
                        </td>
                    </tr>
                <% } } %>

                </tbody>
            </table>
        </div>

    </main>

    <!-- ===== PANEL DERECHO ===== -->
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
