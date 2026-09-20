package com.example.pilotbuddy;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

/**
 * Shared setup for all screens: applies system-bar padding for edge-to-edge
 * layouts and wires the toolbar's up navigation to onBackPressed().
 */
public abstract class BaseActivity extends AppCompatActivity {

    /** Pads the given root view so content is not drawn under the system bars. */
    protected void applyEdgeToEdgeInsets(View root) {
        ViewCompat.setOnApplyWindowInsetsListener(root, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars()
                        | WindowInsetsCompat.Type.ime());
                v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                return insets;
            }
        });
    }

    /** Makes the toolbar's navigation icon act as an up/back control. */
    protected void setupToolbarBack(MaterialToolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
