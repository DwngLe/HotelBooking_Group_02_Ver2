package com.example.hotelbookingsystem.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;

public class ManagerHomescreen extends AppCompatActivity {
    ImageButton manager_profile, manager_viewList, manager_availableRooms, manager_searchRoom, logout;
    Button btnAddRoom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managerhomepage);

        manager_profile = findViewById(R.id.manager_profile);
        manager_viewList = findViewById(R.id.manager_listReservation);
        manager_availableRooms = findViewById(R.id.manager_available);
        manager_searchRoom = findViewById(R.id.manager_search);

        btnAddRoom = findViewById(R.id.manager_home_btn_add_room);

        manager_viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerHomescreen.this, ManagerReservationSummary.class));
            }
        });

        manager_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerHomescreen.this, ManagerProfile.class));
            }
        });

        manager_availableRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHomescreen.this, ManagerAvailableRooms.class);
                startActivity(intent);
            }
        });

        manager_searchRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHomescreen.this, Searchroom.class);
                startActivity(intent);
            }
        });

        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHomescreen.this, ManagerAddRoom.class);
                startActivity(intent);
            }
        });
    }
}