package com.bodegapp.usuarios.controller;

import com.bodegapp.usuarios.model.UsuarioModel;
import com.bodegapp.usuarios.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UsuarioController", urlPatterns = {"/usuarioController"})
public class UsuarioController extends HttpServlet {

    private final UsuarioService service = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Cargar lista de usuarios y enviarla al JSP
        request.setAttribute("usuarios", service.obtenerUsuarios());
        request.getRequestDispatcher("usuarios/listar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioModel u = new UsuarioModel();

        try {
            // Captura de datos desde el formulario
            u.setID_EMPRESA(Integer.parseInt(request.getParameter("id_empresa")));
            u.setID_ROL(Integer.parseInt(request.getParameter("id_rol")));
            u.setNOMBRE1(request.getParameter("nombre1"));
            u.setNOMBRE2(request.getParameter("nombre2"));
            u.setAPELLIDO1(request.getParameter("apellido1"));
            u.setAPELLIDO2(request.getParameter("apellido2"));
            u.setCEDULA(request.getParameter("cedula"));
            u.setCORREO(request.getParameter("correo"));
            u.setCONTRASENA_HASH(request.getParameter("contrasena_hash"));

            // Convertir fecha String → java.util.Date → java.sql.Date
            String fechaIngresoStr = request.getParameter("fecha_ingreso");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaUtil = sdf.parse(fechaIngresoStr);
            u.setFECHA_INGRESO(new java.sql.Date(fechaUtil.getTime()));

            // Campos numéricos y booleanos
            u.setSALARIO(Double.parseDouble(request.getParameter("salario")));
            u.setTIPO_CONTRATO(request.getParameter("tipo_contrato"));
            u.setESTADO(Boolean.parseBoolean(request.getParameter("estado")));

            // Registrar en la BD
            if (service.registrarUsuario(u)) {
                response.sendRedirect("usuarioController"); // Redirige a la lista
            } else {
                request.setAttribute("error", "No se pudo registrar el usuario.");
                request.getRequestDispatcher("usuarios/registrar.jsp").forward(request, response);
            }

        } catch (ParseException | NumberFormatException e) {
            System.out.println("⚠️ Error al procesar datos del formulario: " + e.getMessage());
            request.setAttribute("error", "Error en los datos ingresados. Verifique los campos.");
            request.getRequestDispatcher("usuarios/registrar.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestión de usuarios.";
    }
}
