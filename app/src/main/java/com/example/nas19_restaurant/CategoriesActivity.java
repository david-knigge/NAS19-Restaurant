package com.example.nas19_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ArrayList<String> categories;

    private class onCategoryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedCategory = categories.get(position);
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.putExtra("category", clickedCategory);
            startActivity(intent);
        }
    }

    // On creation of the activity, start categories request
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest req = new CategoriesRequest(this);
        req.getCategories(this);
        setTitle("Categories");
    }

    // Set categories adapter, onclick listener
    @Override
    public void gotCategories(ArrayList<String> categories) {
        this.categories = categories;
        ((ListView)findViewById(R.id.categoriesList)).setAdapter(
                new CategoriesAdapter(this, R.id.categoriesList, categories)
        );
        ((ListView)findViewById(R.id.categoriesList)).setOnItemClickListener(
                new onCategoryClickListener()
        );
    }

    // Show error
    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }
}
