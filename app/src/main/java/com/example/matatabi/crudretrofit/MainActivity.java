package com.example.matatabi.crudretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.api.ApiRequest;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText edtTextKabupaten;
    private Button btn_daftar, btn_lihat, btn_tambah_keca, btn_lihat_kecamatan, btn_tambah_desa, btn_lihat_desa, btn_lihat_latlng, btn_tambah_laylng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTextKabupaten = (EditText) findViewById(R.id.edtTextKabupaten);
        btn_daftar = (Button) findViewById(R.id.btn_daftar);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nm_kabupaten = edtTextKabupaten.getText().toString().trim();

                if (nm_kabupaten.isEmpty()){
                    edtTextKabupaten.setError("Kabupaten/Kota Harus diisi");
                    edtTextKabupaten.requestFocus();
                    return;
                }

                Call<Value> call = RetrofitClient.getmInstance().getApi().tambahKabupaten(nm_kabupaten);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, ReadActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Tidak Dapat Merespon", Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
            }
        });
        btn_lihat = (Button) findViewById(R.id.btn_lihat);
        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReadActivity.class));
            }
        });

        btn_tambah_keca = (Button) findViewById(R.id.btn_tambah_kec);
        btn_tambah_keca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddKecamatanActivity.class));
            }
        });

        btn_lihat_kecamatan = (Button) findViewById(R.id.btn_lihat_kec);
        btn_lihat_kecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReadKecActivity.class));
            }
        });

        btn_tambah_desa = (Button) findViewById(R.id.btn_tambah_desa);
        btn_tambah_desa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReadActivity.class));
            }
        });

        btn_lihat_desa = (Button) findViewById(R.id.btn_lihat_desa);
        btn_lihat_desa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReadKelActivity.class));
            }
        });

        btn_tambah_laylng = (Button) findViewById(R.id.btn_tambah_latlng);
        btn_tambah_laylng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReadActivity.class));
            }
        });

        btn_lihat_latlng = (Button) findViewById(R.id.btn_lihat_latlng);
        btn_lihat_latlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReadLatlngActivity.class));
            }
        });
    }
}
