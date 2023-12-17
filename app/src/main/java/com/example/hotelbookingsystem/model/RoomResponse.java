package com.example.hotelbookingsystem.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RoomResponse {
    @SerializedName("roomDetails")
    List<Room1> listRoom = new ArrayList<>();

    public RoomResponse() {
    }

    public RoomResponse(List<Room1> listRoom) {
        this.listRoom = listRoom;
    }

    public List<Room1> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<Room1> listRoom) {
        this.listRoom = listRoom;
    }
}
