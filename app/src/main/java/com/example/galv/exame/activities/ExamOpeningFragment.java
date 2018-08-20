package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.handlers.UpdateFor;


public class ExamOpeningFragment extends Fragment {

    private Exam exam;
    private Button backButton;
    private Button startButton;
    private TextView tvTitle;
    private TextView tvTime;

    public ExamOpeningFragment() {
        // Required empty public constructor
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_exam_opening, container, false);
        startButton = rootView.findViewById(R.id.exam_opening_fragment_start_button);
        backButton = rootView.findViewById(R.id.exam_opening_fragment_back_button);
        tvTitle = rootView.findViewById(R.id.tvTextTitle);
        tvTime= rootView.findViewById(R.id.tvTime);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CommonBaseActivity)getActivity()).UpdateUi(UpdateFor.UPDATE_FOR_GOTO_EXAM_MAIN_FRAGMENT);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CommonBaseActivity)getActivity()).UpdateUi(UpdateFor.UPDATE_FOR_FINISH);
            }
        });

        setIt();
        return rootView;
    }

    private void setIt() {
        tvTitle.setText(exam.getTitle());
        String TimeForTest=exam.getTimeForTimer()+" Min";
        tvTime.setText(TimeForTest);

    }

}
