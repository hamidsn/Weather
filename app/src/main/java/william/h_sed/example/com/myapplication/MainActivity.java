package william.h_sed.example.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import william.h_sed.example.com.myapplication.adapters.CustomGridViewAdapter;
import william.h_sed.example.com.myapplication.adapters.Item;
import william.h_sed.example.com.myapplication.mappings.Weather;

import static william.h_sed.example.com.myapplication.ApiApplication.getAppContext;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.toolbar_progress_bar);
        showFetchingProgress(true);



        // The app only needs to make a single call at launch
        callAPI();
    }

    private void callAPI() {
        //First we need a listener to errors, It can be in a different class
        // when we have lots of error codes to handle
        final Response.ErrorListener errorHandler = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //todo: Handle error better than showing a single message
                showErrorReason(volleyError.toString());
                showFetchingProgress(false);
            }
        };
        showFetchingProgress(true);
        // A different class to call the api(s)
        // We pass listeners and api parameters
        ApiUtils.getWeatherData(new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather response) {
                // Now background task is done, we need to update some foreground stuff
                // All we need in in response which is a Weather class that we have access to it
                if (response != null) {
                    String[] imageUri ,date;
                    int numberOfForecastdays = response.getMyForecast().getMyTxt_forecast().getForecastDay().size();
                    imageUri = new String[numberOfForecastdays];
                    date = new String[numberOfForecastdays];

                    for(int i = 0; i< numberOfForecastdays ; i++){
                        imageUri[i] = response.getMyForecast().getMyTxt_forecast().getForecastDay().get(i).getMyIcon_url();
                        date[i] = response.getMyForecast().getMyTxt_forecast().getForecastDay().get(i).getMyTitle();
                    }
                    updateUI(response.getMyForecast().getMyTxt_forecast().getMyDate() , numberOfForecastdays , imageUri , date);

                } else {
                    showErrorReason(getString(R.string.general_error));
                }
            }
        }, errorHandler);
    }

    private void updateUI(String myDate, int numberOfForecastdays, String[] imageUri, String[] date) {
        //Managing of User Interface
        for(int i = 0; i< numberOfForecastdays ; i++){
            gridArray.add(new Item(imageUri[i],date[i]));
        }


        showFetchingProgress(false);
        TextView today = (TextView) findViewById(R.id.date);
        today.setText(myDate);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);
    }

    private void showFetchingProgress(boolean isFetching) {
        // To show a progressBar
        progressBar.setVisibility(isFetching ? View.VISIBLE : View.GONE);
    }

    private void showErrorReason(String message) {
        Toast.makeText(getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

}
