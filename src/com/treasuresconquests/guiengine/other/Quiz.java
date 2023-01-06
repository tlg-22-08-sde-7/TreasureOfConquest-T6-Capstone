package com.treasuresconquests.guiengine.other;

import com.treasuresconquests.engine.WorldMap;

import java.util.Vector;

public class Quiz extends Data{
    private String answer;
    private int moneyValue;
    private WorldMap.Countries.Attraction.Treasures treasureValue;

    public Quiz(String question, Vector<String> items,
                String answer, int moneyValue,
                WorldMap.Countries.Attraction.Treasures treasureValue) {
        super(question, items);
        this.answer = answer;
        this.moneyValue = moneyValue;
        this.treasureValue = treasureValue;
    }

    public Quiz(RiddlesTreasures riddles) {
        super(riddles.getRiddle().getText(),
                Utilities.convertListToVector(riddles.getRiddle().getOptions()));
        this.answer = riddles.getRiddle().getAnswer();
        this.moneyValue = riddles.getTreasure().getValue();
        this.treasureValue = riddles.getTreasure();
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMoneyValue(int moneyValue) {
        this.moneyValue = moneyValue;
    }

    public WorldMap.Countries.Attraction.Treasures getTreasureValue() {
        return treasureValue;
    }

    public void setTreasureValue(WorldMap.Countries.Attraction.Treasures treasureValue) {
        this.treasureValue = treasureValue;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "question='" + getQuestion() + '\'' +
                ", answer=" + getAnswer() +
                ", moneyValue=" + moneyValue +
                ", treasureValue=" + treasureValue.getName() +
                '}';
    }
}