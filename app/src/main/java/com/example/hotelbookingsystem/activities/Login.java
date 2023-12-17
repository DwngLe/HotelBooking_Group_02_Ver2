package com.example.hotelbookingsystem.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Profile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    Button reg, sign;
    EditText user, pwd;
    SharedPreferences sharedpreferences;
    TextView reg_success;

    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_ROLE = "Role";
    public static final String KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = findViewById(R.id.register);
        sign = findViewById(R.id.signin);
        user = findViewById(R.id.log_username);
        pwd = findViewById(R.id.log_password);
        reg_success = findViewById(R.id.reg_success);

        reg_success.setVisibility(View.GONE);


        sign.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Recycle")
            @Override
            public void onClick(View view) {
                if (user.getText().toString().trim().length() <= 0 || pwd.getText().toString().trim().length() <= 0) {
                    Toast.makeText(Login.this, "Please fill all the information!", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogin();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

    }

    private void checkLogin() {
        Profile profile = new Profile();
        profile.setUsername(user.getText().toString().trim());
        profile.setPassword(pwd.getText().toString().trim());
        ApiService.apiService.checkLogin(profile).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile profile = response.body();
                System.out.println("Da goi toi ham onResponse");
                if (profile != null) {
                    String role = profile.getRole();
                    String firstName = profile.getFirstName();
                    String lastName = profile.getLastName();

                    sharedpreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

                    Editor session = sharedpreferences.edit();
                    session.putString(KEY_USERNAME, user.getText().toString().trim());
                    session.putString(KEY_ROLE, role);
                    session.putString(KEY_FIRSTNAME, firstName);
                    session.putString(KEY_LASTNAME, lastName);
                    session.apply();

                    System.out.println("Thong tin dang nhap dc xac thuc, firstName:" + firstName + ", lastName: " + lastName + ", role: " + role);

                    if (role.equals("MANAGER")) {
                        startActivity(new Intent(Login.this, ManagerHomescreen.class));
                        session.commit();
                        user.setText("");
                        pwd.setText("");
                    } else if (role.equals("ADMIN")) {
                        startActivity(new Intent(Login.this, AdminHome.class));
                        session.commit();
                        user.setText("");
                        pwd.setText("");
                    } else {
                        startActivity(new Intent(Login.this, userHomeScreen.class));
                        session.commit();
                        user.setText("");
                        pwd.setText("");
                    }

                } else {
                    System.out.println("Khong nhan duoc ket qua");
                    Toast.makeText(Login.this, "Username or Password is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                System.out.println("Da goi toi ham onFailure");
                Toast.makeText(Login.this, "Something is error, please try later!", Toast.LENGTH_LONG).show();
                user.setText("");
                pwd.setText("");
            }
        });
    }

}