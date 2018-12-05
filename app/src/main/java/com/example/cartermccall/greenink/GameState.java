package com.example.cartermccall.greenink;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class GameState extends AppCompatActivity {

    public static int year, month, dangerCount;
    public static boolean game = false, chickenDinner;//when game is set to false by any means,
    public static Country country;                              //the game will be over.
    private static String lossCondition;

    //Buildings - needs balancing and realism checks on the numbers.  More buildings  c - cost, q - quantity
    //Green
    private static Building windFarm = new Building(5, -2, 15, 160, 0);
    private static Building solarFarm = new Building(3, -2, 25, 210, 0);
    private static Building waterTurbine = new Building(6, -1, 35, 260, 0);
    private static Building geothermalPlant = new Building(3,-1,8,100,0);
    private static Building gasPlant = new Building (2,-1,5,120,0);
    //not green
    private static Building oilRig = new Building(12, 7, 40, 150, 0);
    private static Building coalPlant = new Building(10, 5, 35, 125, 0);
    private static Building nuclearPlant = new Building(15, 10, 200, 300, 0);
    //
    private static Building[] buildings;

    //Policies - don't have a class because what they all do could be vastly different,
    //toggled and executed with purchase policy switch case method, which is accessed by the
    //purchase policy screen
    private boolean policyNuclearPower = false;
    private boolean policyPollutionLimit = false;
    private boolean policyBuildingLimit1 = false;
    private boolean policyBuildingLimit2 = false;
    //

    public static Country gameStart(int cID){
        month = 1;
        year = 1;
        game = true;
        dangerCount = 21;
        buildings = new Building[8];
        buildings[0] = windFarm;
        buildings[1] = solarFarm;
        buildings[2] = waterTurbine;
        buildings[3] = geothermalPlant;
        buildings[4] = gasPlant;
        buildings[5] = oilRig;
        buildings[6] = coalPlant;
        buildings[7] = nuclearPlant;


        //cID is the id by which the country is identified
        //country constructor needs starting values for:
        //p - pollution, m - money, i - income, e - energy, t - temperature, b - building limit

        //may be able to move these into difficulty select buttons
        switch (cID) {
            case 0://USA
                country = new Country();
                country.setPollution(10);
                country.setTemperature(70);
                country.setMoney(500);
                country.setEnergy(0);
                country.setIncome(25);
                country.setcID(cID);
                country.setQuantities(8);
                return country;
            case 1://Brazil
                country = new Country();
                country.setPollution(20);
                country.setTemperature(80);
                country.setMoney(350);
                country.setEnergy(0);
                country.setIncome(20);
                country.setcID(cID);
                country.setQuantities(8);
                return country;
            default://Third world country
                country = new Country();
                country.setPollution(15);
                country.setTemperature(85);
                country.setMoney(200);
                country.setEnergy(0);
                country.setIncome(15);
                country.setcID(cID);
                country.setQuantities(8);
                return country;
        }
    }

    public static boolean advanceTurn() {
        //advances turn and checks values for game state

        //advances the date and checks for win condition
        if (month < 12) {
            month++;
        }
        else {
            month = 1;
            if (year < 10){
                year++;
            }
            else if (year == 9 && month == 11){
                chickenDinner = true;
                game = false;
            }
        }
        //Country data values calculations
        //energy value is also adjusted with on purchase action
        country.setPollution(country.getPollution() + 5);
        //Country data values calculations
        //energy value is also adjusted with on purchase action
        if (country.getPollution() > 20){      //checks if pollution is above the threshold for
            country.setTemperature(country.getTemperature() + country.getPollution()/20);         // increasing average temperature
        }


        country.setMoney(country.getMoney() + country.getIncome()); //adds income to treasury

        country.energyNeedCalc(country.getcID(), year);

        if(country.getTemperature() > 109){
            lossCondition = "Global warming catastrophe";
            game = false;
        }
        if(country.getTemperature() > 99){
            dangerCount--;
        }

        /*
         TODO:
         1. link win/lose conditions with GameActivity so you actually go to end screens when
            the conditions are met (either instantly OR on next time advanceTurn() is called)
         2. wipe game data after a game ends so new game doesn't load the same one
        */

        return game;
    }
    //end advance turn

    public void purchasePolicy(int policy){
        switch (policy){
            case 0://Nuclear Power - costs 1500
                if (country.getMoney() >= 1500 && !policyNuclearPower) {
                    policyNuclearPower = true;
                    country.setMoney(country.getMoney() - 1500);
                }
            case 1://Pollution Limit - costs 1000
                policyPollutionLimit = true;
            case 2://Building limit 1
                if (country.getMoney() >= 1000 && !policyBuildingLimit1) {
                    policyBuildingLimit1 = true;
                    country.setBuildingLimit(country.getBuildingLimit() + 5);
                    country.setMoney(country.getMoney() - 1000);
                }
                else {
                    //insufficient money toast
                }
                break;
            case 3://Building limit 2 - costs 2500
                if (policyBuildingLimit1 && !policyBuildingLimit2) {
                    if (country.getMoney() >= 2500) {
                        policyBuildingLimit2 = true;
                        country.setBuildingLimit(country.getBuildingLimit() + 5);
                        country.setMoney(country.getMoney() - 2500);
                    }
                    else{
                        //not enough money toast
                    }
                }
                else{
                    //missing the first building policy toast
                }
        }
    }

    public void purchaseBuilding(int building) {
        Building current = buildings[building];
        if(country.getMoney() < current.getCost()){
            //not enough money
        }
        else{
            country.setMoney(country.getMoney() - current.getCost());
            country.setQuantity(building, current.getQuantity() + 1);
            buildings[building].setQuantity(current.getQuantity() + 1);
            country.setPollution(country.getPollution() + current.getPollutionOutput());
            country.setIncome(country.getIncome() + current.getIncomeOutput());
            country.setEnergy(country.getEnergy() + current.getEnergyOutput());
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
