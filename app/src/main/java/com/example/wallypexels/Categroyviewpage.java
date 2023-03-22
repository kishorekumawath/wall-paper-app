package com.example.wallypexels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Categroyviewpage extends AppCompatActivity {
    RecyclerView CategoryPageRV;
    wallpaperAdapter wallpaperAdapter;
    ArrayList<String> wallpaperlist;
    TextView toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroyviewpage);
        toolbar = findViewById(R.id.titleCV);
        String CategoryName = getIntent().getStringExtra("CategoryName");
        toolbar.setText(CategoryName);
        CategoryPageRV = findViewById(R.id.CategoryPageRV);
        CategoryPageRV.setLayoutManager(new GridLayoutManager(Categroyviewpage.this,2));
        wallpaperlist = new ArrayList<>();
        getwallpaper(CategoryName);
        wallpaperAdapter = new wallpaperAdapter(wallpaperlist,Categroyviewpage.this);
        CategoryPageRV.setAdapter(wallpaperAdapter);

    }
    public void getwallpaper(String Cname){
        wallpaperlist.clear();
        int min = 1;
        int max = 50;
        int highmax = 100;
        int ran = (int) (Math.random() * (max - min + 1) + min);
        int ran2 = (int) (Math.random() * (highmax - max + 1) + max);
        String imgurl = "https://api.pexels.com/v1/search?query=" + Cname + "&per_page="+ran2+"&page=1";
        RequestQueue requestQueue = Volley.newRequestQueue(Categroyviewpage.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, imgurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray photoarray = response.getJSONArray("photos");
                    for (int i = 0; i < photoarray.length(); i++) {
                        JSONObject photoobj = photoarray.getJSONObject(i);
                        String imgurl = photoobj.getJSONObject("src").getString("portrait");
                        wallpaperlist.add(imgurl);
                    }
                    wallpaperAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(Categroyviewpage.this, "Error Occured "+ e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Categroyviewpage.this, "failed to load wallpaper", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Authorization", "563492ad6f917000010000018114f73fe61941f383995c06b2b9a183");
                return hashMap;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }
}