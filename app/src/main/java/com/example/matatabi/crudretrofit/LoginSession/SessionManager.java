package com.example.matatabi.crudretrofit.LoginSession;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.matatabi.crudretrofit.LoginActivity;

import java.util.HashMap;
@SuppressLint("CommitPrefEdits")
public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Sesi";

    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_LEVEL = "level";

    public SessionManager(Context context) {
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
        this.context = context;
        this.PRIVATE_MODE = PRIVATE_MODE;
    }

    public void createLoginSession(String username, String level){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_LEVEL, level);
        editor.commit();
    }

    public void checkLogin(){
        if (!this.isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
