<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bodegapp.inventario.model.InventarioModel" %>
<%@ page import="com.bodegapp.proveedor.model.ProveedorModel" %>
<%@ page import="com.bodegapp.usuarios.model.UsuarioModel" %>
<%@ page import="java.util.List" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");
    InventarioModel prod = (InventarioModel) request.getAttribute("producto");
    List<ProveedorModel> listaProveedores = (List<ProveedorModel>) request.getAttribute("listaProveedores");

    if (prod == null) {
%>
        <h2 style="color:red">⚠ No se encontró el producto a editar.</h2>
        <a href="InventarioController">Volver</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assest/styles/styles.css">

    <style>
        .form-card { background: var(--color-white); padding: 2rem; border-radius: 1rem; box-shadow: var(--box-shadow); margin-top: 2rem; width: 100%; }
        .form-title { font-size: 1.5rem; margin-bottom: 1.2rem; color: var(--color-dark); }
        .row { display: grid; grid-template-columns: repeat(2, 1fr); gap: 1.2rem; }
        .form-group { display: flex; flex-direction: column; }
        .form-group label { font-weight: bold; margin-bottom: .4rem; color: var(--color-dark); }
        .form-group input, .form-group select { padding: .9rem; border: 1px solid var(--color-light); border-radius: .6rem; background: var(--color-white); }
        .form-actions { margin-top: 1.5rem; display: flex; gap: 1rem; }
        .btn-primary { background: var(--color-primary); color: white; padding: .9rem 1.4rem; border-radius: .6rem; border: none; cursor: pointer; font-weight: bold; }
        .btn-secondary { background: var(--color-light); padding: .9rem 1.4rem; border-radius: .6rem; color: var(--color-dark); text-decoration: none; font-weight: bold; }
    </style>
</head>

<body>
<div class="container">

    <aside>
        <div class="top">
            <div class="logo"><img src="65844.png" alt="logo"></div>
        </div>
        <div class="sidebar">
            <a href="<%= request.getContextPath() %>/dashboard">
                <span class="material-symbols-sharp">dashboard</span>
                <h3>Dashboard</h3>
            </a>
            <a href="<%= request.getContextPath() %>/InventarioController" class="active">
                <span class="material-symbols-sharp">inventory</span>
                <h3>Inventario</h3>
            </a>
        </div>
    </aside>

    <main>
        <h1>Editar Producto</h1>

        <div class="form-card">

            <form action="InventarioController" method="post">

                <input type="hidden" name="accion" value="actualizar">
                <input type="hidden" name="codigo_producto" value="<%= prod.getCODIGO_PRODUCTO() %>">

                <h2 class="form-title">Datos del Producto</h2>

                <div class="row">

                    <div class="form-group">
                        <label>Descripción</label>
                        <input type="text" name="descripcion_producto" value="<%= prod.getDESCRIPCION_PRODUCTO() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Precio</label>
                        <input type="number" step="0.01" name="precio_producto" value="<%= prod.getPRECIO_PRODUCTO() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Cantidad</label>
                        <input type="number" name="saco_producto" value="<%= prod.getSACO_PRODUCTO() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Stock Mínimo</label>
                        <input type="number" name="minimo_stock" value="<%= prod.getMINIMO_STOCK() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Unidad</label>
                        <input type="text" name="unidad_producto" value="<%= prod.getUNIDAD_PRODUCTO() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Línea</label>
                        <input type="text" name="linea_producto" value="<%= prod.getLINEA_PRODUCTO() %>" required>
                    </div>

                    <div class="form-group">
                        <label>Impuesto</label>
                        <input type="text" name="impresion_producto" value="<%= prod.getIMPUESTO_PRODUCTO() %>">
                    </div>

                    <div class="form-group">
                        <label>Proveedor</label>
                        <select name="codigoProveedor" required>
                            <option value="">-- Seleccione proveedor --</option>
                            <% for (ProveedorModel p : listaProveedores) {
                                String selected = p.getCODIGO_PROVEEDOR().equals(prod.getNOMBRE_PROVEEDOR()) ? "selected" : "";
                            %>
                                <option value="<%= p.getCODIGO_PROVEEDOR() %>" <%= selected %>><%= p.getRAZON_SOCIAL() %></option>
                            <% } %>
                        </select>
                    </div>

                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-primary">Actualizar</button>
                    <a href="InventarioController" class="btn-secondary">Cancelar</a>
                </div>

            </form>
        </div>

    </main>
</div>
</body>
</html>
