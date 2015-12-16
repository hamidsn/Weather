package william.h_sed.example.com.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import william.h_sed.example.com.myapplication.R;

/**
 * Created by hsedghinezhad on 16/12/2015.
 */
public class CustomGridViewAdapter extends ArrayAdapter<Item> {
    Context context;
    int layoutResourceId;
    ArrayList<Item> data = new ArrayList<Item>();

    public CustomGridViewAdapter(Context context, int layoutResourceId,
                                 ArrayList<Item> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Item item = data.get(position);
        holder.txtTitle.setText(item.getTitle());
        // Loading online images by Picasso
        Picasso.with(context).load(item.getImageURL()).into(holder.imageItem);
        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;

    }
}