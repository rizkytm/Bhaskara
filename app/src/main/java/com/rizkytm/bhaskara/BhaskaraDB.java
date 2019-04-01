package com.rizkytm.bhaskara;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.rizkytm.bhaskara.QuizContract.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Images.Thumbnails.IMAGE_ID;

public class BhaskaraDB extends SQLiteOpenHelper {

    public int[] imagesA = {R.drawable.ka, R.drawable.ga, R.drawable.nga, R.drawable.ca, R.drawable.ja, R.drawable.nya,
            R.drawable.ta, R.drawable.da, R.drawable.na, R.drawable.pa, R.drawable.ba, R.drawable.ma,
            R.drawable.ya, R.drawable.ra, R.drawable.la, R.drawable.wa, R.drawable.sa, R.drawable.ha};
    int[] imagesB = {R.drawable.ka_s, R.drawable.ga_s, R.drawable.nga_s, R.drawable.ca_s, R.drawable.ja_s, R.drawable.nya_s,
            R.drawable.ta_s, R.drawable.da_s, R.drawable.na_s, R.drawable.pa_s, R.drawable.ba_s, R.drawable.ma_s,
            R.drawable.ya_s, R.drawable.ra_s, R.drawable.la_s, R.drawable.wa_s, R.drawable.sa_s, R.drawable.ha_s};


    private Context mContext;

    public static final String DATABASE_NAME = "bhaskarasunda.db";
    public static final int DATABASE_VERSION = 1;

    private String DATABASE_LOCATION = "";
    private String DATABASE_FULL_PATH = "";

    private final String TBL_IND_SUN = "indo_sunda";
    private final String TBL_SUN_IND = "sunda_indo";
    private final String TBL_SUN_SUN = "sunda_sunda";
//    private final String TBL_BOOKMARK = "bookmark";

    private final String COL_ID = "id";
    private final String COL_KEY = "key";
    private final String COL_VALUE = "value";

    // Table Names
    private static final String TABLE_IMAGE = "images";

    // column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE_A = "image_a";
    private static final String COLUMN_IMAGE_B = "image_b";

//    // Table create statement
//    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE + "("+
//            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_NAME + " TEXT," +
//            COLUMN_IMAGE_A + " BLOB," +
//            COLUMN_IMAGE_B + " BLOB);";

    public SQLiteDatabase db;

    private static BhaskaraDB instance;

    public static synchronized BhaskaraDB getInstance(Context context) {
        if (instance == null) {
            instance = new BhaskaraDB(context.getApplicationContext());
        }
        return instance;
    }

    public BhaskaraDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        DATABASE_LOCATION = "data/data/"+mContext.getPackageName()+"/database/";
        DATABASE_FULL_PATH = DATABASE_LOCATION + DATABASE_NAME;

        if (!isExistingDB()) {
            try {
                File dbLocation = new File(DATABASE_LOCATION);
                dbLocation.mkdirs();

                extractAssetToDatabaseDirectory(DATABASE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        db = SQLiteDatabase.openOrCreateDatabase(DATABASE_FULL_PATH, null);
        loadImages();
        buatSoalEssay();
    }

    boolean isExistingDB() {
        File file = new File(DATABASE_FULL_PATH);
        return file.exists();
    }

    public void extractAssetToDatabaseDirectory(String fileName) throws IOException {

        int length;
        InputStream sourceDatabase = this.mContext.getAssets().open(fileName);
        File destinationPath = new File(DATABASE_FULL_PATH);
        OutputStream destination = new FileOutputStream(destinationPath);

        byte[] buffer = new byte[4096];
        while ((length = sourceDatabase.read(buffer)) > 0) {
            destination.write(buffer, 0, length);
        }

        sourceDatabase.close();
        destination.flush();
        destination.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE_IMAGE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);

        // create new table
//        onCreate(db);
    }


//    public void addImage(String name, byte[] imageA, byte[] imageB) throws SQLiteException {
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_NAME, name);
//        cv.put(COLUMN_IMAGE_A, imageA);
//        cv.put(COLUMN_IMAGE_B, imageB);
//        database.insert(TABLE_IMAGE, null, cv);
//    }

    public void tambah(Image image) {

//        try {
////            String sql="INSERT INTO images (id, name, image_a, image_b) VALUES(1, 'ini huruf ka yah', " + photoID + " , " + photoID + " );";
//            String sql="INSERT INTO images ([name], [image_a], [image_b]) VALUES(?, ?, ?);";
//            db.execSQL(sql, new Object[]{image.name, image.imageA, image.imageB});
//        } catch (SQLException ex) {
//
//        }

        try {
//            String sql="INSERT INTO images (id, name, image_a, image_b) VALUES(1, 'ini huruf ka yah', " + photoID + " , " + photoID + " );";
            String sql="INSERT INTO images ([name], [image_a], [image_b], [topik_id]) VALUES(?, ?, ?, ?);";
            db.execSQL(sql, new Object[]{image.nama, image.imageA, image.imageB, image.topik_id});
        } catch (SQLException ex) {

        }


//        db.execSQL("INSERT INTO images (name, image_a, image_b) VALUES('ini huruf ka', " + photoID + " , " + photoID + ");");

//        Cursor c = db.rawQuery(sql, null);
    }

    public ArrayList<Image> getAllImages() {
        ArrayList<Image> imageList = new ArrayList<>();
//        db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_IMAGE;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                Image image = new Image();
                image.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                image.setNama(c.getString(c.getColumnIndex(COLUMN_NAME)));
                image.setImageA(c.getInt(c.getColumnIndex(COLUMN_IMAGE_A)));
                image.setImageB(c.getInt(c.getColumnIndex(COLUMN_IMAGE_B)));
                image.setTopik_id(c.getInt(c.getColumnIndex(SoalGameTable.COLUMN_TOPIK_ID)));
                imageList.add(image);
            } while (c.moveToNext());
        }

        c.close();
        return imageList;
    }

    public void insetImage(Drawable dbDrawable, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        values.put(COLUMN_IMAGE_A, stream.toByteArray());
        values.put(COLUMN_IMAGE_B, stream.toByteArray());
        db.insert(TABLE_IMAGE, null, values);
        db.close();
    }

//    public ArrayList<String> getImageA() {
//        String q = "SELECT * FROM " + TABLE_IMAGE + ";";
//        Cursor result = db.rawQuery(q, null);
//
//        ArrayList<String> source = new ArrayList<>();
//        while (result.moveToNext()) {
//            source.add(result.getString(result.getColumnIndex()))
//        }
//    }

//    public ArrayList<Image> getImages() {
//        ArrayList<Image> imageList = new ArrayList<>();
////        db = getReadableDatabase();
//        String q = "SELECT * FROM " + TABLE_IMAGE + ";";
//        Cursor c = db.rawQuery(q, null);
//
//        if (c.moveToFirst()) {
//            do {
//                Image image = new Image();
//                image.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
//                image.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
//                image.setImageA(c.getString(c.getColumnIndex(COLUMN_IMAGE_A)));
//                image.setImageB(c.getString(c.getColumnIndex(COLUMN_IMAGE_B)));
//                imageList.add(image);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return imageList;
//    }

    public ArrayList<String> getWord(int dicType) {
        String tableName = getTableName(dicType);
        String q = "SELECT * FROM " + tableName + ";";
        Cursor result = db.rawQuery(q, null);

        ArrayList<String> source = new ArrayList<>();
        while (result.moveToNext()) {
            source.add(result.getString(result.getColumnIndex(COL_KEY)));
        }
        return source;
    }

    public Word getWord(String key, int dicType) {
        String tableName = getTableName(dicType);
        String q = "SELECT * FROM " + tableName + " WHERE upper([key]) = upper(?);";
        Cursor result = db.rawQuery(q, new String[]{key});

        Word word = new Word();
        while (result.moveToNext()) {
            word.id = result.getString(result.getColumnIndex(COL_ID));
            word.key = result.getString(result.getColumnIndex(COL_KEY));
            word.value = result.getString(result.getColumnIndex(COL_VALUE));
        }
        return word;
    }

    public void addBookmark(Word word) {
        try {
            String q = "INSERT INTO bookmark([" + COL_KEY + "],[" + COL_VALUE + "]) VALUES (?, ?);";
            db.execSQL(q, new Object[]{word.key, word.value});
        } catch (SQLException ex) {

        }
    }

    public void removeBookmark(Word word) {
        try {
            String q = "DELETE FROM bookmark WHERE upper([" + COL_KEY + "]) = upper(?) AND [" + COL_VALUE + "] = ?;";
            db.execSQL(q, new Object[]{word.key, word.value});
        } catch (SQLException ex) {

        }
    }

    public ArrayList<String> getAllWordFromBookmark() {
        String q = "SELECT * FROM bookmark ORDER BY [date] DESC;";
        Cursor result = db.rawQuery(q, null);

        ArrayList<String> source = new ArrayList<>();
        while (result.moveToNext()) {
            source.add(result.getString(result.getColumnIndex(COL_KEY)));
        }
        return source;
    }

    public boolean isWordMark(Word word) {
        String q = "SELECT * FROM bookmark WHERE upper([key]) = upper(?) AND [value] = ?";
        Cursor result = db.rawQuery(q, new String[]{word.key, word.value});
        return result.getCount() > 0;
    }

    public Word getWordFromBookmark(String key) {
        String q = "SELECT * FROM bookmark WHERE upper([key]) = upper(?)";
        Cursor result = db.rawQuery(q, new String[]{key});
        Word word = null;
        while (result.moveToNext()) {
            word = new Word();
            word.key = result.getString(result.getColumnIndex(COL_KEY));
            word.value = result.getString(result.getColumnIndex(COL_VALUE));
        }
        return word;
    }

    public String getTableName(int dicType) {
        String tableName = "";
        if (dicType == R.id.action_sun_ind) {
            tableName = TBL_SUN_IND;
        }
//        else if (dicType == R.id.action_sun_sun) {
//            tableName = TBL_SUN_SUN;
//        }
        else if (dicType == R.id.action_ind_sun) {
            tableName = TBL_IND_SUN;
        }
        return tableName;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
//        db = getReadableDatabase();
        String q = "SELECT * FROM " + CategoriesTable.TABLE_NAME;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable.COLUMN_ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
//        db = getReadableDatabase();
        String q = "SELECT * FROM " + CategoriesTable.TABLE_NAME;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setPertanyaan(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PERTANYAAN)));
                question.setPil1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL1)));
                question.setPil2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL2)));
                question.setPil3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL3)));
                question.setPil4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL4)));
                question.setNoJawaban(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_NO_JAWABAN)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
//        db = getReadableDatabase();

//        String q = "SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_CATEGORY_ID +
//                " = " +

        String q = "SELECT * FROM " + QuestionsTable.TABLE_NAME +" WHERE category_id = ? " +
                "AND difficulty = ?";


//        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
//                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c= db.rawQuery(q, selectionArgs);
//
//        Cursor c = db.query(QuestionsTable.TABLE_NAME, null, selection,
//                selectionArgs, null, null, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ID)));
                question.setPertanyaan(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PERTANYAAN)));
                question.setPil1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL1)));
                question.setPil2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL2)));
                question.setPil3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL3)));
                question.setPil4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_PIL4)));
                question.setNoJawaban(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_NO_JAWABAN)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Topik> getAllTopics() {
        ArrayList<Topik> topikList = new ArrayList<>();
//        db = getReadableDatabase();
        String q = "SELECT * FROM " + TopicsTable.TABLE_NAME;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                Topik topik = new Topik();
                topik.setId(c.getInt(c.getColumnIndex(TopicsTable.COLUMN_ID)));
                topik.setJudul(c.getString(c.getColumnIndex(TopicsTable.COLUMN_TITLE)));
                topik.setIsi(c.getString(c.getColumnIndex(TopicsTable.COLUMN_CONTENT)));
                topikList.add(topik);
            } while (c.moveToNext());
        }

        c.close();
        return topikList;
    }

    public ArrayList<TopikAksara> getAllTopicAksara() {
        ArrayList<TopikAksara> topikAksaraList = new ArrayList<>();
//        db = getReadableDatabase();
        String q = "SELECT * FROM " + TopicAksaraTable.TABLE_NAME;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                TopikAksara topikAksara = new TopikAksara();
                topikAksara.setId(c.getInt(c.getColumnIndex(TopicAksaraTable.COLUMN_ID)));
                topikAksara.setJudul(c.getString(c.getColumnIndex(TopicAksaraTable.COLUMN_TITLE)));
                topikAksara.setIsi(c.getString(c.getColumnIndex(TopicAksaraTable.COLUMN_CONTENT)));
                topikAksaraList.add(topikAksara);
            } while (c.moveToNext());
        }

        c.close();
        return topikAksaraList;
    }

    public ArrayList<Pertanyaan> getPertanyaans(int categoryID, String difficulty) {
        ArrayList<Pertanyaan> pertanyaanList = new ArrayList<>();
//        db = getReadableDatabase();

//        String selection = EssaysTable.COLUMN_CATEGORY_ID + " = ? " +
//                " AND " + EssaysTable.COLUMN_DIFFICULTY + " = ? ";

        String q = "SELECT * FROM " + EssaysTable.TABLE_NAME +" WHERE category_id = ? " +
                "AND difficulty = ?";

        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

//        Cursor c = db.query(EssaysTable.TABLE_NAME, null, selection,
//                selectionArgs, null, null, null);

        Cursor c= db.rawQuery(q, selectionArgs);

        if (c.moveToFirst()) {
            do {
                Pertanyaan pertanyaan = new Pertanyaan();
                pertanyaan.setId(c.getInt(c.getColumnIndex(EssaysTable.COLUMN_ID)));
                pertanyaan.setPertanyaan(c.getString(c.getColumnIndex(EssaysTable.COLUMN_PERTANYAAN)));
                pertanyaan.setJawaban(c.getString(c.getColumnIndex(EssaysTable.COLUMN_JAWABAN)));
                pertanyaan.setKesulitan(c.getString(c.getColumnIndex(EssaysTable.COLUMN_DIFFICULTY)));
                pertanyaan.setCategoryID(c.getInt(c.getColumnIndex(EssaysTable.COLUMN_CATEGORY_ID)));
                pertanyaanList.add(pertanyaan);
            } while (c.moveToNext());
        }

        c.close();
        return pertanyaanList;
    }

    public String getURLForResource (int resourceId) {
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }

    public void loadImages() {
//        for (int i=0; i<imagesA.length; i++) {
//            String name = mContext.getResources().getResourceEntryName(imagesA[i]);
//            int imageA = imagesA[i];
//            int imageB = imagesB[i];
//            Image image = new Image(name, imageA, imageB, 1);
//            tambah(image);
//        }

        for (int i=0; i<imagesA.length; i++) {
            String name = mContext.getResources().getResourceEntryName(imagesA[i]);
            String nameB = mContext.getResources().getResourceEntryName(imagesB[i]);
            Resources resources = mContext.getResources();
            int imageA = resources.getIdentifier(name, "drawable",
                    mContext.getPackageName());
//            int imageB = getURLForResource(imagesB[i]);
            int imageB = resources.getIdentifier(nameB, "drawable",
                    mContext.getPackageName());
            Image image = new Image(name, imageA, imageB, 1);
            insertImage(image);
        }
    }

    public List<TopikEssay> getAllTopikEssay() {
        List<TopikEssay> topikList = new ArrayList<>();
        String q = "SELECT * FROM " + TopikEssayTable.TABLE_NAME;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                TopikEssay topikEssay = new TopikEssay();
                topikEssay.setId(c.getInt(c.getColumnIndex(TopikEssayTable.COLUMN_ID)));
                topikEssay.setJudul(c.getString(c.getColumnIndex(TopikEssayTable.COLUMN_JUDUL)));
                topikEssay.setSkor(c.getInt(c.getColumnIndex(TopikEssayTable.COLUMN_SKOR)));
                topikList.add(topikEssay);
            } while (c.moveToNext());
        }

        c.close();
        return topikList;
    }

    public List<TopikGame> getAllTopikGame() {
        List<TopikGame> topikList = new ArrayList<>();
        String q = "SELECT * FROM " + TopikGameTable.TABLE_NAME;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                TopikGame topikGame = new TopikGame();
                topikGame.setId(c.getInt(c.getColumnIndex(TopikEssayTable.COLUMN_ID)));
                topikGame.setJudul(c.getString(c.getColumnIndex(TopikEssayTable.COLUMN_JUDUL)));
                topikGame.setSkor(c.getInt(c.getColumnIndex(TopikEssayTable.COLUMN_SKOR)));
                topikList.add(topikGame);
            } while (c.moveToNext());
        }

        c.close();
        return topikList;
    }

    private void buatSoalEssay() {
        SoalEssay soalEssay1 = new SoalEssay(1, "abdi", "ᮃᮘ᮪ᮓᮤ");
        insertSoalEssay(soalEssay1);
        SoalEssay soalEssay2 = new SoalEssay(1, "bapa", "ᮘᮕ");
        insertSoalEssay(soalEssay2);
        SoalEssay soalEssay3 = new SoalEssay(1, "ibu", "ᮄᮘᮥ");
        insertSoalEssay(soalEssay3);
        SoalEssay soalEssay4 = new SoalEssay(1, "bajigur", "ᮘᮏᮤᮌᮥᮛ᮪");
        insertSoalEssay(soalEssay4);
        SoalEssay soalEssay5 = new SoalEssay(1, "bajakan", "ᮘᮏᮊᮔ᮪");
        insertSoalEssay(soalEssay5);
    }

    public void insertSoalEssay(SoalEssay soalEssay) {

        try {
            String sql="INSERT INTO soal_essay ([topik_id], [pertanyaan], [jawaban]) VALUES(?, ?, ?);";
            db.execSQL(sql, new Object[]{soalEssay.getTopik_id(), soalEssay.getPertanyaan(), soalEssay.getJawaban()});
        } catch (SQLException ex) {

        }
    }

    public List<SoalEssay> loadSoalEssay(int topikID) {
        List<SoalEssay> soalEssayList = new ArrayList<>();
        String q = "SELECT * FROM " + SoalEssayTable.TABLE_NAME +" WHERE topik_id = ? ";

        String[] selectionArgs = new String[]{String.valueOf(topikID)};

//        String q = "SELECT * FROM " + SoalEssayTable.TABLE_NAME + "WHERE topik_id ";
        Cursor c = db.rawQuery(q, selectionArgs);

        if (c.moveToFirst()) {
            do {
                SoalEssay soalEssay = new SoalEssay();
                soalEssay.setId(c.getInt(c.getColumnIndex(SoalEssayTable.COLUMN_ID)));
                soalEssay.setTopik_id(c.getInt(c.getColumnIndex(SoalEssayTable.COLUMN_TOPIK_ID)));
                soalEssay.setPertanyaan(c.getString(c.getColumnIndex(SoalEssayTable.COLUMN_PERTANYAAN)));
                soalEssay.setJawaban(c.getString(c.getColumnIndex(SoalEssayTable.COLUMN_JAWABAN)));
                soalEssayList.add(soalEssay);
            } while (c.moveToNext());
        }

        c.close();
        return soalEssayList;
    }

    public void insertImage(Image image) {

        try {
            String sql="INSERT INTO soal_game ([nama], [image_a], [image_b], [topik_id]) VALUES(?, ?, ?, ?);";
            db.execSQL(sql, new Object[]{image.getNama(), image.getImageA(), image.getImageB(), image.getTopik_id()});
        } catch (SQLException ex) {

        }

    }

    public ArrayList<Image> getAllSoalGame(int topikID) {
        ArrayList<Image> imageList = new ArrayList<>();
//        String q = "SELECT * FROM " + SoalGameTable.TABLE_NAME;
        String q = "SELECT * FROM " + SoalGameTable.TABLE_NAME +" WHERE topik_id = ? ";

        String[] selectionArgs = new String[]{String.valueOf(topikID)};
        Cursor c = db.rawQuery(q, selectionArgs);

        if (c.moveToFirst()) {
            do {
                Image image = new Image();
                image.setId(c.getInt(c.getColumnIndex(SoalGameTable.COLUMN_ID)));
                image.setId(c.getInt(c.getColumnIndex(SoalGameTable.COLUMN_TOPIK_ID)));
                image.setNama(c.getString(c.getColumnIndex(SoalGameTable.COLUMN_NAMA)));
                image.setImageA(c.getInt(c.getColumnIndex(SoalGameTable.COLUMN_IMAGE_A)));
                image.setImageB(c.getInt(c.getColumnIndex(SoalGameTable.COLUMN_IMAGE_B)));
                imageList.add(image);
            } while (c.moveToNext());
        }

        c.close();
        return imageList;
    }

    public void updateSkorEssay(int newSkor, int topikID) {

        try {
            String sql="UPDATE topik_essay SET [skor] = ? WHERE [id] = ?;";
            db.execSQL(sql, new Object[]{newSkor, topikID});
        } catch (SQLException ex) {

        }
    }

    public void updateSkorKuis(int newSkor, int topikID) {

        try {
            String sql="UPDATE topik_pg SET [skor] = ? WHERE [id] = ?;";
            db.execSQL(sql, new Object[]{newSkor, topikID});
        } catch (SQLException ex) {

        }
    }

    public void updateSkorGame(int newSkor, int topikID) {

        try {
            String sql="UPDATE topik_game SET [skor] = ? WHERE [id] = ?;";
            db.execSQL(sql, new Object[]{newSkor, topikID});
        } catch (SQLException ex) {

        }
    }

    public List<TopikKuis> getAllTopikKuis() {
        List<TopikKuis> topikKuisList = new ArrayList<>();
        String q = "SELECT * FROM " + TopikKuisTable.TABLE_NAME;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            do {
                TopikKuis topikKuis = new TopikKuis();
                topikKuis.setId(c.getInt(c.getColumnIndex(TopikKuisTable.COLUMN_ID)));
                topikKuis.setJudul(c.getString(c.getColumnIndex(TopikKuisTable.COLUMN_JUDUL)));
                topikKuis.setSkor(c.getInt(c.getColumnIndex(TopikKuisTable.COLUMN_SKOR)));
                topikKuis.setDifficulty_id(c.getInt(c.getColumnIndex(TopikKuisTable.COLUMN_DIFFICULTY)));
                topikKuisList.add(topikKuis);
            } while (c.moveToNext());
        }

        c.close();
        return topikKuisList;
    }

    public ArrayList<SoalPG> getSoalKuis(int topikID, int difficulty_id) {
        ArrayList<SoalPG> soalPGList = new ArrayList<>();
        String q = "SELECT * FROM " + SoalPGTable.TABLE_NAME +" WHERE topik_id = ? " +
                "AND difficulty_id = ?";
        String[] selectionArgs = new String[]{String.valueOf(topikID), String.valueOf(difficulty_id)};

        Cursor c= db.rawQuery(q, selectionArgs);
        if (c.moveToFirst()) {
            do {
                SoalPG soalPG = new SoalPG();
                soalPG.setId(c.getInt(c.getColumnIndex(SoalPGTable.COLUMN_ID)));
                soalPG.setPertanyaan(c.getString(c.getColumnIndex(SoalPGTable.COLUMN_PERTANYAAN)));
                soalPG.setPil1(c.getString(c.getColumnIndex(SoalPGTable.COLUMN_PIL1)));
                soalPG.setPil2(c.getString(c.getColumnIndex(SoalPGTable.COLUMN_PIL2)));
                soalPG.setPil3(c.getString(c.getColumnIndex(SoalPGTable.COLUMN_PIL3)));
                soalPG.setPil4(c.getString(c.getColumnIndex(SoalPGTable.COLUMN_PIL4)));
                soalPG.setNoJawaban(c.getInt(c.getColumnIndex(SoalPGTable.COLUMN_NO_JAWABAN)));
                soalPG.setDifficulty_id(c.getInt(c.getColumnIndex(SoalPGTable.COLUMN_DIFFICULTY)));
                soalPG.setTopik_id(c.getInt(c.getColumnIndex(SoalPGTable.COLUMN_CATEGORY_ID)));
                soalPGList.add(soalPG);
            } while (c.moveToNext());
        }

        c.close();
        return soalPGList;
    }
}
