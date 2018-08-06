package com.example.galv.exame.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.galv.exame.R;

public class MainActivity extends AppCompatActivity {


    Button homeActivity;
    Button newExamActivity;
    Button finishedActivity;
    Button statisticsActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeActivity = (Button) findViewById(R.id.homeButton);

    }
}
