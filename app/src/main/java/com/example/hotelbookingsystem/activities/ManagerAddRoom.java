package com.example.hotelbookingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.util.Log;


import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Result;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.model.Room1;


import com.example.hotelbookingsystem.R;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerAddRoom extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    RadioGroup radioGroupRoomType;
    RadioButton radioButtonStandard, radioButtonDeluxe, radioButtonSuite;
    EditText etRoomNumber, etRoomFloor, etRoomPrice, etRoomDes, etNumberOfBed;
    Button btnSubmit;
    Intent myIntent;

    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_ROLE = "Role";
    public static final String KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_room);
        myIntent = new Intent(ManagerAddRoom.this, ManagerHomescreen.class);

        btnSubmit = findViewById(R.id.manager_add_btn_submit);
        etRoomNumber = findViewById(R.id.manager_add_et_room_num);
        etRoomFloor = findViewById(R.id.manager_add_et_room_floor);
        etRoomPrice = findViewById(R.id.manager_add_et_room_price);
        etRoomDes = findViewById(R.id.manager_add_et_room_des);
        etNumberOfBed = findViewById(R.id.manager_add_et_room_number_beds);

        radioGroupRoomType = findViewById(R.id.manager_add_radioGroup);
        radioButtonStandard = findViewById(R.id.manager_add_radioButtonStandard);
        radioButtonDeluxe = findViewById(R.id.manager_add_radioButtonDeluxe);
        radioButtonSuite = findViewById(R.id.manager_add_radioButtonSuite);

        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        final String user = sharedpreferences.getString(Login.KEY_USERNAME,"");
        System.out.println(user);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomType = "";
                int selectedRadioButtonId = radioGroupRoomType.getCheckedRadioButtonId();
                switch (selectedRadioButtonId) {
                    case R.id.manager_add_radioButtonStandard:
                        roomType = "Standard";
                        break;
                    case R.id.manager_add_radioButtonDeluxe:
                        roomType = "Deluxe";
                        break;
                    case R.id.manager_add_radioButtonSuite:
                        roomType = "Suite";
                        break;
                }
                if(roomType.isEmpty()) {
                    Toast.makeText(ManagerAddRoom.this,"Please fill the reservation date!", Toast.LENGTH_LONG).show();
                }else{
                    addRoom(roomType, user);
                }
            }
        });
    }

    private void addRoom(String roomType, String user){
        Room1 room = new Room1();
        room.setRoomNumber(etRoomNumber.getText().toString());
        room.setRoomFloor(Integer.parseInt(etRoomFloor.getText().toString()));
        room.setRoomPrice(Float.parseFloat(etRoomPrice.getText().toString()));
        room.setRoomDes(etRoomDes.getText().toString());
        room.setNumberOfBeds(Integer.parseInt(etNumberOfBed.getText().toString()));
        room.setRoomType(roomType);
        room.setUser(user);
        room.setRoomStatus(null);

        Gson gson = new Gson();
        String json = gson.toJson(room);
        System.out.println(json);

        ApiService.apiService.addRoom(room).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                System.out.println("Chay vao onRespone");
                Result result = new Result(response.body().toString());
                System.out.println("kq: "  + result);
                if(result != null){
                    Toast.makeText(ManagerAddRoom.this, "Add room successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(myIntent);
                }else{
                    Toast.makeText(ManagerAddRoom.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(ManagerAddRoom.this, "Something is error, please try again", Toast.LENGTH_SHORT).show();
                System.out.println("Error: " + t.getMessage());
            }
        });

    }

}