package com.example.racunalo.weatherapp;


import com.example.racunalo.weatherapp.model.Forecast;
import com.example.racunalo.weatherapp.model.Location;
//import android.Location;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {


    public static Forecast getWeather(String data) throws JSONException {

        Forecast forecast = new Forecast();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        // We start extracting the info
        Location loc = new Location();

        JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        loc.setCountry(getString("country", sysObj));
        loc.setCity(getString("name", jObj));

        forecast.location = loc;

        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("weather");

        // We use only the first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        forecast.currentCondition.setWeatherId(getInt("id", JSONWeather));
        forecast.currentCondition.setDescr(getString("description", JSONWeather));
        forecast.currentCondition.setCondition(getString("main", JSONWeather));
        forecast.currentCondition.setIcon(getString("icon", JSONWeather));

        JSONObject mainObj = getObject("main", jObj);
        forecast.currentCondition.setHumidity(getInt("humidity", mainObj));
        forecast.currentCondition.setPressure(getInt("pressure", mainObj));

        forecast.currentCondition.setTemp(getFloat("temp", mainObj));

        // Wind
        JSONObject wObj = getObject("wind", jObj);
        forecast.currentCondition.setSpeed(getFloat("speed", wObj));
        forecast.currentCondition.setDeg(getFloat("deg", wObj));



        // We download the icon to show


        return forecast;
    }

    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }






}
