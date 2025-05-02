package com.example.qoutesrandom;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavedQuotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SavedQuotesAdapter adapter;
    private LocalQuoteHelper localQuoteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        localQuoteHelper = new LocalQuoteHelper(this);
        List<QuoteModel> quotes = localQuoteHelper.getSavedQuotes();

        if (quotes.isEmpty()) {
            Toast.makeText(this, "No saved quotes found", Toast.LENGTH_SHORT).show();
        }

        adapter = new SavedQuotesAdapter(quotes, this);
        recyclerView.setAdapter(adapter);
    }
}