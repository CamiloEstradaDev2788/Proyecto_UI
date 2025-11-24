<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.roles.model.RolModel" %>
<%@ page import="com.bodegapp.distrito.model.DistritoModel" %>

<%
    List<RolModel> roles = (List<RolModel>) request.getAttribute("roles");
    List<DistritoModel> distritos = (List<DistritoModel>) request.getAttribute("distritos");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Personal</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">
</head>
<body>
<div class="container">
    <main>
        <h1>Agregar Personal</h1>

        <% if (error != null) { %>
            <div class="error-message"><%= error %></div>
        <% } %>

        <form action="PersonalController" method="post">
            <input type="hidden" name="accion" value="insertar">
            <input type="hidden" id="esVendedorInput" name="es_vendedor" value="false">

            <div class="row">
                <!-- Rol -->
                <div class="form-group">
                    <label>Rol</label>
                    <select name="id_rol" id="rolSelect" required>
                        <option value="">Seleccione rol</option>
                        <% if (roles != null) {
                               for (RolModel r: roles) { %>
                            <option value="<%= r.getID_ROL() %>" data-vendedor="<%= r.getNOMBRE_ROL().equalsIgnoreCase("Vendedor") ? "true" : "false" %>">
                                <%= r.getNOMBRE_ROL() %>
                            </option>
                        <% } } %>
                    </select>
                </div>

                <!-- Datos personales -->
                <div class="form-group">
                    <label>Nombre 1</label>
                    <input type="text" name="nombre1" required>
                </div>
                <div class="form-group">
                    <label>Nombre 2</label>
                    <input type="text" name="nombre2">
                </div>
                <div class="form-group">
                    <label>Apellido 1</label>
                    <input type="text" name="apellido1" required>
                </div>
                <div class="form-group">
                    <label>Apellido 2</label>
                    <input type="text" name="apellido2">
                </div>
                <div class="form-group">
                    <label>Cédula</label>
                    <input type="text" name="cedula" required>
                </div>
                <div class="form-group">
                    <label>Correo</label>
                    <input type="email" name="correo" required>
                </div>
                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" name="contrasena" required>
                </div>
                <div class="form-group">
                    <label>Salario</label>
                    <input type="number" step="0.01" name="salario" required>
                </div>
                <div class="form-group">
                    <label>Tipo contrato</label>
                    <input type="text" name="tipo_contrato" required>
                </div>
                <div class="form-group">
                    <label>Estado</label>
                    <select name="estado">
                        <option value="true">Activo</option>
                        <option value="false">Inactivo</option>
                    </select>
                </div>
            </div>

            <!-- Sección Vendedor -->
            <div id="seccionVendedor" style="display:none; margin-top:1rem; border:1px solid #eee; padding:1rem;">
                <h3>Datos de Vendedor</h3>
                <div class="form-group">
                    <label>Código vendedor (opcional)</label>
                    <input type="text" name="codigo_vendedor">
                </div>
                <div class="form-group">
                    <label>Distrito</label>
                    <select name="codigo_distrito">
                        <option value="">Seleccione distrito</option>
                        <% if (distritos != null) {
                               for (DistritoModel d : distritos) { %>
                            <option value="<%= d.getCodigoDistrito()%>"><%= d.getNombreDistrito()%></option>
                        <% } } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Tipo vendedor</label>
                    <input type="text" name="tipo_vendedor">
                </div>
            </div>

            <div class="form-actions" style="margin-top:1rem;">
                <button type="submit" class="btn-primary">Guardar</button>
                <a href="PersonalController" class="btn-secondary">Cancelar</a>
            </div>
        </form>
    </main>
</div>

<script>
    const rolSelect = document.getElementById('rolSelect');
    const seccionVendedor = document.getElementById('seccionVendedor');
    const esVendedorInput = document.getElementById('esVendedorInput');

    function actualizarSeccionVendedor() {
        const opcion = rolSelect.selectedOptions[0];
        const esVendedor = opcion.getAttribute('data-vendedor') === 'true';
        seccionVendedor.style.display = esVendedor ? 'block' : 'none';
        esVendedorInput.value = esVendedor; // Actualiza hidden input
    }

    rolSelect.addEventListener('change', actualizarSeccionVendedor);
    window.onload = actualizarSeccionVendedor;
</script>
</body>
</html>
