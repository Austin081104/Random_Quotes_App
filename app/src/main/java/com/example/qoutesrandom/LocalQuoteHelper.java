package com.example.qoutesrandom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LocalQuoteHelper {
    private final SQLiteDatabase db;

    public LocalQuoteHelper(Context context) {
        db = new QuoteDatabaseHelper(context).getWritableDatabase();
    }

    public void saveQuote(String quote, String author) {
        ContentValues values = new ContentValues();
        values.put("quote", quote);
        values.put("author", author);
        db.insert("Quotes", null, values);
    }

    public List<QuoteModel> getSavedQuotes() {
        List<QuoteModel> quotes = new ArrayList<>();
        Cursor cursor = db.query("Quotes", null, null, null, null, null, "id DESC");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String quote = cursor.getString(cursor.getColumnIndexOrThrow("quote"));
            String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            quotes.add(new QuoteModel(String.valueOf(id), quote, author));
        }
        cursor.close();
        return quotes;
    }

    public void deleteQuote(String id) {
        db.delete("Quotes", "id = ?", new String[]{id});
    }
}