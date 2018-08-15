package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Answer;
import com.example.galv.exame.entities.Question;

public class QuestionFragment extends Fragment {

    private Question mQuestion;
    private String answer;
    private boolean answered;
    private boolean isCorrectAnswer;
    private int numOfAnswers;


    private TextView text_view_qeustoin_text;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_question, container, false);
        buildComponents(view);



        return view;
    }


    public boolean isAnswered(){
        return answered;
    }

    private void setAnswer(String answer){
        this.answer = answer;
        answered = true;
    }

    private boolean isCorrectAnswer(){
        return isCorrectAnswer;
    }

    private void checkAnswer(){}
    private void buildComponents(View view){
        text_view_qeustoin_text = view.findViewById(R.id.text_view_question_text);
        text_view_qeustoin_text.setText(mQuestion.getText());

        numOfAnswers = mQuestion.getAnswers().size();

        buildAnswersComponents(view);
    }

    private void buildAnswersComponents(View view){
        TableLayout tableLayout;
        tableLayout = (TableLayout) view.findViewById(R.id.table_layout_id);
        for (int i = 0; i<numOfAnswers; i++){
            TableRow row = buildAnswerRow(i, mQuestion.getAnswers().get(i));
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private TableRow buildAnswerRow(int index, Answer answer){
        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        Button button = new Button(getActivity());
        button.setId(index);
        button.setText("" + answer.getCode());
        row.addView(button);

        TextView answerText = new TextView(getActivity());
        answerText.setText(answer.getAnswerText());
        row.addView(answerText);

        return row;
    }

    public void setQuestion(Question question) {
        this.mQuestion = question;
    }
}
