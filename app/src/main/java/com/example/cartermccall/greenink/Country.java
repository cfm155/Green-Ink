package com.example.cartermccall.greenink;

import java.io.Serializable;

import io.realm.RealmObject;

public class Country extends RealmObject implements Serializable {

    private int pollution, money, income, energy, energyNeed, buildingLimit, buildingCount, cID;
    private int temperature;

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

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
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

    public void setcID(int cID){
        this.cID = cID;
    }

    public int getcID(){
        return cID;
    }
}

