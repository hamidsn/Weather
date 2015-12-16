package william.h_sed.example.com.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by hsedghinezhad on 16/12/2015.
 */
public class ApiApplication extends Application {

    private static Context myContext;

    @Override
    public void onCreate() {
        super.onCreate();
        myContext = this;
    }

    public static Context getAppContext() {
        return myContext;
    }
}
