package com.rizkytm.bhaskara;

import android.os.Parcel;
import android.os.Parcelable;

public class Pertanyaan implements Parcelable {
    public static final String DIFFICULTY_EASY = "Mudah";
    public static final String DIFFICULTY_MEDIUM = "Sedang";
    public static final String DIFFICULTY_HARD = "Sulit";

    private int id;
    private String pertanyaan;
    private String jawaban;
//    private String jawabanBenar;
    private String kesulitan;
    private int categoryID;

    public Pertanyaan() {

    }

    public Pertanyaan(String pertanyaan, String jawaban, String kesulitan, int categoryID) {
        this.id = id;
        this.pertanyaan = pertanyaan;
        this.jawaban = jawaban;
//        this.jawabanBenar = jawabanBenar;
        this.kesulitan = kesulitan;
        this.categoryID = categoryID;
    }

    protected Pertanyaan(Parcel in) {
        id = in.readInt();
        pertanyaan = in.readString();
        jawaban = in.readString();
//        jawabanBenar = in.readString();
        kesulitan = in.readString();
        categoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(pertanyaan);
        dest.writeString(jawaban);
//        dest.writeString(jawabanBenar);
        dest.writeString(kesulitan);
        dest.writeInt(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pertanyaan> CREATOR = new Creator<Pertanyaan>() {
        @Override
        public Pertanyaan createFromParcel(Parcel in) {
            return new Pertanyaan(in);
        }

        @Override
        public Pertanyaan[] newArray(int size) {
            return new Pertanyaan[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

//    public String getJawabanBenar() {
//        return jawabanBenar;
//    }
//
//    public void setJawabanBenar(String jawabanBenar) {
//        this.jawabanBenar = jawabanBenar;
//    }

    public String getKesulitan() {
        return kesulitan;
    }

    public void setKesulitan(String kesulitan) {
        this.kesulitan = kesulitan;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public static String[] getAllDifficultyLevels() {
        return new String[] {
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }
}
