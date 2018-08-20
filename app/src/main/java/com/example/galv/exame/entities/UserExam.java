package com.example.galv.exame.entities;

import java.util.ArrayList;
import java.util.List;

public class UserExam {
    private String examKey;
    private List<UserQuestion> userQuestions;

    private float grade;


    public UserExam() {
        this.examKey = "";
        this.userQuestions = new ArrayList<>();
    }

    public UserExam(String examKey) {
        this.examKey = examKey;
        this.userQuestions = new ArrayList<>();
    }

    public String getExamKey() {
        return examKey;
    }

    public void setExamKey(String examKey) {
        this.examKey = examKey;
    }

    public List<UserQuestion> getUserQuestions() {
        return userQuestions;
    }

    public void setUserQuestions(List<UserQuestion> userQuestions) {
        this.userQuestions = userQuestions;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void addQuestion(UserQuestion userQuestion){
        userQuestions.add(userQuestion);
    }

}
