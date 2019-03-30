package com.rizkytm.bhaskara;

public class Image {

    public int id;
    public String nama;
    public int imageA;
    public int imageB;
    public int topik_id;

    public Image() {}

    public Image(String nama, int imageA, int imageB, int topik_id) {
        this.nama = nama;
        this.imageA = imageA;
        this.imageB = imageB;
        this.topik_id = topik_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getImageA() {
        return imageA;
    }

    public void setImageA(int imageA) {
        this.imageA = imageA;
    }

    public int getImageB() {
        return imageB;
    }

    public void setImageB(int imageB) {
        this.imageB = imageB;
    }

    public int getTopik_id() {
        return topik_id;
    }

    public void setTopik_id(int topik_id) {
        this.topik_id = topik_id;
    }
}
