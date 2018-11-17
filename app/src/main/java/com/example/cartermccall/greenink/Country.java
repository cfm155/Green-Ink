package com.example.cartermccall.greenink;

public class Country {

    private int pollution, money, income, energy;
    private float temperature;

    Country(int p, int m, int i, int e, int t){
        setPollution(p);
        setEnergy(e);
        setIncome(i);
        setMoney(m);
        setTemperature(t);
    }

    public int getPollution() {
        return pollution;
    }

    public void setPollution(int pollution) {
        this.pollution = pollution;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}

