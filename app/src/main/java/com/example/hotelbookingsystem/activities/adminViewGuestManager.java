package com.example.hotelbookingsystem.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.adapter.MyAdapter;
import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminViewGuestManager extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_id = "id";
    Long id;

    Button admin_modifyGM, admin_deleteGM, home, logout;
    EditText admin_userGM, admin_roleGM, admin_lastGM, admin_firstGM, admin_pwdGM, admin_staddrGM, admin_cityGM, admin_stateGM, admin_zipGM, admin_emailGM, admin_phone;
    TextView tvName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_guest_manager);
        getSupportActionBar().setTitle("User Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        final String keyUserName = sharedpreferences.getString(MyAdapter.KEY_un, "");

        admin_userGM = findViewById(R.id.admin_userGM);
        admin_roleGM = findViewById(R.id.admin_roleGM);
        admin_firstGM = findViewById(R.id.admin_firstGM);
        admin_lastGM = findViewById(R.id.admin_lastGM);
        admin_pwdGM = findViewById(R.id.admin_pwdGM);
        admin_staddrGM = findViewById(R.id.admin_staddrGM);
        admin_cityGM = findViewById(R.id.admin_cityGM);
        admin_stateGM = findViewById(R.id.admin_stateGM);
        admin_zipGM = findViewById(R.id.admin_zipGM);
        admin_emailGM = findViewById(R.id.admin_emailGM);
        admin_phone = findViewById(R.id.admin_phoneGM);
        tvName = findViewById(R.id.guest_manager_tv_profile_name);
        admin_modifyGM = (Button) findViewById(R.id.admin_modifyGM);
        admin_deleteGM = (Button) findViewById(R.id.admin_deleteGM);
        id = sharedpreferences.getLong(MyAdapter.KEY_id, 1);

        getUser(keyUserName);


        admin_modifyGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                admin_firstGM.setFocusableInTouchMode(true);
                admin_lastGM.setFocusableInTouchMode(true);
                admin_staddrGM.setFocusableInTouchMode(true);
                admin_stateGM.setFocusableInTouchMode(true);
                admin_cityGM.setFocusableInTouchMode(true);
                admin_zipGM.setFocusableInTouchMode(true);
                admin_emailGM.setFocusableInTouchMode(true);
                admin_phone.setFocusableInTouchMode(true);

                admin_modifyGM.setText("Confirm");
                admin_deleteGM.setVisibility(View.GONE);


                admin_modifyGM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Have to save those details in DataBase
                        admin_deleteGM.setVisibility(View.VISIBLE);

                        Profile profile = new Profile(
                                id,
                                admin_userGM.getText().toString(),
                                admin_pwdGM.getText().toString(),
                                admin_roleGM.getText().toString(),
                                admin_lastGM.getText().toString(),
                                admin_firstGM.getText().toString(),
                                admin_staddrGM.getText().toString(),
                                admin_cityGM.getText().toString(),
                                admin_stateGM.getText().toString(),
                                admin_zipGM.getText().toString(),
                                admin_emailGM.getText().toString(),
                                admin_phone.getText().toString());
                        updateUser(profile);
                    }
                });
            }
        });

        admin_deleteGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adminViewGuestManager.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ApiService.apiService.deleteUser(id).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    startActivity(new Intent(adminViewGuestManager.this, searchGusetManager.class));
                                }else{
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(adminViewGuestManager.this, "Something is error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void getUser(String username) {
        ApiService.apiService.getProfile(username).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse != null) {
                    Profile profile = userResponse.getUserList().get(0);
                    refresh(profile);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(adminViewGuestManager.this, "Something is error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(Profile profile) {
        ApiService.apiService.updateUser(profile).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile profile = response.body();
                if (profile != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(adminViewGuestManager.this);
                    builder.setTitle("Confirm");
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            if (profile != null) {
                                startActivity(new Intent(adminViewGuestManager.this, adminViewGuestManager.class));
                            }
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    private void refresh(Profile profile) {
        admin_userGM.setText(profile.getUsername());
        admin_pwdGM.setText(profile.getPassword());
        admin_firstGM.setText(profile.getFirstName());
        admin_lastGM.setText(profile.getLastName());
        admin_staddrGM.setText(profile.getStreetAddress());
        admin_cityGM.setText(profile.getCity());
        admin_stateGM.setText(profile.getState());
        admin_zipGM.setText(profile.getZipCode());
        admin_emailGM.setText(profile.getEmail());
        admin_phone.setText(profile.getPhone());
        admin_roleGM.setText(profile.getRole());
        tvName.setText(profile.getLastName());

        admin_userGM.setFocusable(false);
        admin_pwdGM.setFocusable(false);
        admin_firstGM.setFocusable(false);
        admin_lastGM.setFocusable(false);
        admin_staddrGM.setFocusable(false);
        admin_stateGM.setFocusable(false);
        admin_cityGM.setFocusable(false);
        admin_zipGM.setFocusable(false);
        admin_emailGM.setFocusable(false);
        admin_phone.setFocusable(false);
        admin_roleGM.setFocusable(false);
    }

}
