package com.example.cartermccall.greenink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import static com.example.cartermccall.greenink.GameState.country;

import static com.example.cartermccall.greenink.GameState.country;
import static com.example.cartermccall.greenink.GameState.month;

public class StatsActivity extends AppCompatActivity {

    private int currentTemp, currentPoll, currentEnergy, currentMoney, currentBuildings, currentIncome;
    private int currentBuildingLimit, currentEnergyRequirement;
    private android.support.v7.widget.Toolbar toolbar;
    private TextView tempView, pollutionView, energyView, energyReqView, moneyView, buildingView;
    private TextView buildingLimitView, incomeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        currentTemp = country.getTemperature();
        currentPoll = country.getPollution();
        currentEnergy = country.getEnergy();
        currentMoney = country.getMoney();
        currentBuildings = country.getBuildingCount();
        currentBuildingLimit = country.getBuildingLimit();
        currentEnergyRequirement = country.getEnergyNeed();
        currentIncome = country.getIncome();

//        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Stats");
//        setSupportActionBar(toolbar);

        tempView = (TextView) findViewById(R.id.TemperatureView);
        tempView.setText(currentTemp + "");

        pollutionView = (TextView) findViewById(R.id.PollutionView);
        pollutionView.setText(currentPoll + "");

        energyView = (TextView) findViewById(R.id.EnergyView);
        energyView.setText(currentEnergy + "");

        energyReqView = (TextView) findViewById(R.id.EnergyReqView);
        energyReqView.setText(currentEnergyRequirement + "");

        moneyView = (TextView) findViewById(R.id.MoneyView);
        moneyView.setText(currentMoney + "");

        buildingView = (TextView) findViewById(R.id.BuildingView);
        buildingView.setText(currentBuildings + "");

        buildingLimitView = (TextView) findViewById(R.id.BuildingLimitView);
        buildingLimitView.setText(currentBuildingLimit + "");

        incomeView = (TextView) findViewById(R.id.IncomeView);
        incomeView.setText(currentIncome + "");
    }
}
