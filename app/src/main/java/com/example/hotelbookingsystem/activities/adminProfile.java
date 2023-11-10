package com.example.hotelbookingsystem.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.UserResponse;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminProfile extends AppCompatActivity {

    Button home, logout;
    TextView pro_user, pro_pwd, pro_first, pro_last, pro_staddr, pro_city, pro_state, pro_zip, pro_email, pro_phone;
    TextView pro_name, tvName;
    SharedPreferences sharedpreferences;

    ImageButton ibSearch, ibHome, ibProfile;

    public static final String SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Admin Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        final String username = sharedpreferences.getString(MainActivity.KEY_USERNAME, "");
        System.out.println("username: " + username);
        final String role = sharedpreferences.getString(MainActivity.KEY_ROLE, "");
        setContentView(R.layout.activity_admin_profile);

        pro_name = findViewById(R.id.admin_profile_name);
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
        logout = findViewById(R.id.adminLogout);
        ibProfile = findViewById(R.id.adProfile_profile);
        ibHome = findViewById(R.id.adProfile_home);
        ibSearch = findViewById(R.id.adProfile_search);

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminProfile.this, searchGusetManager.class));
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminProfile.this, adminHomeScreen.class));
            }
        });

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminProfile.this, adminProfile.class));
            }
        });

        getAdminProfile(username);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminProfile.this, MainActivity.class));
            }
        });

    }


    public void setData(Profile profile) {
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
        pro_name.setText(profile.getFirstName() + " " + profile.getLastName());


    }

    public void detailId() {


    }

    public void getAdminProfile(String username) {
        ApiService.apiService.getProfile(username).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                System.out.println("Chay vao on respon o adminProfile");
                UserResponse adminProfile = response.body();

                if (adminProfile != null) {
                    System.out.println(adminProfile.getUserList().get(0).toString());
                    final String ln = sharedpreferences.getString(MainActivity.KEY_LASTNAME, "");
                    final String fn = sharedpreferences.getString(MainActivity.KEY_FIRSTNAME, "");
                    final String rl = sharedpreferences.getString(MainActivity.KEY_ROLE, "");
                    System.out.println("Last name is : " + ln + "\n\n" + "Firstname is : " + fn + "\n\n + Role is  : " + rl + "\\n\\n");
                    setData(adminProfile.getUserList().get(0));

                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                Toast.makeText(adminProfile.this, "Something is error, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
