package com.rizkytm.bhaskara;

import android.os.Parcel;
import android.os.Parcelable;

public class SoalPG implements Parcelable {
    public static final String DIFFICULTY_EASY = "mudah";
    public static final String DIFFICULTY_MEDIUM = "sedang";
    public static final String DIFFICULTY_HARD = "sulit";

    private int id;
    private String pertanyaan;
    private String pil1;
    private String pil2;
    private String pil3;
    private String pil4;
    private int noJawaban;
    private int difficulty_id;
    private int topik_id;

    public SoalPG() {

    }

    public SoalPG(String pertanyaan, String pil1, String pil2,
                  String pil3, String pil4,
                  int noJawaban, int difficulty_id, int topik_id) {
        this.pertanyaan = pertanyaan;
        this.pil1 = pil1;
        this.pil2 = pil2;
        this.pil3 = pil3;
        this.pil4 = pil4;
        this.noJawaban = noJawaban;
        this.difficulty_id = difficulty_id;
        this.topik_id = topik_id;
    }

    protected SoalPG(Parcel in) {
        id = in.readInt();
        pertanyaan = in.readString();
        pil1 = in.readString();
        pil2 = in.readString();
        pil3 = in.readString();
        pil4 = in.readString();
        noJawaban = in.readInt();
        difficulty_id = in.readInt();
        topik_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(pertanyaan);
        dest.writeString(pil1);
        dest.writeString(pil2);
        dest.writeString(pil3);
        dest.writeString(pil4);
        dest.writeInt(noJawaban);
        dest.writeInt(difficulty_id);
        dest.writeInt(topik_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SoalPG> CREATOR = new Creator<SoalPG>() {
        @Override
        public SoalPG createFromParcel(Parcel in) {
            return new SoalPG(in);
        }

        @Override
        public SoalPG[] newArray(int size) {
            return new SoalPG[size];
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

    public String getPil1() {
        return pil1;
    }

    public void setPil1(String pil1) {
        this.pil1 = pil1;
    }

    public String getPil2() {
        return pil2;
    }

    public void setPil2(String pil2) {
        this.pil2 = pil2;
    }

    public String getPil3() {
        return pil3;
    }

    public void setPil3(String pil3) {
        this.pil3 = pil3;
    }

    public String getPil4() {
        return pil4;
    }

    public void setPil4(String pil4) {
        this.pil4 = pil4;
    }

    public int getNoJawaban() {
        return noJawaban;
    }

    public void setNoJawaban(int noJawaban) {
        this.noJawaban = noJawaban;
    }

    public int getDifficulty_id() {
        return difficulty_id;
    }

    public void setDifficulty_id(int difficulty_id) {
        this.difficulty_id = difficulty_id;
    }

    public int getTopik_id() {
        return topik_id;
    }

    public void setTopik_id(int topik_id) {
        this.topik_id = topik_id;
    }

    public static String[] getAllDifficultyLevels() {
        return new String[] {
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }
}
