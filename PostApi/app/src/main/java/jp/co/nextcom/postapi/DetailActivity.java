package jp.co.nextcom.postapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.postapi.R;

import java.util.Timer;
import java.util.TimerTask;

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