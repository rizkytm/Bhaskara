package com.rizkytm.bhaskara;

public class Word {
    public String key = "";
    public String value = "";

    public Word() {

    }

    public Word(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
