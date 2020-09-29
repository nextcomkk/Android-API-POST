package jp.co.nextcom.postapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.postapi.R;

public class SettingActivity extends AppCompatActivity {
    EditText descriptionEditText;
    EditText endpointEditText;
    EditText authorizationEditText;
    EditText delayEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        descriptionEditText = findViewById(R.id.descriptionEditText);
        endpointEditText = findViewById(R.id.endpointEditText);
        authorizationEditText = findViewById(R.id.authorizationEditText);
        delayEditText = findViewById(R.id.delayEditText);
        saveButton = findViewById(R.id.saveButton);

        String description = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.DESCRIPTION), "");
        descriptionEditText.setText(description);

        String endpoint = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.ENDPOINNT), "");
        endpointEditText.setText(endpoint);

        String auth = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.AUTH), "");
        authorizationEditText.setText(auth);

        int delay = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt(getResources().getString(R.string.DELAY), 3);
        delayEditText.setText(String.valueOf(delay));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newDescription = descriptionEditText.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getResources().getString(R.string.DESCRIPTION), newDescription).apply();

                String newEndpoint = endpointEditText.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getResources().getString(R.string.ENDPOINNT), newEndpoint).apply();

                String newAuth = authorizationEditText.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getResources().getString(R.string.AUTH), newAuth).apply();

                String newDelay = delayEditText.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt(getResources().getString(R.string.DELAY), Integer.parseInt(newDelay)).apply();

                onBackPressed();
            }
        });
    }
}