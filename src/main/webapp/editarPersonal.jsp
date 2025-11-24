<%@page import="com.bodegapp.roles.model.RolModel"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bodegapp.personal.model.PersonalModel" %>
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

    <!-- Material Icons Sharp -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">

    <!-- Estilos -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">

    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; }
        .container { display: flex; min-height: 100vh; }
        aside { width: 220px; background: #1e1e2f; color: #fff; display: flex; flex-direction: column; }
        aside .logo { text-align: center; padding: 1rem; font-size: 1.2rem; }
        aside .logo .material-icons-sharp { font-size: 2rem; vertical-align: middle; margin-right: 0.5rem; }
        aside .sidebar a { display: flex; align-items: center; padding: 0.8rem 1rem; color: #fff; text-decoration: none; transition: 0.3s; }
        aside .sidebar a.active, aside .sidebar a:hover { background: #3b3b5e; border-radius: 0.5rem; }
        aside .sidebar a span { margin-right: 0.8rem; }
        main { flex: 1; padding: 2rem; background: #f5f5f5; }

        h1 { color: #1e1e2f; margin-bottom: 1rem; }
        .form-card { background: #fff; padding: 2rem; border-radius: 1rem; box-shadow: 0 4px 12px rgba(0,0,0,0.1); max-width: 900px; margin: auto; }
        .form-card h2 { margin-bottom: 1rem; color: #1e1e2f; }
        .row { display: grid; grid-template-columns: repeat(2, 1fr); gap: 1.2rem; }
        .form-group { display: flex; flex-direction: column; }
        .form-group label { font-weight: 600; margin-bottom: 0.4rem; }
        .form-group input, .form-group select { padding: 0.8rem; border: 1px solid #ccc; border-radius: 0.5rem; }
        .form-actions { margin-top: 1.5rem; display: flex; gap: 1rem; }
        .btn-primary { background: #5c6bc0; color: #fff; border: none; padding: 0.8rem 1.5rem; border-radius: 0.5rem; cursor: pointer; transition: 0.3s; }
        .btn-primary:hover { background: #3f51b5; }
        .btn-secondary { background: #ccc; color: #333; padding: 0.8rem 1.5rem; border-radius: 0.5rem; text-decoration: none; display: inline-flex; align-items: center; justify-content: center; }
        #seccionVendedor { border: 1px solid #eee; padding: 1rem; border-radius: 0.6rem; margin-top: 1rem; background: #fafafa; }
    </style>

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

    <!-- Sidebar -->
    <aside>
        <div class="logo">
            <span class="material-icons-sharp">storefront</span>
            BodegApp
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
                <a href="<%= request.getContextPath() %>/DistritoController" >
                <span class="material-symbols-sharp">location_city</span>
                <h3>Distritos</h3>
            </a>
            <a href="<%= request.getContextPath() %>/ClientesController" class="active">
                <span class="material-icons-sharp">groups</span>
                <h3>Clientes</h3>
            </a>
        </div>
    </aside>

    <!-- MAIN -->
    <main>
        <h1>Editar Personal</h1>

        <div class="form-card">
            <form action="PersonalController" method="post">
                <input type="hidden" name="accion" value="actualizar">
                <input type="hidden" name="id_user" value="<%= p.getID_USER() %>">

                <h2>Datos del Personal</h2>
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

                <div id="seccionVendedor">
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

                <div class="form-actions">
                    <button type="submit" class="btn-primary">Actualizar</button>
                    <a href="PersonalController" class="btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </main>

</div>
</body>
</html>
