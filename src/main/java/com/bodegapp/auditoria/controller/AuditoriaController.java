package com.bodegapp.auditoria.controller;

import com.bodegapp.auditoria.dao.AuditoriaDAO;
import com.bodegapp.auditoria.model.AuditoriaModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/auditoria")
public class AuditoriaController extends HttpServlet {

    private AuditoriaDAO dao = new AuditoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        List<AuditoriaModel> lista = dao.listar(fechaInicio, fechaFin);

        request.setAttribute("listaAuditoria", lista);
        request.setAttribute("fechaInicio", fechaInicio);
        request.setAttribute("fechaFin", fechaFin);

        request.getRequestDispatcher("auditoria.jsp").forward(request, response);
    }
}
