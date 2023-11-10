package com.example.hotelbookingsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.adapter.MyAdapter;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class searchGusetManager extends AppCompatActivity {
    //Day la Duong Le
    Button search, logout;
    EditText lastName;
    ListView lv_customerList;
    ArrayList<Profile> arrayList;
    MyAdapter myAdapter;
    TextView tvNumOfResult;

    ImageButton ibHome, ibSearch, ibProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_guest_manager);
        getSupportActionBar().setTitle("Search User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lastName = findViewById(R.id.admin_lastname);
        search = findViewById(R.id.admin_search);
        lv_customerList = findViewById(R.id.admin_list);
        tvNumOfResult = findViewById(R.id.search_guest_manager_tv_numberOfResult);
        ibHome = findViewById(R.id.searchUser_home);
        ibSearch = findViewById(R.id.searchUser_find);
        ibProfile = findViewById(R.id.searchUser_adProfile);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userLastName = lastName.getText().toString();
                getAllUserByLastName(userLastName);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(searchGusetManager.this, adminHomeScreen.class));
            }
        });

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(searchGusetManager.this, adminHomeScreen.class));
            }
        });

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(searchGusetManager.this, adminProfile.class));
            }
        });
    }



    private void getAllUserByLastName(String lastName) {
        ApiService.apiService.getUserByLastName(lastName).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse != null) {
                    List<Profile> profileList = userResponse.getUserList();
                    int number = profileList.size();
                    if (number > 0) {
                        myAdapter = new MyAdapter(searchGusetManager.this, profileList);
                        lv_customerList.setAdapter(myAdapter);
                        myAdapter.notifyDataSetChanged();
                    }
                    tvNumOfResult.setText("Found " + number + " results");
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(searchGusetManager.this, "Something is error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
