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
import com.example.matatabi.crudretrofit.model.Kabupaten;
import com.example.matatabi.crudretrofit.model.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateKelActivity extends AppCompatActivity {

    private EditText edtIdKel, edtKabb, edtKecc, edtKell;
    private Button btnUbahDes, btnHapusDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kel);

        edtIdKel = (EditText) findViewById(R.id.edtidKel);
        edtKabb = (EditText) findViewById(R.id.edtkabb);
        edtKecc = (EditText) findViewById(R.id.edtKecc);
        edtKell = (EditText) findViewById(R.id.edtKeldess);

        Intent intent = getIntent();
        edtIdKel.setText(intent.getStringExtra("id_kelurahan"));
        edtIdKel.setKeyListener(edtIdKel.getKeyListener());
        edtIdKel.setKeyListener(null);

        String nm_kabupaten = intent.getStringExtra("nm_kabupaten");
        edtKabb.setText(nm_kabupaten);
        String nm_kecamatan = intent.getStringExtra("nm_kecamatan");
        edtKecc.setText(nm_kecamatan);
        String nm_kelurahan = intent.getStringExtra("nm_kelurahan");
        edtKell.setText(nm_kelurahan);

        btnUbahDes = (Button) findViewById(R.id.btnUbahDes);
        btnUbahDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_kelurahan = edtIdKel.getText().toString();
                String nm_kabupaten = edtKabb.getText().toString();
                String nm_kecamatan = edtKecc.getText().toString();
                String nm_kelurahan = edtKell.getText().toString();

                if (nm_kabupaten.isEmpty()){
                    edtKabb.setError("Kabupaten Harus Diisi");
                    edtKabb.requestFocus();
                    return;
                }
                
                if (nm_kecamatan.isEmpty()){
                    edtKecc.setError("Kecamatan Harus Diisi");
                    edtKecc.requestFocus();
                    return;
                }
                
                if (nm_kelurahan.isEmpty()){
                    edtKell.setError("Kelurahan/Desa Harus diisi");
                    edtKell.requestFocus();
                    return;
                }

                Call<Value> call = RetrofitClient.getmInstance().getApi().ubahkel(id_kelurahan, nm_kabupaten, nm_kecamatan, nm_kelurahan);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(UpdateKelActivity.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(UpdateKelActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(UpdateKelActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnHapusDes = (Button) findViewById(R.id.btnHapusDes);
        btnHapusDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateKelActivity.this);
                alertDialog.setTitle("Peringatan!!!");
                alertDialog.setMessage("Yakin Ingin Menghapus Data Ini?")
                            .setCancelable(false)
                            .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String id_kelurahan = edtIdKel.getText().toString();
                                    Call<Value> call = RetrofitClient.getmInstance().getApi().hapusKel(id_kelurahan);
                                    call.enqueue(new Callback<Value>() {
                                        @Override
                                        public void onResponse(Call<Value> call, Response<Value> response) {
                                            String value = response.body().getValue();
                                            String message = response.body().getMessage();
                                            if (value.equals("1")){
                                                Toast.makeText(UpdateKelActivity.this, message, Toast.LENGTH_SHORT).show();
                                                finish();
                                            }else {
                                                Toast.makeText(UpdateKelActivity.this, message, Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Value> call, Throwable t) {
                                            t.printStackTrace();
                                            Toast.makeText(UpdateKelActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }
}
