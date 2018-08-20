package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.entities.UserQuestion;
import com.example.galv.exame.handlers.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamMainFragment extends Fragment {
    private static final int                NOT_ANS         = -1;
    private static final int                ANS_WRONG       = 0;
    private static final int                ANS_CORRECT     = 1;

    private FragmentManager                 fragmentManager;
    private Map<Integer, QuestionFragment>  questionFragmentsMap;
    private Map<Integer, Button>            buttonsMap;
    private HorizontalScrollView            buttonsScrollView;
    private Exam                            exam;
    private Map<Integer, Integer>           isQuestionAnswered;
    private int                             currentQuestionNum;
    private TextView tvTimeForTimer;

    private float grade;
    private float questionScore;

    private ExamActivity myActivity;

    public ExamMainFragment() {
    }

    public void setExam(Exam exam) {
        this.exam = exam;
        grade = 0;
        questionScore = 100 / exam.getQuestions().size();
    }

    public void setMyActivity(ExamActivity myActivity) {
        this.myActivity = myActivity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionFragmentsMap    = new HashMap<>();
        buttonsMap              = new HashMap<>();
        isQuestionAnswered      = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exam_main, container, false);
        tvTimeForTimer =  rootView.findViewById(R.id.tvTimer1);
        buttonsScrollView = (HorizontalScrollView) rootView.findViewById(R.id.exam_main_questions_horizontal_button_scroll);

        init();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        currentQuestionNum = 1;
        switchToQustion(currentQuestionNum);
    }

    public void init(){
        List<Question> questions = this.exam.getQuestions();
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        int i=1;
        for (Question q : questions){
            QuestionFragment f = new QuestionFragment();
            f.setQuestion(q);
            f.setContext(this);
            questionFragmentsMap.put(i, f);
            isQuestionAnswered.put(i,NOT_ANS);
            Button b = new Button(getActivity());
            b.setText(Integer.toString(i));
            b.setId(i);
            b.setBackgroundResource(R.drawable.ic_action_question_btn_regular);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchToQustion(view.getId());
                }
            });
            buttonsMap.put(i, b);
            linearLayout.addView(b);
            i++;
        }
        buttonsScrollView.addView(linearLayout);
    }


    public void switchToQustion(int index){
        Logger.ReportInfo("switchToQustion()", "Switched to :" + index);
        QuestionFragment qf = questionFragmentsMap.get(index);
        if (qf == null)
            return;
        if (isQuestionAnswered.get(currentQuestionNum) == NOT_ANS)
            buttonsMap.get(currentQuestionNum).setBackgroundResource(R.drawable.ic_action_question_btn_regular);
        if (isQuestionAnswered.get(index) == NOT_ANS)
            buttonsMap.get(index).setBackgroundResource(R.drawable.ic_action_question_btn_pressed);
        currentQuestionNum = index;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.exam_main_question_frame, qf);
        transaction.commit();
    }

    public void updateText(String timeLeftText) {
        tvTimeForTimer.setText(timeLeftText);
    }

    public void updateAnswer(String answer, boolean isCorrect){
        buttonsMap.get(currentQuestionNum).setBackgroundResource(isCorrect ?
                                                                R.drawable.ic_action_answer_correct_btn
                                                                : R.drawable.ic_action_answer_wrong_btn);
        isQuestionAnswered.put(currentQuestionNum, isCorrect ? ANS_CORRECT : ANS_WRONG);
        this.grade += isCorrect ? questionScore : 0;
        myActivity.updateAnswer(new UserQuestion(currentQuestionNum, answer));
        myActivity.updateGrade(this.grade);
        checkIfDone();
    }

    public void checkIfDone(){
        if(isQuestionAnswered.containsValue(NOT_ANS))
            return;
        myActivity.allAnswered(false);
    }
}
