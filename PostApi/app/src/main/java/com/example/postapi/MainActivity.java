package com.example.postapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postapi.api.APIInterface;
import com.example.postapi.api.APIService;
import com.example.postapi.model.Request;
import com.example.postapi.model.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button postButton;
    private Button settingButton;

    String endpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        postButton = findViewById(R.id.button);
        settingButton = findViewById(R.id.settingButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "clicked");
                String userid = editText.getText().toString();
                try {
                    getPoint(userid);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        endpoint = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.ENDPOINNT), "");
    }

    void getPoint(String userid) throws UnsupportedEncodingException {
        if (endpoint.isEmpty()) {
            Toast.makeText(getApplicationContext(), "set end point", Toast.LENGTH_LONG).show();
            return;
        }
        APIInterface apiInterface = APIService.createService(APIInterface.class);
        Request request = new Request(userid);
        Call<Response> call = apiInterface.getPoint(endpoint, request);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("test", String.valueOf(response.body()));
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra(getString(R.string.NAME), response.body().getUsername());
                    intent.putExtra(getString(R.string.POINT), response.body().getPoint());
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("test", String.valueOf(t));
            }
        });
    }
}