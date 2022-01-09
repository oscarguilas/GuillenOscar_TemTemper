package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.test1.ListFragment;

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

    public void goList(){
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
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill the text fields", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Match input = new Match(opp_name.getText().toString(), draftside.getSelectedItem().toString(), rating.getText().toString(), result.getSelectedItem().toString());
            dbHelper.insertMatch(db,input);
            Toast toast = Toast.makeText(getApplicationContext(), "Successfully added new match", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    //This function is called by the "WIPE LIST" button in ListFragment()
    public void wipeList(View view){
        //First we create a confirmation window to make sure they didn't misclick the button.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation prompt");
        builder.setMessage("Are you sure you want to wipe the contents of the list?")
                //Accept button
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //As the user has accepted, we commit to wiping the matches by calling dbHelper
                        dbHelper.wipeAllMatches(db);
                        //Reloads the ListFragment by calling a transaction to "go" to the ListFragment, reflecting the wipe to the user
                        goList();
                        //Confirms the transaction via Toast
                        Toast toast = Toast.makeText(getApplicationContext(), "The wipe was successful", Toast.LENGTH_LONG);
                        toast.show();
                    }
                })
                //Decline button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Nothing happens, returns to screen as it was
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        db.close();
        super.onDestroy();
    }
}