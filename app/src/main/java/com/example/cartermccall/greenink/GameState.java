package com.example.cartermccall.greenink;

public class GameState {

    private static int year, month, cID;
    private static boolean game, pollutionThreshold, chickenDinner;//when game is set to false by any means,
    private static Country country;                              //the game will be over.
    private String lossCondition;

    //Buildings - needs balancing and realism checks on the numbers.  More buildings  c - cost, q - quantity
    private Building windFarm = new Building(5, -2, 15, 160, 0);
    private Building solarFarm = new Building(3, -2, 25, 210, 0);
    private Building coalPlant = new Building(10, 5, 35, 125, 0);
    private Building waterTurbine = new Building(6, -1, 35, 260, 0);
    //

    public static void gameStart(int cID){
        setMonth(0);
        setYear(0);
        setcID(cID);
        game = true;
        pollutionThreshold = false;

        //cID is the id by which the country is identified
        //country constructor needs starting values for:
        //p - pollution, m - money, i - income, e - energy, t - temperature
        switch (cID) {
            case 0://USA
                country = new Country(10, 500, 25, 0, 70);
                break;
            case 1://Brazil
                country = new Country(20, 350, 20, 0, 80);
                break;
            case 2://Third world country
                country = new Country(15, 200, 15, 0, 85);
                break;
        }
    }

    public static void advanceTurn() {
        //advances turn and checks values for game state

        //advances the date and checks for win condition
        if (month < 11) {
            setMonth(month++);
        }
        else {
            setMonth(0);
            if (year < 9){
                setYear(year++);
            }
            else{
                chickenDinner = true;
                game = false;
            }
        }

        //Country data values calculations - INCOMPLETE, needs income value to be adjusted by onPurchase building action
        //energy value is also adjusted with on purchase action
        if (country.getPollution() > 70){      //checks if pollution is above the threshold for
            pollutionThreshold = true;         // increasing average temperature
        }
        // If the pollution threshold is crossed, increases the temperature by pollution per year or 1/12 pollution per month
        if (pollutionThreshold == true){
            country.setTemperature(country.getTemperature() + ((country.getPollution() - 70) / 12));
        }

        country.setMoney(country.getMoney() + country.getIncome()); //adds income to treasury


        if (game == false){                 //The game ends after a turn when game is set to false,
                                            // checks what the losing condition was

        }
    }



    public int getYear() {
        return year;
    }

    public static void setYear(int year) {
        GameState.year = year;
    }

    public int getMonth() {
        return month;
    }

    public static void setMonth(int month) {
        GameState.month = month;
    }

    public int getcID() {
        return cID;
    }

    public static void setcID(int cID) {
        GameState.country = country;
    }
}
