package com.example.matatabi.crudretrofit.model;

public class Users {
    private String id_user;
    private String username;
    private String level;
    private String password;

    public Users(String id_user, String username, String level, String password) {
        this.id_user = id_user;
        this.username = username;
        this.level = level;
        this.password = password;
    }

    public String getId_user() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public String getLevel() {
        return level;
    }

    public String getPassword() {
        return password;
    }
}
