package com.bodegapp.personal.controller;

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
        String idVendedor = request.getParameter("idVendedor");
        
        if (accion != null && accion.equals("verVentas") && idVendedor != null) {
            // Mostrar ventas de un vendedor específico
            try {
                PersonalModel vendedor = service.obtenerPersonalPorId(Integer.parseInt(idVendedor));
                
                if (vendedor != null && vendedor.isES_VENDEDOR() && vendedor.getCODIGO_VENDEDOR() != null) {
                    List<VentaVendedorModel> ventas = service.obtenerVentasVendedor(vendedor.getCODIGO_VENDEDOR());
                    double totalVentas = service.obtenerTotalVentasVendedor(vendedor.getCODIGO_VENDEDOR());
                    
                    System.out.println("Vendedor: " + vendedor.getNOMBRE_COMPLETO());
                    System.out.println("Código Vendedor: " + vendedor.getCODIGO_VENDEDOR());
                    System.out.println("Total de ventas encontradas: " + ventas.size());
                    
                    request.setAttribute("vendedor", vendedor);
                    request.setAttribute("ventas", ventas);
                    request.setAttribute("totalVentas", totalVentas);
                    request.getRequestDispatcher("ventasVendedor.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("error", "El usuario seleccionado no es un vendedor o no tiene código de vendedor.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID de vendedor inválido.");
            }
        }
        
        // Listar todo el personal
        List<PersonalModel> listaPersonal = service.listarPersonal(usuario.getID_EMPRESA());
        request.setAttribute("listaPersonal", listaPersonal);
        request.getRequestDispatcher("personal.jsp").forward(request, response);
    }
}

