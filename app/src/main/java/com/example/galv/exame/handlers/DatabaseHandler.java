package com.example.galv.exame.handlers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseHandler {

    private DatabaseReference mFirebaseDatabase;

    public DatabaseHandler (){
        InitDbConnection();
    }

    private void InitDbConnection(){
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void Save(Object entity){
        String entName = entity.getClass().getName();
        mFirebaseDatabase.child(entName).setValue(entity);
    }
}
