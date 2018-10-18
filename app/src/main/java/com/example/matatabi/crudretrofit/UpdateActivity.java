package com.example.matatabi.crudretrofit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private EditText editTextKabupaten, edt_id;
    private Button btn_ubah, btn_hapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edt_id = (EditText)findViewById(R.id.editTextid);
        editTextKabupaten = (EditText)findViewById(R.id.editTextKabupaten);
        btn_ubah = (Button)findViewById(R.id.btn_ubah);
        btn_hapus = (Button)findViewById(R.id.btn_hapus);

        Intent intent = getIntent();
        edt_id.setText(intent.getStringExtra("id_kabupaten"));
        edt_id.setKeyListener(edt_id.getKeyListener());
        edt_id.setKeyListener(null);

        String nm_kabupaten = intent.getStringExtra("nm_kabupaten");

        editTextKabupaten.setText(nm_kabupaten);

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_kabupaten = edt_id.getText().toString();
                String nm_kabupaten = editTextKabupaten.getText().toString();

                if (nm_kabupaten.isEmpty()){
                    editTextKabupaten.setError("Kabupaten/Kota Harus diisi");
                    editTextKabupaten.requestFocus();
                    return;
                }

                Call<Value> call = RetrofitClient.getmInstance().getApi().ubah(id_kabupaten, nm_kabupaten);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(UpdateActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(UpdateActivity.this);
                ad.setTitle("Attention");
                ad.setMessage("Yakin Ingin Menhapus Data Ini?")
                        .setCancelable(false)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id_kabupaten = edt_id.getText().toString();
                                Call<Value> call = RetrofitClient.getmInstance().getApi().hapus(id_kabupaten);
                                call.enqueue(new Callback<Value>() {
                                    @Override
                                    public void onResponse(Call<Value> call, Response<Value> response) {
                                        String value = response.body().getValue();
                                        String message = response.body().getMessage();
                                        if (value.equals("1")){
                                            Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_LONG).show();
                                            finish();
                                        }else{
                                            Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Value> call, Throwable t) {
                                        t.printStackTrace();
                                        Toast.makeText(UpdateActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog = ad.create();
                alertDialog.show();
            }
        });
    }
}
