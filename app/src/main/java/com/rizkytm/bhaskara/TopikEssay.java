package com.rizkytm.bhaskara;

public class TopikEssay {

    private int id;
    private String judul;
    private int skor;

    public TopikEssay() {}

    public TopikEssay(int id, String judul, int skor) {
        this.id = id;
        this.judul = judul;
        this.skor = skor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }
}
