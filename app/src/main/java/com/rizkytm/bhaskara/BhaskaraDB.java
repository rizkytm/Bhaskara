package com.rizkytm.bhaskara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rizkytm.bhaskara.QuizContract.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BhaskaraDB extends SQLiteOpenHelper {

    private Context mContext;

    public static final String DATABASE_NAME = "bhaskarasunda.db";
    public static final int DATABASE_VERSION = 1;

    private String DATABASE_LOCATION = "";
    private String DATABASE_FULL_PATH = "";

    private final String TBL_IND_SUN = "ind_sun";
    private final String TBL_SUN_IND = "sun_ind";
    private final String TBL_SUN_SUN = "sun_sun";
    private final String TBL_BOOKMARK = "bookmark";

    private final String COL_KEY = "key";
    private final String COL_VALUE = "value";

//    // Table Names
//    private static final String TABLE_IMAGE = "images";
//
//    // column names
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_NAME = "name";
//    private static final String COLUMN_IMAGE_A = "image_a";
//    private static final String COLUMN_IMAGE_B = "image_b";
//
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
        } else if (dicType == R.id.action_sun_sun) {
            tableName = TBL_SUN_SUN;
        } else if (dicType == R.id.action_ind_sun) {
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
}
