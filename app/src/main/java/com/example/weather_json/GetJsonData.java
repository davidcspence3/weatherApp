package com.example.weather_json;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.weather_json.MainActivity.cityEditText;
import static com.example.weather_json.MainActivity.zipEditText;

public class GetJsonData extends AsyncTask<Void, Void, Void> {



    String city = cityEditText.getText().toString();
    String zip = zipEditText.getText().toString();




    public String data = "", line = "", name = "";
    int temp, minTemp, maxTemp;

    @Override
    protected Void doInBackground(Void... voids) {

        //create URL to get data or Json feed
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=8c33553bb85655e4488f0d62f303295d");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jsonObject = new JSONObject(data);
            JSONObject main = jsonObject.getJSONObject("main");

            name = jsonObject.getString("name");
            double Kelvintemp = main.getDouble("temp");
            temp = (int)((Kelvintemp - 273.15) * 1.8 + 32);
            //minTemp = main.getDouble("temp_min");
            //maxTemp = main.getDouble("temp_max");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //open connection to this URL

        //InputStream

        //BufferReader

        //Loop through the feed

        //Get information from the feed


        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.jsonTV.setText(data);
        MainActivity.tempTV.setText("City:\t" + name + "\nTemp:\t" + temp);

        Log.d("Json Feed", name + " Temp: " + temp);
    }

    public boolean isDigits(String number){
        if(!TextUtils.isEmpty(number)){
            return TextUtils.isDigitsOnly(number);
        }else{
            return false;
        }
    }
}

