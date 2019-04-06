//package com.rizkytm.bhaskara;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.rizkytm.bhaskara.QuizContract.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuizDbHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "BhaskaraQuiz.db";
//    private static final int DATABASE_VERSION = 1;
//
//    private static QuizDbHelper instance;
//
//    private SQLiteDatabase db;
//
//    public QuizDbHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    public static synchronized QuizDbHelper getInstance(Context context) {
//        if (instance == null) {
//            instance = new QuizDbHelper(context.getApplicationContext());
//        }
//        return instance;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        this.db = db;
//
//        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
//                CategoriesTable.TABLE_NAME + "( " +
//                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                CategoriesTable.COLUMN_NAME + " TEXT " +
//                ")";
//
//        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
//                QuestionsTable.TABLE_NAME + " ( " +
//                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                QuestionsTable.COLUMN_PERTANYAAN + " TEXT, " +
//                QuestionsTable.COLUMN_PIL1 + " TEXT," +
//                QuestionsTable.COLUMN_PIL2 + " TEXT," +
//                QuestionsTable.COLUMN_PIL3 + " TEXT," +
//                QuestionsTable.COLUMN_PIL4 + " TEXT," +
//                QuestionsTable.COLUMN_NO_JAWABAN + " INTEGER, " +
//                QuestionsTable.COLUMN_DIFFICULTY + " TEXT," +
//                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
//                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
//                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
//                ")";
//
//        final String SQL_CREATE_ESSAYS_TABLE = "CREATE TABLE " +
//                EssaysTable.TABLE_NAME + " ( " +
//                EssaysTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                EssaysTable.COLUMN_PERTANYAAN + " TEXT, " +
//                EssaysTable.COLUMN_JAWABAN + " TEXT," +
//                EssaysTable.COLUMN_DIFFICULTY + " TEXT," +
//                EssaysTable.COLUMN_CATEGORY_ID + " INTEGER, " +
//                "FOREIGN KEY(" + EssaysTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
//                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
//                ")";
//
//        final String SQL_CREATE_TOPICS_TABLE = "CREATE TABLE " +
//                TopicsTable.TABLE_NAME + "( " +
//                TopicsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                TopicsTable.COLUMN_TITLE + " TEXT, " +
//                TopicsTable.COLUMN_CONTENT + " TEXT " +
//                ")";
//
//        final String SQL_CREATE_TOPICAKSARA_TABLE = "CREATE TABLE " +
//                TopicAksaraTable.TABLE_NAME + "( " +
//                TopicAksaraTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                TopicAksaraTable.COLUMN_TITLE + " TEXT, " +
//                TopicAksaraTable.COLUMN_CONTENT + " TEXT " +
//                ")";
//
//        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
//        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
//        db.execSQL(SQL_CREATE_TOPICS_TABLE);
//        db.execSQL(SQL_CREATE_TOPICAKSARA_TABLE);
//        db.execSQL(SQL_CREATE_ESSAYS_TABLE);
//        fillCategoriesTable();
//        fillQuestionsTable();
//        fillTopicsTable();
//        fillTopicAksaraTable();
//        fillEssaysTable();
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
//        onCreate(db);
//    }
//
//    @Override
//    public void onConfigure(SQLiteDatabase db) {
//        super.onConfigure(db);
//        db.setForeignKeyConstraintsEnabled(true);
//    }
//
//    private void fillCategoriesTable() {
//        Category c1 = new Category("Bahasa Sunda");
//        addCategory(c1);
//        Category c2 = new Category("Aksara Sunda");
//        addCategory(c2);
//        Category c3 = new Category("Terjemahan");
//        addCategory(c3);
//    }
//
//    private void fillEssaysTable() {
//        Pertanyaan p1 = new Pertanyaan("Apa bahasa sundanya makan?", "neda", Pertanyaan.DIFFICULTY_EASY, 1);
//        addEssay(p1);
//        Pertanyaan p2 = new Pertanyaan("Apa bahasa sundanya pergi?", "indit", Pertanyaan.DIFFICULTY_EASY, 1);
//        addEssay(p2);
//        Pertanyaan p3 = new Pertanyaan("Apa bahasa sundanya tidur?", "sare", Pertanyaan.DIFFICULTY_EASY, 1);
//        addEssay(p3);
//    }
//
//    private void addEssay(Pertanyaan pertanyaan) {
//        ContentValues cv = new ContentValues();
//        cv.put(EssaysTable.COLUMN_PERTANYAAN, pertanyaan.getPertanyaan());
//        cv.put(EssaysTable.COLUMN_JAWABAN, pertanyaan.getJawaban());
//        cv.put(EssaysTable.COLUMN_DIFFICULTY, pertanyaan.getKesulitan());
//        cv.put(EssaysTable.COLUMN_CATEGORY_ID, pertanyaan.getCategoryID());
//        db.insert(EssaysTable.TABLE_NAME, null, cv);
//    }
//
//    private void addCategory(Category category) {
//        ContentValues cv = new ContentValues();
//        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
//        db.insert(CategoriesTable.TABLE_NAME, null, cv);
//    }
//
//    private void fillTopicAksaraTable() {
//        TopikAksara t1 = new TopikAksara("Sejarah Aksara Sunda",
//                "Sebagai salah satu kebudayaan yang telah berusia cukup lama, " +
//                        "secara historis lebih dari 16 abad yang lalu, kebudayaan Sunda " +
//                        "memiliki kekayaan peninggalan kebudayaan berupa benda-benda " +
//                        "bertulis, seperti prasasti, piagam, serta naskah kuno yang cukup " +
//                        "banyak. Hal ini menunjukkan adanya kecakapan tradisi tulis-menulis " +
//                        "di kalangan masyarakat Sunda. Kenyataan tersebut sekaligus mem- " +
//                        "buktikan adanya kesadaran yang tinggi dari para pendahulu " +
//                        "masyarakat Sunda mengenai pentingnya penyampaian informasi " +
//                        "hasil ketajaman wawasan, pikiran, dan perasaan mereka berupa " +
//                        "gagasan atau ide-ide yang mereka rekam melalui sarana bahasa dan " +
//                        "aksara pada setiap kurun waktu yang dilaluinya");
//        addTopicAksara(t1);
//        TopikAksara t2 = new TopikAksara("Tipologi Aksara Sunda",
//                "Aksara Sunda Kuno memiliki tipe dasar aksara Pallawa Lanjut. " +
//                        "Aksara tersebut memiliki kemiripan dengan model aksara Tibet dan " +
//                        "Punjab (band. Holle, 1877), dengan beberapa ciri tipologi dari penga- " +
//                        "ruh model aksara prasasti-prasasti zaman Tarumanagara, sebelum " +
//                        "mencapai taraf modifikasi bentuk khasnya. Hal ini nampak sebagai- " +
//                        "mana yang digunakan dalam prasasti-prasasti dan naskah-naskah " +
//                        "Sunda Kuno berbahan lontar dan bambu abad ke-14 hingga abad ke- " +
//                        "18 Masehi.");
//        addTopicAksara(t2);
//    }
//
//    private void addTopicAksara(TopikAksara topikAksara) {
//        ContentValues cv = new ContentValues();
//        cv.put(TopicAksaraTable.COLUMN_TITLE, topikAksara.getJudul());
//        cv.put(TopicAksaraTable.COLUMN_CONTENT, topikAksara.getIsi());
//        db.insert(TopicAksaraTable.TABLE_NAME, null, cv);
//    }
//
//    private void fillQuestionsTable() {
//        Question q1 = new Question("Baca sempalan hutbah di handap !\n" +
//                "Dina bubuka hutbah, hotib parantos macakeun surat Az Zumar ayat 9, anu pihartoseunana : “Ucapkeun (Muhammad) naha sarua jalma-jalma nu boga kanyaho jeung jalma-jalma anu teu boga kanyaho? Saenyana, ngan jalma anu boga nalar wungkul anu bisa narima pangjaran.”\n" +
//                "Jelas pisan éta ayat téh, nitah ka urang sangkan daék diajar. Kapan urang téh boga akal. Lamun urang embung narima pangajaran atawa embung diajar, sarua baé urang téh jeung teu boga akal. Ku kituna, hayu urang diajar masing bener-bener. Kapan cenah jalma anu boga élmu téh darajatna kacida pisan luhurna.\n" +
//                "Sempalan hutbah di luhur mangrupakeun ....",
//                "Bubuka", "Eusi", "Panutup", "Rarangkay", 1,
//                Question.DIFFICULTY_EASY, Category.BAHASA_SUNDA);
//        addQuestion(q1);
//        Question q2 = new Question("Bapa nuju .... di kolam renang",
//                "Lumpat", "Maca", "Ngojay", "Sare", 3,
//                Question.DIFFICULTY_EASY, Category.BAHASA_SUNDA);
//        addQuestion(q2);
//        Question q3 = new Question("Terjemahkeun kalimah ieu\nᮃᮘ᮪ᮓᮤ ᮅᮛᮀ ᮞᮥᮔ᮪ᮓ",
//                "Abdi Urang Bandung", "Abdi Urang Jakarta", "Abdi Urang Jawa", "Abdi Urang Sunda", 4,
//                Question.DIFFICULTY_MEDIUM, Category.AKSARA_SUNDA);
//        addQuestion(q3);
//        Question q4 = new Question("Eusian titik-titik dina kalimah ieu\nᮃᮘ᮪ᮓᮤ ... ᮞᮥᮔ᮪ᮓ",
//                "ᮅᮒ", "ᮅᮛᮀ", "ᮅᮛ", "ᮅᮑ", 2,
//                Question.DIFFICULTY_MEDIUM, Category.AKSARA_SUNDA);
//        addQuestion(q4);
//        Question q5 = new Question("Terjemahkeun kalimah ieu ka Bahasa Indonesia\nAbdi nuju maca",
//                "Saya sedang membaca", "Saya sedang tidur", "Saya sedang berlari", "Saya sedang makan", 1,
//                Question.DIFFICULTY_HARD, Category.TERJEMAHAN);
//        addQuestion(q5);
//        Question q6 = new Question("Terjemahkeun kalimah ieu ka Basa Sunda\nBapa nuju tuang",
//                "Bapak sedang minum", "Bapak sedang makan", "Bapak sedang tidur", "Bapak sedang mandi", 2,
//                Question.DIFFICULTY_HARD, Category.TERJEMAHAN);
//        addQuestion(q6);
//    }
//
//    private void addQuestion(Question question) {
//        ContentValues cv = new ContentValues();
//        cv.put(QuestionsTable.COLUMN_PERTANYAAN, question.getPertanyaan());
//        cv.put(QuestionsTable.COLUMN_PIL1, question.getPil1());
//        cv.put(QuestionsTable.COLUMN_PIL2, question.getPil2());
//        cv.put(QuestionsTable.COLUMN_PIL3, question.getPil3());
//        cv.put(QuestionsTable.COLUMN_PIL4, question.getPil4());
//        cv.put(QuestionsTable.COLUMN_NO_JAWABAN, question.getNoJawaban());
//        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
//        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
//        db.insert(QuestionsTable.TABLE_NAME, null, cv);
//    }
//
//    private void fillTopicsTable() {
//        Topik c1 = new Topik("Kedudukan Bahasa Sunda",
//                "Bahasa Sunda merupakan bahasa daerah dalam kelompok bahasa di " +
//                        "Jawa, dan tergolong ke dalam bahasa-bahasa Nusantara Bagian Barat, serta " +
//                        "termasluk bahasa daerah besar di wilayah Repuhlik Indonesia dengan jumlah " +
//                        "penuturnya reJatif besar (lebih dari 20 juta) dan mempunyai tradisi sastra, " +
//                        "baik sastra lisan maupun sastra tulis.\n" +
//                    "Bahasa Sunda dipergunakan oleh sebagian besar penduduk Jawa Barat. " +
//                        "Sebagian lainnya penduduk Jawa Barat mempergunakan dialek Jawa (Ban" +
//                        "ten, Cirebon, Indramayu), diaJek Melayu Jakarta (Jakarta dan sekitarnya), " +
//                        "dan di kota-kota besar sebagian penduduk mempergunakan bahasa Indo" +
//                        "nesia. Menurut sensus tahun 1980 penduduk Jawa Barat hnjumlah 27 juta " +
//                        "orang.\n" +
//                    "Bahasa Sunda, seperti bahasa-bahasa daerah lainnya yang terdapat di " +
//                        "wilayah Republik Indonesia, merupakan salah satu unsur kebudayaan nasio" +
//                        "nal yang dipelihara oleh para pemakainya dan oleh karena itu dilindungi " +
//                        "serta dipelihara juga oleh negara, sesuai dengan bunyi penjelasan Pasal 36, " +
//                        "Bab XV, Undang-Undarg Dasar 1945.");
//        addTopik(c1);
//        Topik c2 = new Topik("Fonologi Bahasa Sunda",
//                "1. Fonem\n" +
//                    "Tata bunyi bahasa Sunda mengenal 25 fonem yang terdiri dari dua jenis " +
//                        "fonem segmental, yaitu fonem vokal (7) dan fonem konsonan (18).\n" +
//                    "2. Urutan Fonem dalam Kata\n" +
//                    "a_latin. Urutan Vokal\n" +
//                        "1) Vokal /a_latin/ dapat diikuti oleh semua vokal, kecuali vokal /e/, yang " +
//                        "tidak dapat mengikuti vokal /a_latin/;\n" +
//                        "2) vokal /i/ dapat diikuti oleh semua vokal, kecuali vokal /e/, yang " +
//                        "tidak dapat mengikuti vokal /i/;\n" +
//                        "3) vokal /u/ dapat diikuti oleh semua vokal, kecuali vokal /e/, yang " +
//                        "tidak dapat mengikuti vokal /u/;\n" +
//                    "b. Urutan Konsonan\n" +
//                        "1) Dalam kata dasar tidak terdapat susunan konsunan yang sama. " +
//                            "Tidak semua konsonan dapat diikuti oleh konsonan lain;\n" +
//                        "2) Gugus konsonan (cluster), konsonan keduanya terdiri ataS kon" +
//                        "sonan /I/, /r/, atau /y/;\n" +
//                        "3) Pada umumnya gugus konsonan di awal kala terdapat pada kata " +
//                        "satu suku (ekasuku);");
//        addTopik(c2);
//
//    }
//
//    private void addTopik(Topik topik) {
//        ContentValues cv = new ContentValues();
//        cv.put(TopicsTable.COLUMN_TITLE, topik.getJudul());
//        cv.put(TopicsTable.COLUMN_CONTENT, topik.getIsi());
//        db.insert(TopicsTable.TABLE_NAME, null, cv);
//    }
//
//    public List<Category> getAllCategories() {
//        List<Category> categoryList = new ArrayList<>();
//        db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
//
//        if (c.moveToFirst()) {
//            do {
//                Category category = new Category();
//                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
//                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
//                categoryList.add(category);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return categoryList;
//    }
//
//    public ArrayList<Question> getAllQuestions() {
//        ArrayList<Question> questionList = new ArrayList<>();
//        db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM  " + QuestionsTable.TABLE_NAME, null);
//
//        if (c.moveToFirst()) {
//            do {
//                Question question = new Question();
//                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
//                question.setPertanyaan(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PERTANYAAN)));
//                question.setPil1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL1)));
//                question.setPil2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL2)));
//                question.setPil3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL3)));
//                question.setPil4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL4)));
//                question.setNoJawaban(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_NO_JAWABAN)));
//                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
//                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
//                questionList.add(question);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return questionList;
//    }
//
//    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
//        ArrayList<Question> questionList = new ArrayList<>();
//        db = getReadableDatabase();
//
//        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
//                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
//        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
//
//        Cursor c = db.query(QuestionsTable.TABLE_NAME, null, selection,
//                selectionArgs, null, null, null);
//
//        if (c.moveToFirst()) {
//            do {
//                Question question = new Question();
//                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
//                question.setPertanyaan(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PERTANYAAN)));
//                question.setPil1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL1)));
//                question.setPil2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL2)));
//                question.setPil3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL3)));
//                question.setPil4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL4)));
//                question.setNoJawaban(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_NO_JAWABAN)));
//                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
//                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
//                questionList.add(question);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return questionList;
//    }
//
//    public ArrayList<Topik> getAllTopics() {
//        ArrayList<Topik> topikList = new ArrayList<>();
//        db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM  " + TopicsTable.TABLE_NAME, null);
//
//        if (c.moveToFirst()) {
//            do {
//                Topik topik = new Topik();
//                topik.setId(c.getInt(c.getColumnIndex(TopicsTable._ID)));
//                topik.setJudul(c.getString(c.getColumnIndex(TopicsTable.COLUMN_TITLE)));
//                topik.setIsi(c.getString(c.getColumnIndex(TopicsTable.COLUMN_CONTENT)));
//                topikList.add(topik);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return topikList;
//    }
//
//    public ArrayList<TopikAksara> getAllTopicAksara() {
//        ArrayList<TopikAksara> topikAksaraList = new ArrayList<>();
//        db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM  " + TopicAksaraTable.TABLE_NAME, null);
//
//        if (c.moveToFirst()) {
//            do {
//                TopikAksara topikAksara = new TopikAksara();
//                topikAksara.setId(c.getInt(c.getColumnIndex(TopicAksaraTable._ID)));
//                topikAksara.setJudul(c.getString(c.getColumnIndex(TopicAksaraTable.COLUMN_TITLE)));
//                topikAksara.setIsi(c.getString(c.getColumnIndex(TopicAksaraTable.COLUMN_CONTENT)));
//                topikAksaraList.add(topikAksara);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return topikAksaraList;
//    }
//
//    public ArrayList<Pertanyaan> getPertanyaans(int categoryID, String difficulty) {
//        ArrayList<Pertanyaan> pertanyaanList = new ArrayList<>();
//        db = getReadableDatabase();
//
//        String selection = EssaysTable.COLUMN_CATEGORY_ID + " = ? " +
//                " AND " + EssaysTable.COLUMN_DIFFICULTY + " = ? ";
//        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
//
//        Cursor c = db.query(EssaysTable.TABLE_NAME, null, selection,
//                selectionArgs, null, null, null);
//
//        if (c.moveToFirst()) {
//            do {
//                Pertanyaan pertanyaan = new Pertanyaan();
//                pertanyaan.setId(c.getInt(c.getColumnIndex(EssaysTable._ID)));
//                pertanyaan.setPertanyaan(c.getString(c.getColumnIndex(EssaysTable.COLUMN_PERTANYAAN)));
//                pertanyaan.setJawaban(c.getString(c.getColumnIndex(EssaysTable.COLUMN_JAWABAN)));
//                pertanyaan.setKesulitan(c.getString(c.getColumnIndex(EssaysTable.COLUMN_DIFFICULTY)));
//                pertanyaan.setCategoryID(c.getInt(c.getColumnIndex(EssaysTable.COLUMN_CATEGORY_ID)));
//                pertanyaanList.add(pertanyaan);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return pertanyaanList;
//    }
//}
