package com.example.hotelbookingsystem.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.InputType;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.adapter.MyAdapter;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.Room1;
import com.example.hotelbookingsystem.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerAvailableRooms extends AppCompatActivity {
    DatePickerDialog picker;
    EditText eStartDate, eEndDate;
    Button modify_room,navigate_home,view_available_rooms,availLogout;
    ImageButton ibHome, ibReser, ibSearch, ibProfile;

    String startDate, endDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_rooms);
        ibHome = findViewById(R.id.avaiRoom_home);
        ibReser = findViewById(R.id.avaiRoom_reser);
        ibProfile = findViewById(R.id.avaiRoom_profile);
        ibSearch = findViewById(R.id.avaiRoom_search);

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerAvailableRooms.this, ManagerHomescreen.class));
            }
        });

        ibReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerAvailableRooms.this, ManagerReservationSummary.class));
            }
        });

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerAvailableRooms.this, ManagerProfile.class));
            }
        });

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerAvailableRooms.this, Searchroom.class));
            }
        });

        eStartDate =(EditText) findViewById(R.id.date_text);
        eStartDate.setInputType(InputType.TYPE_NULL);
        eStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ManagerAvailableRooms.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Format and display the date in "MM/dd/yyyy" format
                                String formattedDate = String.format(Locale.US, "%02d/%02d/%d", monthOfYear + 1, dayOfMonth, year);
                                eStartDate.setText(formattedDate);
                                startDate = eStartDate.getText().toString();

                            }
                        }, year, month, day);
                picker.show();
            }
        });




        eEndDate =(EditText) findViewById(R.id.time_text);
        eEndDate.setInputType(InputType.TYPE_NULL);
        eEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ManagerAvailableRooms.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Format and display the date in "MM/dd/yyyy" format
                                String formattedDate = String.format(Locale.US, "%02d/%02d/%d", monthOfYear + 1, dayOfMonth, year);
                                eEndDate.setText(formattedDate);
                                endDate = eEndDate.getText().toString();

                            }

                        }, year, month, day);
                picker.show();
            }
        });

        view_available_rooms = (Button)findViewById(R.id.avl_room_search_btn);
        view_available_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAvaiableRoom(startDate, endDate, "AVAILABLE");
            }
        });

    }

    private void getAvaiableRoom(String startDate, String endDate, String status){
        System.out.println("startDate: " + startDate);
        System.out.println("endDate: " + endDate);
        System.out.println("status: " + status);
        ApiService.apiService.getListRoom(startDate, endDate, status).enqueue(new Callback<List<Room1>>() {

            @Override
            public void onResponse(Call<List<Room1>> call, Response<List<Room1>> response) {
                System.out.println(1);
                List<Room1> listRoom = response.body();
                if (listRoom != null) {
                    System.out.println("Do dai danh sach cac phong trong la: " + listRoom.size());
                    TableLayout roomTableLayout = (TableLayout) findViewById(R.id.room_table);
                    for (Room1 room: listRoom) {
                        TableRow row= new TableRow(getBaseContext());
                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                        row.setLayoutParams(lp);
                        TextView roomNumber = new TextView(getBaseContext());
                        roomNumber.setText(room.getRoomNumber());
                        final  String rnString = roomNumber.getText().toString();
                        TextView room_Type = new TextView(getBaseContext());
                        room_Type.setText(room.getRoomType());
                        Button modifyButton = new Button(getBaseContext());
                        modifyButton.setText("modify");
                        row.addView(roomNumber);
                        row.addView(room_Type);
                        row.addView(modifyButton);
                        roomTableLayout.addView(row);

                        // set some properties of rowTextView or something

                        modifyButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(ManagerAvailableRooms.this, ModifyRoom.class);
                                intent.putExtra("roomNumber",rnString);
                                intent.putExtra("roomPrice",room.getRoomPrice());
                                intent.putExtra("roomType",room.getRoomType());
                                startActivity(intent);
                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Room1>> call, Throwable t) {
                System.out.println("Chay vao onFailure");
                Toast.makeText(ManagerAvailableRooms.this, "Something is error", Toast.LENGTH_SHORT).show();
                System.out.println("Error: " + t.getMessage());
            }
        });
    }
}
