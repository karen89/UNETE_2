package com.unete.kvalenzuela.unete_2.api.model;

public class AssociationBody {

    private int Id_AC;
    private String Razon_Social;

    public AssociationBody(int id_AC, String razon_Social) {
        Id_AC = id_AC;
        Razon_Social = razon_Social;
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
}
