package com.example.nas19_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesAdapter extends ArrayAdapter<ArrayList> {

    private ArrayList<String> categories;

    public CategoriesAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        categories = (ArrayList<String>) objects;
    }

    // Fill category list item with its title
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categories_list_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.categoriesListTitle)).setText(categories.get(position));

        return convertView;
    }
}
