package com.unete.kvalenzuela.unete_2.api.model;

public class SignupBody {

   // @SerializedName("Id_AC")
    private String Razon_Social;
    private String Rep_Nombre;
    private String Rep_Cel;
    private String Rep_Correo;
    private String Contrasena;

    public SignupBody(String razon_Social, String rep_Nombre, String rep_Cel, String rep_Correo, String contrasena) {
        this.Razon_Social = razon_Social;
        this.Rep_Nombre = rep_Nombre;
        this.Rep_Cel = rep_Cel;
        this.Rep_Correo = rep_Correo;
        this.Contrasena = contrasena;
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

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
}
