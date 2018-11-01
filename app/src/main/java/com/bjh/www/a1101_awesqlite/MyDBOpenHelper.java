package com.bjh.www.a1101_awesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    private static final String name = "awe.db";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;

    public MyDBOpenHelper(Context context) {
        super(context, name, factory, version);
    }

    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   // version에 따라 1회 실행
        String createSQL = "CREATE TABLE awe_country (_id INTEGER PRIMARY KEY AUTOINCREMENT, country TEXT, city TEXT);";
        // CREATE TABLE awe_country (_id INTEGER PRIMARY KEY AUTOINCREMENT, country TEXT, city TEXT);
        db.execSQL(createSQL);

        for(int i=0; i<10; i++) {
            String insertSQL = "INSERT INTO awe_country VALUES(null, '" + "country" + i + "', '" + "city" + i + "');";
            // INSERT INTO awe_country VALUES(null, '" + "Country" + i + "', '" + "City" + i + "');
            db.execSQL(insertSQL);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  // version 변환 시 사용
        String dropSQL = "DROP TABLE awe_country;";
        db.execSQL(dropSQL);
        onCreate(db);
    }

    public void deleteRecord(SQLiteDatabase mdb, String country) {
        String deleteSQL = "DELETE FROM awe_country WHERE country='" + country + "';";
        mdb.execSQL(deleteSQL);
    }
}