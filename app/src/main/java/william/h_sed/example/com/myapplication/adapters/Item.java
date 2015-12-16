package william.h_sed.example.com.myapplication.adapters;

/**
 * Created by hsedghinezhad on 16/12/2015.
 */
public class Item {
    String imageURL;
    String title;

    public Item(String imageUrl, String title) {
        super();
        this.imageURL = imageUrl;
        this.title = title;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String image) {
        this.imageURL = image;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


}