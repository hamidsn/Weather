package william.h_sed.example.com.myapplication.mappings;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hsedghinezhad on 16/12/2015.
 */
public class TxtForecastResponse implements Serializable {
    @SerializedName("date")
    private String myDate;

    @SerializedName("forecastday")
    private List<ForecastDayResponse> forecastDay;

    public String getMyDate() {
        return myDate;
    }

    public void setMyDate(String myDate) {
        this.myDate = myDate;
    }

    public List<ForecastDayResponse> getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(List<ForecastDayResponse> forecastDay) {
        this.forecastDay = forecastDay;
    }
}

