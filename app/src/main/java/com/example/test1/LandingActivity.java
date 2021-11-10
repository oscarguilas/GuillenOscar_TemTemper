package com.example.test1;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.test1.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        BottomNavigationView bottomNav = findViewById(R.id.main_menu);

        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_list:
                    selectedFragment = new ListFragment();
                    break;

                case R.id.nav_form:
                    selectedFragment = new FormFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });

/*
     binding = ActivityLandingBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
*/

    }

    public void goHome(View v){
        Fragment home = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,home).commit();
    }

    public void goList(View v){
        Fragment list = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,list).commit();
    }

    public void goForm(View v){
        Fragment form = new FormFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,form).commit();
    }

}