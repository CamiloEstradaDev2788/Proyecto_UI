package com.bodegapp.dashboard.controller;

import com.bodegapp.dashboard.dao.DashboardDAO;
import com.bodegapp.usuarios.model.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

    private DashboardDAO dashboardDAO = new DashboardDAO();

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

        // Ejecutar consulta
        double totalVentas      = dashboardDAO.obtenerTotalVentas(idEmpresa);
        double gastosTotales    = dashboardDAO.obtenerGastosTotales(idEmpresa, inicio, fin);

        // Pasar datos al JSP
        request.setAttribute("totalVentas", totalVentas);
        request.setAttribute("gastosTotales", gastosTotales);

        // Ir al dashboard
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
