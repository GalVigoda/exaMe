package com.example.galv.exame.entities;

import java.io.Serializable;

public class Answer implements Serializable {

    private int code;
    private String answerText;
    private int sort;

    public Answer() {
    }

    public Answer(int code, String answerText, int sort) {
        this.code = code;
        this.answerText = answerText;
        this.sort = sort;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "code=" + code +
                ", answerText='" + answerText + '\'' +
                ", sort=" + sort +
                '}';
    }
}
