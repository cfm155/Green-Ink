package com.example.cartermccall.greenink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.cartermccall.greenink.GameState.buildings;
import static com.example.cartermccall.greenink.GameState.country;

public class MainActivity extends AppCompatActivity {

    private Button tutorialButton, newGame, resumeGame;
    public static boolean save = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame = (Button) findViewById(R.id.new_button);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewGameActivity.class);
                startActivity(intent);
            }
        });

        resumeGame = (Button) findViewById(R.id.resume_button);
        resumeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                RealmResults<SaveState> saves= realm.where(SaveState.class).findAll();
                if(saves.size() != 0) {
                    save = true;
                    SaveState load = realm.copyFromRealm(realm.where(SaveState.class).findFirst());
                    GameState.month = load.getMonth();
                    GameState.year = load.getYear();
                    GameState.dangerCount = load.getDangerCount();
                    GameState.game = true;
                    country = load.getCountry();
                    for(int i = 0; i < country.getQuantities().size(); i++){
                        buildings[i].setQuantity(country.getQuantity(i));
                    }
                    Intent intent = new Intent(v.getContext(), GameActivity.class);
                    startActivity(intent);
                }
            }
        });

        tutorialButton = (Button) findViewById(R.id.tutorial_button);
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TutorialActivity.class);
                startActivity(intent);
            }
        });
    }
}
