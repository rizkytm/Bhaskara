package com.rizkytm.bhaskara;

public class Image {

    private int id;
    private String name;
    private byte[] imageA;
    private byte[] imageB;

    public Image() {}

    public Image(String name, byte[] imageA, byte[] imageB) {
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

    public byte[] getImageA() {
        return imageA;
    }

    public void setImageA(byte[] imageA) {
        this.imageA = imageA;
    }

    public byte[] getImageB() {
        return imageB;
    }

    public void setImageB(byte[] imageB) {
        this.imageB = imageB;
    }
}
