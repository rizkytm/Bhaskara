package com.rizkytm.bhaskara;

public class SoalEssay {

    private int id;
    private int topik_id;
    private String pertanyaan;
    private String jawaban;

    public SoalEssay() {}

    public SoalEssay(int topik_id, String pertanyaan, String jawaban) {
        this.topik_id = topik_id;
        this.pertanyaan = pertanyaan;
        this.jawaban = jawaban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopik_id() {
        return topik_id;
    }

    public void setTopik_id(int topik_id) {
        this.topik_id = topik_id;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
