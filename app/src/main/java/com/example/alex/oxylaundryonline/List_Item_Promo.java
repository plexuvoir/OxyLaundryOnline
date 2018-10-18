package com.example.alex.oxylaundryonline;
public class List_Item_Promo {

    private String kdPromo;
    private int imgSrc;
    //private int imgId;

    //constructor item yang akan dibuat
    public List_Item_Promo(String kdPromo, int imgSrc) {
        this.kdPromo = kdPromo;
        this.imgSrc = imgSrc;
    }

    public String getKdPromo() {
        return kdPromo;
    }

    public int getImgSrc() {
        return imgSrc;
    }
}

