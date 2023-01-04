package com.treasuresconquests.guiengine.other;

public class PurchaseData {
    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "PurchaseData{" +
                "item='" + item + '\'' +
                '}';
    }
}