package com.treasuresconquests.engine;

public class PlayerHealthBank {

    private final int maxHealth = 100;
    private int health = maxHealth;

    public void receiveDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increaseHealth(int addedHealth) {
        setHealth(Math.min(getHealth() + addedHealth, maxHealth));
    }

}