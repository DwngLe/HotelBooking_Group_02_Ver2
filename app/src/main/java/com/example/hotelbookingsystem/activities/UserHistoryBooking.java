package com.example.hotelbookingsystem.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.api.ApiService;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHistoryBooking extends AppCompatActivity {

    private ArrayList<String> bookingHistory;
    private RecyclerView recyclerView;
    List<Booking> booking = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history_booking);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookingHistory = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);

        Long userId = sharedPreferences.getLong("userId", -1);

        if (userId != -1) {
            GetBookingHistoryTask(userId);
        } else {

        }
    }

    private void GetBookingHistoryTask( Long userId) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        Date startDate = calendar.getTime();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = calendar.getTime();
        ApiService.apiService.getHistoryBooking(userId,startDate,endDate).enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful()) {
                    booking = response.body();
                    System.out.println(booking.size());
                    if (booking != null && !booking.isEmpty()) {
                        BookingHistoryAdapter adapter = new BookingHistoryAdapter(UserHistoryBooking.this, booking);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(UserHistoryBooking.this, "No booking history found", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(UserHistoryBooking.this, "Failed to retrieve booking history", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<Booking> >call, Throwable t) {
                System.out.println("Da goi toi ham onFailure");
                Toast.makeText(UserHistoryBooking.this, "Something is error, please try later!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
