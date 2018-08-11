package com.example.galv.exame.activities;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CommonBaseActivity extends AppCompatActivity {



    public String getUserId(){
        return getUser().getUid();
    }

    public FirebaseUser getUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }
}
