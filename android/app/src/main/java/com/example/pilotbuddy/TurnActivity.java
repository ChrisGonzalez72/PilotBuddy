package com.example.pilotbuddy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TurnActivity extends BaseActivity{

    private TextInputLayout inputLayout;
    private TextInputEditText editHeading;
    private TextView result90;
    private TextView result180;
    private TextView result270;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_turn);
        applyEdgeToEdgeInsets(findViewById(R.id.main));

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setupToolbarBack(toolbar);

        inputLayout = findViewById(R.id.input_layout_heading);
        editHeading = findViewById(R.id.edit_heading);
        result90 = findViewById(R.id.result_90).findViewById(R.id.text_result_value);
        result180 = findViewById(R.id.result_180).findViewById(R.id.text_result_value);
        result270 = findViewById(R.id.result_270).findViewById(R.id.text_result_value);

        }

}
