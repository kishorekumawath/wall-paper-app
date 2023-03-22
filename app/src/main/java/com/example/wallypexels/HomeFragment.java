package com.example.wallypexels;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<String> wallpaperlist;
    wallpaperAdapter wallpaperAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        RecyclerView rvwallpaper = view.findViewById(R.id.RVhomenav);
        rvwallpaper.setLayoutManager(new GridLayoutManager(getContext(),2));
        wallpaperlist = new ArrayList<>();
        wallpaperAdapter = new wallpaperAdapter(wallpaperlist,getContext());
        rvwallpaper.setAdapter(wallpaperAdapter);
        swipeRefreshLayout= view.findViewById(R.id.refreshhome);
//        bypexelx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent browerintent = new Intent(Intent.ACTION_VIEW);
////                browerintent.setData(Uri.parse("https://www.pexels.com/"));
////                browerintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
//            }
//        });
        getwallpaper();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getwallpaper();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }
    public void getwallpaper(){
        wallpaperlist.clear();
        int min = 1;
        int max = 50;
        int highmax = 100;
            int ran = (int) (Math.random() * (max - min + 1) + min);
        int ran2 = (int) (Math.random() * (highmax - max + 1) + max);
             String imgurl = "https://api.pexels.com/v1/search?query=wallpaper&per_page="+ran2+"&page="+ran;
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                        Toast.makeText(getContext(), "Error Occured "+ e, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "failed to load wallpaper", Toast.LENGTH_SHORT).show();
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