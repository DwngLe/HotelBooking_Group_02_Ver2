package com.example.hotelbookingsystem.model;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("reg_success")
    private boolean regSuccess;

    public boolean isRegSuccess() {
        return regSuccess;
    }

    public void setRegSuccess(boolean regSuccess) {
        this.regSuccess = regSuccess;
    }
}