package com.example.cartermccall.greenink;

public class Policy {

    private int incomeOutput, energyOutput, pollutionOutput, timeToEffect, cost;
    private boolean isImplemented;

    public Policy(int e, int p, int i, int t, int cost) {
        this.energyOutput = e;
        this.pollutionOutput = p;
        this.incomeOutput = i;
        this.timeToEffect = t;
        this.cost = cost;
        isImplemented = false;
    }

    public int getIncomeOutput() {
        return incomeOutput;
    }

    public int getEnergyOutput() {
        return energyOutput;
    }

    public int getPollutionOutput() {
        return pollutionOutput;
    }

    public int getTimeToEffect() {
        return timeToEffect;
    }

    public int getCost() {
        return cost;
    }

    public boolean isImplemented() {
        return isImplemented;
    }

    public void setTimeToEffect(int timeToEffect) {
        this.timeToEffect = timeToEffect;
    }

    public void setImplemented(boolean implemented) {
        isImplemented = implemented;
    }
}
