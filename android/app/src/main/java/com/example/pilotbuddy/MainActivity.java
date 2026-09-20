package com.example.pilotbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        applyEdgeToEdgeInsets(findViewById(R.id.main));

        MaterialButton turn = findViewById(R.id.btnTurn);
        MaterialButton crosswind = findViewById(R.id.btnCrosswind);
        MaterialButton airport = findViewById(R.id.btnAirport);

       turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(TurnActivity.class);
            }
        });
        crosswind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CrosswindActivity.class);
            }
        });
        airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(AirportActivity.class);
            }
        });
    }

    private void open(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }
}