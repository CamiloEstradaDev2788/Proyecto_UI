package com.bodegapp.login.model;

public class LoginModel {
    
    private String correo;
    private String contrasena;
    private int ID_EMPRESA;
    private int ID_ROL;


    public LoginModel(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }
    
    

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }

    public int getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(int ID_ROL) {
        this.ID_ROL = ID_ROL;
    }
    
    public LoginModel() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
