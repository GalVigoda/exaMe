package com.example.galv.exame.handlers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.galv.exame.activities.CommonBaseActivity;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.UserExam;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExamsHandler {
    private static final int            PASS_GARADE = 60;
    private CommonBaseActivity          context;
    private String                      mUId;
    private DatabaseHandler             mDatabaseHandler;
    private Set<String>                 newExams;
    private Map<String, UserExam>       userExams;
    private Map<String, Exam>           examsData;
    private DatabaseReference           mFirebaseDatabase;

    public ExamsHandler(String uId,CommonBaseActivity context) {
        this.mUId = uId;
        this.context = context;
        this.newExams = new HashSet<>();
        this.userExams = new HashMap<>();
        this.mDatabaseHandler = new DatabaseHandler();
        this.examsData = new HashMap<>();
        Init();
    }

    private void Init(){
        Logger.ReportError("DatabaseHandler:InitDbConnection", "InitDbConnection()");
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        GetUserNewExamsFromDb();
        GetUserOldExamsFromDb();

        Query newExamsQuery = mFirebaseDatabase.child("userNewExams").child(mUId);
        newExamsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserExam userExam = dataSnapshot.getValue(UserExam.class);
                Logger.ReportInfo("ExamsHandler", "new user exam added, id: " + userExam.getExamKey());

                GetExamByKey(userExam.getExamKey());
                newExams.add(userExam.getExamKey());
                context.UpdateUi(UpdateFor.UPDATE_FOR_NEW_EXAMS);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query oldExamsQuery = mFirebaseDatabase.child("userExams").child(mUId);
        oldExamsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserExam userExam = dataSnapshot.getValue(UserExam.class);
                Logger.ReportInfo("ExamsHandler", "user exam added, id: " + userExam.getExamKey());
                userExams.put(userExam.getExamKey(), userExam);
                context.UpdateUi(UpdateFor.UPDATE_FOR_OLD_EXAMS);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserExam userExam = dataSnapshot.getValue(UserExam.class);
                Logger.ReportInfo("ExamsHandler", "user exam changed, id: " + userExam.getExamKey());
                userExams.put(userExam.getExamKey(), userExam);
                context.UpdateUi(UpdateFor.UPDATE_FOR_OLD_EXAMS);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void GetUserNewExamsFromDb(){
        Query userNewExams = mFirebaseDatabase.child("userNewExams").child(mUId);
        userNewExams.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Logger.ReportInfo("GetUserNewExamsFromDb", "got user new exams");
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    UserExam userExam = child.getValue(UserExam.class);
                    newExams.add(userExam.getExamKey());
                    GetExamByKey(userExam.getExamKey());
                }
                context.UpdateUi(UpdateFor.UPDATE_FOR_NEW_EXAMS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Logger.ReportInfo("GetUserNewExamsFromDb", "got error for user new exams");
            }
        });
    }

    public void GetUserOldExamsFromDb(){
        Query userNewExams = mFirebaseDatabase.child("userExams").child(mUId);
        userNewExams.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Logger.ReportInfo("GetUserOldExamsFromDb", "got user exams");
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    UserExam userExam = child.getValue(UserExam.class);
                    userExams.put(userExam.getExamKey(), userExam);
                    GetExamByKey(userExam.getExamKey());
                }
                context.UpdateUi(UpdateFor.UPDATE_FOR_OLD_EXAMS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void GetExamByKey(String examKey){
        Query examQuery = mFirebaseDatabase.child("exam").child(examKey);
        examQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Logger.ReportInfo("ExamsHandler.GetExamByKey", "got exam data, exam key: " + dataSnapshot.getKey());
                Exam exam = dataSnapshot.getValue(Exam.class);
                AddExamData(dataSnapshot.getKey(), exam);
                context.UpdateUi(UpdateFor.UPDATE_FOR_ALL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //get user new exams
    public List<Exam> GetUserNewExams(){
        List<Exam> ret = new ArrayList<>();
        for (String s : newExams){
            Exam e = examsData.get(s);
            if (e!=null)
                ret.add(e);
        }
        return ret;
    }

    public Exam GetUserNewExamByKey(String key){
        return examsData.get(key);
    }

    //get user old exams
    public List<Exam> GetUserOldExams(){
        List<UserExam> list = new ArrayList<>(userExams.values());
        List<Exam> ret = new ArrayList<>();
        for (UserExam e: list){
            Exam exam = examsData.get(e.getExamKey());
            if (exam != null)
                ret.add(exam);
        }
        return ret;
    }

    //save user exam
    public void SaveUserExam(final UserExam exam){
        Query q = mFirebaseDatabase.child("userExams").child(mUId).orderByChild("examKey").equalTo(exam.getExamKey());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key="";
                if (dataSnapshot.exists()){
                    key = dataSnapshot.getChildren().iterator().next().getKey();
                    Logger.ReportInfo("SaveUserExam", "user exam exists - updating (key=" + key + ")");
                }else {
                    key = mFirebaseDatabase.child("userExams").child(mUId).push().getKey();
                }
                mFirebaseDatabase.child("userExams").child(mUId).child(key).setValue(exam);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void AddExamData(String examKey, Exam exam){
        this.examsData.put(examKey, exam);
    }

    public List<Exam> GetNewExamsDetails (){
        List<Exam> list = new ArrayList<>();
        for(String key: newExams){
            Exam exam = examsData.get(key);
            if (exam != null)
                list.add(exam);
        }
        return list;
    }

    public String SaveExam(Exam exam){
        String key = mFirebaseDatabase.child("exam").push().getKey();
        exam.setKey(key);
        Map<String, Object> map = new HashMap<>();
        map.put("/exam/" + key, exam);
        mFirebaseDatabase.updateChildren(map);
        return key;
    }

    public Exam GetExamDetailsByKey(String key){
        return examsData.get(key);
    }

    public void SaveUserNewExam(final UserExam exam){
        mFirebaseDatabase.child("userNewExams").child(mUId).push().setValue(exam);
    }

    public int getNumOfPassed(){
        int count = 0;
        for (UserExam e: userExams.values()){
            if(e.getGrade() >= PASS_GARADE)
                count++;
        }
        return count;
    }

    public int getNumOfFailed(){
        int count = 0;
        for (UserExam e: userExams.values()){
            if(e.getGrade() < PASS_GARADE)
                count++;
        }
        return count;
    }

    public float getNumOfAverage(){
        float ans = 0;
        for (UserExam e: userExams.values()){
            ans += e.getGrade();
        }
        return ans == 0 || userExams.size() == 0 ? 0 : ans/userExams.size();
    }
}
