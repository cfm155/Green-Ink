package com.example.cartermccall.greenink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.cartermccall.greenink.GameState.gameStart;

public class NewGameActivity extends AppCompatActivity {

    private Button backButton, easyButton, mediumButton, hardButton;
    private Country country;
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
                intent.putExtra("country", country);
                startActivity(intent);
            }
        });

        mediumButton = (Button) findViewById(R.id.medium_button);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = gameStart(1);
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                intent.putExtra("country", country);
                startActivity(intent);
            }
        });

        hardButton = (Button) findViewById(R.id.hard_button);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = gameStart(2);
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                intent.putExtra("country", country);
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
