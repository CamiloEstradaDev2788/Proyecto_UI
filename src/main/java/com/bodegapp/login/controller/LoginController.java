package com.bodegapp.login.controller;

import java.io.IOException;

import com.bodegapp.login.model.LoginModel;
import com.bodegapp.login.service.LoginService;
import com.bodegapp.usuarios.model.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena_hash");

        LoginModel loginData = new LoginModel(correo, contrasena);

        UsuarioModel usuario = loginService.autenticar(loginData);

        if (usuario != null) {
            // Crear sesión
            HttpSession sesion = request.getSession();
            
            sesion.setAttribute("usuario", usuario);
            sesion.setAttribute("idEmpresa", usuario.getID_EMPRESA());
            sesion.setAttribute("idRol", usuario.getID_ROL());
            

            // Según el rol del usuario puedes redirigir al dashboard correcto
            response.sendRedirect("dashboard");

        } else {
            // Login fallido
            request.setAttribute("error", "Correo o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
