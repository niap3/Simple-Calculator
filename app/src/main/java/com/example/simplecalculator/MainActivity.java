package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV, solutionTV;
    MaterialButton buttonC, buttonAC;
    MaterialButton buttonOpenBrack, buttoncloseBrack;
    MaterialButton bttonAdd, buttonMultiply, buttonDivide, buttonSub, buttomEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.query_tv);
        buttonC = findViewById(R.id.buttons_C);

    }

    @Override
    public void onClick(View view) {

    }
}