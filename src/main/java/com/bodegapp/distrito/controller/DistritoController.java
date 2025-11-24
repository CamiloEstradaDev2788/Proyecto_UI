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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if ("nuevo".equals(accion)) {
                // Mostrar formulario para agregar
                request.getRequestDispatcher("AgregarDistrito.jsp").forward(request, response);
            } else if ("editar".equals(accion)) {
                // Mostrar formulario para editar
                String codigo = request.getParameter("id");
                if (codigo != null) {
                    DistritoModel distrito = service.obtenerDistrito(codigo);
                    request.setAttribute("distrito", distrito);
                    request.getRequestDispatcher("editarDistrito.jsp").forward(request, response);
                } else {
                    response.sendRedirect("DistritoController");
                }
            } else if ("eliminar".equals(accion)) {
                // Eliminar distrito
                String codigo = request.getParameter("id");
                if (codigo != null) {
                    service.eliminarDistrito(codigo);
                }
                response.sendRedirect("DistritoController");
            } else {
                // Listado por defecto
                List<DistritoModel> lista = service.listarDistritos();
                request.setAttribute("listaDistritos", lista);
                request.getRequestDispatcher("distritosLista.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error al procesar DistritoController", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accion = request.getParameter("accion"); // insert o actualizar
        String codigo = request.getParameter("codigoDistrito");
        String nombre = request.getParameter("nombreDistrito");

        DistritoModel distrito = new DistritoModel(codigo, nombre);

        try {
            if ("insertar".equals(accion)) {
                service.agregarDistrito(distrito);
            } else if ("actualizar".equals(accion)) {
                service.actualizarDistrito(distrito);
            }
            response.sendRedirect("DistritoController");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
