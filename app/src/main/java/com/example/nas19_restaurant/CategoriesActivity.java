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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest req = new CategoriesRequest(this);
        req.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();
        this.categories = categories;
        ((ListView)findViewById(R.id.categoriesList)).setAdapter(
                new CategoriesAdapter(this, R.id.categoriesList, categories)
        );
        ((ListView)findViewById(R.id.categoriesList)).setOnItemClickListener(
                new onCategoryClickListener()
        );
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }
}
