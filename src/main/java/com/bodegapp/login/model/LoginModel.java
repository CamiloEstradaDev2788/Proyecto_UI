package com.bodegapp.login.model;

public class LoginModel {
    
    private String correo;
    private String contrasena;

    public LoginModel(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
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
