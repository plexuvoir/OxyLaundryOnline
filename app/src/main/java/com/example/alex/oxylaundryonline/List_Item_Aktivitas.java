package com.example.alex.oxylaundryonline;
public class List_Item_Aktivitas {

    private String noOrder, jenis, tglAwal, tglAkhir, next;
    //private int imgId;

    //constructor item yang akan dibuat


    public List_Item_Aktivitas(String noOrder, String jenis, String tglAwal, String tglAkhir, String next) {
        this.noOrder = noOrder;
        this.jenis = jenis;
        this.tglAwal = tglAwal;
        this.tglAkhir = tglAkhir;
        this.next = next;
    }

    public String getNoOrder() {
        return noOrder;
    }

    public String getJenis() {
        return jenis;
    }

    public String getTglAwal() {
        return tglAwal;
    }

    public String getTglAkhir() {
        return tglAkhir;
    }

    public String getNext() {
        return next;
    }
}

