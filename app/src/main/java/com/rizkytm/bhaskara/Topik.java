package com.rizkytm.bhaskara;

public class Topik {

    String judul;
    String isi;
    int id;
    int image;

    public Topik() {

    }

    public Topik(String judul, String isi, int image) {
        this.judul = judul;
        this.isi = isi;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
