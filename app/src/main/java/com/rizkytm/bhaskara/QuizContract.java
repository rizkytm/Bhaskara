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
}
