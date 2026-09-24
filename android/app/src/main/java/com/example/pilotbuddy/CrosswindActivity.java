package com.example.pilotbuddy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class CrosswindActivity extends BaseActivity{

    private TextInputLayout headingLayout;
    private TextInputLayout windDirLayout;
    private TextInputLayout windSpeedLayout;
    private TextInputEditText editHeading;
    private TextInputEditText editWindDir;
    private TextInputEditText editWindSpeed;
    private TextView resultHeadTail;
    private TextView resultCrosswind;

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
        resultHeadTail = findViewById(R.id.result_headtail).findViewById(R.id.text_result_value);
        resultCrosswind = findViewById(R.id.result_crosswind).findViewById(R.id.text_result_value);

        SimpleTextWatcher watcher = new SimpleTextWatcher(new Runnable() {
            @Override
            public void run() {
                recalculate();
            }
        });
        editHeading.addTextChangedListener(watcher);
        editWindDir.addTextChangedListener(watcher);
        editWindSpeed.addTextChangedListener(watcher);
        recalculate();
    }

    private void recalculate() {
        Integer heading = parseDegrees(editHeading, headingLayout);
        Integer windDir = parseDegrees(editWindDir, windDirLayout);
        Integer windSpeed = parseSpeed(editWindSpeed, windSpeedLayout);

        if (heading == null || windDir == null || windSpeed == null) {
            clearResults();
            return;
        }

        // Angle of the wind relative to the nose, normalized to -180..180.
        // Positive => wind is coming from the right of the aircraft.
        double theta = Math.toRadians(((windDir - heading + 540) % 360) - 180);
        double headwind = windSpeed * Math.cos(theta);
        double crosswind = windSpeed * Math.sin(theta);

        resultHeadTail.setText(formatHeadTail(headwind));
        resultCrosswind.setText(formatCrosswind(crosswind));
    }

    /** Parses a 0–359 degree field; sets an error and returns null if invalid. */
    private Integer parseDegrees(TextInputEditText edit, TextInputLayout layout) {
        String raw = edit.getText() == null ? "" : edit.getText().toString().trim();
        if (raw.isEmpty()) {
            layout.setError(null);
            return null;
        }
        try {
            int value = Integer.parseInt(raw);
            if (value < 0 || value > 359) {
                layout.setError(getString(R.string.wind_error_range_deg));
                return null;
            }
            layout.setError(null);
            return value;
        } catch (NumberFormatException e) {
            layout.setError(getString(R.string.wind_error_range_deg));
            return null;
        }
    }

    /** Parses a non-negative speed field; sets an error and returns null if invalid. */
    private Integer parseSpeed(TextInputEditText edit, TextInputLayout layout) {
        String raw = edit.getText() == null ? "" : edit.getText().toString().trim();
        if (raw.isEmpty()) {
            layout.setError(null);
            return null;
        }
        try {
            int value = Integer.parseInt(raw);
            if (value < 0) {
                layout.setError(getString(R.string.wind_error_speed));
                return null;
            }
            layout.setError(null);
            return value;
        } catch (NumberFormatException e) {
            layout.setError(getString(R.string.wind_error_speed));
            return null;
        }
    }

    private String formatHeadTail(double headwind) {
        int magnitude = (int) Math.round(Math.abs(headwind));
        int resId = headwind >= 0 ? R.string.wind_headwind_fmt : R.string.wind_tailwind_fmt;
        return String.format(Locale.US, getString(resId), magnitude);
    }

    private String formatCrosswind(double crosswind) {
        int magnitude = (int) Math.round(Math.abs(crosswind));
        if (magnitude == 0) {
            return getString(R.string.wind_crosswind_none);
        }
        int resId = crosswind > 0 ? R.string.wind_crosswind_right_fmt : R.string.wind_crosswind_left_fmt;
        return String.format(Locale.US, getString(resId), magnitude);
    }

    private void clearResults() {
        String placeholder = getString(R.string.result_placeholder);
        resultHeadTail.setText(placeholder);
        resultCrosswind.setText(placeholder);
    }

}
