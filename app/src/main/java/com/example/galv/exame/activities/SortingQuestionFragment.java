package com.example.galv.exame.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.galv.exame.components.AnswerButton;
import com.example.galv.exame.entities.Answer;

import java.util.Stack;

public class SortingQuestionFragment extends QuestionFragment {

    private Stack<AnswerButton> buttonsStack;
    private Stack<Integer> answersStack;
    private int currIndex;

    public SortingQuestionFragment() {
        super();
        currIndex = 1;
        buttonsStack = new Stack<>();
        answersStack = new Stack<>();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (AnswerButton b: answerButtonMap.values()){
            b.setText("");
        }
    }

    @Override
    protected void buttonClicked(AnswerButton button) {
        if (button.isSelected()) {
            if (buttonsStack.peek().getId() == button.getId()) {
                buttonsStack.pop();
                button.changeState();
                button.setText("");
                currIndex--;
            }
        } else {
            buttonsStack.push(button);
            button.changeState();
            button.setText(Integer.toString(currIndex));
            currIndex++;
        }
        buildAnswer();
    }

    public void buildAnswer() {
        Stack<AnswerButton> temp = new Stack<>();
        while(!buttonsStack.empty()){
            temp.push(buttonsStack.pop());
        }
        StringBuffer sb = new StringBuffer();
        boolean isFirst = true;
        while(!temp.empty()){
            AnswerButton btn = temp.pop();
            if (!isFirst)
                sb.append("|");
            sb.append(Integer.toString(btn.getId() + 1));
            isFirst = false;
            buttonsStack.push(btn);
        }
        answer = sb.toString();
        answered = answer.length() > 0;
        checkAnswer();
    }

    protected void updateSelectedAnsBtn(){
    }

    protected void initLastState(){
        if (answered)
            approveAnswer(false);
    }
}