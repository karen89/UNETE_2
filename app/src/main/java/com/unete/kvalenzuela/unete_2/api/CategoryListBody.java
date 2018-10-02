package com.unete.kvalenzuela.unete_2.api;

public class CategoryListBody {

    private String Subcategoria_Id_SubCat;

    public CategoryListBody(String subcategoria_Id_SubCat) {
        Subcategoria_Id_SubCat = subcategoria_Id_SubCat;
    }

    public String getSubcategoria_Id_SubCat() {
        return Subcategoria_Id_SubCat;
    }

    public void setSubcategoria_Id_SubCat(String subcategoria_Id_SubCat) {
        Subcategoria_Id_SubCat = subcategoria_Id_SubCat;
    }
}
