package com.rizkytm.bhaskara;

public class Image {

    public int id;
    public String name;
    public int imageA;
    public int imageB;

    public Image() {}

    public Image(String name, int imageA, int imageB) {
        this.name = name;
        this.imageA = imageA;
        this.imageB = imageB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
