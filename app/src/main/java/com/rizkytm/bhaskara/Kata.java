package com.rizkytm.bhaskara;

public class Kata {

    private String kataIndo;
    private String kataSunda;

    public Kata() {

    }

    public Kata(String kataIndo, String kataSunda) {
        this.kataIndo = kataIndo;
        this.kataSunda = kataSunda;
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
}
