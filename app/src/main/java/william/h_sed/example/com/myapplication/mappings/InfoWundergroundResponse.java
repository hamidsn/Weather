package william.h_sed.example.com.myapplication.mappings;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hsedghinezhad on 16/12/2015.
 */
public class InfoWundergroundResponse implements Serializable {

    @SerializedName("version")
    private String myVersion;

    @SerializedName("termsofService")
    private String myTermsofService;

    @SerializedName("features")
    private Features myFeatures;

    public String getMyVersion() {
        return myVersion;
    }

    public void setMyVersion(String myVersion) {
        this.myVersion = myVersion;
    }

    public String getMyTermsofService() {
        return myTermsofService;
    }

    public void setMyTermsofService(String myTermsofService) {
        this.myTermsofService = myTermsofService;
    }

    public Features getMyFeatures() {
        return myFeatures;
    }

    public void setMyFeatures(Features myFeatures) {
        this.myFeatures = myFeatures;
    }

    public static class Features {
        @SerializedName("forecast")
        private String myForecast;

        public String getMyForecast() {
            return myForecast;
        }

        public void setMyForecast(String myForecast) {
            this.myForecast = myForecast;
        }
    }

}
