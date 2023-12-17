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
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Room1;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                startActivity(new Intent(Searchroom.this, ManagerHomescreen.class));
            }
        });

        ibReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Searchroom.this, ManagerReservationSummary.class));
            }
        });

        ibAvaiable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Searchroom.this, ManagerAvailableRooms.class));
            }
        });

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Searchroom.this, ManagerProfile.class));
            }
        });



        search_room = (Button)findViewById(R.id.search_room);
        search_room.setMovementMethod(LinkMovementMethod.getInstance());
        search_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("inside oncick ","inside oncick");
                room_number = (TextView)findViewById( R.id.room_number );
                room_status = (TextView)findViewById( R.id.room_status );
                room_price = (TextView)findViewById( R.id.room_price );
                room_type = (TextView)findViewById( R.id.room_type );

                EditText editText=findViewById(R. id. editText1);
                String roomNumber =editText.getText().toString();

                room_number = (TextView)findViewById( R.id.room_number );
                room_status = (TextView)findViewById( R.id.room_status );
                room_price = (TextView)findViewById( R.id.room_price );
                room_type = (TextView)findViewById( R.id.room_type );

                getRoomByNum(roomNumber);
            }
        });
    }

    public void getRoomByNum(String roomNumber){
        ApiService.apiService.getRoomByNumber(roomNumber).enqueue(new Callback<Room1>() {
            @Override
            public void onResponse(Call<Room1> call, Response<Room1> response) {
                Room1 room1 = response.body();
                if(room1 != null){
                    room_number.setText(room1.getRoomNumber());
                    room_status.setText("AVAILABLE");
                    room_price.setText(room1.getRoomPrice().toString());
                    room_type.setText(room1.getRoomType());
                }
            }

            @Override
            public void onFailure(Call<Room1> call, Throwable t) {

            }
        });
    }

}