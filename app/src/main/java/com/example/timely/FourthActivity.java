package com.example.timely;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;

import android.widget.TextView;
import android.widget.Button;
import android.os.CountDownTimer;

import java.util.Locale;


public class FourthActivity extends AppCompatActivity {
    WebView gifWebView;

    private static final long BREAK_START_TIME = 300000;
    private TextView breakCountDown;
    private CountDownTimer breakTimer;
    private boolean breakTimerRunning;
    private Button breakStartButton;
    private Button breakStopButton;
    private long breakTimeLeftInMillis = BREAK_START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        breakCountDown = findViewById(R.id.breakCountDown);
        breakStopButton=findViewById(R.id.breakStopButton);
        breakStartButton = findViewById(R.id.breakStartButton);

        //integrating gifs onto app using WebView
        gifWebView = findViewById(R.id.gifWebView);

        WebSettings webSettings = gifWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String file = "file:android_asset/chill.gif";
        gifWebView.loadUrl(file);

        //Begins and/or resets timer
        breakStartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(breakTimerRunning) {
                    pauseTimer();
                }
                else
                {
                    startTimer();
                }
            }

        });
        breakStopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                resetTimer();
                openSecondActivity();
            }
        });
        updateBreakCountDownText();
    }


        //starts timer
        private void startTimer() {
            breakTimer=new CountDownTimer(breakTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilDone)
                {
                    breakTimeLeftInMillis=millisUntilDone;
                    updateBreakCountDownText();
                }
                @Override
                public void onFinish() {
                    breakTimerRunning = false;
                    breakStartButton.setText("Start");
                    breakStartButton.setVisibility(View.INVISIBLE);
                    breakStopButton.setText("Go Back");
                    breakStopButton.setVisibility(View.VISIBLE);

                }
            }.start();

            breakTimerRunning=true;
            breakStartButton.setText("Pause");
            breakStopButton.setVisibility(View.INVISIBLE);

        }

        //pauses timer
        private void pauseTimer() {
            breakTimer.cancel();
            breakTimerRunning=false;
            breakStartButton.setText("Start");
            breakStopButton.setVisibility(View.VISIBLE);

        }

        //resets timer
        private void resetTimer() {
            breakTimeLeftInMillis = BREAK_START_TIME;
            updateBreakCountDownText();
            breakStopButton.setText("Go Back");
            breakStartButton.setVisibility(View.INVISIBLE);
            breakStopButton.setVisibility(View.VISIBLE);

        }


        //updates countdown time
        private void updateBreakCountDownText () {
            int breakMinutes = (int) (breakTimeLeftInMillis / 1000) / 60;
            int breakSeconds = (int) (breakTimeLeftInMillis / 1000) % 60;

            //create String so can be formatted to look like a clock
            String formattedTimeLeft = String.format(Locale.getDefault(), "%02d:%02d", breakMinutes, breakSeconds);
            breakCountDown.setText(formattedTimeLeft);
        }

    //opens to new Activity
    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}