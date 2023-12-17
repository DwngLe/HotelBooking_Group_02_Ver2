package com.example.hotelbookingsystem.model;

import com.google.gson.annotations.SerializedName;

public class Room1 {
    @SerializedName("id")
    private Long id;
    @SerializedName("room_type")
    private String roomType;
    @SerializedName("number_of_beds")
    private int numberOfBeds;
    @SerializedName("room_number")
    private String roomNumber;
    @SerializedName("room_status")
    private String roomStatus;
    @SerializedName("room_floor")
    private int roomFloor;
    @SerializedName("room_price")
    private Float roomPrice;
    @SerializedName("room_desciption")
    private String roomDes;
    @SerializedName("username")
    private String user;

    public Room1() {
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public Float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomDes() {
        return roomDes;
    }

    public void setRoomDes(String roomDes) {
        this.roomDes = roomDes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
