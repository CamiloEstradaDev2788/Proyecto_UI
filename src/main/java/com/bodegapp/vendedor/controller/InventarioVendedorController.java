package com.bodegapp.vendedor.controller;

import com.bodegapp.usuarios.model.UsuarioModel;
import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.vendedor.service.DashboardVendedorService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/InventarioVendedorController")
public class InventarioVendedorController extends HttpServlet {

    private DashboardVendedorService service = new DashboardVendedorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        UsuarioModel vendedor = (UsuarioModel) session.getAttribute("usuario");
        if (vendedor == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Obtener inventario según la empresa del vendedor
        List<InventarioModel> listaInventario = service.listarInventario(vendedor.getID_EMPRESA());

        req.setAttribute("listaInventario", listaInventario);
        req.setAttribute("usuario", vendedor);
        req.getRequestDispatcher("inventarioVendedor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        UsuarioModel vendedor = (UsuarioModel) session.getAttribute("usuario");
        if (vendedor == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String codigoProducto = req.getParameter("codigoProducto");
        String cantidadStr = req.getParameter("cantidadSolicitada");

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Cantidad inválida");
            req.setAttribute("listaInventario", service.listarInventario(vendedor.getID_EMPRESA()));
            req.setAttribute("usuario", vendedor);
            req.getRequestDispatcher("inventarioVendedor.jsp").forward(req, resp);
            return;
        }

        // ✔ AHORA SÍ LLAMA AL MÉTODO CORRECTO
        boolean exito = service.solicitarProducto(
                vendedor.getID_EMPRESA(),   // ✔ TU SERVICE RECIBE LA EMPRESA
                codigoProducto,
                cantidad
        );

        if (exito) {
            req.setAttribute("mensaje", "Solicitud enviada correctamente");
        } else {
            req.setAttribute("error", "Error al enviar la solicitud");
        }

        req.setAttribute("listaInventario", service.listarInventario(vendedor.getID_EMPRESA()));
        req.setAttribute("usuario", vendedor);
        req.getRequestDispatcher("inventarioVendedor.jsp").forward(req, resp);
    }
}
