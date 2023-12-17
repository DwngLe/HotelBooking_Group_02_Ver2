package com.example.hotelbookingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hotelbookingsystem.R;

import java.util.Calendar;

public class ManagerReservationSummary extends AppCompatActivity {

    DatePickerDialog picker;
    SQLiteDatabase sqLiteDatabaseObj;
    EditText EditTextDate;
    Button ButtonLogOut, ButtonHome, ButtonListView;
    RadioGroup RadioGroupRoomType;
    RadioButton RadioButtonStandard, RadioButtonDeluxe, RadioButtonSuite;

    ImageButton ibHome, ibAvailable, ibSearch, ibProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary_manager_);


//        ButtonLogOut = (Button)findViewById(R.id.button_logout);
//        ButtonHome = (Button) findViewById(R.id.button_home);
        ButtonListView = (Button) findViewById(R.id.buttonListView);
        EditTextDate = (EditText)findViewById(R.id.editTextDate);
        RadioGroupRoomType = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButtonStandard = (RadioButton) findViewById(R.id.radioButtonStandard);
        RadioButtonDeluxe = (RadioButton) findViewById(R.id.radioButtonDeluxe);
        RadioButtonSuite = (RadioButton) findViewById(R.id.radioButtonSuite);
        ibHome = findViewById(R.id.manager_summary_home);
        ibAvailable = findViewById(R.id.manager_summary_available);
        ibProfile = findViewById(R.id.manager_summary_profile);
        ibSearch = findViewById(R.id.manager_summary_search);

        EditTextDate.setInputType(InputType.TYPE_NULL);
        EditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ManagerReservationSummary.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                EditTextDate.setText( (monthOfYear + 1) +  "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });



        ButtonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = EditTextDate.getText().toString();
                String roomType = "";
                int selectedRadioButtonId = RadioGroupRoomType.getCheckedRadioButtonId();
                switch (selectedRadioButtonId) {
                    case R.id.radioButtonStandard:
                        roomType = "Standard";
                        break;
                    case R.id.radioButtonDeluxe:
                        roomType = "Deluxe";
                        break;
                    case R.id.radioButtonSuite:
                        roomType = "Suite";
                        break;
                }
                if(roomType.isEmpty() || date.isEmpty()) {      // add default date and default room type

                    Toast.makeText(ManagerReservationSummary.this,"Please fill the reservation date!", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(ManagerReservationSummary.this, List_of_Reservations_Activity.class);
                intent.putExtra("date", date);
                intent.putExtra("roomType", roomType);
                startActivity(intent);
            }
        });


        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerReservationSummary.this, ManagerHomescreen.class));
            }
        });

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerReservationSummary.this, Searchroom.class));
            }
        });

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerReservationSummary.this, ManagerProfile.class));
            }
        });

        ibAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerReservationSummary.this, ManagerAvailableRooms.class));
            }
        });
    }

}