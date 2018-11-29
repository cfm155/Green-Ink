package com.example.cartermccall.greenink;

public class Country {

    private int pollution, money, income, energy, cID, energyNeed, buildingLimit, buildingCount;
    private float temperature;

    Country(int p, int m, int i, int e, int t, int b){
        setPollution(p);
        setEnergy(e);
        setIncome(i);
        setMoney(m);
        setTemperature(t);
    }

    public int energyNeedCalc(int cID, int e){
        double i = 1;
        switch (cID){
            case 0://USA
                i = 1.35;
            case 1://Brazil
                i = 1.5;
        }
        return (int)(i * e);
    }

    public void setEnergyNeed(int energyNeed){
        this.energyNeed = energyNeed;
    }

    public int getEnergyNeed(){
        return energyNeed;
    }

    public int getCID(){
        return cID;
    }

    public void setCID(int cID){
        this.cID = cID;
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

    public void setBuildingLimit(int buildingLimit){
        this.buildingLimit = buildingLimit;
    }

    public int getBuildingLimit(){
        return buildingLimit;
    }

    public void setBuildingCount(int buildingCount){
        this.buildingCount = buildingCount;
    }

    public int getBuildingCount(){
        return buildingCount;
    }
}

