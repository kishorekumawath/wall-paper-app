package com.example.wallypexels;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {
   // RecyclerView colorRV;
   // colorcategoryadapter wallpaperAdapter;
   // ArrayList<String> colorRVlist,colorsRV;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView CategoryRV;
    private ArrayList<String> categorylist;
    categoryAdapter categoryAdapter ;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_category, container, false);
        CategoryRV = view.findViewById(R.id.CategoryView);
        categorylist = new ArrayList<>();
        categoryitemlist();
        categoryAdapter = new categoryAdapter(getContext() ,categorylist);
        CategoryRV.setLayoutManager(new GridLayoutManager(getContext(),3));
        CategoryRV.setAdapter(categoryAdapter);

        return view;
    }


private void categoryitemlist() {
    categorylist.add("Abstract");
    categorylist.add("Amoled");
    categorylist.add("Animal");
    categorylist.add("Art");
    categorylist.add("Car");
    categorylist.add("Horror");
    categorylist.add("Colorful background");
    categorylist.add("Flower");
    categorylist.add("food");
    categorylist.add("Gradient");
    categorylist.add("Material");
    categorylist.add("Music");
    categorylist.add("nature");
    categorylist.add("pattern");
    categorylist.add("Road");
    categorylist.add("Sad");
    categorylist.add("Space");
    categorylist.add("Technology");
    categorylist.add("Weapon");
}

}