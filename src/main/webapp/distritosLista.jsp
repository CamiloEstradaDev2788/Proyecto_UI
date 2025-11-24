<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.distrito.model.DistritoModel" %>

<%
    List<DistritoModel> lista = (List<DistritoModel>) request.getAttribute("listaDistritos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Distritos</title>

    <!-- ICONOS -->
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" rel="stylesheet">

    <!-- ESTILOS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">

    <style>
        .table-container {
            margin-top: 1rem;
            overflow-x: auto;
        }

        .styled-table {
            width: 100%;
            border-collapse: collapse;
        }

        .styled-table th, .styled-table td {
            padding: 0.75rem;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .styled-table th {
            background-color: #f2f2f2;
        }

        .icon-btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 0.4rem;
            margin: 0 0.2rem;
            border-radius: 0.3rem;
            text-decoration: none;
            color: #fff;
            cursor: pointer;
        }

        .edit-btn {
            background-color: #4caf50;
        }

        .delete-btn {
            background-color: #f44336;
        }

        .btn-add {
            display: inline-flex;
            align-items: center;
            padding: 0.6rem 1rem;
            border-radius: 0.5rem;
            background-color: #2196f3;
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }

        .btn-add span {
            margin-right: 0.5rem;
        }

        .no-records {
            text-align: center;
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- ASIDE -->
    <aside>
        <div class="top">
            <div class="logo">
                <img src="65844.png" alt="logo">
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
            <a href="<%= request.getContextPath() %>/ClientesController">
                <span class="material-symbols-sharp">groups</span>
                <h3>Clientes</h3>
            </a>
            <a href="<%= request.getContextPath() %>/DistritoController" class="active">
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
        <h1>Distritos</h1>

        <div style="margin-bottom: 15px; text-align: right;">
            <a href="<%= request.getContextPath() %>/DistritoController?accion=nuevo" class="btn-add">
                <span class="material-symbols-sharp">add_location</span> Agregar Distrito
            </a>
        </div>

        <div class="table-container">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th style="text-align:center;">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                <% if (lista == null || lista.isEmpty()) { %>
                    <tr>
                        <td colspan="3" class="no-records">No hay distritos registrados...</td>
                    </tr>
                <% } else {
                       for (DistritoModel d : lista) { %>
                    <tr>
                        <td><%= d.getCodigoDistrito() %></td>
                        <td><%= d.getNombreDistrito() %></td>
                        <td class="action-buttons" style="text-align:center;">
                            <a class="icon-btn edit-btn"
                               href="<%= request.getContextPath() %>/DistritoController?accion=editar&id=<%= d.getCodigoDistrito() %>"
                               title="Editar">
                                <span class="material-symbols-sharp">edit</span>
                            </a>
                            <a class="icon-btn delete-btn"
                               href="<%= request.getContextPath() %>/DistritoController?accion=eliminar&id=<%= d.getCodigoDistrito() %>"
                               title="Eliminar"
                               onclick="return confirm('¿Está seguro de eliminar este distrito?');">
                                <span class="material-symbols-sharp">delete</span>
                            </a>
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
