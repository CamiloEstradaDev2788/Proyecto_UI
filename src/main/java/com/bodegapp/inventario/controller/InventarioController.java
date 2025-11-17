package com.bodegapp.inventario.controller;

import java.io.IOException;
import java.util.List;

import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.inventario.service.InventarioService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/InventarioController")
public class InventarioController extends HttpServlet {

    InventarioService service = new InventarioService();

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {

        String accion = rq.getParameter("accion");
        String buscar = rq.getParameter("buscar");

        List<InventarioModel> lista;

        if (buscar != null && !buscar.trim().isEmpty()) {
            lista = service.buscar(buscar.trim());
        } else {
            lista = service.listar();
        }

        if (accion != null && accion.equals("eliminar")) {
            // Aquí puedes implementar la eliminación si es necesario
            // Por ahora solo redirigimos a la lista
        }

        rq.setAttribute("listaInventario", lista);
        rq.setAttribute("criterioBusqueda", buscar != null ? buscar : "");

        rq.getRequestDispatcher("inventario.jsp").forward(rq, rs);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {

        InventarioModel producto = new InventarioModel();

        try {
            producto.setCODIGO_PRODUCTO(rq.getParameter("codigo_producto"));
            producto.setDESCRIPCION_PRODUCTO(rq.getParameter("descripcion_producto"));
            producto.setPRECIO_PRODUCTO(Double.parseDouble(rq.getParameter("precio_producto")));
            producto.setSACO_PRODUCTO(Integer.parseInt(rq.getParameter("saco_producto")));
            producto.setMINIMO_STOCK(Integer.parseInt(rq.getParameter("minimo_stock")));
            producto.setUNIDAD_PRODUCTO(rq.getParameter("unidad_producto"));
            producto.setLINEA_PRODUCTO(rq.getParameter("linea_producto"));
            producto.setIMPUESTO_PRODUCTO(rq.getParameter("impuesto_producto"));

            if (service.registrar(producto)) {
                rs.sendRedirect("InventarioController");
            } else {
                rq.setAttribute("error", "No se pudo registrar el producto.");
                rq.getRequestDispatcher("agregarProducto.jsp").forward(rq, rs);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error al procesar datos del formulario: " + e.getMessage());
            rq.setAttribute("error", "Error en los datos ingresados. Verifique los campos numéricos.");
            rq.getRequestDispatcher("agregarProducto.jsp").forward(rq, rs);
        }
    }
}
