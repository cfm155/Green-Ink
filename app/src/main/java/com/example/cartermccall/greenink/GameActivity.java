package com.example.cartermccall.greenink;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import io.realm.Realm;

import static com.example.cartermccall.greenink.GameState.dangerCount;
import static com.example.cartermccall.greenink.GameState.gameStart;
import static com.example.cartermccall.greenink.GameState.month;
import static com.example.cartermccall.greenink.GameState.year;
import static com.example.cartermccall.greenink.GameState.country;

public class GameActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button statsButton, upgradesButton, nextTurnButton;
    private ImageView map;
    private int currentTemp, currentPoll, currentEnergy, currentMoney;
    private int red, green, orange, mapColor;
    private TextView tempView, pollutionView, yearView, monthView, energyView, moneyView, dangerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("World Map");
        setSupportActionBar(toolbar);

        map = (ImageView) findViewById(R.id.game_map);
        red = getRed();
        green = Color.parseColor("#00A14B");
        orange = getOrange();

        country = (Country) getIntent().getSerializableExtra("country");
        currentTemp = country.getTemperature();
        currentPoll = country.getPollution();
        currentEnergy = country.getEnergy();
        currentMoney = country.getMoney();

        dangerView = (TextView) findViewById(R.id.danger_view);
        if (dangerCount < 20) {
            dangerView.setText("Danger Countdown: " + dangerCount + " months");
        }

        monthView = (TextView) findViewById(R.id.month_view);
        monthView.setText("Month: " + month);

        energyView = (TextView) findViewById(R.id.energy_view);
        energyView.setText("Energy: " + currentEnergy);

        moneyView = (TextView) findViewById(R.id.money_view);
        moneyView.setText("Money: " + currentMoney);

        yearView = (TextView) findViewById(R.id.year_view);
        yearView.setText("Year: " + year);

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
                if (!GameState.advanceTurn()){                 // The game ends after a turn when game is set to false,
                    if (GameState.chickenDinner){ // what happens when you win (go to end-win screen)
                        Intent intent = new Intent(v.getContext(), EndWinActivity.class);
                        startActivity(intent);
                    }
                    else{ // what happens when you lose (go to end-lose screen)
                        Intent intent = new Intent (v.getContext(), EndLoseActivity.class);
                        startActivity(intent);
                    }
                }
                currentTemp = country.getTemperature();
                tempView.setText("Temperature: " + currentTemp);
                mapColor = findMapColor(currentTemp);
                map.setColorFilter(mapColor);

                currentPoll = country.getPollution();
                pollutionView.setText("Pollution: " + currentPoll);

                monthView.setText("Month: " + month);
                yearView.setText("Year: " + year);

                currentEnergy = country.getEnergy();
                currentMoney = country.getMoney();
                energyView.setText("Energy: " + currentEnergy);
                moneyView.setText("Money: " + currentMoney);

                if (dangerCount < 21) {
                    dangerView.setText("Danger Countdown: " + dangerCount + " months");
                }

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
        // 00A14B
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        isFinish("Are you sure you want to exit the game? Any unsaved progress will be lost.",false);
    }




    public void isFinish(String alertmessage, boolean menu) {
        final boolean finish = menu;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if(finish) {
                            Intent intent = new Intent(getBaseContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setMessage(alertmessage)
                .setTitle("Exit Game?")
                .setPositiveButton("Exit", dialogClickListener)
                .setNegativeButton("Cancel", dialogClickListener).show();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.menu_button) {
            isFinish("Are you sure you want to return to the menu? Any unsaved progress will be lost.",true);
        }
        if(item.getItemId() == R.id.save_button) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    SaveState save = new SaveState();
                    save.setCountry(country);
                    save.setDangerCount(dangerCount);
                    save.setMonth(month);
                    save.setYear(year);
                    realm.copyToRealmOrUpdate(save);
                }
            });
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Intent intent = new Intent(getBaseContext(),MainActivity.class);
                            startActivity(intent);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:

                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setMessage("Your game has been saved successfully! Would you like to exit or continue playing?")
                    .setTitle("Game Saved!")
                    .setPositiveButton("Exit", dialogClickListener)
                    .setNegativeButton("Continue Playing", dialogClickListener).show();
        }
        return super.onOptionsItemSelected(item);
    }



}


