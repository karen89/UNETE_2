package com.unete.kvalenzuela.unete_2.api;

public class Asociacion {

    private int Id_AC;
    private String Razon_Social;
    private String Rep_Nombre;
    private String Rep_Cel;
    private String Rep_Correo;
    private String Usuario;
    private String Contrasena;
    private String Slogan;
    private String Descripcion;
    private String Logotipo;
    private String Estado;
    private int CuentaBancaria;
    private int Subcategoria_Id_SubCat;
    private String Token;

    public Asociacion(int id_AC, String razon_Social, String rep_Nombre, String rep_Cel, String rep_Correo, String usuario, String contrasena, String slogan, String descripcion, String logotipo, String estado, int cuentaBancaria, int subcategoria_Id_SubCat, String token) {
        Id_AC = id_AC;
        Razon_Social = razon_Social;
        Rep_Nombre = rep_Nombre;
        Rep_Cel = rep_Cel;
        Rep_Correo = rep_Correo;
        Usuario = usuario;
        Contrasena = contrasena;
        Slogan = slogan;
        Descripcion = descripcion;
        Logotipo = logotipo;
        Estado = estado;
        CuentaBancaria = cuentaBancaria;
        Subcategoria_Id_SubCat = subcategoria_Id_SubCat;
        Token = token;
    }

    public int getId_AC() {
        return Id_AC;
    }

    public void setId_AC(int id_AC) {
        Id_AC = id_AC;
    }

    public String getRazon_Social() {
        return Razon_Social;
    }

    public void setRazon_Social(String razon_Social) {
        Razon_Social = razon_Social;
    }

    public String getRep_Nombre() {
        return Rep_Nombre;
    }

    public void setRep_Nombre(String rep_Nombre) {
        Rep_Nombre = rep_Nombre;
    }

    public String getRep_Cel() {
        return Rep_Cel;
    }

    public void setRep_Cel(String rep_Cel) {
        Rep_Cel = rep_Cel;
    }

    public String getRep_Correo() {
        return Rep_Correo;
    }

    public void setRep_Correo(String rep_Correo) {
        Rep_Correo = rep_Correo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getSlogan() {
        return Slogan;
    }

    public void setSlogan(String slogan) {
        Slogan = slogan;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getLogotipo() {
        return Logotipo;
    }

    public void setLogotipo(String logotipo) {
        Logotipo = logotipo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public int getCuentaBancaria() {
        return CuentaBancaria;
    }

    public void setCuentaBancaria(int cuentaBancaria) {
        CuentaBancaria = cuentaBancaria;
    }

    public int getSubcategoria_Id_SubCat() {
        return Subcategoria_Id_SubCat;
    }

    public void setSubcategoria_Id_SubCat(int subcategoria_Id_SubCat) {
        Subcategoria_Id_SubCat = subcategoria_Id_SubCat;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
