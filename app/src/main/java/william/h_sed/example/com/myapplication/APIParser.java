package william.h_sed.example.com.myapplication;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import william.h_sed.example.com.myapplication.mappings.Weather;

/**
 * Created by hsedghinezhad on 16/12/2015.
 */
public class APIParser {
    public static Weather parseWeather(JSONObject jsonObject) throws JSONException {

        return new Gson().fromJson(jsonObject.toString(), Weather.class);
    }
}