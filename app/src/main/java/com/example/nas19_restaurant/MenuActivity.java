package com.example.nas19_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    ArrayList<MenuItem> menu;

    private class onMenuItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem clickedItem = menu.get(position);

            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("item", clickedItem);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String category = (String) getIntent().getExtras().get("category");
        MenuRequest req = new MenuRequest(this);
        req.getMenu(this, category);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
        ((ListView)findViewById(R.id.menuList)).setAdapter(
                new MenuAdapter(this, R.id.menuList, menu)
        );
        ((ListView)findViewById(R.id.menuList)).setOnItemClickListener(
                new onMenuItemClickListener()
        );
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }

}
