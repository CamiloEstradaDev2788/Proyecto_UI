<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>
<%@ page import="com.bodegapp.proveedor.model.ProveedorModel" %>
<%@ page import="java.util.List" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");

    List<ProveedorModel> proveedores = (List<ProveedorModel>) request.getAttribute("listaProveedores");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto</title>

    <!-- ICONOS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />

    <!-- ESTILOS GENERALES -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">

    <style>
        .form-card {
            background: var(--color-white);
            padding: 2rem;
            border-radius: 1rem;
            box-shadow: var(--box-shadow);
            margin-top: 2rem;
            width: 100%;
        }
        .form-title {
            font-size: 1.5rem;
            margin-bottom: 1rem;
            color: var(--color-dark);
        }
        .row {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 1.2rem;
        }
        .form-group {
            display: flex;
            flex-direction: column;
        }
        .form-group label {
            font-weight: 600;
            color: var(--color-dark);
            margin-bottom: .5rem;
        }
        .form-group input, .form-group select {
            padding: .9rem;
            border: 1px solid var(--color-light);
            border-radius: .6rem;
            background: var(--color-white);
            transition: .3s ease;
        }
        .form-group input:focus, .form-group select:focus {
            border-color: var(--color-primary);
        }
        .btn-primary {
            background: var(--color-primary);
            color: white;
            padding: .9rem 1.5rem;
            border-radius: .6rem;
            cursor: pointer;
            border: none;
            font-weight: bold;
            transition: .3s ease;
        }
        .btn-primary:hover {
            background: var(--color-primary-variant);
        }
        .btn-secondary {
            background: var(--color-light);
            padding: .9rem 1.5rem;
            border-radius: .6rem;
            color: var(--color-dark);
            text-decoration: none;
            font-weight: bold;
        }
        .error-message {
            background: var(--color-danger);
            color: white;
            padding: 1rem;
            border-radius: .6rem;
            margin-bottom: 1rem;
        }
        .form-actions {
            margin-top: 1.8rem;
            display: flex;
            gap: 1rem;
        }
    </style>
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
            <a href="<%= request.getContextPath() %>/dashboard" >
                    <span class="material-symbols-sharp">dashboard</span>
                    <h3>Dashboard</h3>
                </a>
                <a href="<%= request.getContextPath() %>/InventarioController" class="active">
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
                <a href="<%= request.getContextPath() %>/DistritoController">
                <span class="material-symbols-sharp">location_city</span>
                <h3>Distritos</h3>
            </a>

            <a href="logout">
                <span class="material-symbols-sharp">logout</span>
                <h3>Logout</h3>
            </a>
        </div>
    </aside>

    <!-- ============ MAIN ============ -->
    <main>
        <h1>Agregar Producto</h1>

        <% if (error != null) { %>
            <div class="error-message"><%= error %></div>
        <% } %>

        <div class="form-card">

            <form action="<%= request.getContextPath() %>/InventarioController" method="post">

                <h2 class="form-title">Datos del Producto</h2>

                <div class="row">
                    <div class="form-group">
                        <label>Código *</label>
                        <input type="text" name="codigo_producto" required>
                    </div>

                    <div class="form-group">
                        <label>Descripción *</label>
                        <input type="text" name="descripcion_producto" required>
                    </div>

                    <div class="form-group">
                        <label>Precio *</label>
                        <input type="number" name="precio_producto" step="0.01" min="0" required>
                    </div>

                    <div class="form-group">
                        <label>Sacos *</label>
                        <input type="number" name="saco_producto" min="0" required>
                    </div>

                    <div class="form-group">
                        <label>Stock mínimo *</label>
                        <input type="number" name="minimo_stock" min="0" required>
                    </div>

                    <div class="form-group">
                        <label>Unidad *</label>
                        <input type="text" name="unidad_producto" required>
                    </div>

                    <div class="form-group">
                        <label>Línea *</label>
                        <input type="text" name="linea_producto" required>
                    </div>

                    <div class="form-group">
                        <label>Impuesto *</label>
                        <input type="text" name="impuesto_producto" required>
                    </div>

                    <div class="form-group">
                        <label>Proveedor *</label>
                        <select name="codigoProveedor" required>
                            <option value="">Seleccione un proveedor</option>

                            <% if (proveedores != null) { 
                                   for (ProveedorModel p : proveedores) { %>
                                <option value="<%= p.getCODIGO_PROVEEDOR() %>">
                                    <%= p.getREPRESENTANTE_PROVEEDOR() %>
                                </option>
                            <% }} %>
                        </select>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-primary">Guardar</button>
                    <a href="<%= request.getContextPath() %>/InventarioController" class="btn-secondary">Cancelar</a>
                </div>

            </form>

        </div>

    </main>
</div>

</body>
</html>
