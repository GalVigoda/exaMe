package com.example.galv.exame.activities;

import android.os.Bundle;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Answer;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.entities.Tag;
import com.example.galv.exame.entities.UserExam;
import com.example.galv.exame.entities.UserQuestion;
import com.example.galv.exame.handlers.DatabaseHandler;
import com.example.galv.exame.handlers.ExamsHandler;
import com.example.galv.exame.handlers.Logger;
import com.example.galv.exame.handlers.UpdateFor;

public class TestingsActivity extends CommonBaseActivity {
    DatabaseHandler mDatabaseHandler;
    ExamsHandler mExamsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testings);

        mExamsHandler = new ExamsHandler(getUserId(), this);

        Exam e = new Exam("Test Exam 3", 20);
        Question q1 = new Question("How are you", 1, 1);
        Answer a1 = new Answer(1, "Fine", 0);
        Answer a2 = new Answer(2, "Not Fine", 0);
        Answer a3 = new Answer(3, "Dont know", 0);
        Tag t1 = new Tag(5, "Electric");
        Tag t2 = new Tag(2, "English");
        q1.addAnswer(a1);
        q1.addAnswer(a2);
        q1.addAnswer(a3);
        q1.addTag(t1);
        q1.addTag(t2);
        e.addQuestion(q1);
        mExamsHandler.SaveExam(e);

        UserExam userExam = new UserExam("-LJo9S4de4-rljoDkG7c");
        UserQuestion userQuestion1 = new UserQuestion(1,"Hey1Hey11111");
        UserQuestion userQuestion2 = new UserQuestion(2,"Hey2Hey2");
        UserQuestion userQuestion3 = new UserQuestion(3,"Hey3Hey3");
        UserQuestion userQuestion4 = new UserQuestion(4,"Hey4");
        userExam.addQuestion(userQuestion1);
        userExam.addQuestion(userQuestion2);
        userExam.addQuestion(userQuestion3);
        userExam.addQuestion(userQuestion4);

        mExamsHandler.SaveUserExam(userExam);


    }

    @Override
    public void UpdateUi(UpdateFor updateFor){
        Logger.ReportError("TestingsActivity.UpdateUi:", "Got Update for: " + updateFor.name() );

    }
}
