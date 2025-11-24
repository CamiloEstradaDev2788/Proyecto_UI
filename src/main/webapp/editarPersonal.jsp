<%@page import="com.bodegapp.roles.model.RolModel"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.personal.model.PersonalModel" %>
<%@ page import="com.bodegapp.roles.model.RolModel" %>
<%@ page import="com.bodegapp.distrito.model.DistritoModel" %>

<%
    PersonalModel p = (PersonalModel) request.getAttribute("personal");
    List<RolModel> roles = (List<RolModel>) request.getAttribute("roles");
    List<DistritoModel> distritos = (List<DistritoModel>) request.getAttribute("distritos");
    if (p == null) {
%>
    <h2>No se encontró el personal</h2>
    <a href="PersonalController">Volver</a>
<%
    return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Personal</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">
    <script>
        function toggleVendedor(checkbox) {
            var sec = document.getElementById('seccionVendedor');
            sec.style.display = checkbox.checked ? 'block' : 'none';
        }
        window.onload = function() {
            var cb = document.getElementById('es_vendedor');
            toggleVendedor(cb);
        }
    </script>
</head>
<body>
<div class="container">
    <main>
        <h1>Editar Personal</h1>

        <form action="PersonalController" method="post">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="id_user" value="<%= p.getID_USER() %>">

            <div class="row">
                <div class="form-group">
                    <label>Rol</label>
                    <select name="id_rol" required>
                        <option value="">Seleccione rol</option>
                        <% if (roles!=null) for (RolModel r: roles) { %>
                            <option value="<%= r.getID_ROL() %>" <%= (p.getID_ROL()==r.getID_ROL()) ? "selected" : "" %>><%= r.getNOMBRE_ROL() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label>Nombre 1</label>
                    <input type="text" name="nombre1" value="<%= p.getNOMBRE1() %>" required>
                </div>

                <div class="form-group">
                    <label>Nombre 2</label>
                    <input type="text" name="nombre2" value="<%= p.getNOMBRE2() %>">
                </div>

                <div class="form-group">
                    <label>Apellido 1</label>
                    <input type="text" name="apellido1" value="<%= p.getAPELLIDO1() %>" required>
                </div>

                <div class="form-group">
                    <label>Apellido 2</label>
                    <input type="text" name="apellido2" value="<%= p.getAPELLIDO2() %>">
                </div>

                <div class="form-group">
                    <label>Cédula</label>
                    <input type="text" name="cedula" value="<%= p.getCEDULA() %>" required>
                </div>

                <div class="form-group">
                    <label>Correo</label>
                    <input type="email" name="correo" value="<%= p.getCORREO() %>" required>
                </div>

                <div class="form-group">
                    <label>Salario</label>
                    <input type="number" step="0.01" name="salario" value="<%= p.getSALARIO() %>" required>
                </div>

                <div class="form-group">
                    <label>Tipo contrato</label>
                    <input type="text" name="tipo_contrato" value="<%= p.getTIPO_CONTRATO() %>" required>
                </div>

                <div class="form-group">
                    <label>Estado</label>
                    <select name="estado">
                        <option value="true" <%= p.isESTADO()? "selected":"" %>>Activo</option>
                        <option value="false" <%= !p.isESTADO()? "selected":"" %>>Inactivo</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Es vendedor</label>
                    <input type="checkbox" id="es_vendedor" name="es_vendedor" onchange="toggleVendedor(this)" <%= p.isES_VENDEDOR()? "checked":"" %>>
                </div>
            </div>

            <div id="seccionVendedor" style="display:none; margin-top:1rem; border:1px solid #eee; padding:1rem;">
                <h3>Datos de Vendedor</h3>
                <div class="form-group">
                    <label>Código vendedor</label>
                    <input type="text" name="codigo_vendedor" value="<%= p.getCODIGO_VENDEDOR() %>">
                </div>
                <div class="form-group">
                    <label>Distrito</label>
                    <select name="codigo_distrito">
                        <option value="">Seleccione distrito</option>
                        <% if (distritos!=null) for (DistritoModel d : distritos) { %>
                            <option value="<%= d.getCodigoDistrito() %>" <%= (d.getCodigoDistrito().equals(p.getCODIGO_DISTRITO()) ? "selected":"") %>><%= d.getNombreDistrito()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Tipo vendedor</label>
                    <input type="text" name="tipo_vendedor" value="<%= p.getTIPO_VENDEDOR() %>">
                </div>
            </div>

            <div class="form-actions" style="margin-top:1rem;">
                <button type="submit" class="btn-primary">Actualizar</button>
                <a href="PersonalController" class="btn-secondary">Cancelar</a>
            </div>
        </form>

    </main>
</div>
</body>
</html>
