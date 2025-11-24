<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.auditoria.model.AuditoriaModel" %>

<%
    List<AuditoriaModel> listaAuditoria = 
        (List<AuditoriaModel>) request.getAttribute("listaAuditoria");

    // Parámetros de filtro
    String fechaInicio = request.getParameter("fechaInicio");
    String fechaFin = request.getParameter("fechaFin");

    if (fechaInicio == null) fechaInicio = "";
    if (fechaFin == null) fechaFin = "";
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Auditoría del Sistema</title>

    <style>
        body { font-family: Arial; background: #f3f3f3; padding: 20px; }
        .card { background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 6px #ccc; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 8px; border: 1px solid #ddd; font-size: 14px; }
        th { background: #007bff; color: #fff; }
        .filter { margin-bottom: 15px; }
        input[type="date"] { padding: 5px; }
        button { padding: 6px 15px; background: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; }
        button:hover { background: #0056b3; }
        pre { white-space: pre-wrap; background: #fafafa; padding: 8px; border-radius: 5px; border: 1px solid #ccc; }
    </style>
</head>

<body>

<div class="card">

    <h2>Registro de Auditoría</h2>

    <form action="auditoria" method="GET">
        <div class="filter">

            <label>Fecha Inicio:</label>
            <input type="date" name="fechaInicio" value="<%= fechaInicio %>">

            &nbsp;&nbsp;

            <label>Fecha Fin:</label>
            <input type="date" name="fechaFin" value="<%= fechaFin %>">

            &nbsp;&nbsp;

            <button type="submit">Buscar</button>
        </div>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Tabla</th>
            <th>Operación</th>
            <th>PK Registro</th>
            <th>Usuario</th>
            <th>Fecha - Hora</th>
            <th>Valor Anterior</th>
            <th>Valor Nuevo</th>
        </tr>
        </thead>

        <tbody>
        <%
            if (listaAuditoria == null || listaAuditoria.isEmpty()) {
        %>
            <tr>
                <td colspan="8" style="text-align:center; color:#888;">
                    No hay registros de auditoría para mostrar…
                </td>
            </tr>
        <%
            } else {
                for (AuditoriaModel a : listaAuditoria) {
        %>
            <tr>
                <td><%= a.getIdAuditoria() %></td>
                <td><%= a.getNombreTabla() %></td>
                <td><%= a.getOperacion() %></td>
                <td><%= a.getPkRegistro() %></td>
                <td><%= a.getUsuarioApp() %></td>
                <td><%= a.getFechaHora() %></td>
                <td><pre><%= a.getValorAnterior() %></pre></td>
                <td><pre><%= a.getValorNuevo() %></pre></td>
            </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

</div>

</body>
</html>
