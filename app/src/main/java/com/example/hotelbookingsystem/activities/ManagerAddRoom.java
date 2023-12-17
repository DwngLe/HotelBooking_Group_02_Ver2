package com.example.hotelbookingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.hotelbookingsystem.model.Room;


import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.model.Room;

public class ManagerAddRoom extends AppCompatActivity {
    RadioGroup radioGroupRoomType;
    RadioButton radioButtonStandard, radioButtonDeluxe, radioButtonSuite;
    EditText etRoomNumber, etRoomFloor, etRoomPrice, etRoomDes, etNumberOfBed;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_room);

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
                    case R.id.radioButtonSuite:
                        roomType = "Suite";
                        break;
                }
                if(roomType.isEmpty()) {
                    Toast.makeText(ManagerAddRoom.this,"Please fill the reservation date!", Toast.LENGTH_LONG).show();
                }else{
                    addRoom(roomType);
                }
            }
        });
    }

    private void addRoom(String roomType){
        Room room = new Room();
        room.set
    }

}