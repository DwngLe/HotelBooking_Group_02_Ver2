package com.example.hotelbookingsystem.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.Reservation;

public class viewProfile extends AppCompatActivity {
    Button home,logout;
    TextView pro_user,pro_pwd,pro_first,pro_last,pro_staddr,pro_city,pro_state,pro_zip,pro_email,pro_phone,pro_cname,pro_cnum,pro_cexp,pro_role;
    Spinner pro_ctype;
    TextView pro_name, tvName;
    SharedPreferences sharedpreferences;

    public static final String SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);
        getSupportActionBar().setTitle("View Profile");
        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        final String user = sharedpreferences.getString(MainActivity.KEY_USERNAME,"");
        final String role = sharedpreferences.getString(MainActivity.KEY_ROLE,"");

        detailId();


        DBManager dbManager = new DBManager(viewProfile.this);
        Profile profile = dbManager.viewProfileDetails(user,role);

        //Setting the details from db to show

        setData(profile);


        home = findViewById(R.id.guestViewHome);
        logout = findViewById(R.id.guestViewLogout);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewProfile.this,userHomeScreen.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewProfile.this,MainActivity.class));
            }
        });

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
        tvName.setText(profile.getLastName());
    }

    public void detailId()
    {
        pro_name = findViewById(R.id.pro_name);
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
        tvName = findViewById(R.id.admin_profile_name);


    }


}



