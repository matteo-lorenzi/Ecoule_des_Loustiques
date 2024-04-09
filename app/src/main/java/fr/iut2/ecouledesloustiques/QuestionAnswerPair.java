package fr.iut2.ecouledesloustiques;

import java.util.ArrayList;

// QuestionAnswerPair.java

public class QuestionAnswerPair {
    private String question;
    private ArrayList<String> answers;
    private int correctAnswerIndex;

    public QuestionAnswerPair(String question, ArrayList<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}


