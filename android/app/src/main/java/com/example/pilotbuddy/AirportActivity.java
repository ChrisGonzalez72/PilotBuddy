package com.example.pilotbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class AirportActivity extends BaseActivity {

    private TextInputLayout identifierLayout;
    private MaterialAutoCompleteTextView autoIdentifier;
    private View emptyState;
    private LinearLayout resultsGroup;
    private TextView airportName;
    private TextView airportIdentifier;
    private LinearLayout runwaysContainer;
    private LinearLayout frequenciesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_airport);
        applyEdgeToEdgeInsets(findViewById(R.id.main));

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setupToolbarBack(toolbar);

        identifierLayout = findViewById(R.id.input_layout_identifier);
        autoIdentifier = findViewById(R.id.auto_identifier);
        emptyState = findViewById(R.id.text_empty_state);
        resultsGroup = findViewById(R.id.group_results);
        airportName = findViewById(R.id.text_airport_name);
        airportIdentifier = findViewById(R.id.text_airport_identifier);
        runwaysContainer = findViewById(R.id.container_runways);
        frequenciesContainer = findViewById(R.id.container_frequencies);
    }

}
