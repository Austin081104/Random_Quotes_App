package com.example.qoutesrandom;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface QuoteService {
    @Headers("X-Api-Key: https://qapi.vercel.app/")  // Replace with your real API key
    @GET("quotes")
    Call<Quote[]> getRandomQuote();
}