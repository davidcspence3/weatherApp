package com.example.weather_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView tempTV, jsonTV;
    public static EditText cityEditText, zipEditText;
    private Button getWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempTV = findViewById(R.id.tempTV);
        jsonTV = findViewById(R.id.jsonFeedTV);
        cityEditText = findViewById(R.id.cityEditText);
        zipEditText = findViewById(R.id.zipEditText);

        getWeather = findViewById(R.id.getWeatherButton);

        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetJsonData getJsonData = new GetJsonData();
                getJsonData.execute();
            }
        });


    }
}
