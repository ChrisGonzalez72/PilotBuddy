package com.example.pilotbuddy;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;


import com.example.pilotbuddy.datamodel.AirportDB;
import com.example.pilotbuddy.datamodel.Frequency;
import com.example.pilotbuddy.datamodel.Runway;
import com.example.pilotbuddy.datamodel.Airport;

import java.util.Locale;

public class AirportActivity extends BaseActivity {
    private AirportDB airportDB;
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

        airportDB = new AirportDB(this);
        identifierLayout = findViewById(R.id.input_layout_identifier);
        autoIdentifier = findViewById(R.id.auto_identifier);
        emptyState = findViewById(R.id.text_empty_state);
        resultsGroup = findViewById(R.id.group_results);
        airportName = findViewById(R.id.text_airport_name);
        airportIdentifier = findViewById(R.id.text_airport_identifier);
        runwaysContainer = findViewById(R.id.container_runways);
        frequenciesContainer = findViewById(R.id.container_frequencies);

        autoIdentifier.setSimpleItems(airportDB.getAllIdentifiers());
        autoIdentifier.setOnItemClickListener((parent, view, position, id) -> lookup(autoIdentifier.getText().toString()));
        autoIdentifier.setOnEditorActionListener((v, actionId, event) -> {
            lookup(autoIdentifier.getText().toString());
            return true;
        });
    }

    private void lookup(String identifier) {
        if (identifier == null || identifier.trim().isEmpty()) {
            showEmpty(R.string.airport_empty);
            return;
        }

        Airport airport = airportDB.findByIdentifier(identifier);
        if (airport == null) {
            identifierLayout.setError(getString(R.string.airport_not_found));
            showEmpty(R.string.airport_empty);
            return;
        }

        identifierLayout.setError(null);
        bindAirport(airport);
    }

    private void bindAirport(Airport airport) {
        airportName.setText(airport.getName());
        airportIdentifier.setText(airport.getIdentifier());

        runwaysContainer.removeAllViews();
        for (Runway runway : airport.getRunways()) {
            String value = String.format(Locale.US, getString(R.string.airport_runway_fmt),
                    withCommas(runway.getLengthFeet()),
                    runway.getSurface());
            addRow(runwaysContainer, runway.getDesignation(), value);
        }

        frequenciesContainer.removeAllViews();
        for (Frequency frequency : airport.getFrequencies()) {
            addRow(frequenciesContainer, frequency.getType(), frequency.getValue());
        }

        emptyState.setVisibility(View.GONE);
        resultsGroup.setVisibility(View.VISIBLE);
    }

    /** Formats a whole number with thousands separators, e.g. 12390 becomes 12,390. */
    private String withCommas(int number) {
        String digits = String.valueOf(number);
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = digits.length() - 1; i >= 0; i--) {
            result.insert(0, digits.charAt(i));
            count++;
            if (count % 3 == 0 && i > 0) {
                result.insert(0, ",");
            }
        }
        return result.toString();
    }

    private void addRow(ViewGroup container, String label, String value) {
        View row = LayoutInflater.from(this)
                .inflate(R.layout.view_data_row, container, false);
        ((TextView) row.findViewById(R.id.text_row_label)).setText(label);
        ((TextView) row.findViewById(R.id.text_row_value)).setText(value);
        container.addView(row);
    }

    private void showEmpty(int messageRes) {
        ((TextView) emptyState).setText(messageRes);
        emptyState.setVisibility(View.VISIBLE);
        resultsGroup.setVisibility(View.GONE);
    }

}
