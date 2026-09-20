package com.example.pilotbuddy;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * A {@link TextWatcher} that runs a single callback after text changes,
 * so callers don't have to implement the two unused methods each time.
 */
public class SimpleTextWatcher implements TextWatcher {

    private final Runnable callback;

    public SimpleTextWatcher(Runnable callback) {
        this.callback = callback;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // no-op
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // no-op
    }

    @Override
    public void afterTextChanged(Editable s) {
        callback.run();
    }
}
