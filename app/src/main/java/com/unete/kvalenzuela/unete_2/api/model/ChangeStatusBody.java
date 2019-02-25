package com.unete.kvalenzuela.unete_2.api.model;

public class ChangeStatusBody {

    int Id_AC;
    String Estatus;

    public ChangeStatusBody(int id_AC, String estatus) {
        Id_AC = id_AC;
        Estatus = estatus;
    }

    public int getId_AC() {
        return Id_AC;
    }

    public void setId_AC(int id_AC) {
        Id_AC = id_AC;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String estatus) {
        Estatus = estatus;
    }
}
