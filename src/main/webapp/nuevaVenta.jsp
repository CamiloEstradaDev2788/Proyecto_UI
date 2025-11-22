<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.bodegapp.vendedor.model.*" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva Venta</title>

    <style>
        body {
            font-family: Arial;
            margin: 30px;
        }
        h2 {
            margin-bottom: 10px;
        }

        .form-card {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            width: 900px;
            box-shadow: 0 0 10px #ccc;
        }

        input, select {
            padding: 5px;
            margin: 5px 0;
            width: 250px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        table, th, td {
            border: 1px solid #bbb;
        }

        th, td {
            padding: 8px;
            text-align: center;
        }

        .btn {
            padding: 8px 15px;
            background: #1976d2;
            color: #fff;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        .btn-delete {
            background: #d32f2f;
        }
        .btn-add {
            background: #2e7d32;
        }

        .msg-error {
            padding: 10px;
            background: #ffebee;
            color: #c62828;
            border-left: 4px solid #c62828;
            margin-bottom: 10px;
        }

        .msg-ok {
            padding: 10px;
            background: #e8f5e9;
            color: #2e7d32;
            border-left: 4px solid #2e7d32;
            margin-bottom: 10px;
        }
    </style>

    <script>
        function agregarFila() {
            const table = document.getElementById("tablaDetalles");
            const row = table.insertRow();

            row.innerHTML = `
                <td><input name="CODIGO_PRODUCTO" required></td>
                <td><input name="CANTIDAD_VENTA" type="number" min="1" required></td>
                <td><input name="PRECIO_VENTA" type="number" step="0.01" required></td>
                <td><button type="button" class="btn btn-delete" onclick="eliminarFila(this)">X</button></td>
            `;
        }

        function eliminarFila(btn) {
            const row = btn.parentNode.parentNode;
            row.remove();
        }
    </script>
</head>

<body>

    <h2>Registrar Nueva Venta</h2>

    <% if (request.getAttribute("error") != null) { %>
        <div class="msg-error"><%= request.getAttribute("error") %></div>
    <% } %>

    <% if (request.getAttribute("mensaje") != null) { %>
        <div class="msg-ok">
            <%= request.getAttribute("mensaje") %><br>
            ID Venta: <b><%= request.getAttribute("idVentaGenerada") %></b>
        </div>
    <% } %>

    <div class="form-card">

        <form action="<%= request.getContextPath() %>/nuevaVenta" method="POST">

            <h3>Datos Generales</h3>

            <label>Vendedor:</label><br>
            <input type="text" name="CODIGO_VENDEDOR" required><br><br>

            <label>Cliente:</label><br>
            <input type="text" name="CODIGO_CLIENTE" required>
            <button type="button" class="btn">Agregar Cliente</button>
            <br><br>

            <label>Fecha Factura:</label><br>
            <input type="date" name="FECHA_FACTURA" required><br><br>

            <label>Fecha Cancelación:</label><br>
            <input type="date" name="FECHA_CANCELACION" required><br><br>

            <label>Estado:</label><br>
            <select name="ESTADO_FACTURA">
                <option value="PAGADA">PAGADA</option>
                <option value="PENDIENTE">PENDIENTE</option>
            </select>
            <br><br>

            <label>IGV (%):</label><br>
            <input type="number" name="PORCENTAJE_IGV" step="0.01" value="18"><br><br>

            <hr><br>

            <h3>Detalle de Productos</h3>

            <button type="button" class="btn btn-add" onclick="agregarFila()">Agregar Producto</button>

            <table id="tablaDetalles">
                <tr>
                    <th>Código Producto</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Acción</th>
                </tr>
            </table>

            <br><br>

            <button type="submit" class="btn">Registrar Venta</button>

        </form>

    </div>

</body>
</html>
