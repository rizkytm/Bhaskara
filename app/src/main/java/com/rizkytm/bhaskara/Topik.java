package com.rizkytm.bhaskara;

public class Topik {

    String judul;
    String isi;
    int id;

    public Topik() {

    }

    public Topik(String judul, String isi) {
        this.judul = judul;
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}