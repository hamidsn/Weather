package william.h_sed.example.com.myapplication;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import william.h_sed.example.com.myapplication.mappings.Weather;

/**
 * Created by hsedghinezhad on 16/12/2015.
 * I use Volley for making network calls and GSON for parsing response with dependencies
 */
public class ApiUtils {

    public static final String UTF8 = "UTF-8";
    private final static RequestQueue myMainRequestQueue = Volley
            .newRequestQueue(ApiApplication.getAppContext());


    public static void getWeatherData(final Response.Listener<Weather> listener, Response.ErrorListener errorListener) {
        //Creating URI follow the URI methods
        String url = ApiApplication.getAppContext().getString(R.string.base_url)
                + ApiApplication.getAppContext().getString(R.string.api_key) +
                ApiApplication.getAppContext().getString(R.string.method_guid) +
                getGeoLocation() + ApiApplication.getAppContext().getString(R.string.fileFormat);

        Request<Weather> request = new Request<Weather>(
                Request.Method.GET, url, errorListener) {

            @Override
            protected Response<Weather> parseNetworkResponse(NetworkResponse networkResponse) {

                try {
                    String jsonString = new String(networkResponse.data, UTF8);
                    JSONObject jsonObject = new JSONObject(jsonString);

                    //Parse response in a different class
                    //When we have multiple API calls we send them to this class to parse
                    Weather weather = APIParser.parseWeather(jsonObject);
                    //Send data back to UI thread
                    //Data fetching is done, lets back to the UI
                    //Data binding principals in progress
                    return Response.success(weather,
                            HttpHeaderParser.parseCacheHeaders(networkResponse));
                } catch (JSONException e) {
                    Log.e(ApiUtils.class
                            .getSimpleName(), "Failed communication with API.  [" + e + "]");
                } catch (UnsupportedEncodingException e) {
                    Log.e(ApiUtils.class.getSimpleName(), "A valid json string" +
                            "was not received from the server [" + e + "]");

                }
                //An error occured
                listener.onResponse(null);
                return null;
            }

            @Override
            protected void deliverResponse(Weather weather) {
                listener.onResponse(weather);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        //This queue will manage future API calls when we have multiple calls
        myMainRequestQueue.add(request);
    }

    private static String getGeoLocation() {
        return ApiApplication.getAppContext().getString(R.string.harcoded_location);
    }
}