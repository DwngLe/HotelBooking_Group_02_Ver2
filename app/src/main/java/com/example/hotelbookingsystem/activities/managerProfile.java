package com.example.hotelbookingsystem.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.model.Profile;

public class managerProfile extends AppCompatActivity {

    Button modify,home,logout;
    EditText pro_user,pro_pwd,pro_first,pro_last,pro_staddr,pro_city,pro_state,pro_zip,pro_email,pro_phone,pro_cname,pro_cnum,pro_cexp,pro_role;
    Spinner pro_ctype;
    TextView pro_name;
    SharedPreferences sharedpreferences;

    ImageButton ibHome, ibReservation, ibAvaiable, ibSearch;

    public static final String SHARED_PREF_NAME = "mypref";
    //    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        final String user = sharedpreferences.getString(MainActivity.KEY_USERNAME,"");
        final String role = sharedpreferences.getString(MainActivity.KEY_ROLE,"");

        setContentView(R.layout.manager_profile);
        detailId();
        ibHome = findViewById(R.id.maprofile_home);
        ibAvaiable = findViewById(R.id.maprofile_avaiable);
        ibReservation = findViewById(R.id.maprofile_reservation);
        ibSearch = findViewById(R.id.maprofile_search);

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(managerProfile.this, managerHomescreen.class));
            }
        });

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(managerProfile.this, Searchroom.class));
            }
        });

        ibReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(managerProfile.this, reservation_summary_manager_Activity.class));
            }
        });

        ibAvaiable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(managerProfile.this, Available_rooms.class));
            }
        });


        logout = findViewById(R.id.guestViewLogout);


//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(managerProfile.this,managerHomescreen.class));
//            }
//        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(managerProfile.this,MainActivity.class));
            }
        });

        DBManager dbManager = new DBManager(managerProfile.this);
        Profile profile = dbManager.viewProfileDetails(user,role);


        setData(profile);

        //To make fields non Editable
        nonEdit();

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
                modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Have to save those details in DataBase

                        DBManager dbManager = new DBManager(managerProfile.this);
                        Profile profile = new Profile(pro_user.getText().toString(),pro_pwd.getText().toString(),pro_last.getText().toString(),pro_first.getText().toString(),
                                pro_staddr.getText().toString(),pro_city.getText().toString(),pro_state.getText().toString(),pro_zip.getText().toString(),pro_email.getText().toString(),pro_phone.getText().toString());

                        final boolean updateResult = dbManager.updateManagerProfile(profile,user);

                        AlertDialog.Builder builder = new AlertDialog.Builder(managerProfile.this);

                        builder.setTitle("Confirm");
                        builder.setMessage("Are you sure?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing but close the dialog
                                if(updateResult)
                                {
                                    startActivity(new Intent(managerProfile.this,managerProfile.class));
                                }
                                dialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });

            }
        });
    }

    public void nonEdit()
    {
        pro_user.setFocusable(false);
        pro_pwd.setFocusable(false);
        pro_first.setFocusable(false);
        pro_last.setFocusable(false);
        pro_staddr.setFocusable(false);
        pro_state.setFocusable(false);
        pro_city.setFocusable(false);
        pro_zip.setFocusable(false);
        pro_email.setFocusable(false);
        pro_phone.setFocusable(false);


    }

    public void edit()
    {
        pro_pwd.setFocusableInTouchMode(true);
        pro_first.setFocusableInTouchMode(true);
        pro_last.setFocusableInTouchMode(true);
        pro_staddr.setFocusableInTouchMode(true);
        pro_state.setFocusableInTouchMode(true);
        pro_city.setFocusableInTouchMode(true);
        pro_zip.setFocusableInTouchMode(true);
        pro_email.setFocusableInTouchMode(true);
        pro_phone.setFocusableInTouchMode(true);


        pro_name.setText("Modify Details");
    }

    public void setData(Profile profile)
    {
        pro_user.setText(profile.getUsername());
        pro_pwd.setText(profile.getPassword());
        pro_first.setText(profile.getFirstName());
        pro_last.setText(profile.getLastName());
        pro_staddr.setText(profile.getStreetAddress());
        pro_city.setText(profile.getCity());
        pro_state.setText(profile.getState());
        pro_zip.setText(profile.getZipCode());
        pro_email.setText(profile.getEmail());
        pro_phone.setText(profile.getPhone());
    }

    public void detailId()
    {
//        pro_name = findViewById(R.id.pro_name);
        pro_user = findViewById(R.id.admin_userGM);
        pro_pwd = findViewById(R.id.admin_pwdGM);
        pro_first = findViewById(R.id.admin_firstGM);
        pro_last = findViewById(R.id.admin_lastGM);
        pro_staddr = findViewById(R.id.admin_staddrGM);
        pro_city = findViewById(R.id.admin_cityGM);
        pro_state = findViewById(R.id.admin_stateGM);
        pro_zip = findViewById(R.id.admin_zipGM);
        pro_email = findViewById(R.id.admin_emailGM);
        pro_phone = findViewById(R.id.admin_phoneGM);


        modify = findViewById(R.id.mana_pro_modify);
    }

}
