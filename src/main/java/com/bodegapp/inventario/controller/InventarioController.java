package com.bodegapp.inventario.controller;

import java.io.IOException;
import java.util.List;

import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.inventario.service.InventarioService;
import com.bodegapp.usuarios.model.UsuarioModel;
import com.bodegapp.proveedor.dao.ProveedorDAO;
import com.bodegapp.proveedor.model.ProveedorModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/InventarioController")
public class InventarioController extends HttpServlet {

    private InventarioService service = new InventarioService();
    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {

        HttpSession session = rq.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        if (usuario == null) {
            rs.sendRedirect("login.jsp");
            return;
        }

        int idEmpresa = usuario.getID_EMPRESA();
        String accion = rq.getParameter("accion");
        String buscar = rq.getParameter("buscar");

        // ========= FORMULARIO NUEVO =========
        if ("nuevo".equals(accion)) {
            cargarFormularioNuevoProducto(rq, rs, idEmpresa);
            return;
        }

        // ========= LISTAR =========
        List<InventarioModel> lista =
            (buscar != null && !buscar.trim().isEmpty())
                ? service.buscar(buscar.trim())
                : service.listar(idEmpresa);

        rq.setAttribute("listaInventario", lista);
        rq.setAttribute("criterioBusqueda", buscar != null ? buscar : "");

        rq.getRequestDispatcher("inventario.jsp").forward(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {

        HttpSession session = rq.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        if (usuario == null) {
            rs.sendRedirect("login.jsp");
            return;
        }

        int idEmpresa = usuario.getID_EMPRESA();

        try {
            // ========= PRODUCTO =========
            InventarioModel producto = new InventarioModel();
            producto.setCODIGO_PRODUCTO(rq.getParameter("codigo_producto"));
            producto.setDESCRIPCION_PRODUCTO(rq.getParameter("descripcion_producto"));
            producto.setPRECIO_PRODUCTO(Double.parseDouble(rq.getParameter("precio_producto")));
            producto.setSACO_PRODUCTO(Integer.parseInt(rq.getParameter("saco_producto")));
            producto.setMINIMO_STOCK(Integer.parseInt(rq.getParameter("minimo_stock")));
            producto.setUNIDAD_PRODUCTO(rq.getParameter("unidad_producto"));
            producto.setLINEA_PRODUCTO(rq.getParameter("linea_producto"));
            producto.setIMPUESTO_PRODUCTO(rq.getParameter("impresion_producto"));

            // ========= PROVEEDOR =========
            String proveedorParam = rq.getParameter("codigoProveedor");
            if (proveedorParam == null || proveedorParam.isEmpty()) {
                rq.setAttribute("error", "Debe seleccionar un proveedor.");
                recargarFormularioConError(rq, rs, idEmpresa);
                return;
            }

            ProveedorModel proveedor = new ProveedorModel();
            proveedor.setCODIGO_PROVEEDOR(proveedorParam);

            // ========= INSERTAR =========
            boolean registrado = service.registrar(producto, proveedor);

            if (registrado) {
                rs.sendRedirect("InventarioController");
            } else {
                rq.setAttribute("error", "No se pudo registrar el producto.");
                recargarFormularioConError(rq, rs, idEmpresa);
            }

        } catch (Exception e) {
            System.out.println("ERROR FORM: " + e.getMessage());
            rq.setAttribute("error", "Datos incorrectos.");
            recargarFormularioConError(rq, rs, idEmpresa);
        }
    }

    // ======================================================
    private void cargarFormularioNuevoProducto(HttpServletRequest rq, HttpServletResponse rs, int idEmpresa)
            throws ServletException, IOException {

        List<ProveedorModel> proveedores = proveedorDAO.listarProveedores(idEmpresa);

        rq.setAttribute("listaProveedores", proveedores);
        rq.getRequestDispatcher("agregarProducto.jsp").forward(rq, rs);
    }

    private void recargarFormularioConError(HttpServletRequest rq, HttpServletResponse rs, int idEmpresa)
            throws ServletException, IOException {

        List<ProveedorModel> proveedores = proveedorDAO.listarProveedores(idEmpresa);

        rq.setAttribute("listaProveedores", proveedores);
        rq.getRequestDispatcher("agregarProducto.jsp").forward(rq, rs);
    }
}
