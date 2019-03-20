package com.example.nas19_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> menu;

    public MenuAdapter(Context context, int resource, List<MenuItem> objects) {
        super(context, resource, objects);

        menu = (ArrayList<MenuItem>) objects;
    }

    // Render the details of this menu item.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_list_item, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.menuListName)).setText(menu.get(position).getName());

        // Format the price
        ((TextView) convertView.findViewById(R.id.menuListPrice)).setText(
                String.format("€%s0", menu.get(position).getPrice())
        );
        // Load the image
        Picasso.get().load(menu.get(position).getImageUrl()).into(
                (ImageView)convertView.findViewById(R.id.menuListImage)
        );

        return convertView;
    }
}
