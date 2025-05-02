package com.example.qoutesrandom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavedQuotesAdapter extends RecyclerView.Adapter<SavedQuotesAdapter.ViewHolder> {
    private final List<QuoteModel> quotes;
    private final LocalQuoteHelper localQuoteHelper;

    public SavedQuotesAdapter(List<QuoteModel> quotes, Context context) {
        this.quotes = quotes;
        this.localQuoteHelper = new LocalQuoteHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuoteModel quote = quotes.get(position);
        holder.quoteText.setText(quote.getQuote());
        holder.authorText.setText(quote.getAuthor());

        holder.deleteBtn.setOnClickListener(v -> {
            localQuoteHelper.deleteQuote(quote.getId());
            quotes.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView quoteText, authorText;
        Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quoteText = itemView.findViewById(R.id.quoteText);
            authorText = itemView.findViewById(R.id.authorText);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}