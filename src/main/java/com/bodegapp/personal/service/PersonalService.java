package com.bodegapp.personal.service;

import com.bodegapp.personal.dao.PersonalDAO;
import com.bodegapp.personal.dao.VentaVendedorDAO;
import com.bodegapp.personal.model.PersonalModel;
import com.bodegapp.personal.model.VentaVendedorModel;
import java.util.List;

public class PersonalService {
    
    private PersonalDAO personalDAO = new PersonalDAO();
    private VentaVendedorDAO ventaVendedorDAO = new VentaVendedorDAO();
    
    public List<PersonalModel> listarPersonal(int idEmpresa) {
        return personalDAO.listar(idEmpresa);
    }
    
    public PersonalModel obtenerPersonalPorId(int idUser) {
        return personalDAO.obtenerPorId(idUser);
    }
    
    public List<VentaVendedorModel> obtenerVentasVendedor(String codigoVendedor) {
        return ventaVendedorDAO.obtenerVentasPorVendedor(codigoVendedor);
    }
    
    public double obtenerTotalVentasVendedor(String codigoVendedor) {
        return ventaVendedorDAO.obtenerTotalVentasVendedor(codigoVendedor);
    }
}

