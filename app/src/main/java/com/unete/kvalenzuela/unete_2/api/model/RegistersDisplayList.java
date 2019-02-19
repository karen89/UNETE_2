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
            Arrays.asList("Todas", "ACTIVO", "INACTIVO", "EN ESPERA");

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

    public RegistersDisplayList(int id, String razonsocial, String representante,
                                  String celular, String correo, String estatus) {
        Id_AC = id;
        Razon_Social = razonsocial;
        Rep_Nombre = representante;
        Rep_Cel = celular;
        Rep_Correo = correo;
        Estatus = estatus;
    }

    public int getId() {
        return Id_AC;
    }

    public String getRazon_Social() {
        return Razon_Social;
    }

    public String getRep_Nombre() {
        return Rep_Nombre;
    }

    public String getRep_Cel() {
        return Rep_Cel;
    }

    public String getRep_Correo() {
        return Rep_Correo;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setId(int mId) {
        this.Id_AC = mId;
    }

    public void setRazon_Social(String mRazonSocial) {
        this.Razon_Social = mRazonSocial;
    }

    public void setRep_Nombre(String mRepNombre) {
        this.Rep_Nombre = mRepNombre;
    }

    public void setRep_Cel(String mRepCel) {
        this.Rep_Cel = mRepCel;
    }

    public void setRep_Correo(String mRepCorreo) {
        this.Rep_Correo = mRepCorreo;
    }

    public void setEstatus(String mEstatus) {
        this.Estatus = mEstatus;
    }


}
