package com.example.galv.exame.handlers;

import com.example.galv.exame.entities.Answer;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.entities.Tag;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseHandler {

    private DatabaseReference mFirebaseDatabase;

    public DatabaseHandler (){
        InitDbConnection();
    }

    private void InitDbConnection(){
        Logger.ReportError("DatabaseHandler:InitDbConnection", "InitDbConnection()");
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void Save(Object entity){
        String entName = entity.getClass().getSimpleName();
        Logger.ReportError("DatabaseHandler:Save", "before save entity: " + entName);

        try {
            mFirebaseDatabase.child(entName).setValue(entity);
        }catch (Exception e){
            Logger.ReportError("DatabaseHandler:Save", "Exception: " + e.getMessage());
        }
        Logger.ReportError("DatabaseHandler:Save", "after save");
    }

    public void SaveExam(Exam exam){
        mFirebaseDatabase.child("exam").setValue(exam);
    }

    public void SaveQuestion (Question question){

    }

    public void SaveAnswer (Answer answer){
        String key = mFirebaseDatabase.child("answer").push().getKey();
    }

}
