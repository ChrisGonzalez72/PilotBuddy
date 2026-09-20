package com.example.pilotbuddy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CrosswindActivity extends BaseActivity{

    private TextInputLayout headingLayout;
    private TextInputLayout windDirLayout;
    private TextInputLayout windSpeedLayout;
    private TextInputEditText editHeading;
    private TextInputEditText editWindDir;
    private TextInputEditText editWindSpeed;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crosswind);
        applyEdgeToEdgeInsets(findViewById(R.id.main));

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setupToolbarBack(toolbar);

        headingLayout = findViewById(R.id.input_layout_heading);
        windDirLayout = findViewById(R.id.input_layout_wind_direction);
        windSpeedLayout = findViewById(R.id.input_layout_wind_speed);
        editHeading = findViewById(R.id.edit_heading);
        editWindDir = findViewById(R.id.edit_wind_direction);
        editWindSpeed = findViewById(R.id.edit_wind_speed);

    }

}
