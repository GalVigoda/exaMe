package com.example.galv.exame.activities;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galv.exame.R;
import com.example.galv.exame.components.AnswerButton;
import com.example.galv.exame.entities.Answer;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.entities.UserQuestion;

import java.util.HashMap;
import java.util.Map;

public class QuestionFragment extends Fragment {
    private static final int NO_ANSREW_SELECTED = -1;

    private Question mQuestion;
    private String answer;
    private boolean answered;
    private boolean isCorrectAnswer;
    private int numOfAnswers;
    private int lastSelectedAnswer;
    private Map<Integer, AnswerButton> answerButtonMap;
    private Map<Integer, TextView> answerTextMap;

    private TextView text_view_qeustoin_text;
    private Button approveButton;
    private UserQuestion userQuestion;

    private TableLayout tableLayout;

    private int defaultResource;
    private int selectedResource;
    private int approvedCorrectResource;
    private int approvedWrongResource;


    public QuestionFragment() {
        // Required empty public constructor
        lastSelectedAnswer      = NO_ANSREW_SELECTED;
        defaultResource         = R.drawable.ic_action_answer_default_btn;
        selectedResource        = R.drawable.ic_action_answer_pressed_btn;
        approvedCorrectResource = R.drawable.ic_action_answer_correct_btn;
        approvedWrongResource   = R.drawable.ic_action_answer_wrong_btn;
        answerButtonMap = new HashMap<>();
        answerTextMap = new HashMap<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_question, container, false);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildComponents(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        initLastState();
    }

    public boolean isAnswered(){
        return answered;
    }

    private boolean isCorrectAnswer(){
        return isCorrectAnswer;
    }

    private void buildComponents(View view){
        text_view_qeustoin_text = view.findViewById(R.id.text_view_question_text);
        text_view_qeustoin_text.setText(mQuestion.getText());

        numOfAnswers = mQuestion.getAnswers().size();

        buildAnswersComponents(view);
    }

    private void buildAnswersComponents(View view){
        tableLayout = (TableLayout) view.findViewById(R.id.table_layout_id);
        for (int i = 0; i<numOfAnswers; i++){
            TableRow row = buildAnswerRow(i, mQuestion.getAnswers().get(i));
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        approveButton = new Button(getActivity());
        approveButton.setText(getString(R.string.approve_answer_btn));
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approveAnswer();
            }
        });
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 2;
        approveButton.setLayoutParams(params);
        TableRow approveBtnRow = new TableRow(getActivity());
        approveBtnRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        approveBtnRow.addView(approveButton);
        tableLayout.addView(approveBtnRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

    }

    private TableRow buildAnswerRow(int index, Answer answer){
        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        AnswerButton button = new AnswerButton(getActivity());
        button.setResources(defaultResource, selectedResource, approvedCorrectResource, approvedWrongResource);
        button.setId(index);
        button.setText(Integer.toString(answer.getCode()));
        button.setHeight(50);
        button.setWidth(50);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked((AnswerButton) view);
            }
        });
        answerButtonMap.put(index, button);
        row.addView(button);

        TextView answerText = new TextView(getActivity());
        answerText.setText(answer.getAnswerText());
        answerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        answerTextMap.put(index, answerText);
        row.addView(answerText);

        return row;
    }

    public void setQuestion(Question question) {
        this.mQuestion = question;
    }

    private void buttonClicked(AnswerButton button){
        button.changeState();
        if (button.isSelected())
            setAnswerSelected(button.getId());
        else
            setAnswerUnSelected(button.getId());
    }

    private void setAnswerSelected(int index){
        if (lastSelectedAnswer != NO_ANSREW_SELECTED && lastSelectedAnswer != index)
            setAnswerUnSelected(lastSelectedAnswer);
        lastSelectedAnswer = index;

        if (!answerButtonMap.get(index).isSelected())
            answerButtonMap.get(index).changeState();

        Answer a = mQuestion.getAnswers().get(index);
        updateAnswer(a);
    }

    private void setAnswerUnSelected(int index){
        AnswerButton button = answerButtonMap.get(index);
        if (button.isSelected())
            button.changeState();

        Answer a = mQuestion.getAnswers().get(index);
        lastSelectedAnswer = NO_ANSREW_SELECTED;
        checkNeedToResetAnswer();
    }

    private void checkAnswer(){
        isCorrectAnswer = this.answer == mQuestion.getCorrectAnswer();
    }

    public void updateAnswer(Answer answer){
        this.answer = Integer.toString(answer.getCode());
        answered = true;
        checkAnswer();

        Toast.makeText(getActivity(), "Answer: " + this.answer + " , correct = " + Boolean.toString(isCorrectAnswer()), Toast.LENGTH_LONG);
    }

    public void checkNeedToResetAnswer(){
        if (lastSelectedAnswer != NO_ANSREW_SELECTED)
            return;

        this.answer = "";
        isCorrectAnswer = false;
    }

    private void initLastState(){
        if (answered || lastSelectedAnswer != NO_ANSREW_SELECTED)
            setAnswerSelected(lastSelectedAnswer);
        if (answered)
            approveAnswer();
    }

    public void approveAnswer(){
        if (!answered)
            return;

        answerButtonMap.get(lastSelectedAnswer).setApproved(isCorrectAnswer);

        for (AnswerButton ab :answerButtonMap.values())
            ab.setEnabled(false);

        approveButton.setEnabled(false);
        showExplanation();
        showCorrectAnswer();
    }

    public void showExplanation(){
        TableRow explanaionRow = new TableRow(getActivity());
        explanaionRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView explanaionTextView = new TextView(getActivity());
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 2;
        explanaionTextView.setLayoutParams(params);
        explanaionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        explanaionTextView.setText(mQuestion.getExplanation());
        explanaionRow.addView(explanaionTextView);
        tableLayout.addView(explanaionRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    public void showCorrectAnswer(){
        if (mQuestion.getType() != Question.QUESTION_TYPE_AMERICAN)
            return;

        String correct = mQuestion.getCorrectAnswer();
        int index = Integer.parseInt(correct)-1;
        TextView ans = answerTextMap.get(index);
        ans.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        ans.setTypeface(ans.getTypeface(), Typeface.BOLD_ITALIC);
    }
}
