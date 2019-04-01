package com.rizkytm.bhaskara;

public class TopikKuis {

    String judul;
    int id;
    int skor;
    int difficulty_id;

    public TopikKuis() {

    }

    public TopikKuis(int id, String judul, int skor, int difficulty_id) {
        this.id = id;
        this.judul = judul;
        this.skor = skor;
        this.difficulty_id = difficulty_id;
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

    public int getDifficulty_id() {
        return difficulty_id;
    }

    public void setDifficulty_id(int difficulty_id) {
        this.difficulty_id = difficulty_id;
    }
}
