package com.example.cartermccall.greenink;

public class Building {

    private int energyOutput, pollutionOutput, incomeOutput, cost, quantity;
    private boolean isOwned;

    public Building(int e, int p, int i, int c, int q){
        setEnergyOutput(e);
        setIncomeOutput(i);
        setPollutionOutput(p);
    }

    public int getEnergyOutput() {
        return energyOutput;
    }

    public void setEnergyOutput(int energyOutput) {
        this.energyOutput = energyOutput;
    }

    public int getPollutionOutput() {
        return pollutionOutput;
    }

    public void setPollutionOutput(int pollutionOutput) {
        this.pollutionOutput = pollutionOutput;
    }

    public int getIncomeOutput() {
        return incomeOutput;
    }

    public void setIncomeOutput(int incomeOutput) {
        this.incomeOutput = incomeOutput;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getIsOwned() {
        return isOwned;
    }

    public void setIsOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }
}