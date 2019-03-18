package com.example.nas19_restaurant;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray items = response.getJSONArray("items");
            ArrayList<MenuItem> menu = new ArrayList<>();
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                menu.add(new MenuItem(
                        item.getString("name"),
                        item.getString("description"),
                        item.getString("image_url"),
                        item.getString("price"),
                        item.getString("category")
                ));
            }
            activity.gotMenu(menu);
        } catch (JSONException e) {
            activity.gotMenuError(e.getMessage());
        }

    }

    private Context context;
    private Callback activity;

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    public MenuRequest(Context context) {
        this.context = context;
    }

    public void getMenu(Callback activity, String category) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?categories=" + category, null, this, this);
        queue.add(jsonObjectRequest);
    }
}
