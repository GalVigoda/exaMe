package com.example.galv.exame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Answer;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.entities.Tag;
import com.example.galv.exame.handlers.UpdateFor;

public class ExamActivity extends CommonBaseActivity{

    private FragmentManager mainFragmentManager;
    private ExamMainFragment examMainFragment;
    private ExamOpeningFragment examOpeningFragment;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         Intent i = getIntent();
         Exam exam = (Exam)i.getSerializableExtra("EXAM_DETAILS");
        //setContentView(R.layout.new_exam_fragment);
        setContentView(R.layout.activity_exam);
         mainFragmentManager = getSupportFragmentManager();

        examMainFragment = new ExamMainFragment();
        examMainFragment.setExam(exam);

        examOpeningFragment = new ExamOpeningFragment();
        examOpeningFragment.setExam(exam);

        showExamOpeningFragment();
    }


    public void showExamOpeningFragment(){
        FragmentTransaction transaction = mainFragmentManager.beginTransaction();
        transaction.replace(R.id.exam_activity_main_fragment, examOpeningFragment);
        transaction.commit();
    }

    public void showMainFragment(){
        FragmentTransaction transaction = mainFragmentManager.beginTransaction();
        transaction.replace(R.id.exam_activity_main_fragment, examMainFragment);
        transaction.commit();
    }

    @Override
    public void UpdateUi(UpdateFor updateFor){
         switch (updateFor){
             case UPDATE_FOR_GOTO_EXAM_OPENING_FRAGMENT:
                 showExamOpeningFragment();
                 break;
             case UPDATE_FOR_GOTO_EXAM_MAIN_FRAGMENT:
                 showMainFragment();
                 break;
             case UPDATE_FOR_FINISH:
                 finish();
                 break;
         }
    }

    private Exam getExam (){
        Exam e1 = new Exam("Exam no.1 - Colors", 20);
        Question q1 = new Question("What is the color of the Sun?", Question.QUESTION_TYPE_AMERICAN, 1);
        q1.addAnswer(new Answer(1, "Red", 1));
        q1.addAnswer(new Answer(2, "White", 2));
        q1.addAnswer(new Answer(3, "Yellow", 3));
        q1.addAnswer(new Answer(4, "Green", 4));
        q1.addTag(new Tag(5, "Colors"));
        q1.addTag(new Tag(2, "Basics"));
        q1.setCorrectAnswer("3");
        q1.setExplanation("The sun is yellow cause it is hot there");
        e1.addQuestion(q1);

        Question q2 = new Question("What is the color of the Moon?", Question.QUESTION_TYPE_AMERICAN, 2);
        q2.addAnswer(new Answer(1, "Green", 1));
        q2.addAnswer(new Answer(2, "White", 2));
        q2.addAnswer(new Answer(3, "Yellow", 3));
        q2.addAnswer(new Answer(4, "Black", 4));
        q2.addTag(new Tag(5, "Colors"));
        q2.addTag(new Tag(2, "Basics"));
        q2.setCorrectAnswer("2");
        q2.setExplanation("The Moon is white cause it is cold there");
        e1.addQuestion(q2);

        Question q3 = new Question("What is the color of the Tomato?", Question.QUESTION_TYPE_AMERICAN, 3);
        q3.addAnswer(new Answer(1, "Green", 1));
        q3.addAnswer(new Answer(2, "Red", 2));
        q3.addAnswer(new Answer(3, "Yellow", 3));
        q3.addAnswer(new Answer(4, "Black", 4));
        q3.addTag(new Tag(5, "Colors"));
        q3.addTag(new Tag(2, "Basics"));
        q3.setCorrectAnswer("2");
        q3.setExplanation("The Tomato is red cause it is shy vegetable");
        e1.addQuestion(q3);

        Question q4 = new Question("What is the color of the Mouse?", Question.QUESTION_TYPE_AMERICAN, 4);
        q4.addAnswer(new Answer(1, "Green", 1));
        q4.addAnswer(new Answer(2, "Red", 2));
        q4.addAnswer(new Answer(3, "Yellow", 3));
        q4.addAnswer(new Answer(4, "Gray", 4));
        q4.addTag(new Tag(5, "Colors"));
        q4.addTag(new Tag(2, "Basics"));
        q4.setCorrectAnswer("4");
        q4.setExplanation("The Mouse is gray cause it is pessimistic animal");
        e1.addQuestion(q4);

        Question q5 = new Question("Sort the colors above by their names", Question.QUESTION_TYPE_SORTING, 5);
        q5.addAnswer(new Answer(1, "Green", 1));
        q5.addAnswer(new Answer(2, "Red", 2));
        q5.addAnswer(new Answer(3, "Yellow", 3));
        q5.addAnswer(new Answer(4, "Gray", 4));
        q5.addTag(new Tag(5, "Colors"));
        q5.addTag(new Tag(2, "Basics"));
        q5.setCorrectAnswer("4|1|2|3");
        q5.setExplanation("GRA < GRE < R < Y");
        e1.addQuestion(q5);

        Question q6 = new Question("Sort the numbers above by their value", Question.QUESTION_TYPE_SORTING, 6);
        q6.addAnswer(new Answer(1, "100", 1));
        q6.addAnswer(new Answer(2, "10", 2));
        q6.addAnswer(new Answer(3, "500", 3));
        q6.addAnswer(new Answer(4, "1000", 4));
        q6.addTag(new Tag(5, "Colors"));
        q6.addTag(new Tag(2, "Basics"));
        q6.setCorrectAnswer("2|1|3|4");
        q6.setExplanation("10 < 100 < 500 < 1000");
        e1.addQuestion(q6);
        return e1;
    }
}
