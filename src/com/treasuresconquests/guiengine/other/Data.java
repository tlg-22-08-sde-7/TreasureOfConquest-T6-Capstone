package com.treasuresconquests.guiengine.other;

import java.util.Vector;

public class Data {
    private String question;
    private Vector<String> items;

    public Data(String question, Vector<String> items){
        this.question = question;
        this.items = items;
    }

    public String getQuestion() {
        return question;
    }

    public Vector<String> getItems() {
        return items;
    }

    public void setItems(Vector<String> items) {
        this.items = items;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}