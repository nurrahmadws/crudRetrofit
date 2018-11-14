package com.example.matatabi.crudretrofit.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("value")
    private String value;

    @SerializedName("message")
    private String message;

    private Users users;

    public LoginResponse(String value, String message, Users users) {
        this.value = value;
        this.message = message;
        this.users = users;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public Users getUsers() {
        return users;
    }
}
