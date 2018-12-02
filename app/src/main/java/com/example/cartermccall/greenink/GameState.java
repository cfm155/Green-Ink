package com.example.cartermccall.greenink;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class GameState extends AppCompatActivity {

    public static int year, month, dangerCount;
    public static boolean game = false, chickenDinner;//when game is set to false by any means,
    public static Country country;                              //the game will be over.
    private static String lossCondition;

    //Buildings - needs balancing and realism checks on the numbers.  More buildings  c - cost, q - quantity
    //Green
    private Building windFarm = new Building(5, -2, 15, 160, 0);
    private Building solarFarm = new Building(3, -2, 25, 210, 0);
    private Building waterTurbine = new Building(6, -1, 35, 260, 0);
    private Building geothermalPlant = new Building(0,0,0,0,0);
    private Building gasPlant = new Building (0,0,0,0,0);
    //not green
    private Building oilRig = new Building(12, 7, 40, 150, 0);
    private Building coalPlant = new Building(10, 5, 35, 125, 0);
    private Building nuclearPlant = new Building(15, 10, 200, 300, 0);
    //

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
                return country;
            case 1://Brazil
                country = new Country();
                country.setPollution(20);
                country.setTemperature(80);
                country.setMoney(350);
                country.setEnergy(0);
                country.setIncome(20);
                return country;
            default://Third world country
                country = new Country();
                country.setPollution(15);
                country.setTemperature(85);
                country.setMoney(200);
                country.setEnergy(0);
                country.setIncome(15);
                return country;
        }
    }

    public static void advanceTurn() {
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
        //Country data values calculations - INCOMPLETE, needs income value to be adjusted by onPurchase building action
        //energy value is also adjusted with on purchase action
        if (country.getPollution() > 20){      //checks if pollution is above the threshold for
            country.setTemperature(country.getTemperature() + country.getPollution()/20);         // increasing average temperature
        }

        if (country.getTemperature() >= 100){
            lossCondition = "Global warming catastrophe";
            game = false;
        }

        country.setMoney(country.getMoney() + country.getIncome()); //adds income to treasury

        if(country.getTemperature() > 109){
            chickenDinner = true;
            game = false;
        }
        if(country.getTemperature() > 99){
            dangerCount--;
        }

        if (!game){                 //The game ends after a turn when game is set to false,
            if (chickenDinner){//what happens when you win

            }
            else{//what happens when you lose

            }
        }
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
        if (country.getBuildingCount() < country.getBuildingLimit()) {
            Building construction = new Building(0, 0, 0, 0, 0);
            for (int i = 0; i < 2; i++) {
                switch (building) {
                    case 0://wind farm
                        if (i == 0) {
                            construction = windFarm;
                        } else {
                            windFarm = construction;
                        }
                    case 1://solar farm
                        if (i == 0) {
                            construction = solarFarm;
                        } else {
                            solarFarm = construction;
                        }
                    case 2://water turbine
                        if (i == 0) {
                            construction = waterTurbine;
                        } else {
                            waterTurbine = construction;
                        }
                    case 3://geothermal plant
                        if (i == 0) {
                            construction = geothermalPlant;
                        } else {
                            geothermalPlant = construction;
                        }
                    case 4://gas plant
                        if (i == 0) {
                            construction = gasPlant;
                        } else {
                            gasPlant = construction;
                        }
                    case 5://oil rig
                        if (i == 0) {
                            construction = oilRig;
                        } else {
                            oilRig = construction;
                        }
                    case 6://coal plant
                        if (i == 0) {
                            construction = coalPlant;
                        } else {
                            coalPlant = construction;
                        }
                    case 7://nuclear plant
                        if (i == 0) {
                            if (policyNuclearPower) {
                                construction = nuclearPlant;
                            } else {
                                //need nuclear power policy toast
                            }
                        } else {
                            nuclearPlant = construction;
                        }
                        if (0 < building && building < 7 && i == 0) {
                            if (country.getBuildingCount() < country.getBuildingLimit()) {
                                if (country.getMoney() > construction.getCost()) {
                                    construction.setIsOwned(true);
                                    construction.setQuantity((construction.getQuantity() + 1));
                                    country.setPollution(country.getPollution() + construction.getPollutionOutput());
                                    country.setEnergy(country.getEnergy() + construction.getEnergyOutput());
                                    country.setIncome(country.getIncome() + construction.getIncomeOutput());
                                    country.setMoney(country.getMoney() - construction.getCost());
                                }
                            }
                        }
                }
            }
        } else {
            //already at max buildings toast
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
