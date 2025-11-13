<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    List<UsuarioModel> usuarios = (List<UsuarioModel>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios Registrados</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; }
        h2 { color: #333; }
        table { border-collapse: collapse; width: 100%; background-color: #fff; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .container { max-width: 1000px; margin: 0 auto; }
    </style>
</head>
<body>
<div class="container">
    <h2>Usuarios Registrados</h2>
    <a href="usuarios/registrar.jsp">Registrar nuevo usuario</a><br><br>

    <table>
        <tr>
            <th>ID</th>
            <th>Empresa</th>
            <th>Rol</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Cédula</th>
            <th>Correo</th>
            <th>Fecha Ingreso</th>
            <th>Salario</th>
            <th>Tipo Contrato</th>
            <th>Estado</th>
        </tr>

        <% if (usuarios != null && !usuarios.isEmpty()) { 
               for (UsuarioModel u : usuarios) { %>
            <tr>
                <td><%= u.getID_USER() %></td>
                <td><%= u.getID_EMPRESA() %></td>
                <td><%= u.getID_ROL() %></td>
                <td><%= u.getNOMBRE1() %> <%= u.getNOMBRE2() %></td>
                <td><%= u.getAPELLIDO1() %> <%= u.getAPELLIDO2() %></td>
                <td><%= u.getCEDULA() %></td>
                <td><%= u.getCORREO() %></td>
                <td><%= u.getFECHA_INGRESO() %></td>
                <td><%= u.getSALARIO() %></td>
                <td><%= u.getTIPO_CONTRATO() %></td>
                <td><%= u.isESTADO() ? "Activo" : "Inactivo" %></td>
            </tr>
        <%   }
           } else { %>
            <tr><td colspan="11">No hay usuarios registrados.</td></tr>
        <% } %>
    </table>
</div>
</body>
</html>
