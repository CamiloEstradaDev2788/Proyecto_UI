package com.bodegapp.login.controller;

import java.io.IOException;

import com.bodegapp.login.model.LoginModel;
import com.bodegapp.login.service.LoginService;
import com.bodegapp.usuarios.model.UsuarioModel;
import com.bodegapp.usuarios.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginService();
    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena_hash");

        LoginModel loginData = new LoginModel(correo, contrasena);

        UsuarioModel usuario = loginService.autenticar(loginData);

        if (usuario != null) {

            // Crear sesión
            HttpSession session = request.getSession();

            session.setAttribute("usuario", usuario);
            session.setAttribute("idEmpresa", usuario.getID_EMPRESA());
            session.setAttribute("idRol", usuario.getID_ROL());

            // -------------------------------
            // SI EL USUARIO ES VENDEDOR
            // -------------------------------
            if ("VENDEDOR".equalsIgnoreCase(usuario.getCARGO())) {

                String codigoVendedor = usuarioService.obtenerCodigoVendedor(usuario.getID_USER());

                session.setAttribute("codigoVendedor", codigoVendedor);

                // Enviar al dashboard de vendedor
                response.sendRedirect("dashboardVendedor");
                return;
            }

            // ------------------------------
            // OTROS ROLES → dashboard general
            // ------------------------------
            response.sendRedirect("dashboard");
            return;

        } else {
            // Error de login
            request.setAttribute("error", "Correo o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
