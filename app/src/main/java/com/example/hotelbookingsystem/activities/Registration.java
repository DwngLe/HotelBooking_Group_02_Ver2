package com.example.hotelbookingsystem.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    Boolean reg_success = false;
    Intent myIntent;
    Button reg;
    EditText reg_user, reg_pwd, reg_last, reg_first, card_num, name_on_card, expiry_date, reg_staddr, reg_city, reg_state, reg_zip, reg_email, reg_phone;
    RegistrationResponse rr;
    Spinner card_type;
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
        card_num = findViewById(R.id.reg_cnum);
        name_on_card = findViewById(R.id.reg_cname);
        card_type = (Spinner) findViewById(R.id.reg_ctype);
        expiry_date = findViewById(R.id.reg_cexp);
        reg_staddr = findViewById(R.id.reg_staddr);
        reg_city = findViewById(R.id.reg_city);
        reg_state = findViewById(R.id.reg_state);
        reg_zip = findViewById(R.id.reg_zip);
        reg_email = findViewById(R.id.reg_email);
        reg_phone = findViewById(R.id.reg_phone);
//        reg_success = findViewById(R.id.reg_success);

        myIntent = new Intent(Registration.this, Login.class);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((reg_user.getText().toString().isEmpty()) || (reg_pwd.getText().toString().isEmpty()) || (reg_last.getText().toString().isEmpty()) || (reg_first.getText().toString().isEmpty())
                        || (reg_staddr.getText().toString().isEmpty()) || (reg_city.getText().toString().isEmpty()) || (reg_state.getText().toString().isEmpty()) || (reg_zip.getText().toString().isEmpty())
                        || (reg_email.getText().toString().isEmpty()) || (reg_phone.getText().toString().isEmpty()) || (name_on_card.getText().toString().isEmpty()) || (expiry_date.getText().toString().isEmpty())
                        || (card_num.getText().toString().isEmpty())) {

                    Toast.makeText(Registration.this, "Fileds cannot be empty. Please enter all fields and try again", Toast.LENGTH_SHORT).show();
                } else {
                    registerAccount();
                    if (reg_success) {

                    }
                }

            }
        });

    }

    private void registerAccount() {

        com.example.hotelbookingsystem.model.Registration registration = new com.example.hotelbookingsystem.model.Registration(
                reg_user.getText().toString(),
                reg_pwd.getText().toString(),
                reg_last.getText().toString(),
                reg_first.getText().toString(),
                name_on_card.getText().toString(),
                card_num.getText().toString(),
                expiry_date.getText().toString(),
                reg_staddr.getText().toString(),
                reg_city.getText().toString(),
                reg_state.getText().toString(),
                reg_zip.getText().toString(),
                reg_email.getText().toString(),
                reg_phone.getText().toString(),
                card_type.getSelectedItem().toString());


        ApiService.apiService.registerAccount(registration).enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                System.out.println("Chay vao onRespone");
                System.out.println(registration.toString());

                rr = response.body();
                if (rr!=null) {
                    Toast.makeText(Registration.this, "Register successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(myIntent);
                } else {
                    Toast.makeText(Registration.this, "Username or Card Number is existed, please choose another Username or Card Number", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Toast.makeText(Registration.this, "Something is error, please try again", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
