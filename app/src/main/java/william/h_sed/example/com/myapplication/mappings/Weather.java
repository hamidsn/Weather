package william.h_sed.example.com.myapplication.mappings;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hsedghinezhad on 16/12/2015.
 */
public class Weather implements Serializable {

    @SerializedName("response")
    private InfoWundergroundResponse myResponse;

    @SerializedName("forecast")
    private Forcast myForecast;

    public InfoWundergroundResponse getMyResponse() {
        return myResponse;
    }

    public void setMyResponse(InfoWundergroundResponse myResponse) {
        this.myResponse = myResponse;
    }

    public Forcast getMyForecast() {
        return myForecast;
    }

    public void setMyForecast(Forcast myForecast) {
        this.myForecast = myForecast;
    }
}
