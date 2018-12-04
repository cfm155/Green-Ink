package com.example.cartermccall.greenink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.cartermccall.greenink.GameState.country;
import static com.example.cartermccall.greenink.GameState.month;

public class StatsActivity extends AppCompatActivity {

    private int currentTemp, currentPoll, currentEnergy, currentMoney, currentBuildings;
    private int currentBuildingLimit, currentEnergyRequirement;
    private android.support.v7.widget.Toolbar toolbar;
    private TextView tempView, pollutionView, energyView, energyReqView, moneyView, buildingView;
    private TextView buildingLimitView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        country = (Country) getIntent().getSerializableExtra("country");
        currentTemp = country.getTemperature();
        currentPoll = country.getPollution();
        currentEnergy = country.getEnergy();
        currentMoney = country.getMoney();
        currentBuildings = country.getBuildingCount();
        currentBuildingLimit = country.getBuildingLimit();
        currentEnergyRequirement = country.getEnergyNeed();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Stats");
        setSupportActionBar(toolbar);

        tempView = (TextView) findViewById(R.id.TemperatureView);
        tempView.setText(currentTemp);

        pollutionView = (TextView) findViewById(R.id.PollutionView);
        pollutionView.setText(currentPoll);

        energyView = (TextView) findViewById(R.id.EnergyView);
        energyView.setText(currentEnergy);

        energyReqView = (TextView) findViewById(R.id.EnergyReqView);
        energyReqView.setText(currentEnergyRequirement);

        moneyView = (TextView) findViewById(R.id.MoneyView);
        moneyView.setText(currentMoney);

        buildingView = (TextView) findViewById(R.id.BuildingView);
        buildingView.setText(currentBuildings);

        buildingLimitView = (TextView) findViewById(R.id.BuildingLimitView);
        buildingLimitView.setText(currentBuildingLimit);
    }
}
