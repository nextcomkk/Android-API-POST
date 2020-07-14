package com.example.postapi;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postapi.api.APIInterface;
import com.example.postapi.api.APIService;
import com.example.postapi.model.Response;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button postButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.endpointEditText);
        postButton = findViewById(R.id.button);

        String userid = "yZ2Xl3WdihZMQ4sF4IT01HJIBl5yJCPlrz2Ppg09iC9ZSTk99aI2Pmq0ptw98xEPBNvrbNqYRTSyHTZRb5zI4WGeibA5U1bU8uyey27vUTl5D1tGEO7c85UOgqianueZS7AmvVti7dXlLLKqR3Bf49WcKT8iU7iAOvmGBhkSvYe4hIdEP1st295TlxMMXkya4xGpzeZKTSUf414bPDC3OZIPADueEI5kAeaUcXfKcSKjoZYuCL6uRFvfY1Z6TUf";
        Log.d("test", userid);
        editText.setText(userid);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("aaa", String.valueOf(s.toString().contains("\n")));
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "clicked");
                String userid = editText.getText().toString();
                getPoint(userid);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    void getPoint(String request) {
        String endpoint = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.ENDPOINNT), "");
        String auth = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.AUTH), "");
        if (endpoint.isEmpty()) {
            Toast.makeText(getApplicationContext(), "set end point", Toast.LENGTH_LONG).show();
            return;
        }
        APIInterface apiInterface = APIService.createService(APIInterface.class,auth);
        Call<Response> call = apiInterface.getPoint(endpoint, generateFromString(request));
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra(getString(R.string.NAME), response.body().getUser_lastname());
                    intent.putExtra(getString(R.string.POINT), response.body().getUser_hold_point());
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("test", String.valueOf(t));
            }
        });
    }

    JsonObject generateFromString(String string) {
        JsonObject jsonObject = new JsonObject();
        String[] parameters = string.split("&");
        for (String s : parameters) {
            String[] parameter = s.split("=");
            if (parameter[0].equals("point")) {
                jsonObject.addProperty(parameter[0],Integer.parseInt(parameter[1]));
            } else {
                jsonObject.addProperty(parameter[0], parameter[1]);
            }

        }
        return jsonObject;
    }
}