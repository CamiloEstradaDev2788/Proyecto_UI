<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.clientes.model.ClienteModel" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    ClienteModel c = (ClienteModel) request.getAttribute("cliente");
    if (c == null) {
%>
        <h2 style="color:red">⚠ No se encontró el cliente a editar.</h2>
        <a href="ClientesController">Volver</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Cliente</title>

    <!-- ICONOS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />

    <!-- ESTILOS -->
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
            margin-bottom: 1.2rem;
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
            font-weight: bold;
            margin-bottom: .4rem;
            color: var(--color-dark);
        }
        .form-group input {
            padding: .9rem;
            border: 1px solid var(--color-light);
            border-radius: .6rem;
            background: var(--color-white);
        }
        .form-actions {
            margin-top: 1.5rem;
            display: flex;
            gap: 1rem;
        }
        .btn-primary {
            background: var(--color-primary);
            color: white;
            padding: .9rem 1.4rem;
            border-radius: .6rem;
            border: none;
            cursor: pointer;
            font-weight: bold;
        }
        .btn-secondary {
            background: var(--color-light);
            padding: .9rem 1.4rem;
            border-radius: .6rem;
            color: var(--color-dark);
            text-decoration: none;
            font-weight: bold;
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
                <a href="<%= request.getContextPath() %>/ClientesController" class="active">
                <span class="material-symbols-sharp">groups</span>
                <h3>Clientes</h3>
            </a>
                <a href="<%= request.getContextPath() %>/DistritoController" >
                <span class="material-symbols-sharp">location_city</span>
                <h3>Distritos</h3>
            </a>
        </div>
    </aside>

    <!-- ============ MAIN ============ -->
    <main>
        <h1>Editar Cliente</h1>

        <div class="form-card">

            <form action="ClientesController" method="post">

                <input type="hidden" name="action" value="actualizar">

                <h2 class="form-title">Datos del Cliente</h2>

                <div class="row">

                    <div class="form-group">
                        <label>Código</label>
                        <input type="text" name="codigo_cliente"
                               value="<%= c.getCODIGO_CLIENTE() %>" readonly>
                    </div>

                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" name="nombre_cliente"
                               value="<%= c.getNOMBRE_CLIENTE() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Dirección</label>
                        <input type="text" name="direccion_cliente"
                               value="<%= c.getDIRECCION_CLIENTE() %>">
                    </div>

                    <div class="form-group">
                        <label>Teléfono</label>
                        <input type="text" name="telefono_cliente"
                               value="<%= c.getTELEFONO_CLIENTE() %>">
                    </div>

                    <div class="form-group">
                        <label>RUC</label>
                        <input type="text" name="ruc_cliente"
                               value="<%= c.getRUC_CLIENTE() %>">
                    </div>

                    <div class="form-group">
                        <label>Fecha Registro</label>
                        <input type="date" name="fecha_registro"
                               value="<%= (c.getFECHA_REGISTRO() != null)
                                        ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(c.getFECHA_REGISTRO())
                                        : "" %>">
                    </div>

                    <div class="form-group">
                        <label>Tipo</label>
                        <input type="text" name="tipo_cliente"
                               value="<%= c.getTIPO_CLIENTE() %>">
                    </div>

                    <div class="form-group">
                        <label>Condición</label>
                        <input type="text" name="condicion_cliente"
                               value="<%= c.getCONDICION_CLIENTE() %>">
                    </div>

                    <div class="form-group">
                        <label>Distrito</label>
                        <input type="text" name="codigo_distrito"
                               value="<%= c.getCODIGO_DISTRITO() %>">
                    </div>

                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-primary">Actualizar</button>
                    <a href="ClientesController" class="btn-secondary">Cancelar</a>
                </div>

            </form>

        </div>
    </main>

</div>

</body>
</html>
