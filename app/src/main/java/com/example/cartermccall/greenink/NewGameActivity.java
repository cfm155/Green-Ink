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
                country = gameStart(0);
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                startActivity(intent);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:


                                break;


                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                builder.setMessage("Welcome to GreenEarth. Your goal is to save the world from urgent global warming. Take over as a world leader and help save the environment by lowering the global temperature and sea levels. You’ll invest in resources and energy, each with a set of consequences, as you manage your economy and industry. Choose wisely what resources to use, as the wrong choices could lead to global panic.")
                        .setTitle("Welcome To GreenEarth!")
                        .setPositiveButton("Continue", dialogClickListener).show();
            }
        });

        mediumButton = (Button) findViewById(R.id.medium_button);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = gameStart(1);
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        hardButton = (Button) findViewById(R.id.hard_button);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = gameStart(2);
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                startActivity(intent);
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
