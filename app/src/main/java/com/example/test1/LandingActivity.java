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

import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test1.DB.MatchDBHelper;
import com.example.test1.Model.Match;

public class LandingActivity extends AppCompatActivity {

    private MatchDBHelper dbHelper;
    private SQLiteDatabase db;


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
        //dbHelper initialization to be later used in recordMatch()
        dbHelper = new MatchDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();


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

    //recordMatch is called from the button in the form id=insert_button
    //It creates an entry for a match in the SQLite db
    public void recordMatch(View v){
        EditText opp_name = findViewById(R.id.opp_name_text);
        Spinner draftside = findViewById(R.id.draftside_spinner);
        EditText rating = findViewById(R.id.rating_text);
        Spinner result = findViewById(R.id.result_spinner);

        //TextView debug = findViewById(R.id.debug_text);
        //debug.setText("" + opp_name.getText().toString() + "\n" + rating.getText().toString());

        if(opp_name.getText().toString() == null || rating.getText().toString() == null
                || opp_name.getText().toString().equals("") || rating.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill the text fields.", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Match input = new Match(opp_name.getText().toString(), draftside.getSelectedItem().toString(), rating.getText().toString(), result.getSelectedItem().toString());
            dbHelper.insertMatch(db,input);
            Toast toast = Toast.makeText(getApplicationContext(), "Successfully added new match", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}