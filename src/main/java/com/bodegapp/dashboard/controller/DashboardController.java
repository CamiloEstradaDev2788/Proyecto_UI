package com.bodegapp.dashboard.controller;

import com.bodegapp.dashboard.dao.DashboardDAO;
import com.bodegapp.inventario.dto.ProductoDTO;
import com.bodegapp.dashboard.dao.AlertaDTO;  // <-- Nueva importación
import com.bodegapp.dashboard.dao.AlertasDAO;
import com.bodegapp.usuarios.model.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

    private DashboardDAO dashboardDAO = new DashboardDAO();
    private AlertasDAO alerta = new AlertasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Validar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String inicio = "2013-11-01";
        String fin = "2013-12-31";

        // Obtener usuario desde sesión
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        // Obtener id_empresa
        int idEmpresa = usuario.getID_EMPRESA();

        // Consultas existentes
        double totalVentas      = dashboardDAO.obtenerTotalVentas(idEmpresa);
        double gastosTotales    = dashboardDAO.obtenerGastosTotales(idEmpresa, inicio, fin);
        double ganancias        = dashboardDAO.obtenerGanancias(idEmpresa, inicio, fin);

        // Productos mostrados en la tabla
        List<ProductoDTO> productosRecientes = dashboardDAO.obtenerProductosRecientes(idEmpresa);

        // NUEVO: Obtener alertas
        List<AlertaDTO> alertas = alerta.obtenerAlertas(idEmpresa);
        System.out.println("Alertas encontradas: " + alertas.size());

        // Enviar datos al JSP
        request.setAttribute("totalVentas", totalVentas);
        request.setAttribute("gastosTotales", gastosTotales);
        request.setAttribute("gananciasTotales", ganancias);
        request.setAttribute("productosRecientes", productosRecientes);

        // NUEVO: enviar alertas al dashboard.jsp
        request.setAttribute("alertas", alertas);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
