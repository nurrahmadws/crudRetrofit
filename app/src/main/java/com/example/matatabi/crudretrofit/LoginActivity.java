package com.example.matatabi.crudretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    
    private EditText edtTextUsernameLogin, edtTextPasswordLogin;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTextUsernameLogin = findViewById(R.id.edtTextUsernameLogin);

        edtTextPasswordLogin = findViewById(R.id.edtTextPasswordLogin);
        
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(edtTextUsernameLogin.getText());
                String password = String.valueOf(edtTextPasswordLogin.getText());

                if (username.isEmpty()){
                    edtTextUsernameLogin.setError("Username Harus di Isi");
                    edtTextUsernameLogin.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    edtTextPasswordLogin.setError("Password Harus di Isi");
                    edtTextPasswordLogin.requestFocus();
                    return;
                }
                Call<LoginResponse> call = RetrofitClient.getmInstance().getApi().userLogin(username, password);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()){
                            Boolean value = response.body().getValue();
                            if (value){
                                String message = response.body().getMessage();
                                String username = response.body().getUsername();
                                String level = response.body().getLevel();

                                if (level.equals("Admin")){
                                    String error = "Sukses, "+message;
                                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                                    finish();
                                    Intent goAdmin = new Intent(LoginActivity.this, AdminActivity.class);
                                    startActivity(goAdmin);
                                }else if (level.equals("Mahasiswa")){
                                    String error = "Sukses, "+message;
                                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                                    finish();
                                    Intent goMhs = new Intent(LoginActivity.this, MahasiswaActivity.class);
                                    startActivity(goMhs);
                                }

                            }else {
                                Toast.makeText(LoginActivity.this, "Username Tidak Terdaftar/Password Salah", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error try "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
