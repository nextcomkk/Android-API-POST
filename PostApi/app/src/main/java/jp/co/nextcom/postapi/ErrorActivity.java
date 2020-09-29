package jp.co.nextcom.postapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.postapi.R;

import java.util.Timer;
import java.util.TimerTask;

public class ErrorActivity extends AppCompatActivity {
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        nameTextView = findViewById(R.id.errorTextView);

        nameTextView.setText(String.format("%s", getIntent().getStringExtra(getResources().getString(R.string.NAME))));

        TimerTask task = new TimerTask() {
            public void run() {
                finish();
            }
        };

        Timer timer = new Timer();

        int delay = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt(getResources().getString(R.string.DELAY), 3);
        timer.schedule(task, delay * 1000);
    }
}