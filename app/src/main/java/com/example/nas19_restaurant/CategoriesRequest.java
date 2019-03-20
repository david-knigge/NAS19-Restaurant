package com.example.nas19_restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Add all categories to an arraylist, return it. Pass error to activity on JSONException.
    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray categories = response.getJSONArray("categories");
            ArrayList<String> extractedCategories = new ArrayList();

            for (int i=0; i<categories.length(); i++) {
                extractedCategories.add(categories.getString(i));
            }
            activity.gotCategories(extractedCategories);
        } catch (org.json.JSONException e) {
            activity.gotCategoriesError(e.getMessage());
        }
    }

    // Pass error to activity.
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Request the categories
    public void getCategories(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

}
