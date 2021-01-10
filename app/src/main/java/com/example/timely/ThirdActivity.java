package com.example.timely;
import android.content.Intent;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;


import java.util.Locale;

public class ThirdActivity extends AppCompatActivity  {

    private static final long START_TIME_MILLIS = 50000;
    private TextView textViewCountDown;
    private Button workStartButton;
    private Button workStopButton;
    private CountDownTimer workTimer;
    private boolean workTimerRunning;
    private long timeLeftInMillis = START_TIME_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textViewCountDown=findViewById(R.id.textViewCountDown);
        workStartButton=findViewById(R.id.workStartButton);
        workStopButton=findViewById(R.id.workStopButton);


        //Begins and/or resets timer
        workStartButton.setOnClickListener(new View.OnClickListener()
             {
                 @Override
                 public void onClick(View v)
                 {
                    if(workTimerRunning) {
                        pauseTimer();
                    }
                    else
                    {
                        startTimer();
                    }
                 }

                });
        workStopButton.setOnClickListener(new View.OnClickListener()
              {
                    @Override
                  public void onClick(View v) {
                        resetTimer();
                        openFourthActivity();
                    }
               });
        updateCountDownText();
    }

    //starts timer
    private void startTimer() {
        workTimer=new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilDone)
            {
                timeLeftInMillis=millisUntilDone;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                workTimerRunning = false;
                workStartButton.setText("Start");
                workStartButton.setVisibility(View.INVISIBLE);
                workStopButton.setText("Break Time!");
                workStopButton.setVisibility(View.VISIBLE);

            }
        }.start();

        workTimerRunning=true;
        workStartButton.setText("Pause");
        workStopButton.setVisibility(View.INVISIBLE);

    }

    //pauses timer
    private void pauseTimer() {
        workTimer.cancel();
        workTimerRunning=false;
        workStartButton.setText("Start");
        workStopButton.setVisibility(View.VISIBLE);

    }

    //resets timer
    private void resetTimer() {
        timeLeftInMillis = START_TIME_MILLIS;
        updateCountDownText();
        workStopButton.setText("Break Time!");
        workStartButton.setVisibility(View.INVISIBLE);
        workStopButton.setVisibility(View.VISIBLE);

    }

    //updates countdown time
    private void updateCountDownText() {
        int workMinutes = (int) (timeLeftInMillis / 1000) / 60;
        int workSeconds = (int) (timeLeftInMillis / 1000) % 60;

        //create String so can be formatted to look like a clock
        String formattedTimeLeft = String.format(Locale.getDefault(), "%02d:%02d", workMinutes, workSeconds);
        textViewCountDown.setText(formattedTimeLeft);
    }

    //opens to new Activity (break interval window)
    public void openFourthActivity() {
        Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);
    }
}