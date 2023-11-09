package com.example.hotelbookingsystem.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("systemUserEntityList")
   private List<Profile> userList;

    public UserResponse(List<Profile> userList) {
        this.userList = userList;
    }

    public UserResponse() {
    }

    public List<Profile> getUserList() {
        return userList;
    }

    public void setUserList(List<Profile> userList) {
        this.userList = userList;
    }
}
