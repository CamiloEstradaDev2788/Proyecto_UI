<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto</title>

    <!-- ICONOS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />

    <!-- ESTILOS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">
    
    <style>
        .form-container {
            background: var(--color-white);
            padding: var(--card-padding);
            border-radius: var(--card-border-radius);
            margin-top: 1rem;
            box-shadow: var(--box-shadow);
        }
        
        .form-group {
            margin-bottom: 1.5rem;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: var(--color-dark);
            font-weight: 500;
        }
        
        .form-group input,
        .form-group select {
            width: 100%;
            padding: 0.8rem;
            border: 1px solid var(--color-light);
            border-radius: var(--border-radius-1);
            font-size: 0.9rem;
            transition: all 0.3s ease;
        }
        
        .form-group input:focus,
        .form-group select:focus {
            border-color: var(--color-primary);
            outline: none;
        }
        
        .form-actions {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
        }
        
        .error-message {
            background-color: var(--color-danger);
            color: white;
            padding: 1rem;
            border-radius: var(--border-radius-1);
            margin-bottom: 1rem;
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
            <a href="<%= request.getContextPath() %>/dashboard.jsp">
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

            <a href="#">
                <span class="material-symbols-sharp">package_2</span>
                <h3>Proveedores</h3>
            </a>

            <a href="#">
                <span class="material-symbols-sharp">order_approve</span>
                <h3>Ventas</h3>
            </a>

            <a href="#">
                <span class="material-symbols-sharp">finance_mode</span>
                <h3>Analíticas</h3>
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
            <div class="error-message">
                <%= error %>
            </div>
        <% } %>

        <div class="form-container">
            <form action="<%= request.getContextPath() %>/InventarioController" method="post">
                <div class="form-group">
                    <label for="codigo_producto">Código del Producto *</label>
                    <input type="text" id="codigo_producto" name="codigo_producto" required>
                </div>

                <div class="form-group">
                    <label for="descripcion_producto">Descripción del Producto *</label>
                    <input type="text" id="descripcion_producto" name="descripcion_producto" required>
                </div>

                <div class="form-group">
                    <label for="precio_producto">Precio del Producto *</label>
                    <input type="number" id="precio_producto" name="precio_producto" step="0.01" min="0" required>
                </div>

                <div class="form-group">
                    <label for="saco_producto">Cantidad en Sacos *</label>
                    <input type="number" id="saco_producto" name="saco_producto" min="0" required>
                </div>

                <div class="form-group">
                    <label for="minimo_stock">Stock Mínimo *</label>
                    <input type="number" id="minimo_stock" name="minimo_stock" min="0" required>
                </div>

                <div class="form-group">
                    <label for="unidad_producto">Unidad del Producto *</label>
                    <input type="text" id="unidad_producto" name="unidad_producto" required>
                </div>

                <div class="form-group">
                    <label for="linea_producto">Línea del Producto *</label>
                    <input type="text" id="linea_producto" name="linea_producto" required>
                </div>

                <div class="form-group">
                    <label for="impuesto_producto">Impuesto del Producto *</label>
                    <input type="text" id="impuesto_producto" name="impuesto_producto" required>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Guardar Producto</button>
                    <a href="<%= request.getContextPath() %>/InventarioController" class="btn">Cancelar</a>
                </div>
            </form>
        </div>
    </main>

    <!-- ============ RIGHT PANEL ============ -->
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

