package com.example.hotelbookingsystem.activities;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.Reservation;
import com.example.hotelbookingsystem.model.UserResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewProfile extends AppCompatActivity {
//    ScrollView sc = (ScrollView) findViewById(R.id.profile_scroll);

    Button logout;
    EditText pro_user,pro_pwd,pro_first,pro_last,pro_staddr,pro_city,pro_state,pro_zip,pro_email,pro_phone,pro_cname,pro_cnum,pro_cexp,pro_role;
    Spinner pro_ctype;
    TextView pro_name, tvName;
    SharedPreferences sharedpreferences;
    ImageButton view_profile, view_home, view_reservations, view_pending;

    public static final String SHARED_PREF_NAME = "mypref";
//    public static final String KEY_FIRSTNAME = "firstName";
//    public static final String KEY_LASTNAME = "lastName";
//    @SuppressLint("WrongViewCast")
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);
        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        final String username = sharedpreferences.getString(MainActivity.KEY_USERNAME, "");
        System.out.println("user name: " + username);



        pro_user = findViewById(R.id.user_userGM);
        pro_pwd = findViewById(R.id.user_pwdGM);
        pro_first = findViewById(R.id.user_firstGM);
        pro_last = findViewById(R.id.user_lastGM);
        pro_staddr = findViewById(R.id.user_staddrGM);
        pro_city = findViewById(R.id.user_cityGM);
        pro_state = findViewById(R.id.user_stateGM);
        pro_zip = findViewById(R.id.user_zipGM);
        pro_email = findViewById(R.id.user_emailGM);
        pro_phone = findViewById(R.id.user_phoneGM);
        tvName = findViewById(R.id.user_profile_name);
        logout = findViewById(R.id.guestViewLogout);

        view_profile = findViewById(R.id.imgbtnProfile);
        view_pending = findViewById(R.id.imageList);
        view_reservations = findViewById(R.id.imageHistory);
        view_home = findViewById(R.id.imgbtnHome);

        getAdminProfile(username);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewProfile.this, Login.class));
            }
        });

        view_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewProfile.this,pendingRoomScreen.class));
            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewProfile.this,viewProfile.class));
            }
        });

        view_reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewProfile.this,reservation_summary_guest_Activity.class));
            }
        });

        view_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewProfile.this,userHomeScreen.class));

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
//        pro_name.setText(profile.getFirstName() + " " + profile.getLastName());


    }

    public void detailId() {


    }

        public void getAdminProfile(String username) {
            System.out.println("uẻname size  " + username.length());

            ApiService.apiService.getProfile(username).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    System.out.println("Chay vao on respon o userProfile");
                    UserResponse userProfile = response.body();
                    System.out.println(userProfile);
                    if (userProfile != null) {
                        System.out.println(userProfile.getUserList().get(0).toString());
                        final String ln = sharedpreferences.getString(MainActivity.KEY_LASTNAME, "");
                        final String fn = sharedpreferences.getString(MainActivity.KEY_FIRSTNAME, "");
                        final String rl = sharedpreferences.getString(MainActivity.KEY_ROLE, "");
                        System.out.println("Last name is : " + ln + "\n\n" + "Firstname is : " + fn + "\n\n + Role is  : " + rl + "\\n\\n");
                        setData(userProfile.getUserList().get(0));

                    } else {
                        System.out.println("userProfile rong");
                    }

                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                    Toast.makeText(viewProfile.this, "Something is error, please try again", Toast.LENGTH_SHORT).show();
                }
            });
        }

}




















//
//        SQLiteDatabase db = openOrCreateDatabase("HotelManagement.db", MODE_PRIVATE, null);
//        Cursor cursor = db.rawQuery("select name from sqlite_master WHERE type = 'table' AND name = 'system_user'", null);
//
//        if(cursor.getCount() > 0)
//        {
//            String query = "select * from system_user where username = '" + user + "' ";
//            cursor = db.rawQuery(query,null);
//            if(cursor.getCount() == 0)
//            {
//                return;
//            }
//
//            StringBuffer buffer = new StringBuffer();
//            while(cursor.moveToNext())
//            {
//                buffer.append("Username : " + cursor.getString(1) + "\n");
//                buffer.append("Password : " + cursor.getString(2) + "\n");
//                buffer.append("First Name : " + cursor.getString(3) + "\n");
//                buffer.append("Last Name : " + cursor.getString(4) + "\n");
//            }
//
//            Toast.makeText(this,buffer.toString(), Toast.LENGTH_SHORT).show();
//        }


