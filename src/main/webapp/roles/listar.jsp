<%@ page import="com.bodegapp.rol.model.RolModel" %>
<%@ page import="java.util.List" %>
<%
    List<RolModel> roles = (List<RolModel>) request.getAttribute("roles");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Roles</title>
</head>
<body>
    <h2>Roles Registrados</h2>
    <a href="registrar.jsp">Registrar nuevo rol</a>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID ROL</th><th>ID EMPRESA</th><th>Nombre Rol</th><th>Descripción</th>
        </tr>
        <% if (roles != null) {
            for (RolModel r : roles) { %>
                <tr>
                    <td><%= r.getID_ROL() %></td>
                    <td><%= r.getID_EMPRESA() %></td>
                    <td><%= r.getNOMBRE_ROL() %></td>
                    <td><%= r.getDESCRIPCION() %></td>
                </tr>
        <%  } } %>
    </table>
</body>
</html>
