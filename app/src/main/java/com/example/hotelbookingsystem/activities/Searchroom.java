package com.example.hotelbookingsystem.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hotelbookingsystem.R;

import java.util.HashMap;

public class Searchroom extends AppCompatActivity {
    Button navigate_home, search_room, log, availLogout;
    TextView room_number , room_status , room_price , room_type ;
    DBManager db;

    ImageButton ibHome, ibReser, ibAvaiable, ibProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBManager(this);
        setContentView(R.layout.activity_searchroom);
        ibHome = findViewById(R.id.searchR_home);
        ibReser = findViewById(R.id.searchR_reser);
        ibAvaiable = findViewById(R.id.searchR_avaiable);
        ibProfile = findViewById(R.id.searchR_profile);

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Searchroom.this, managerHomescreen.class));
            }
        });

        ibReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Searchroom.this, reservation_summary_manager_Activity.class));
            }
        });

        ibAvaiable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Searchroom.this, Available_rooms.class));
            }
        });

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Searchroom.this, managerProfile.class));
            }
        });



        search_room = (Button)findViewById(R.id.search_room);
        search_room.setMovementMethod(LinkMovementMethod.getInstance());
        search_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent roomintent = new Intent(Searchroom.this, Searchroom.class);
                Log.i("inside oncick ","inside oncick");
                room_number = (TextView)findViewById( R.id.room_number );
                room_status = (TextView)findViewById( R.id.room_status );
                room_price = (TextView)findViewById( R.id.room_price );
                room_type = (TextView)findViewById( R.id.room_type );
                /*room_number.setText("1234");
                room_status.setText("occ");
                room_price.setText("1000");
                room_type.setText("st");*/

                EditText editText=findViewById(R. id. editText1);
                String roomNumber =editText. getText(). toString();
                HashMap<String,String> roomMap =  db.getHotelData(roomNumber);
                room_number = (TextView)findViewById( R.id.room_number );
                room_status = (TextView)findViewById( R.id.room_status );
                room_price = (TextView)findViewById( R.id.room_price );
                room_type = (TextView)findViewById( R.id.room_type );
                room_number.setText(roomMap.get("RoomNumber"));
                room_status.setText(roomMap.get("roomStatus"));
                room_price.setText(roomMap.get("pricePerNight"));
                room_type.setText(roomMap.get("roomType"));
                Log.i("RoomNumber ",room_number.getText().toString());
                Log.i("roomStatus ",room_status.getText().toString());
                Log.i("pricePerNight ",room_price.getText().toString());
                Log.i("roomType ",room_type.getText().toString());
                //startActivity(roomintent);
            }
        });

    }
}