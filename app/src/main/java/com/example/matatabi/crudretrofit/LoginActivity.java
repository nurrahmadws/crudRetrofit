package com.example.matatabi.crudretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.LoginSession.JsonParser;
import com.example.matatabi.crudretrofit.LoginSession.SessionManager;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.LoginResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    
    private EditText edtTextUsernameLogin, edtTextPasswordLogin;
    private Button btnLogin;
    private Intent a;
    String url, success;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        sessionManager = new SessionManager(getApplicationContext());
//        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();
        
        edtTextUsernameLogin = findViewById(R.id.edtTextUsernameLogin);
        edtTextPasswordLogin = findViewById(R.id.edtTextPasswordLogin);
        
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtTextUsernameLogin.getText().toString();
                String password = edtTextPasswordLogin.getText().toString();

                String user = username.replace("", "%20");
                String pass = password.replace("", "%20");
                url = "http://192.168.43.207/lagi/login.php?"+"username="+user+"&password="+pass;
                if (edtTextUsernameLogin.getText().toString().trim().length() > 0
                        && edtTextPasswordLogin.getText().toString().trim().length() > 0){
                    new Masuk().execute();
                }else {
                    Toast.makeText(LoginActivity.this, "Username/Passowrd Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class Masuk extends AsyncTask<String, String, String>{
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            JsonParser jsonParser = new JsonParser();
            JSONObject json = jsonParser.getJsonFromUrl(url);

            try{
                success = json.getString("berhasil");
                Log.e("error", "nilai sukses=" + success);
                JSONArray hasil = json.getJSONArray("login");

                if (success.equals("1")){
                    for (int i = 0; i < hasil.length(); i++){
                        JSONObject c = hasil.getJSONObject(i);
                        String username = c.getString("username");
                        String level = c.getString("level");
                        sessionManager.createLoginSession(username, level);
                        Log.e("ok", " ambil data");
                    }
                }else {
                    Log.e("error", "tidak bisa ambil data 0");
                }
            } catch (Exception e) {
                Log.e("erro", "tidak bisa ambil data 1");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (success.equals("1")){
                a = new Intent(LoginActivity.this, ReadMhsActivity.class);
                startActivity(a);
                finish();
            }else {
                Toast.makeText(LoginActivity.this, "Username/Password Salah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
