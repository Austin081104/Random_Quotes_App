package com.example.qoutesrandom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        LottieAnimationView lottieAnimation = findViewById(R.id.lottieAnimation);

        // Play animation (optional if already set in XML)
        lottieAnimation.playAnimation();

        // Delay for 3 seconds, then open MainActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(splash.this, MainActivity.class));
            finish(); // Close SplashActivity
        }, 3000);
    }
}