package com.example.pilotbuddy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

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

        editHeading.addTextChangedListener(new SimpleTextWatcher(this::recalculate));

        }

    private void recalculate() {
        String raw = editHeading.getText() == null ? "" : editHeading.getText().toString().trim();
        if (raw.isEmpty()) {
            inputLayout.setError(null);
            clearResults();
            return;
        }

        int heading;
        try {
            heading = Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            inputLayout.setError(getString(R.string.turn_error_range));
            clearResults();
            return;
        }

        if (heading < 0 || heading > 359) {
            inputLayout.setError(getString(R.string.turn_error_range));
            clearResults();
            return;
        }

        inputLayout.setError(null);
        result90.setText(formatHeading((heading + 90) % 360));
        result180.setText(formatHeading((heading + 180) % 360));
        result270.setText(formatHeading((heading + 270) % 360));
    }

    private String formatHeading(int degrees) {
        return String.format(Locale.US, "%03d%s", degrees, getString(R.string.turn_input_suffix));
    }

    private void clearResults() {
        String placeholder = getString(R.string.result_placeholder);
        result90.setText(placeholder);
        result180.setText(placeholder);
        result270.setText(placeholder);
    }
}
