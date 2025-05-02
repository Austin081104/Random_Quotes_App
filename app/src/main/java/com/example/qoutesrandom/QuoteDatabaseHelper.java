package com.example.qoutesrandom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuoteDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quotes.db";
    private static final int DB_VERSION = 1;

    public QuoteDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Quotes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quote TEXT NOT NULL, " +
                "author TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Quotes");
        onCreate(db);
    }
}