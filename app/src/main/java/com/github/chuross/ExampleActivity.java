package com.github.chuross;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.chuross.widget.ToggleExtraTextView;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        final ToggleExtraTextView toggleText = (ToggleExtraTextView) findViewById(R.id.toggle_txt);
        toggleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleText.toggle();
            }
        });

        findViewById(R.id.waiting_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleText.getState().equals(ToggleExtraTextView.State.WAITING)) {
                    toggleText.setState(ToggleExtraTextView.State.IDLE);
                } else {
                    toggleText.setState(ToggleExtraTextView.State.WAITING);
                }
            }
        });
    }
}
