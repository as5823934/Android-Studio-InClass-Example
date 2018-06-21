package com.example.huntertsai.mydictionary;

/**
 * Created by huntertsai on 2018-01-25.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String>{


    private final Activity context;
    private List itemname;
    private int imgid;

    public CustomListAdapter(Activity context, List itemname, int imgid) {
        super(context, R.layout.list_layout, itemname);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_layout, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.list_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image);

        txtTitle.setText(itemname.get(position).toString());
        imageView.setImageResource(imgid);

        return rowView;
    }

}
