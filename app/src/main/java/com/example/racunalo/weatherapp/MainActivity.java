package com.example.racunalo.weatherapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.racunalo.weatherapp.model.Forecast;

import org.json.JSONException;

public class MainActivity extends Activity {

    TextView cityText;
    TextView condDescr;
    TextView temp;
    TextView hum;
    TextView press;
    TextView windSpeed;
    TextView windDeg;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);

        String city = "Rome,IT";
        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);
       /* imgView = (ImageView) findViewById(R.id.condIcon);*/
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});


    }


    private class JSONWeatherTask extends AsyncTask<String, Void, Forecast> {

        @Override
        protected Forecast doInBackground(String... params) {


            Forecast forecast = new Forecast();
            String data = ((new HttpClient()).getWeatherData(params[0]));

            try {
                forecast = JSONParser.getWeather(data);

                // Let's retrieve the icon
               // forecast.iconData = ((new HttpClient()).getImage(forecast.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return forecast;

        }


        @Override
        protected void onPostExecute(Forecast forecast) {

            super.onPostExecute(forecast);
/*
            if (forecast.iconData != null && forecast.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(forecast.iconData, 0, forecast.iconData.length);
                imgView.setImageBitmap(img);
            }*/

            cityText.setText(forecast.location.getCity() + "," + forecast.location.getCountry());
            condDescr.setText(forecast.currentCondition.getCondition() + "(" + forecast.currentCondition.getDescr() + ")");
            temp.setText("" + Math.round((forecast.currentCondition.getTemp() - 273.15)) + "�C");
            hum.setText("" + forecast.currentCondition.getHumidity() + "%");
            press.setText("" + forecast.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + forecast.currentCondition.getSpeed() + " mps");
            windDeg.setText("" + forecast.currentCondition.getDeg() + "�");
        }


    }


}