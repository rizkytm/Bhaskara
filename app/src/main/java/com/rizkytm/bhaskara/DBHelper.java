package com.rizkytm.bhaskara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    public static final String DATABASE_NAME = "kamus.db";
    public static final int DATABASE_VERSION = 1;

    private String DATABASE_LOCATION = "";
    private String DATABASE_FULL_PATH = "";

    private final String TBL_IND_SUN = "ind_sun";
    private final String TBL_SUN_IND = "sun_ind";
    private final String TBL_SUN_SUN = "sun_sun";
    private final String TBL_BOOKMARK = "bookmark";
    private final String TBL_TOPIK = "topik";

    private final String COL_KEY = "key";
    private final String COL_VALUE = "value";
    private final String COL_JUDUL = "judul";
    private final String COL_ISI = "isi";

    public SQLiteDatabase mDB;

    public ArrayList<Topik> topikList = new ArrayList<>();

    public DBHelper(Context context) {
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

        mDB = SQLiteDatabase.openOrCreateDatabase(DATABASE_FULL_PATH, null);
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
        this.mDB = db;

        final String SQL_CREATE_TOPICS_TABLE = "CREATE TABLE " +
                TBL_TOPIK + "( " +
                "judul" + " VARCHAR, " +
                "isi" + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_TOPICS_TABLE);

        fillTopikTable();
    }

    private void fillTopikTable() {
        Topik c1 = new Topik("Bahasa Sunda", "Ini adalah deskripsi dari Bahasa Sunda");
        addTopik(c1);
        Topik c2 = new Topik("Aksara Sunda", "Ini adalah deskripsi dari Aksara Sunda");
        addTopik(c2);
        Topik c3 = new Topik("Terjemahan", "Ini adalah deskripsi dari Terjemahan");
        addTopik(c3);
    }

    private void addTopik(Topik topik) {
        ContentValues cv = new ContentValues();
        cv.put(COL_JUDUL, topik.getJudul());
        cv.put(COL_ISI, topik.getIsi());
        mDB.insert(TBL_TOPIK, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_TOPIK);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_BOOKMARK);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_IND_SUN);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_SUN_IND);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_SUN_SUN);
        onCreate(db);
    }

    public ArrayList<String> getWord(int dicType) {
        String tableName = getTableName(dicType);
        String q = "SELECT * FROM " + tableName + ";";
        Cursor result = mDB.rawQuery(q, null);

        ArrayList<String> source = new ArrayList<>();
        while (result.moveToNext()) {
            source.add(result.getString(result.getColumnIndex(COL_KEY)));
        }
        return source;
    }

    public ArrayList<Topik> getTopikList() {
//        String tableName = getTableName(dicType);
        mDB = this.getWritableDatabase();
        String q = "SELECT * FROM " + TBL_TOPIK;
        Cursor result = (Cursor) mDB.rawQuery(q, null);

//        ArrayList<String> source = new ArrayList<>();
//        while (result.moveToNext()) {
//            source.add(result.getString(result.getColumnIndex(COL_JUDUL)));
//        }
//        return source;

        if (result.getCount() != 0) {
            if (result.moveToFirst()) {
                do {
                    Topik topik = new Topik();
                    topik.judul = result.getString(result.getColumnIndex(COL_JUDUL));
                } while (result.moveToNext());
            }
        }
        result.close();
        mDB.close();
        return topikList;
    }

    public Word getWord(String key, int dicType) {
        String tableName = getTableName(dicType);
        String q = "SELECT * FROM " + tableName + " WHERE upper([key]) = upper(?);";
        Cursor result = mDB.rawQuery(q, new String[]{key});

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
            mDB.execSQL(q, new Object[]{word.key, word.value});
        } catch (SQLException ex) {

        }
    }

    public void removeBookmark(Word word) {
        try {
            String q = "DELETE FROM bookmark WHERE upper([" + COL_KEY + "]) = upper(?) AND [" + COL_VALUE + "] = ?;";
            mDB.execSQL(q, new Object[]{word.key, word.value});
        } catch (SQLException ex) {

        }
    }

    public ArrayList<String> getAllWordFromBookmark() {
        String q = "SELECT * FROM bookmark ORDER BY [date] DESC;";
        Cursor result = mDB.rawQuery(q, null);

        ArrayList<String> source = new ArrayList<>();
        while (result.moveToNext()) {
            source.add(result.getString(result.getColumnIndex(COL_KEY)));
        }
        return source;
    }

    public boolean isWordMark(Word word) {
        String q = "SELECT * FROM bookmark WHERE upper([key]) = upper(?) AND [value] = ?";
        Cursor result = mDB.rawQuery(q, new String[]{word.key, word.value});
        return result.getCount() > 0;
    }

    public Word getWordFromBookmark(String key) {
        String q = "SELECT * FROM bookmark WHERE upper([key]) = upper(?)";
        Cursor result = mDB.rawQuery(q, new String[]{key});
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
}
