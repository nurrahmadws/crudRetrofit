package com.example.matatabi.crudretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("value")
    private Boolean value;

    @SerializedName("message")
    private String message;

    @SerializedName("username")
    private String username;

    @SerializedName("level")
    private String level;

    public LoginResponse(Boolean value, String message, String username, String level) {
        this.value = value;
        this.message = message;
        this.username = username;
        this.level = level;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
