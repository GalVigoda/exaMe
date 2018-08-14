package com.example.galv.exame.entities;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    public static final int QUESTION_TYPE_REGULAR   = 0;
    public static final int QUESTION_TYPE_AMERICAN  = 1;
    public static final int QUESTION_TYPE_SORTING   = 2;

    private String key;
    private String title;
    private int timeForTimer; //arr(?)
    private List<Question> questions;

    public Exam() {
    }

    public Exam(String title, int timeForTimer) {
        this.title = title;
        this.timeForTimer = timeForTimer;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimeForTimer() {
        return timeForTimer;
    }

    public void setTimeForTimer(int timeForTimer) {
        this.timeForTimer = timeForTimer;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "title='" + title + '\'' +
                ", timeForTimer=" + timeForTimer +
                ", questions=" + questions +
                '}';
    }
}
