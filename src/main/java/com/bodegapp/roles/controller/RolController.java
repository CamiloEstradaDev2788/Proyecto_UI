package com.bodegapp.roles.controller;

import com.bodegapp.roles.model.RolModel;
import com.bodegapp.roles.service.RolService;
import com.bodegapp.usuarios.model.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RolController", urlPatterns = {"/rolController"})
public class RolController extends HttpServlet {

    private final RolService service = new RolService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         HttpSession session = request.getSession(false);
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        // Obtener id_empresa
        int idEmpresa = usuario.getID_EMPRESA();
        request.setAttribute("roles", service.obtenerRoles(idEmpresa));
        request.getRequestDispatcher("roles/listar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RolModel rol = new RolModel();
    
        rol.setID_EMPRESA(Integer.parseInt(request.getParameter("id_empresa")));
        rol.setNOMBRE_ROL(request.getParameter("nombre_rol"));
        rol.setDESCRIPCION(request.getParameter("descripcion"));
        
        System.out.println("ID de la empresa: " + rol.getID_EMPRESA());
        System.out.println("Nombre del rol: " + rol.getNOMBRE_ROL());
        System.out.println("Descricion del rol: " + rol.getDESCRIPCION());

        if (service.registrarRol(rol)) {
            response.sendRedirect("rolController");
        } else {
            request.setAttribute("error", "No se pudo registrar el rol.");
            request.getRequestDispatcher("roles/registrar.jsp").forward(request, response);
        }
    }
}

