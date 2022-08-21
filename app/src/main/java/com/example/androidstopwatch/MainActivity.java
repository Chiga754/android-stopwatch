package com.example.androidstopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean isRunning = false;
    private int seconds = 0;

    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        if( savedInstanceState != null ) {
            isRunning = savedInstanceState.getBoolean("isRunning");
            seconds = savedInstanceState.getInt("seconds");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isRunning", isRunning);
        outState.putInt("seconds", seconds);
    }

    private void runTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                textViewTimer.setText(time);
                handler.postDelayed(this, 1000);

                if(isRunning) {
                    seconds++;
                }
            }
        });
    }

    public void onClickPause(View view) {
        isRunning = false;
    }

    public void onClickStart(View view) {
        isRunning = true;
    }

    public void onClickReset(View view) {
        isRunning = false;
        seconds = 0;
    }
}