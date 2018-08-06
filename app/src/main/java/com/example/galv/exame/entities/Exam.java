package com.example.galv.exame.entities;

import java.util.ArrayList;
import java.util.List;

public class Exam {
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

    @Override
    public String toString() {
        return "Exam{" +
                "title='" + title + '\'' +
                ", timeForTimer=" + timeForTimer +
                ", questions=" + questions +
                '}';
    }
}
