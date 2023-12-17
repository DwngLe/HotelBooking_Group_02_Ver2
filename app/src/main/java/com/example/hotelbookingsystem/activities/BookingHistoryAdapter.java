package com.example.hotelbookingsystem.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbookingsystem.R;
import com.example.hotelbookingsystem.model.Booking;

import java.text.SimpleDateFormat;
//import com.bumptech.glide.Glide;
import java.util.List;

public class BookingHistoryAdapter extends RecyclerView .Adapter<BookingHistoryAdapter.HistoryHolder>{
    private Context context;
    private List<Booking> bookingList;

    public BookingHistoryAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.bookinghistoryitem,parent,false);
       return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Booking booking=bookingList.get(position);
        System.out.println(booking.getId());
        holder.name.setText(booking.getRoomNumber());
        String formattedCheckInDate = sdf.format(booking.getCheck_in_date());
        holder.checkin.setText(formattedCheckInDate);
        String formattedCheckOutDate = sdf.format(booking.getCheck_out_date());
        holder.checkout.setText(formattedCheckOutDate);
        System.out.println(booking.toString());
        holder.price.setText(booking.getTotal_price()+" VND");
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{
        TextView name,checkin,checkout,price;
        ConstraintLayout constraintLayout;
        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.bookingname);
            checkin=itemView.findViewById(R.id.checkinbooking);
            checkout=itemView.findViewById(R.id.checkoutbooking);
            price=itemView.findViewById(R.id.priceBooking);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
