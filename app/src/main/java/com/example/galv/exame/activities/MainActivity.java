package com.example.galv.exame.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.handlers.AuthenticationHandler;
import com.example.galv.exame.handlers.DatabaseHandler;
import com.example.galv.exame.handlers.Logger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHandler = new DatabaseHandler();
    }
}
