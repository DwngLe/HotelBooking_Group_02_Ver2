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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Room1;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerReservationSummary extends AppCompatActivity {

    DatePickerDialog picker;
    EditText etStartDate, etEndDate;
    Button ButtonLogOut, ButtonHome, ButtonListView;
    RadioGroup RadioGroupRoomType;
    RadioButton RadioButtonStandard, RadioButtonDeluxe, RadioButtonSuite;
    ImageButton ibHome, ibAvailable, ibSearch, ibProfile;

    String startDate, endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary_manager_);


//        ButtonLogOut = (Button)findViewById(R.id.button_logout);
//        ButtonHome = (Button) findViewById(R.id.button_home);
        ButtonListView = (Button) findViewById(R.id.buttonListView);
        etStartDate = findViewById(R.id.manager_revervation_et_startDate);
        etEndDate = findViewById(R.id.manager_revervation_et_endDate);
//        RadioGroupRoomType = (RadioGroup) findViewById(R.id.radioGroup);
//        RadioButtonStandard = (RadioButton) findViewById(R.id.radioButtonStandard);
//        RadioButtonDeluxe = (RadioButton) findViewById(R.id.radioButtonDeluxe);
//        RadioButtonSuite = (RadioButton) findViewById(R.id.radioButtonSuite);
//        ibHome = findViewById(R.id.manager_summary_home);
//        ibAvailable = findViewById(R.id.manager_summary_available);
//        ibProfile = findViewById(R.id.manager_summary_profile);
//        ibSearch = findViewById(R.id.manager_summary_search);

        etEndDate.setInputType(InputType.TYPE_NULL);
        etStartDate.setInputType(InputType.TYPE_NULL);

        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ManagerReservationSummary.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Format and display the date in "MM/dd/yyyy" format
                                String formattedDate = String.format(Locale.US, "%02d/%02d/%d", monthOfYear + 1, dayOfMonth, year);
                                etStartDate.setText(formattedDate);
                                startDate = etStartDate.getText().toString();

                            }
                        }, year, month, day);
                picker.show();
            }
        });

        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ManagerReservationSummary.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Format and display the date in "MM/dd/yyyy" format
                                String formattedDate = String.format(Locale.US, "%02d/%02d/%d", monthOfYear + 1, dayOfMonth, year);
                                etEndDate.setText(formattedDate);
                                endDate = etEndDate.getText().toString();
                            }

                        }, year, month, day);
                picker.show();
            }
        });



        ButtonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAvaiableRoom(startDate, endDate, "BOOKED");
            }
        });


//        ibHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ManagerReservationSummary.this, ManagerHomescreen.class));
//            }
//        });
//
//        ibSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ManagerReservationSummary.this, Searchroom.class));
//            }
//        });
//
//        ibProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ManagerReservationSummary.this, ManagerProfile.class));
//            }
//        });
//
//        ibAvailable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ManagerReservationSummary.this, ManagerAvailableRooms.class));
//            }
//        });
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

                                Intent intent = new Intent(ManagerReservationSummary.this, ModifyRoom.class);
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
                Toast.makeText(ManagerReservationSummary.this, "Something is error", Toast.LENGTH_SHORT).show();
                System.out.println("Error: " + t.getMessage());
            }
        });
    }


}