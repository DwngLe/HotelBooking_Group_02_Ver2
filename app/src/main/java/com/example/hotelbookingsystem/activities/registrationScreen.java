package com.example.hotelbookingsystem.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.Registration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrationScreen extends AppCompatActivity {
    Boolean isSaved = false;
    Intent myIntent;
    Button reg;
    EditText reg_user, reg_pwd, reg_last, reg_first, reg_cnum, reg_cname, reg_cexp, reg_staddr, reg_city, reg_state, reg_zip, reg_email, reg_phone;
    TextView reg_success;
    Spinner reg_ctype;
    String role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reg = findViewById(R.id.now);
        reg_user = findViewById(R.id.reg_user);
        reg_pwd = findViewById(R.id.reg_pwd);
        reg_last = findViewById(R.id.reg_last);
        reg_first = findViewById(R.id.reg_first);
        reg_cnum = findViewById(R.id.reg_cnum);
        reg_cname = findViewById(R.id.reg_cname);
        reg_ctype = (Spinner) findViewById(R.id.reg_ctype);
        reg_cexp = findViewById(R.id.reg_cexp);
        reg_staddr = findViewById(R.id.reg_staddr);
        reg_city = findViewById(R.id.reg_city);
        reg_state = findViewById(R.id.reg_state);
        reg_zip = findViewById(R.id.reg_zip);
        reg_email = findViewById(R.id.reg_email);
        reg_phone = findViewById(R.id.reg_phone);
//        reg_success = findViewById(R.id.reg_success);

        myIntent = new Intent(registrationScreen.this, MainActivity.class);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((reg_user.getText().toString().isEmpty()) || (reg_pwd.getText().toString().isEmpty()) || (reg_last.getText().toString().isEmpty()) || (reg_first.getText().toString().isEmpty())
                        || (reg_staddr.getText().toString().isEmpty()) || (reg_city.getText().toString().isEmpty()) || (reg_state.getText().toString().isEmpty()) || (reg_zip.getText().toString().isEmpty())
                        || (reg_email.getText().toString().isEmpty()) || (reg_phone.getText().toString().isEmpty()) || (reg_cname.getText().toString().isEmpty()) || (reg_cexp.getText().toString().isEmpty())
                        || (reg_cnum.getText().toString().isEmpty())) {

                    Toast.makeText(registrationScreen.this, "Fileds cannot be empty. Please enter all fields and try again", Toast.LENGTH_SHORT).show();
                } else {
                    registerAccount();
                    if (isSaved) {
                        Toast.makeText(registrationScreen.this, "Register successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(myIntent);
                    }
                }

            }
        });

    }

    private void registerAccount() {
        Profile profile = new Profile(reg_user.getText().toString(), reg_pwd.getText().toString(), reg_first.getText().toString(), reg_last.getText().toString(), reg_staddr.getText().toString(),
                reg_city.getText().toString(), reg_state.getText().toString(), reg_zip.getText().toString(), reg_email.getText().toString(),
                reg_phone.getText().toString(), reg_cname.getText().toString(), reg_ctype.getSelectedItem().toString(), reg_cnum.getText().toString(),
                reg_cexp.getText().toString());
        ApiService.apiService.registerAccount(profile).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                isSaved = response.body();
                if (isSaved) {
                    System.out.println("Da luu account thanh cong, username: " + profile.getUsername());
                } else {
                    System.out.println("Khong the luu account, username: " + profile.getUsername());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(registrationScreen.this, "Something is error, please try again", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
