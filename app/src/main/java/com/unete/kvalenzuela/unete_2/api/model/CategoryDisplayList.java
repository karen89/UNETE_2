package com.unete.kvalenzuela.unete_2.api.model;

import java.util.List;

public class CategoryDisplayList {
    private int Id_AC;
    private String Razon_Social;
    private String Descripcion;

    private String Calle;
    private String Numero;
    private String Cruzam_1;
    private String Cruzam_2;
    private String Colonia;
    private int CP;
    private String Municipio;
    private String Estado;

    private String Rep_Nombre;
    private String Rep_Cel;
    private String Rep_Correo;

    private String Logotipo;
    private int CuentaBancaria;
    private int Subcategoria_Id_SubCat;

    public CategoryDisplayList(int id_AC, String razon_Social, String descripcion, String calle, String numero, String cruzam_1, String cruzam_2, String colonia, int CP, String municipio, String estado, String rep_Nombre, String rep_Cel, String rep_Correo, String logotipo, int cuentaBancaria, int subcategoria_Id_SubCat) {
        Id_AC = id_AC;
        Razon_Social = razon_Social;
        Descripcion = descripcion;
        Calle = calle;
        Numero = numero;
        Cruzam_1 = cruzam_1;
        Cruzam_2 = cruzam_2;
        Colonia = colonia;
        this.CP = CP;
        Municipio = municipio;
        Estado = estado;
        Rep_Nombre = rep_Nombre;
        Rep_Cel = rep_Cel;
        Rep_Correo = rep_Correo;
        Logotipo = logotipo;
        CuentaBancaria = cuentaBancaria;
        Subcategoria_Id_SubCat = subcategoria_Id_SubCat;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getCruzam_1() {
        return Cruzam_1;
    }

    public void setCruzam_1(String cruzam_1) {
        Cruzam_1 = cruzam_1;
    }

    public String getCruzam_2() {
        return Cruzam_2;
    }

    public void setCruzam_2(String cruzam_2) {
        Cruzam_2 = cruzam_2;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
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

    public String getLogotipo() {
        return Logotipo;
    }

    public void setLogotipo(String logotipo) {
        Logotipo = logotipo;
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


}
