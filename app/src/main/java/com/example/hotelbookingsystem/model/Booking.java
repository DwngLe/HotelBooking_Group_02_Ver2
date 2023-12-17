package com.example.hotelbookingsystem.model;

import java.util.Date;

public class  Booking {
    private Long id;
    private String roomNumber;
    private int num_of_rooms;
    private int num_of_adults;
    private int num_of_children;
    private Date check_in_date;
    private Date check_out_date;
    private int num_of_days;
    private int total_price;
    private int tax;
    private int billed_price;
    private boolean is_paid;
    private boolean is_active;
    private Long roomId;
    private Long userId;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", num_of_rooms=" + num_of_rooms +
                ", num_of_adults=" + num_of_adults +
                ", num_of_children=" + num_of_children +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", num_of_days=" + num_of_days +
                ", total_price=" + total_price +
                ", tax=" + tax +
                ", billed_price=" + billed_price +
                ", is_paid=" + is_paid +
                ", is_active=" + is_active +
                ", roomId=" + roomId +
                ", userId=" + userId +
                '}';
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNum_of_rooms() {
        return num_of_rooms;
    }

    public void setNum_of_rooms(int num_of_rooms) {
        this.num_of_rooms = num_of_rooms;
    }

    public int getNum_of_adults() {
        return num_of_adults;
    }

    public void setNum_of_adults(int num_of_adults) {
        this.num_of_adults = num_of_adults;
    }

    public int getNum_of_children() {
        return num_of_children;
    }

    public void setNum_of_children(int num_of_children) {
        this.num_of_children = num_of_children;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }

    public int getNum_of_days() {
        return num_of_days;
    }

    public void setNum_of_days(int num_of_days) {
        this.num_of_days = num_of_days;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getBilled_price() {
        return billed_price;
    }

    public void setBilled_price(int billed_price) {
        this.billed_price = billed_price;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Booking(Long id, String roomNumber, int num_of_rooms, int num_of_adults, int num_of_children, Date check_in_date, Date check_out_date, int num_of_days, int total_price, int tax, int billed_price, boolean is_paid, boolean is_active, Long roomId, Long userId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.num_of_rooms = num_of_rooms;
        this.num_of_adults = num_of_adults;
        this.num_of_children = num_of_children;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.num_of_days = num_of_days;
        this.total_price = total_price;
        this.tax = tax;
        this.billed_price = billed_price;
        this.is_paid = is_paid;
        this.is_active = is_active;
        this.roomId = roomId;
        this.userId = userId;
    }
}
