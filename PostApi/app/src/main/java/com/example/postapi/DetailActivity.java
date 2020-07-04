package com.example.postapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView pointTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameTextView = findViewById(R.id.nameTextView);
        pointTextView = findViewById(R.id.pointTextView);

        nameTextView.setText(String.format("%s様", getIntent().getStringExtra(getResources().getString(R.string.NAME))));
        pointTextView.setText(String.valueOf(getIntent().getIntExtra(getResources().getString(R.string.POINT), 0)));
    }
}