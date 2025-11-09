package com.bodegapp.empresa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bodegapp.empresa.model.EmpresaModel;
import com.bodegapp.empresa.service.EmpresaService;

@WebServlet(name = "EmpresaController", urlPatterns = {"/empresaController"})
public class EmpresaController extends HttpServlet {
    
    private final EmpresaService service = new EmpresaService();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmpresaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmpresaController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("empresas", service.obtenerEmpresas());
        request.getRequestDispatcher("Empresas/listar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EmpresaModel e = new EmpresaModel();
        
        e.setNOMBRE(request.getParameter("nombre"));
        e.setNIT(request.getParameter("nit"));
        e.setDIRECCION(request.getParameter("direccion"));
        e.setTELEFONO(request.getParameter("telefono"));
        e.setESTADO(false);
        e.setLOGO(request.getParameter("logo"));
        
        System.out.println("NOMBRE: " + e.getNOMBRE());
    System.out.println("NIT: " + e.getNIT());
    System.out.println("DIRECCION: " + e.getDIRECCION());
    System.out.println("TELEFONO: " + e.getTELEFONO());
    System.out.println("ESTADO: " + e.isESTADO());
    System.out.println("LOGO: " + e.getLOGO());
        
        if(service.registrarEmpresa(e)){
            response.sendRedirect("empresaController");
        }else{
            request.setAttribute("error", "No se pudo registrar la empresa.");
            request.getRequestDispatcher("Empresas/registrar.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
