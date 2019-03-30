package com.rizkytm.bhaskara;

public class TopikKuis {

    String judul;
    int id;
    int skor;

    public TopikKuis() {

    }

    public TopikKuis(int id, String judul, int skor) {
        this.id = id;
        this.judul = judul;
        this.skor = skor;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }
}
