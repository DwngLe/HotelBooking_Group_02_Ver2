package com.example.hotelbookingsystem.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.model.Reservation;
import com.example.hotelbookingsystem.model.Room;

import java.util.ArrayList;

public class viewRoomScreen extends AppCompatActivity {

    Button reserve, logout;
    EditText hotelName, hotelLocation, roomType, nBeds, rFacilities, cid, cod, price, totalPrice;

    EditText nNights;

    ImageButton view_profile, home2, view_reservations, view_pending, home;

    SharedPreferences sharedpreferences;

    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_ROOMNUMBER = "RoomNumber";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_room_screen);
        getSupportActionBar().setTitle("View Room");

        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        final String rt = sharedpreferences.getString(searchRoomAdapter.KEY_rt, "");
        final String hn = sharedpreferences.getString(searchRoomAdapter.KEY_hn, "");
        final String check = sharedpreferences.getString(searchRoomScreen.KEY_CHECKINDATE, "");
        final String check_out = sharedpreferences.getString(searchRoomScreen.KEY_CHECKOUTDATE, "");
        final String numNights = sharedpreferences.getString(searchRoomScreen.KEY_NUMBEROFNIGHTS, "");
        final String numRooms = sharedpreferences.getString(searchRoomScreen.KEY_NUMBEROFROOMS, "");
        final String adults = sharedpreferences.getString(searchRoomScreen.KEY_ADULT, "");
        final String child = sharedpreferences.getString(searchRoomScreen.KEY_CHILDREN, "");
        final String fn = sharedpreferences.getString(MainActivity.KEY_FIRSTNAME, "");
        final String ln = sharedpreferences.getString(MainActivity.KEY_LASTNAME, "");
        final String un = sharedpreferences.getString(MainActivity.KEY_USERNAME, "");
        view_profile = findViewById(R.id.imgbtnProfile);
        view_pending = findViewById(R.id.imageList);
        view_reservations = findViewById(R.id.imageHistory);
//        home2 = findViewById(R.id.imgbtnHome);


        reserve = findViewById(R.id.viewReserve);
        logout = findViewById(R.id.viewLogout);
        home = findViewById(R.id.imgbtnHome);

        hotelName = findViewById(R.id.viewHotelName);
        hotelLocation = findViewById(R.id.viewHotelLocation);
        roomType = findViewById(R.id.viewRoomType);
        nBeds = findViewById(R.id.viewBeds);
        rFacilities = findViewById(R.id.viewRoomFacilities);
        cid = findViewById(R.id.viewCheckInDate);
        cod = findViewById(R.id.viewCheckOutDate);
//        nNights = findViewById(R.id.viewNights);
        price = findViewById(R.id.viewPrice);
        totalPrice = findViewById(R.id.viewTotalPrice);

        System.out.println(rt + "   " + hn);


        DBManager dbManager = new DBManager(viewRoomScreen.this);
        ArrayList<Room> room = dbManager.getSingleRoomDetail(rt, hn);

        int roomNum = Integer.parseInt(room.get(0).getRoomNumber());

        String roo = room.get(0).getRoomNumber();

        SharedPreferences.Editor session = sharedpreferences.edit();
        session.putString(KEY_ROOMNUMBER, roo);
        session.apply();



        double numRo = Integer.parseInt(numRooms);
        double pri = Integer.parseInt(room.get(0).getPricePerNight());
        double ta = 1.08;
        // double nights = Integer.parseInt(room.get(0).getNumberOfNights());
        String tax = String.valueOf(ta);
        double tot = pri * ta * numRo * Integer.parseInt(numNights);
        double billedPrice = tot;
        String bp = String.valueOf(billedPrice);
        String status = "Pending";

//        String loca = room.get(0).getHotelLocation().toString();
////      if (location.equals("HaNoi")) {
////            location = "Hà Nội";
////        } else if (location.equals("NgheAn")) {
////            location = "Nghệ An";
////        } else {
////            location = "Sài Gòn";
////        }

        hotelName.setText(room.get(0).getHotelName());
        hotelLocation.setText(room.get(0).getHotelLocation().toString());
        roomType.setText(room.get(0).getRoomType());
        nBeds.setText(room.get(0).getNumberOfBeds());
        rFacilities.setText(room.get(0).getRoomFacilities());
        price.setText(room.get(0).getPricePerNight());
        cid.setText(check);
        cod.setText(check_out);
//        nNights.setText(numNights);
        totalPrice.setText(String.format("%.2f", tot));

        hotelName.setFocusable(false);
        hotelLocation.setFocusable(false);
        roomType.setFocusable(false);
        nBeds.setFocusable(false);
        rFacilities.setFocusable(false);
        price.setFocusable(false);
        cid.setFocusable(false);
        cod.setFocusable(false);
//        nNights.setFocusable(false);
        totalPrice.setFocusable(false);

//        System.out.println(nNights);

        System.out.println("BILLING PRICE IS : " + bp);


        Reservation reservation = new Reservation(hotelName.getText().toString(), hotelLocation.getText().toString(), roomType.getText().toString(), numRooms, numNights, adults, child,
                cid.getText().toString(), cod.getText().toString(), price.getText().toString(), tax, totalPrice.getText().toString(), bp, null, fn, ln, check, un, roo, status);

        dbManager = new DBManager(viewRoomScreen.this);

        boolean res = dbManager.addReservationafterView(reservation);

//       boolean res = true;


        if (res) {
            reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(viewRoomScreen.this, reserveRoomScreen.class));

                }
            });

        }else{
            Toast.makeText(viewRoomScreen.this,"Error when reservation, please try again", Toast.LENGTH_LONG).show();

        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewRoomScreen.this, userHomeScreen.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewRoomScreen.this, MainActivity.class));
            }
        });

        view_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewRoomScreen.this,pendingRoomScreen.class));
            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewRoomScreen.this,viewProfile.class));
            }
        });

        view_reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewRoomScreen.this,reservation_summary_guest_Activity.class));
            }
        });

//        home2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(viewRoomScreen.this,userHomeScreen.class));
//
//            }
//        });
    }

}
