package com.treasuresconquests.engine;

public class PlayerWallet {

    private int amountOfCash = 2000;

    public void gainMoney(int money) {
        setAmountOfCash(getAmountOfCash() + money);
    }

    public void setAmountOfCash(int amountOfCash) {
        this.amountOfCash = amountOfCash;
    }

    public int getAmountOfCash() {
        return amountOfCash;
    }

    public void decreaseCashBalance(int cost) {
        setAmountOfCash(getAmountOfCash() - cost);
    }

    public void makePurchase(int amountOfCash) {
        this.amountOfCash -= amountOfCash;
    }


}