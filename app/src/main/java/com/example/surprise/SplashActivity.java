package com.example.surprise;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.surprise.MainActivity;
import com.example.surprise.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 5000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        TextView appNameTextView = findViewById(R.id.appNameTextView);

        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);

        logoImageView.startAnimation(splashAnimation);
        appNameTextView.startAnimation(splashAnimation);

        logoImageView.setVisibility(View.VISIBLE);
        appNameTextView.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}