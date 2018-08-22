package com.unete.kvalenzuela.unete_2.api;

public class LoginBody {

   // @SerializedName("Id_AC")
    private String Rep_Correo;
    private String Contrasena;

    public LoginBody(String Rep_Correo, String Contrasena) {
        this.Rep_Correo = Rep_Correo;
        this.Contrasena = Contrasena;
    }

    public String getRep_Correo() {
        return Rep_Correo;
    }

    public void setRep_Correo(String rep_Correo) {
        this.Rep_Correo = rep_Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        this.Contrasena = contrasena;
    }

}
