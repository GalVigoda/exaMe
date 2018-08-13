package com.example.galv.exame.entities;

public class UserQuestion {
    private int questionCode;
    private String answer;

    public UserQuestion() {
        this.questionCode = 0;
        this.answer = "";
    }

    public UserQuestion(int questionCode, String answer) {
        this.questionCode = questionCode;
        this.answer = answer;
    }

    public int getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(int questionCode) {
        this.questionCode = questionCode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
