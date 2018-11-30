package com.example.cartermccall.greenink;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class GameState extends AppCompatActivity {

    public static int year, month, dangerCount;
    public static boolean game = false, chickenDinner;//when game is set to false by any means,
    public static Country country;                              //the game will be over.
    private String lossCondition;

    //Buildings - needs balancing and realism checks on the numbers.  More buildings  c - cost, q - quantity
    private Building windFarm = new Building(5, -2, 15, 160, 0);
    private Building solarFarm = new Building(3, -2, 25, 210, 0);
    private Building coalPlant = new Building(10, 5, 35, 125, 0);
    private Building waterTurbine = new Building(6, -1, 35, 260, 0);
    //

    public static Country gameStart(int cID){
        month = 1;
        year = 1;
        game = true;
        dangerCount = 21;

        //cID is the id by which the country is identified
        //country constructor needs starting values for:
        //p - pollution, m - money, i - income, e - energy, t - temperature
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
            else{
                chickenDinner = true;
                game = false;
            }
        }

        country.setPollution(country.getPollution() + 5);
        //Country data values calculations - INCOMPLETE, needs income value to be adjusted by onPurchase building action
        //energy value is also adjusted with on purchase action
        if (country.getPollution() > 20){      //checks if pollution is above the threshold for
            country.setTemperature(country.getTemperature() + country.getPollution()/20);         // increasing average temperature
        }

        country.setMoney(country.getMoney() + country.getIncome()); //adds income to treasury

        if(country.getTemperature() > 109){
            chickenDinner = true;
            game = false;
        }
        if(country.getTemperature() > 99){
            dangerCount--;
        }

        if (game == false){                 //The game ends after a turn when game is set to false,
                                            // checks what the losing condition was

        }
    }

}
