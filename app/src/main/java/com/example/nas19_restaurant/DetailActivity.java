package com.example.nas19_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");

        setItemDetails(item);
    }

    private void setItemDetails(MenuItem item) {
        ((TextView) findViewById(R.id.detailItemTitle)).setText(item.getName());
        ((TextView) findViewById(R.id.detailItemDescription)).setText(item.getDescription());
        ((TextView) findViewById(R.id.detailItemPrice)).setText(
                String.format("€%s0", item.getPrice())
        );
        Picasso.get().load(item.getImageUrl()).into((ImageView)findViewById(R.id.detailItemImage));
    }

    public void orderClicked(View v){

    }
}
