package com.example.cartermccall.greenink;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.cartermccall.greenink.GameState.buildings;
import static com.example.cartermccall.greenink.GameState.country;

import static com.example.cartermccall.greenink.GameState.gameStart;

public class NewGameActivity extends AppCompatActivity {

    private Button backButton, easyButton, mediumButton, hardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        easyButton = (Button) findViewById(R.id.easy_button);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                country = gameStart(0);
                                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                                startActivity(intent);


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(NewGameActivity.this);
                builder.setMessage("Your goal is to save the world from urgent global warming. Take over as a world leader and help save the environment by lowering the global temperature and sea levels. You’ll invest in resources and energy, each with a set of consequences, as you manage your economy and industry. Choose wisely what resources to use, as the wrong choices could lead to global panic.")
                        .setTitle("Welcome to GreenEarth!")
                        .setPositiveButton("Continue", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener).show();
            }
        });

        mediumButton = (Button) findViewById(R.id.medium_button);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                country = gameStart(1);
                                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                                startActivity(intent);


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(NewGameActivity.this);
                builder.setMessage("Your goal is to save the world from urgent global warming. Take over as a world leader and help save the environment by lowering the global temperature and sea levels. You’ll invest in resources and energy, each with a set of consequences, as you manage your economy and industry. Choose wisely what resources to use, as the wrong choices could lead to global panic.")
                        .setTitle("Welcome to GreenEarth!")
                        .setPositiveButton("Continue", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener).show();
            }
        });

        hardButton = (Button) findViewById(R.id.hard_button);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                country = gameStart(2);
                                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                                startActivity(intent);


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(NewGameActivity.this);
                builder.setMessage("Your goal is to save the world from urgent global warming. Take over as a world leader and help save the environment by lowering the global temperature and sea levels. You’ll invest in resources and energy, each with a set of consequences, as you manage your economy and industry. Choose wisely what resources to use, as the wrong choices could lead to global panic.")
                        .setTitle("Welcome to GreenEarth!")
                        .setPositiveButton("Continue", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener).show();
            }
        });

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
