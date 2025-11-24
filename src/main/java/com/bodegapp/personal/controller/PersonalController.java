package com.bodegapp.personal.controller;

import com.bodegapp.personal.dao.PersonalDAO;
import com.bodegapp.personal.model.PersonalModel;
import com.bodegapp.personal.model.VentaVendedorModel;
import com.bodegapp.personal.service.PersonalService;
import com.bodegapp.usuarios.model.UsuarioModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/PersonalController")
public class PersonalController extends HttpServlet {

    private PersonalService service = new PersonalService();
    private PersonalDAO dao = new PersonalDAO();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");

    String accion = request.getParameter("accion");
    String id = request.getParameter("id");
    String idVendedor = request.getParameter("idVendedor");

    switch (accion != null ? accion : "") {

        case "verVentas":
            verVentas(request, response, idVendedor);
            return;

        case "agregar":
            cargarReferencias(request);
            request.getRequestDispatcher("agregarPersonal.jsp").forward(request, response);
            return;

        case "editar":
            if (id != null) {
                PersonalModel p = service.obtenerPersonalPorId(Integer.parseInt(id));
                cargarReferencias(request);
                request.setAttribute("personal", p);
                request.getRequestDispatcher("editarPersonal.jsp").forward(request, response);
                return;
            }
            break;

        case "eliminar":
            if (id != null) {
                service.eliminarPersonal(Integer.parseInt(id));
                response.sendRedirect("PersonalController");
                return;
            }
            break;
    }

    // ===========================================
    // DEFAULT LISTAR PERSONAL Y VENDEDORES CON MENOS VENTAS
    // ===========================================
    List<PersonalModel> lista = service.listarPersonal(usuario.getID_EMPRESA());
    request.setAttribute("listaPersonal", lista);

    // NUEVO: traer 3 vendedores con menos ventas
    List<PersonalModel> menosVentas = service.listarMenosVentas(usuario.getID_EMPRESA());
    request.setAttribute("menosVentas", menosVentas);

    request.getRequestDispatcher("personal.jsp").forward(request, response);
}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {
            try {

                HttpSession sesion = request.getSession(false);
                UsuarioModel usuario = (UsuarioModel) sesion.getAttribute("usuario");

                PersonalModel p = new PersonalModel();
                p.setID_EMPRESA(usuario.getID_EMPRESA());
                p.setID_ROL(Integer.parseInt(request.getParameter("id_rol")));
                p.setNOMBRE1(request.getParameter("nombre1"));
                p.setNOMBRE2(request.getParameter("nombre2"));
                p.setAPELLIDO1(request.getParameter("apellido1"));
                p.setAPELLIDO2(request.getParameter("apellido2"));
                p.setCEDULA(request.getParameter("cedula"));
                p.setCORREO(request.getParameter("correo"));
                p.setCONTRASENA_HASH(request.getParameter("contrasena"));
                p.setSALARIO(Double.parseDouble(request.getParameter("salario")));
                p.setTIPO_CONTRATO(request.getParameter("tipo_contrato"));
                p.setESTADO(Boolean.parseBoolean(request.getParameter("estado")));

                // VENDEDOR
                p.setES_VENDEDOR(Boolean.parseBoolean(request.getParameter("es_vendedor")));
                p.setCODIGO_VENDEDOR(request.getParameter("codigo_vendedor"));
                p.setCODIGO_DISTRITO(request.getParameter("codigo_distrito"));
                p.setTIPO_VENDEDOR(request.getParameter("tipo_vendedor"));

                boolean ok = dao.insertar(p);

                if (ok) {
                    response.sendRedirect("PersonalController");
                } else {
                    request.setAttribute("error", "No se pudo insertar personal.");
                    cargarReferencias(request);
                    request.setAttribute("personal", p);
                    request.getRequestDispatcher("agregarPersonal.jsp").forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al insertar personal.");
                cargarReferencias(request);
                request.getRequestDispatcher("agregarPersonal.jsp").forward(request, response);
            }
        }
    }


    // =====================================================================
    // ========================= MÉTODOS PRIVADOS ===========================
    // =====================================================================

    private void verVentas(HttpServletRequest request, HttpServletResponse response, String idVendedor)
            throws ServletException, IOException {

        if (idVendedor == null) {
            response.sendRedirect("PersonalController");
            return;
        }

        try {
            int id = Integer.parseInt(idVendedor);

            PersonalModel vendedor = service.obtenerPersonalPorId(id);

            if (vendedor == null || !vendedor.isES_VENDEDOR()) {
                response.sendRedirect("PersonalController");
                return;
            }

            // === CONSULTA DE VENTAS CALCULADAS ===
            List<VentaVendedorModel> ventas =
                    service.obtenerVentasVendedor(vendedor.getCODIGO_VENDEDOR());

            double totalVentas =
                    service.obtenerTotalVentasVendedor(vendedor.getCODIGO_VENDEDOR());

            request.setAttribute("vendedor", vendedor);
            request.setAttribute("ventas", ventas);
            request.setAttribute("totalVentas", totalVentas);

            request.getRequestDispatcher("ventasVendedor.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PersonalController");
        }
    }


    private void cargarReferencias(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
            int idEmpresa = usuario.getID_EMPRESA();

            request.setAttribute("roles",
                    new com.bodegapp.roles.dao.RolDAO().listar(idEmpresa));

            request.setAttribute("distritos",
                    new com.bodegapp.distrito.dao.DistritoDAO().listarDistritos());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
