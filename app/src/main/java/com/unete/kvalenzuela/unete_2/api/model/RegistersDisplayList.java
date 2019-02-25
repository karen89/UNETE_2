package com.unete.kvalenzuela.unete_2.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * POJO para las citas mÃ©dicas
 */

public class RegistersDisplayList {

    // estados:
    public static List<String> STATES_VALUES =
            Arrays.asList("Todas", "Activo", "Inactivo", "En Espera");

    @SerializedName("Id_AC")
    private int Id_AC;
    @SerializedName("Razon_Social")
    private String Razon_Social;
    @SerializedName("Rep_Nombre")
    private String Rep_Nombre;
    @SerializedName("Rep_Cel")
    private String Rep_Cel;
    @SerializedName("Rep_Correo")
    private String Rep_Correo;
    @SerializedName("Estatus")
    private String Estatus;
    @SerializedName("Subcategoria_Id_SubCat")
    private String Subcategoria_Id_SubCat;

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

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String estatus) {
        Estatus = estatus;
    }

    public String getSubcategoria_Id_SubCat() {
        return Subcategoria_Id_SubCat;
    }

    public void setSubcategoria_Id_SubCat(String subcategoria_Id_SubCat) {
        Subcategoria_Id_SubCat = subcategoria_Id_SubCat;
    }
}
