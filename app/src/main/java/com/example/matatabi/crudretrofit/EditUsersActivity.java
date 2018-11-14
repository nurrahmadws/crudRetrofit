package com.example.matatabi.crudretrofit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.adapters.UsersAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUsersActivity extends AppCompatActivity {
    private EditText editTextidUser, editTextUsername, editTextPassword;
    private Spinner spinLevelL;
    private Button btn_Ubah_user, btn_hapus_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);

        editTextidUser = findViewById(R.id.editTextidUser);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        spinLevelL = findViewById(R.id.spinLevelL);

        Intent intent = getIntent();
        editTextidUser.setText(intent.getStringExtra("id_user"));
        editTextidUser.setKeyListener(editTextidUser.getKeyListener());
        editTextidUser.setKeyListener(null);

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        editTextUsername.setText(username);
        editTextPassword.setText(password);

        String level = intent.getStringExtra("level");
        if (level.equals("Admin")){
            spinLevelL.setClickable(true);
        }else if (level.equals("Mahasiswa")){
            spinLevelL.setClickable(true);
        }

        btn_Ubah_user = findViewById(R.id.btn_ubah_user);
        btn_Ubah_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder al = new AlertDialog.Builder(EditUsersActivity.this);
                al.setTitle("Peringatan!");
                al.setMessage("Yakin Ingin Mengubah Data Ini?");
                al.setCancelable(false);
                al.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id_user = editTextidUser.getText().toString();
                        String username = editTextUsername.getText().toString();
                        String password = editTextPassword.getText().toString();
                        String level = spinLevelL.getSelectedItem().toString();

                        if (username.isEmpty()){
                            editTextUsername.setError("Username Tidak Boleh Kosong");
                            editTextUsername.requestFocus();
                            return;
                        }
                        if (password.isEmpty()){
                            editTextPassword.setError("Password Tidak Boleh Kosong");
                            editTextPassword.requestFocus();
                            return;
                        }
                        if (password.length() < 6){
                            editTextPassword.setError("Password Harus Lebih Dari 6 Karakter");
                            editTextPassword.requestFocus();
                            return;
                        }

                        Call<Value> call = RetrofitClient.getmInstance().getApi().editUser(id_user, username, password, level);
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                if (value.equals("1")){
                                    Toast.makeText(EditUsersActivity.this, message, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(EditUsersActivity.this, UsersActivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(EditUsersActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(EditUsersActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = al.create();
                alertDialog.show();
            }
        });

        btn_hapus_user = findViewById(R.id.btn_hapus_user);
        btn_hapus_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder al = new AlertDialog.Builder(EditUsersActivity.this);
                al.setTitle("Peringatan!");
                al.setMessage("Ingin Menghapus Data Ini?");
                al.setCancelable(false);
                al.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id_user = editTextidUser.getText().toString();
                        Call<Value> call = RetrofitClient.getmInstance().getApi().hapusUser(id_user);
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                if (value.equals("1")){
                                    Toast.makeText(EditUsersActivity.this, message, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(EditUsersActivity.this, UsersActivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(EditUsersActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(EditUsersActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = al.create();
                dialog.show();
            }
        });

    }
}
