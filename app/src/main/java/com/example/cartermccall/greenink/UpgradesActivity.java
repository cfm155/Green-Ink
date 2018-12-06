package com.example.cartermccall.greenink;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.cartermccall.greenink.GameState.buildings;
import static com.example.cartermccall.greenink.GameState.country;
import static java.security.AccessController.getContext;

public class UpgradesActivity extends AppCompatActivity {

    private RecyclerView upgradesList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter upgradesAdapter;
    private TextView moneyView;
    private Button toMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrades);
        upgradesList = (RecyclerView) findViewById(R.id.upgrade_list);

        moneyView = (TextView) findViewById(R.id.current_money_view);
        moneyView.setText("Money: $" + country.getMoney());

        toMap = (Button) findViewById(R.id.back_to_map_button);
        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        layoutManager = new LinearLayoutManager(this);
        upgradesList.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, final int position) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if(GameState.purchaseBuilding(position)){
                                    ((TextView) findViewById(R.id.current_money_view)).setText("Money: $" + country.getMoney());
                                    Toast.makeText(getBaseContext(), "Purchase Successful! ", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getBaseContext(), "You Don't Have Enough Money :( ", Toast.LENGTH_SHORT).show();
                                }

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(UpgradesActivity.this);
                builder.setMessage("Cost: $" + buildings[position].getCost() + "\nIncome: " + buildings[position].getIncomeOutput() + "\nPollution: " + buildings[position].getPollutionOutput() + "\nEnergy: " + buildings[position].getEnergyOutput() + "\nQuantity: " + buildings[position].getQuantity())
                        .setTitle("Purchase " + buildings[position].getName() + " Upgrade?")
                        .setPositiveButton("Purchase", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener).show();
            }
        };


        upgradesAdapter = new UpgradesAdapter(this, buildings, listener);
        upgradesList.setAdapter(upgradesAdapter);


    }
}
