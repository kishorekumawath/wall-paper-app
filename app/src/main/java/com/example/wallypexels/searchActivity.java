package com.example.wallypexels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.ClipData;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class searchActivity extends AppCompatActivity {
    SearchView searchView;
    private List<ClipData.Item> categorylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_actitivy);
        categorylist = new ArrayList<>();
        searchView = findViewById(R.id.searchview);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });
        categoryitemlist();
    }

    private void categoryitemlist() {
        categorylist.add(new ClipData.Item("Abstract"));
        categorylist.add(new ClipData.Item("Amoled"));
        categorylist.add(new ClipData.Item("Animal"));
        categorylist.add(new ClipData.Item("Art"));
        categorylist.add(new ClipData.Item("Car"));
        categorylist.add(new ClipData.Item("Horror"));
        categorylist.add(new ClipData.Item("Colorful background"));
        categorylist.add(new ClipData.Item("Flower"));
        categorylist.add(new ClipData.Item("food"));
        categorylist.add(new ClipData.Item("Gradient"));
        categorylist.add(new ClipData.Item("Material"));
        categorylist.add(new ClipData.Item("Music"));
        categorylist.add(new ClipData.Item("nature"));
        categorylist.add(new ClipData.Item("pattern"));
        categorylist.add(new ClipData.Item("Road"));
        categorylist.add(new ClipData.Item("Sad"));
        categorylist.add(new ClipData.Item("Space"));
        categorylist.add(new ClipData.Item("Technology"));
        categorylist.add(new ClipData.Item("Weapon"));
        
    }

    public void filterlist(String text){
        List<ClipData.Item> filteredList = new ArrayList<>();
        for (ClipData.Item item : categorylist){
//            if(item.getText().contains(text.toLowerCase())){
//                filteredList.add(item);
//            }
        }

    }
}