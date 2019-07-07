package com.rizkytm.bhaskara;

public class Kata {

    private int id;
    private String kataIndo;
    private String kataSunda;
    private int topikID;

    public Kata() {

    }

    public Kata(String kataIndo, String kataSunda, int topikID) {
        this.kataIndo = kataIndo;
        this.kataSunda = kataSunda;
        this.topikID = topikID;
    }

    public String getKataIndo() {
        return kataIndo;
    }

    public void setKataIndo(String kataIndo) {
        this.kataIndo = kataIndo;
    }

    public String getKataSunda() {
        return kataSunda;
    }

    public void setKataSunda(String kataSunda) {
        this.kataSunda = kataSunda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopikID() {
        return topikID;
    }

    public void setTopikID(int topikID) {
        this.topikID = topikID;
    }
}
