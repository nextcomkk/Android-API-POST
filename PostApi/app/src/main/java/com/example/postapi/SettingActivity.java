package com.example.postapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.PreferenceChangeEvent;

public class SettingActivity extends AppCompatActivity {
    EditText endpointEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        endpointEditText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);

        String endpoint = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.ENDPOINNT), "");
        endpointEditText.setText(endpoint);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEndpoint = endpointEditText.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getResources().getString(R.string.ENDPOINNT), newEndpoint).apply();
                onBackPressed();
            }
        });
    }
}