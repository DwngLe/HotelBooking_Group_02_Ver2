package com.example.hotelbookingsystem.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;


public class userHomeScreen extends AppCompatActivity {
//    FrameLayout search_room;
    EditText heading;
    SharedPreferences sharedpreferences;

    ImageButton search_room, view_profile, logout, view_reservations, view_pending,view_history;

    public static final String SHARED_PREF_NAME = "mypref";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhomepage);
        getSupportActionBar().setTitle("Home");

        search_room = findViewById(R.id.imageButton);
        view_profile = findViewById(R.id.imgbtnProfile);
        view_pending = findViewById(R.id.imageList);
        view_history = findViewById(R.id.imageHistory);
        view_reservations = findViewById(R.id.imageHistory);
        logout = findViewById(R.id.imgbtnHome);

        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        final String keyRole = sharedpreferences.getString(MainActivity.KEY_USERNAME,"");
//        System.out.println(keyRole);

        view_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userHomeScreen.this,pendingRoomScreen.class));
            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userHomeScreen.this,viewProfile.class));
            }
        });
        view_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userHomeScreen.this, UserHistoryBooking.class));
            }
        });

//        view_reservations.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(userHomeScreen.this,reservation_summary_guest_Activity.class));
//            }
//        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userHomeScreen.this,userHomeScreen.class));
            }
        });

        search_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userHomeScreen.this,searchRoomScreen.class));
            }
        });

    }
}
