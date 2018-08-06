package com.example.a16022774.p12_mydatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Item> item;

    public CustomAdapter(Context context, int resource, ArrayList<Item> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        item = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInfalter object
        LayoutInflater inflater = (LayoutInflater) parent_context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate a new view hierarchy from the specified xml resource (layout_id)
        // and return the root View of the inflated hierarchy.
        View rowView = inflater.inflate(layout_id, parent, false);

        ImageView iv = (ImageView) rowView.findViewById(R.id.iv);
        TextView tv = (TextView) rowView.findViewById(R.id.tv);

        Item currentItem = item.get(position);
        tv.setText(currentItem.getText());
        if(currentItem.getText().equalsIgnoreCase("Bio")){
            iv.setImageResource(android.R.drawable.ic_dialog_info);
        }else if (currentItem.getText().equalsIgnoreCase("Vaccination")){
            iv.setImageResource(android.R.drawable.ic_menu_edit);
        }else if (currentItem.getText().equalsIgnoreCase("Anniversary")){
            iv.setImageResource(android.R.drawable.ic_menu_my_calendar);
        }else{
            iv.setImageResource(android.R.drawable.star_big_on);
        }

        //return the View corresponding to the data at the specified position.
        return rowView;
    }
}
