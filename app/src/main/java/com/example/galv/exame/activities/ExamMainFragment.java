package com.example.galv.exame.activities;


import android.graphics.Color;
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
import android.widget.Toast;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.handlers.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamMainFragment extends Fragment {

    private FragmentManager                 fragmentManager;
    private Map<Integer, QuestionFragment>  questionFragmentsMap;
    private Map<Integer, Button>            buttonsMap;
    private HorizontalScrollView            buttonsScrollView;
    private Exam                            exam;

    private int                             currentQuestionNum;

    public ExamMainFragment() {
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionFragmentsMap    = new HashMap<>();
        buttonsMap              = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exam_main, container, false);

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
            questionFragmentsMap.put(i, f);

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
        buttonsMap.get(currentQuestionNum).setBackgroundResource(R.drawable.ic_action_question_btn_regular);
        buttonsMap.get(index).setBackgroundResource(R.drawable.ic_action_question_btn_pressed);
        currentQuestionNum = index;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.exam_main_question_frame, qf);
        transaction.commit();
    }
}
