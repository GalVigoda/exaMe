package com.example.galv.exame.entities;

import java.util.ArrayList;
import java.util.List;

public class Question {
    public static final int QUESTION_TYPE_AMERICAN = 1;
    public static final int QUESTION_TYPE_MISSING_WORDS = 2;
    public static final int QUESTION_TYPE_SORTING = 3;

    private String text;
    private int type;
    private int questionNumber;
    private List<Answer> answers;
    private List<Tag> tags;
    private String correctAnswer;

    public Question() {
    }

    public Question(String text, int type, int questionNumber) {
        this.text = text;
        this.type = type;
        this.questionNumber = questionNumber;
        this.answers = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.correctAnswer = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag (Tag tag){
        this.tags.add(tag);
    }

    public void addAnswer (Answer answer){
        this.answers.add(answer);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", type=" + type +
                ", questionNumber=" + questionNumber +
                ", answers=" + answers +
                ", tags=" + tags +
                '}';
    }
}
