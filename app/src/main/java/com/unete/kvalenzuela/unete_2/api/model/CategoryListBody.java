package com.unete.kvalenzuela.unete_2.api.model;

public class CategoryListBody {

    private String Categoria_Id_Cat;

    public CategoryListBody(String categoria_Id_Cat) {
        Categoria_Id_Cat = categoria_Id_Cat;
    }

    public String getCategoria_Id_Cat() {
        return Categoria_Id_Cat;
    }

    public void setCategoria_Id_Cat(String categoria_Id_Cat) {
        Categoria_Id_Cat = categoria_Id_Cat;
    }
}
