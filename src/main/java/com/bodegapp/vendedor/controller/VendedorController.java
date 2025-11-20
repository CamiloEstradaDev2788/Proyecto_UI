package com.bodegapp.vendedor.controller;

import com.bodegapp.vendedor.service.DashboardVendedorService;
import com.bodegapp.usuarios.model.UsuarioModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/dashboardVendedor")
public class VendedorController extends HttpServlet {

    private DashboardVendedorService service = new DashboardVendedorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        UsuarioModel vendedor = (UsuarioModel) session.getAttribute("usuario");

        if (vendedor == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String codigoVendedor = vendedor.getID_VENDEDOR(); // ajusta si usa otro nombre

        // Totales históricos
        double ventasTotales = service.obtenerTotalVentas(codigoVendedor);
        int productosTotales = service.obtenerTotalProductosVendidos(codigoVendedor);
        int clientesTotales = service.obtenerTotalClientesAtendidos(codigoVendedor);
        var ventasRecientes = service.obtenerVentasRecientes(codigoVendedor);

        // Enviar datos a la JSP
        req.setAttribute("ventasTotales", ventasTotales);        // puedes renombrar en JSP si quieres
        req.setAttribute("productosTotales", productosTotales);
        req.setAttribute("clientesTotales", clientesTotales);
        req.setAttribute("ventasRecientes", ventasRecientes);

        req.getRequestDispatcher("dashboardVendedor.jsp").forward(req, resp);
    }
}
