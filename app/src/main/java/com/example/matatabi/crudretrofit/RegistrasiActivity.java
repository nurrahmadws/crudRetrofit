package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {

    private EditText edtTextUsername,edtTextPassword;
    private Spinner spinLevel;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        edtTextUsername = findViewById(R.id.edtTextUsername);
        edtTextPassword = findViewById(R.id.edtTextPassword);

        spinLevel = findViewById(R.id.spinLevel);

        btnSimpan = findViewById(R.id.btnRegistrasii);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtTextUsername.getText().toString().trim();
                String password = edtTextPassword.getText().toString().trim();
                String level = spinLevel.getSelectedItem().toString().trim();

                if (username.isEmpty()){
                    edtTextUsername.setError("Username Haru diisi");
                    edtTextUsername.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    edtTextPassword.setError("Password Harus Diisi");
                    edtTextPassword.requestFocus();
                    return;
                }
                if (password.length() < 6){
                    edtTextPassword.setError("Password Should be atleast 6 character long");
                    edtTextPassword.requestFocus();
                    return;
                }
                Call<Value> call = RetrofitClient.getmInstance().getApi().registrasi(username, password, level);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(RegistrasiActivity.this, message, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegistrasiActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(RegistrasiActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
