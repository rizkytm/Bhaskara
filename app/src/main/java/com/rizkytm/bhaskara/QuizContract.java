package com.rizkytm.bhaskara;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {}

    public static class CategoriesTable implements BaseColumns {
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PERTANYAAN = "pertanyaan";
        public static final String COLUMN_PIL1 = "pil1";
        public static final String COLUMN_PIL2 = "pil2";
        public static final String COLUMN_PIL3 = "pil3";
        public static final String COLUMN_PIL4 = "pil4";
        public static final String COLUMN_NO_JAWABAN = "no_jawaban";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_CATEGORY_ID = "category_id";
    }

    public static class EssaysTable implements BaseColumns {
        public static final String TABLE_NAME = "essays";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PERTANYAAN = "pertanyaan";
        public static final String COLUMN_JAWABAN = "jawaban";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_CATEGORY_ID = "category_id";
    }

    public static class TopicsTable implements BaseColumns {
        public static final String TABLE_NAME = "teori_bahasa";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
    }

    public static class TopicAksaraTable implements BaseColumns {
        public static final String TABLE_NAME = "teori_aksara";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
    }

    public static class TopikEssayTable implements BaseColumns {
        public static final String TABLE_NAME = "topik_essay";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_SKOR = "skor";
    }

    public static class TopikGameTable implements BaseColumns {
        public static final String TABLE_NAME = "topik_game";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_SKOR = "skor";
    }

    public static class TopikKuisTable implements BaseColumns {
        public static final String TABLE_NAME = "topik_pg";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_SKOR = "skor";
    }

    public static class SoalEssayTable implements BaseColumns {
        public static final String TABLE_NAME = "soal_essay";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TOPIK_ID = "topik_id";
        public static final String COLUMN_PERTANYAAN = "pertanyaan";
        public static final String COLUMN_JAWABAN = "jawaban";
    }

    public static class SoalGameTable implements BaseColumns {
        public static final String TABLE_NAME = "soal_game";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TOPIK_ID = "topik_id";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_IMAGE_A = "image_a";
        public static final String COLUMN_IMAGE_B = "image_b";
    }

    public static class SoalPGTable implements BaseColumns {
        public static final String TABLE_NAME = "soal_pg";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PERTANYAAN = "pertanyaan";
        public static final String COLUMN_PIL1 = "pil1";
        public static final String COLUMN_PIL2 = "pil2";
        public static final String COLUMN_PIL3 = "pil3";
        public static final String COLUMN_PIL4 = "pil4";
        public static final String COLUMN_NO_JAWABAN = "no_jawaban";
        public static final String COLUMN_DIFFICULTY = "difficulty_id";
        public static final String COLUMN_CATEGORY_ID = "topik_id";
    }
}
