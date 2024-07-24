package com.example.werent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScrean extends AppCompatActivity {
long delay = 1200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent i = new Intent(SplashScrean.this, LoginPage.class);
                startActivity(i);
            }
        };
        t.schedule(tt,delay);
    }
}
