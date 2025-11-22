<%@page import="com.bodegapp.inventario.model.InventarioModel"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.bodegapp.vendedor.model.*" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva Venta</title>

    <!-- jQuery local -->
    <script src="js/jquery-3.7.1.min.js"></script>

    <!-- Select2 local -->
    <link href="css/select2.min.css" rel="stylesheet" />
    <script src="js/select2.min.js"></script>

    <style>
        body { font-family: Arial; margin: 30px; }
        h2 { margin-bottom: 10px; }

        .form-card { background: #fff; padding: 20px; border-radius: 10px; width: 900px; box-shadow: 0 0 10px #ccc; }

        input, select { padding: 5px; margin: 5px 0; width: 250px; }

        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        table, th, td { border: 1px solid #bbb; }
        th, td { padding: 8px; text-align: center; }

        .btn { padding: 8px 15px; background: #1976d2; color: #fff; border: none; border-radius: 6px; cursor: pointer; }
        .btn-delete { background: #d32f2f; }
        .btn-add { background: #2e7d32; }

        .msg-error { background: #ffebee; padding: 10px; color: #c62828; border-left: 4px solid #c62828; margin-bottom: 10px; }
        .msg-ok { background: #e8f5e9; padding: 10px; color: #2e7d32; border-left: 4px solid #2e7d32; margin-bottom: 10px; }
    </style>

    <script>
        function agregarFila() {
            const table = document.getElementById("tablaDetalles");
            const row = table.insertRow();

            row.innerHTML = `
                <td>
                    <select name="CODIGO_PRODUCTO" class="select-producto" style="width: 250px" required>
                        <option value="">Seleccione...</option>
                        <% 
                           List<InventarioModel> productos = (List<InventarioModel>) request.getAttribute("listaProductos");
                           if (productos != null) {
                               for (InventarioModel p : productos) {
                        %>
                            <option value="<%= p.getCODIGO_PRODUCTO() %>" data-precio="<%= p.getPRECIO_PRODUCTO() %>">
                                <%= p.getDESCRIPCION_PRODUCTO() %>
                            </option>
                        <% 
                               }
                           }
                        %>
                    </select>
                </td>

                <td><input name="CANTIDAD_VENTA" type="number" min="1" required></td>

                <td><input name="PRECIO_VENTA" type="number" step="0.01" readonly></td>

                <td>
                    <button type="button" class="btn btn-delete" onclick="eliminarFila(this)">X</button>
                </td>
            `;

            const $select = $(row).find(".select-producto");
            $select.select2(); // Inicializa Select2

            $select.on("change", function () {
                const precio = $(this).find("option:selected").data("precio") || 0;
                $(row).find("input[name='PRECIO_VENTA']").val(precio);
            });
        }

        function eliminarFila(btn) {
            btn.closest("tr").remove();
        }

        $(document).ready(function() {
            agregarFila(); // Agregar primera fila al cargar
        });
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
        <input type="text" name="CODIGO_CLIENTE" required><br><br>

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
                <th>Producto</th>
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
