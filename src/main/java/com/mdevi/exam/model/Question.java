package com.mdevi.exam.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int number;
    private String text;
    private String answer;

    public Question(int number, String text, String answer) {
        this.number = number;
        this.text = text;
        this.answer = answer;
    }

    public Question() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "number=" + number +
                ", text='" + text + '\'' +
                '}';
    }
}
