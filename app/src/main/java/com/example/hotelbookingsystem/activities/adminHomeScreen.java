package com.example.hotelbookingsystem.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;

public class adminHomeScreen extends AppCompatActivity {

    public static final String SHARED_PREF_NAME = "mypref";
    SharedPreferences sharedPreferences;
    Button  admin_logout;
    ImageButton admin_view, admin_search, admin_home;
    TextView tvName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
        getSupportActionBar().setTitle("Admin Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        admin_view = findViewById(R.id.admin_view);
        admin_search = findViewById(R.id.admin_search);
        admin_logout = findViewById(R.id.admin_logout);
        admin_home = findViewById(R.id.admin_viewHome);
        tvName = findViewById(R.id.admin_homepage_tv_name);
        String name = sharedPreferences.getString(MainActivity.KEY_FIRSTNAME, "");
        tvName.setText(name);


        admin_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminHomeScreen.this,adminProfile.class));
            }
        });
        admin_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminHomeScreen.this, searchGusetManager.class));
            }
        });

        admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminHomeScreen.this, MainActivity.class));
            }
        });

        admin_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminHomeScreen.this, adminHomeScreen.class));
            }
        });
    }
}
