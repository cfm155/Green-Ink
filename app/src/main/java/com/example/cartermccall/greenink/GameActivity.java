package com.example.cartermccall.greenink;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import static com.example.cartermccall.greenink.GameState.gameStart;

public class GameActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button statsButton, upgradesButton, nextTurnButton;
    private ImageView map;
    public Country country;
    private int countryID, currentTemp, currentPoll;
    private int red, green, orange, mapColor;
    private TextView tempView, pollutionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("World Map");
        setSupportActionBar(toolbar);
        map = (ImageView) findViewById(R.id.game_map);
        red = getRed();
        green = getGreen();
        orange = getOrange();
        countryID = getIntent().getIntExtra("countryID",2);
        country = gameStart(countryID);
        currentTemp = country.getTemperature();
        currentPoll = country.getPollution();
        tempView = (TextView) findViewById(R.id.temp_view);
        tempView.setText("Temperature: " + currentTemp);
        pollutionView = (TextView) findViewById(R.id.pollution_view);
        pollutionView.setText("Pollution: " + currentPoll);
        mapColor = findMapColor(currentTemp);
        map.setColorFilter(mapColor);

        statsButton = (Button) findViewById(R.id.stats_button);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StatsActivity.class);
                startActivity(intent);
            }
        });

        upgradesButton = (Button) findViewById(R.id.upgrades_button);
        upgradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpgradesActivity.class);
                startActivity(intent);
            }
        });

        nextTurnButton = (Button) findViewById(R.id.next_turn_button);
        nextTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameState.advanceTurn();
                currentTemp = country.getTemperature();
                tempView.setText("Temperature: " + currentTemp);
                mapColor = findMapColor(currentTemp);
                currentPoll = country.getTemperature();
                pollutionView.setText("Pollution: " + currentPoll);
                map.setColorFilter(mapColor);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    public int getRed(){
        return getResources().getColor(android.R.color.holo_red_dark);
    }

    public int getOrange(){
        return getResources().getColor(android.R.color.holo_orange_dark);
    }

    public int getGreen(){
        return getResources().getColor(android.R.color.holo_green_dark);
    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public int findMapColor(int currentTemp){
        if(currentTemp < 80){
            return adjustAlpha(green,1);
        }
        else if(currentTemp < 85){
            return adjustAlpha(orange, 1);
        }
        else if(currentTemp < 90){
            return adjustAlpha(orange, (float).5);
        }
        else{
            return adjustAlpha(red,1);
        }
    }



}


