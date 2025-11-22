package com.bodegapp.vendedor.controller;

import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.inventario.model.ProductoModel;
import com.bodegapp.usuarios.model.UsuarioModel;
import com.bodegapp.vendedor.dao.NuevaVentaDAO;
import com.bodegapp.vendedor.model.NuevaVentaDTO;
import com.bodegapp.vendedor.model.NuevoDetalleVentaDTO;
import com.bodegapp.vendedor.service.DashboardVendedorService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/nuevaVenta")
public class NuevaVentaController extends HttpServlet {

     private DashboardVendedorService service = new DashboardVendedorService();
    // =========================================
    // 1️⃣ GET → solo muestra el formulario
    // =========================================
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    try {

        // Obtén el usuario desde sesión (o de donde lo tengas)
        UsuarioModel vendedor = (UsuarioModel) request.getSession().getAttribute("usuario");

        if (vendedor == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Cargar inventario según empresa
        List<InventarioModel> listaProductos = service.listarInventario(vendedor.getID_EMPRESA());
        request.setAttribute("listaProductos", listaProductos);
        request.setAttribute("usuario", vendedor);

        System.out.println("Cantidad productos inventario = " + listaProductos.size());

    for (InventarioModel p : listaProductos) {
    System.out.println("Producto: " + p.getCODIGO_PRODUCTO() + 
        " - " + p.getDESCRIPCION_PRODUCTO() + 
        " - Precio: " + p.getPRECIO_PRODUCTO());
}
        // Ir al JSP
        request.getRequestDispatcher("nuevaVenta.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "No se pudo cargar el inventario para la venta.");
        request.getRequestDispatcher("nuevaVenta.jsp").forward(request, response);
    }
}


    // =========================================
    // 2️⃣ POST → procesa y registra la venta
    // =========================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NuevaVentaDAO dao = new NuevaVentaDAO();

        try {
            // -------------------------------
            // 1️⃣ CAPTURAR DATOS PRINCIPALES
            // -------------------------------
            String codigoVendedor = request.getParameter("CODIGO_VENDEDOR");
            String codigoCliente  = request.getParameter("CODIGO_CLIENTE");
            String fechaFacturaStr = request.getParameter("FECHA_FACTURA");
            String fechaCancelacionStr = request.getParameter("FECHA_CANCELACION");

            Date fechaFactura     = fechaFacturaStr != null && !fechaFacturaStr.isEmpty()
                    ? Date.valueOf(fechaFacturaStr)
                    : null;

            Date fechaCancelacion = fechaCancelacionStr != null && !fechaCancelacionStr.isEmpty()
                    ? Date.valueOf(fechaCancelacionStr)
                    : null;

            String estadoFactura  = request.getParameter("ESTADO_FACTURA");
            double porcentajeIGV  = Double.parseDouble(request.getParameter("PORCENTAJE_IGV"));

            // -------------------------------
            // 2️⃣ VALIDAR CLIENTE
            // -------------------------------
            if (!dao.existeCliente(codigoCliente)) {
                request.setAttribute("error", "❌ El cliente no existe. Debe agregarlo.");
                request.getRequestDispatcher("nuevaVenta.jsp").forward(request, response);
                return;
            }

            // -------------------------------
            // 3️⃣ LLENAR DTO DE VENTA
            // -------------------------------
            NuevaVentaDTO venta = new NuevaVentaDTO();
            venta.setCODIGO_VENDEDOR(codigoVendedor);
            venta.setCODIGO_CLIENTE(codigoCliente);
            venta.setFECHA_FACTURA(fechaFactura);
            venta.setFECHA_CANCELACION(fechaCancelacion);
            venta.setESTADO_FACTURA(estadoFactura);
            venta.setPROCENTAJE_IGV(porcentajeIGV);

            // -------------------------------
            // 4️⃣ DETALLES DE VENTA
            // -------------------------------
            String[] productos = request.getParameterValues("CODIGO_PRODUCTO");
            String[] cantidades = request.getParameterValues("CANTIDAD_VENTA");
            String[] precios = request.getParameterValues("PRECIO_VENTA");

            List<NuevoDetalleVentaDTO> detalles = new ArrayList<>();

            if (productos != null) {
                for (int i = 0; i < productos.length; i++) {
                    NuevoDetalleVentaDTO d = new NuevoDetalleVentaDTO();
                    d.setCODIGO_PRODUCTO(productos[i]);
                    d.setCANTIDAD_VENTA(Integer.parseInt(cantidades[i]));
                    d.setPRECIO_VENTA(Double.parseDouble(precios[i]));
                    detalles.add(d);
                }
            }

            // -------------------------------
            // 5️⃣ REGISTRAR LA VENTA
            // -------------------------------
            String idVentaGenerada = dao.registrarVenta(venta, detalles);

            // -------------------------------
            // 6️⃣ RESPUESTA AL JSP
            // -------------------------------
            request.setAttribute("mensaje", "✔ Venta registrada exitosamente.");
            request.setAttribute("idVentaGenerada", idVentaGenerada);
            response.sendRedirect("dashboardVendedor");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Error al registrar venta: " + e.getMessage());
            request.getRequestDispatcher("dashboardVendedor").forward(request, response);
        }
    }
}
