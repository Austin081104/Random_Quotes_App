package com.example.qoutesrandom;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView quoteText, authorText;
    private ImageButton fetchBtn, saveBtn, shareBtn, copyBtn;
    private Button viewSavedBtn;
    private LocalQuoteHelper localQuoteHelper;

    private final String[][] offlineQuotes = {
            {"Be yourself; everyone else is already taken.", "Oscar Wilde"},
            {"The best way to predict the future is to invent it.", "Alan Kay"},
            {"In the middle of every difficulty lies opportunity.", "Albert Einstein"},
            {"Life is what happens when you're busy making other plans.", "John Lennon"},
            {"You miss 100% of the shots you don’t take.", "Wayne Gretzky"},
            {"Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"},
            {"The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"},
            {"Do what you can, with what you have, where you are.", "Theodore Roosevelt"},
            {"Don't watch the clock; do what it does. Keep going.", "Sam Levenson"},
            {"Happiness is not something ready-made. It comes from your own actions.", "Dalai Lama"},
            {"Believe you can and you're halfway there.", "Theodore Roosevelt"},
            {"What lies behind us and what lies before us are tiny matters compared to what lies within us.", "Ralph Waldo Emerson"},
            {"Change your thoughts and you change your world.", "Norman Vincent Peale"},
            {"It does not matter how slowly you go as long as you do not stop.", "Confucius"},
            {"The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"},
            {"If you can dream it, you can do it.", "Walt Disney"},
            {"Strive not to be a success, but rather to be of value.", "Albert Einstein"},
            {"The only way to do great work is to love what you do.", "Steve Jobs"},
            {"Success usually comes to those who are too busy to be looking for it.", "Henry David Thoreau"},
            {"I have not failed. I've just found 10,000 ways that won't work.", "Thomas Edison"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);
        authorText = findViewById(R.id.authorText);
        fetchBtn = findViewById(R.id.fetchBtn);
        saveBtn = findViewById(R.id.saveBtn);
        shareBtn = findViewById(R.id.shareBtn);
        copyBtn = findViewById(R.id.copyBtn);
        viewSavedBtn = findViewById(R.id.viewSavedBtn);

        localQuoteHelper = new LocalQuoteHelper(this);

        fetchBtn.setOnClickListener(v -> fetchRandomQuote());
        saveBtn.setOnClickListener(v -> saveQuote());
        shareBtn.setOnClickListener(v -> shareQuote());
        copyBtn.setOnClickListener(v -> copyQuote());
        viewSavedBtn.setOnClickListener(v -> startActivity(new Intent(this, SavedQuotesActivity.class)));
    }

    private void fetchRandomQuote() {
        QuoteService service = RetrofitClient.getService();
        service.getRandomQuote().enqueue(new Callback<Quote[]>() {
            @Override
            public void onResponse(Call<Quote[]> call, Response<Quote[]> response) {
                if (response.isSuccessful() && response.body() != null && response.body().length > 0) {
                    Quote quote = response.body()[0];
                    quoteText.setText(quote.getQuote());
                    authorText.setText("- " + quote.getAuthor());
                } else {
                    showOfflineQuote("No quote found from API.");
                }
            }

            @Override
            public void onFailure(Call<Quote[]> call, Throwable t) {
                showOfflineQuote("API failed: " + t.getMessage());
            }
        });
    }

    private void showOfflineQuote(String reason) {
        int index = new Random().nextInt(offlineQuotes.length);
        quoteText.setText(offlineQuotes[index][0]);
        authorText.setText("- " + offlineQuotes[index][1]);
        Toast.makeText(this, reason + " Showing offline quote.", Toast.LENGTH_SHORT).show();
    }

    private void saveQuote() {
        String quote = quoteText.getText().toString();
        String author = authorText.getText().toString();
        if (!quote.isEmpty()) {
            localQuoteHelper.saveQuote(quote, author);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareQuote() {
        String fullQuote = quoteText.getText().toString() + "\n" + authorText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, fullQuote);
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    private void copyQuote() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Quote", quoteText.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
    }
}