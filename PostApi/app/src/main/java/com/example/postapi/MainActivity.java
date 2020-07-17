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

        String title = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.TITLE), "");
        getSupportActionBar().setTitle(title);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("input", s.toString());
                if (s.toString().contains("\n")) {
                    String body = s.toString().replace("\n", "");
                    getPoint(body);
                    editText.setText("");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        editText.requestFocus();
    }

    void getPoint(final String request) {
        String endpoint = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.ENDPOINNT), "");
        String auth = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.AUTH), "");
        if (endpoint.isEmpty()) {
            Toast.makeText(getApplicationContext(), "set end point", Toast.LENGTH_LONG).show();
            return;
        }
        APIInterface apiInterface = APIService.createService(APIInterface.class, auth);
        JsonObject requestObject = generateFromString(request);
        if (requestObject == null) {
            Log.e("Request", "Jsonを作成できませんでした。");
            return;
        }
        Call<Response> call = apiInterface.getPoint(endpoint, requestObject);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("!!!!!!!!", response.body().getResult());
                    if(response.body().getResult().equals("error")) {
                        Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                        intent.putExtra(getString(R.string.NAME), response.body().getMessage());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                        intent.putExtra(getString(R.string.NAME), response.body().getData().getUser_lastname());
                        intent.putExtra(getString(R.string.POINT), response.body().getData().getUser_hold_point());
                        startActivity(intent);
                    }
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
            if (parameter.length < 2) {
                return null;
            }
            if (parameter[0].equals("point")) {
                jsonObject.addProperty(parameter[0], Integer.parseInt(parameter[1]));
            } else {
                jsonObject.addProperty(parameter[0], parameter[1]);
            }

        }
        return jsonObject;
    }
}