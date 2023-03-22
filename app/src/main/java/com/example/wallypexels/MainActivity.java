package com.example.wallypexels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
  BottomNavigationView bottomNavigationView;
  Toolbar toolbar;
  ImageView profilepage;
  DrawerLayout drawerLayout;
  NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilepage = findViewById(R.id.profilepage);
        profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,loginSignup.class);
                startActivity(intent);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomnav);
       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 Fragment fragment =null;
               switch (item.getItemId()){
                   case R.id.menu_home :
                       fragment = new HomeFragment();
                       break;
                   case   R.id.menu_category:
                       fragment = new CategoryFragment();
                       break;
               }
               getSupportFragmentManager().beginTransaction().replace(R.id.bodycontainer,fragment).commit();
               return true;
           }
       });
       bottomNavigationView.setSelectedItemId(R.id.menu_home);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this,loginSignup.class));
                        break;
                }
                return true;
            }
        });

    }
}