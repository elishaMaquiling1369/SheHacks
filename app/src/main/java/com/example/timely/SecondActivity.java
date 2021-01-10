package com.example.timely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private NumberPicker numberPickerWorkMin;
    private NumberPicker numberPickerWorkSec;
    private NumberPicker numberPickerBreakMin;
    private NumberPicker numberPickerBreakSec;
    Button buttonSetOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //sets max and min values for number picker of Minute Work Interval
        numberPickerWorkMin = findViewById(R.id.numberPickerWorkMin);
        numberPickerWorkMin.setMaxValue(3);
        numberPickerWorkMin.setMinValue(0);

        //sets number picker values to 0,15,25,30
        String[] workMinValues;
        workMinValues = new String[] {"0","15","25","30"};
        numberPickerWorkMin.setDisplayedValues(workMinValues);

        //Retrieves values for Work interval: minute value picker
        numberPickerWorkMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int n, int n1) {
                int numWorkMinuteValue = numberPickerWorkMin.getValue();
                Log.d("Minute Value", numWorkMinuteValue + "");
                Log.d("Picker Value", workMinValues[numWorkMinuteValue]);
            }
        });

        //sets max and min values for Work interval Seconds
        numberPickerWorkSec = findViewById(R.id.numberPickerWorkSec);
        numberPickerWorkSec.setMaxValue(59);
        numberPickerWorkSec.setMinValue(0);

        //retrieves second values for work interval
        numberPickerWorkSec.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int n, int n1) {
                int numWorkSecondValue = numberPickerWorkSec.getValue();
                Log.d("Second value", numWorkSecondValue + "");

            }
            });

        //sets max and min values for Minute Break Interval
        numberPickerBreakMin=findViewById(R.id.numberPickerBreakMin);
        numberPickerBreakMin.setMaxValue(3);
        numberPickerBreakMin.setMinValue(0);

        //sets break interval minute values to 0,5,10,15
        String[] breakMinValues;
        breakMinValues = new String[] {"0","5","10","15"};
        numberPickerBreakMin.setDisplayedValues(breakMinValues);

        //Retrieves values for break interval,
        numberPickerBreakMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
                   {
                       @Override
                       public void onValueChange(NumberPicker numberPicker, int n, int n1) {
                           int numBreakMinValue = numberPickerBreakMin.getValue();
                           Log.d("Minute value", numBreakMinValue + "");
                           Log.d("Picker value", breakMinValues[numBreakMinValue]);
                   }
                   });

        // sets max and min values for break interval seconds
        numberPickerBreakSec=findViewById(R.id.numberPickerBreakSec);
        numberPickerBreakSec.setMaxValue(59);
        numberPickerBreakSec.setMinValue(0);

        //retrieve values for break second interval
        numberPickerBreakSec.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
                 {
                     @Override
                     public void onValueChange(NumberPicker numberPicker, int n, int n1) {
                         int numBreakSecValue = numberPickerBreakSec.getValue();
                         Log.d("Second value", numBreakSecValue + "");
                     }
                 });

        //when OK button clicked, starts another activity
        buttonSetOk = (Button)findViewById(R.id.buttonSetOk);
        buttonSetOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openThirdActivity();
            }

        });

        }


    public void openThirdActivity() {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

}

