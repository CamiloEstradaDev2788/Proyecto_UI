<%@ page import="com.bodegapp.empresa.model.EmpresaModel" %>
<%@ page import="java.util.List" %>
<%
    List<EmpresaModel> empresas = (List<EmpresaModel>) request.getAttribute("empresas");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Empresas</title>
</head>
<body>
    <h2>Empresas Registradas</h2>
    <a href="Empresas/registrar.jsp">Registrar nueva empresa</a>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th><th>NIT</th><th>Nombre</th><th>Telefóno</th><th>Estado</th>
        </tr>
        <% if (empresas != null) {
            for (EmpresaModel e : empresas) { %>
                <tr>
                    <td><%= e.getID_EMPRESA()%></td>
                    <td><%= e.getNIT()%></td>
                    <td><%= e.getNOMBRE()%></td>
                    <td><%= e.getTELEFONO()%></td>
                    <td><%= e.isESTADO() ? "Activo":"Inactivo" %></td>
                </tr>
        <%  } } %>
    </table>
</body>
</html>
