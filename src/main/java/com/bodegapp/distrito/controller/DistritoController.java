package com.bodegapp.distrito.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.bodegapp.distrito.model.DistritoModel;
import com.bodegapp.distrito.service.DistritoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DistritoController")
public class DistritoController extends HttpServlet {

    private DistritoService service;

    @Override
    public void init() throws ServletException {
        try {
            service = new DistritoService();
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar DistritoService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {

        String accion = rq.getParameter("accion");

        try {
            if ("nuevo".equals(accion)) {
                rq.getRequestDispatcher("distritoNuevo.jsp").forward(rq, rs);
            } else if ("editar".equals(accion)) {
                String codigo = rq.getParameter("codigo");
                DistritoModel distrito = service.obtenerDistrito(codigo);
                rq.setAttribute("distrito", distrito);
                rq.getRequestDispatcher("distritoEditar.jsp").forward(rq, rs);
            } else if ("eliminar".equals(accion)) {
                String codigo = rq.getParameter("codigo");
                service.eliminarDistrito(codigo);
                rs.sendRedirect("DistritoController");
            } else {
                List<DistritoModel> lista = service.listarDistritos();
                rq.setAttribute("listaDistritos", lista);
                rq.getRequestDispatcher("distritoListar.jsp").forward(rq, rs);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {

        String codigo = rq.getParameter("codigoDistrito");
        String nombre = rq.getParameter("nombreDistrito");

        DistritoModel distrito = new DistritoModel(codigo, nombre);

        try {
            String accion = rq.getParameter("accion");
            if ("agregar".equals(accion)) {
                service.agregarDistrito(distrito);
            } else if ("actualizar".equals(accion)) {
                service.actualizarDistrito(distrito);
            }
            rs.sendRedirect("DistritoController");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
