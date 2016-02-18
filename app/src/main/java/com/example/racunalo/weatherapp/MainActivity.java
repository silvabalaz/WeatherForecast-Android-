package com.example.racunalo.weatherapp;

import android.app.Activity;
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
        imgView = (ImageView) findViewById(R.id.condIcon);
        //JSONWeatherTask task = new JSONWeatherTask();
        //task.execute(new String[]{city});



    }


}
