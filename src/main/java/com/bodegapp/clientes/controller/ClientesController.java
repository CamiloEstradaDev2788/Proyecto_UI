package com.bodegapp.clientes.controller;

import com.bodegapp.clientes.model.ClienteModel;
import com.bodegapp.clientes.service.ClienteService;
import com.bodegapp.usuarios.model.UsuarioModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/ClientesController")
public class ClientesController extends HttpServlet {

    private ClienteService service = new ClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession(false);
        if (ses == null || ses.getAttribute("usuario") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String accion = req.getParameter("accion");
        String buscar = req.getParameter("buscar");

        if ("nuevo".equals(accion)) {
            req.getRequestDispatcher("agregarCliente.jsp").forward(req, resp);
            return;
        } else if ("editar".equals(accion)) {
            String codigo = req.getParameter("id");
            ClienteModel c = service.obtener(codigo);
            req.setAttribute("cliente", c);
            req.getRequestDispatcher("editarCliente.jsp").forward(req, resp);
            return;
        } else if ("eliminar".equals(accion)) {
            String codigo = req.getParameter("id");
            boolean ok = service.eliminar(codigo);
            resp.sendRedirect("ClientesController");
            return;
        }

        List<ClienteModel> lista = (buscar != null && !buscar.trim().isEmpty()) ? service.buscar(buscar.trim()) : service.listar();
        req.setAttribute("listaClientes", lista);
        req.setAttribute("criterioBusqueda", buscar != null ? buscar : "");
        req.getRequestDispatcher("listaClientes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // manejar crear o actualizar segun hidden action
        String action = req.getParameter("action") != null ? req.getParameter("action") : "guardar";

        try {
            ClienteModel c = new ClienteModel();
            c.setCODIGO_CLIENTE(req.getParameter("codigo_cliente"));
            c.setNOMBRE_CLIENTE(req.getParameter("nombre_cliente"));
            c.setDIRECCION_CLIENTE(req.getParameter("direccion_cliente"));
            c.setTELEFONO_CLIENTE(req.getParameter("telefono_cliente"));
            c.setRUC_CLIENTE(req.getParameter("ruc_cliente"));

            String fechaStr = req.getParameter("fecha_registro");
            if (fechaStr == null || fechaStr.isEmpty()) {
                c.setFECHA_REGISTRO(new Date(System.currentTimeMillis()));
            } else {
                c.setFECHA_REGISTRO(Date.valueOf(fechaStr)); // expecting yyyy-MM-dd
            }

            c.setTIPO_CLIENTE(req.getParameter("tipo_cliente"));
            c.setCONDICION_CLIENTE(req.getParameter("condicion_cliente"));
            c.setCODIGO_DISTRITO(req.getParameter("codigo_distrito"));

            if ("guardar".equals(action)) {
                service.registrar(c);
            } else if ("actualizar".equals(action)) {
                service.actualizar(c);
            }

            resp.sendRedirect("ClientesController");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Ocurrió un error.");
            req.getRequestDispatcher("listaClientes.jsp").forward(req, resp);
        }
    }
}
