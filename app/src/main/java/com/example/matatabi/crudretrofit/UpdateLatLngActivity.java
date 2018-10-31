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

public class UpdateLatLngActivity extends AppCompatActivity {

    private EditText edtIdLatLng, edtKabbb, edtKeccc, edtKeldesss, edtLatt, edtLngg;
    private Button btnUbahLatlng, btnHapusLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lat_lng);

        edtIdLatLng = (EditText)findViewById(R.id.edtIdLatLng);
        edtKabbb = (EditText)findViewById(R.id.edtKabbb);
        edtKeccc = (EditText)findViewById(R.id.edtKeccc);
        edtKeldesss = (EditText)findViewById(R.id.edtKeldesss);
        edtLatt = (EditText)findViewById(R.id.edtLatt);
        edtLngg = (EditText)findViewById(R.id.edtLngg);

        Intent intent = getIntent();
        edtIdLatLng.setText(intent.getStringExtra("id_latlng"));
        edtIdLatLng.setKeyListener(edtIdLatLng.getKeyListener());
        edtIdLatLng.setKeyListener(null);

        String nm_kabupaten = intent.getStringExtra("nm_kabupaten");
        edtKabbb.setText(nm_kabupaten);
        String nm_kecamatan = intent.getStringExtra("nm_kecamatan");
        edtKeccc.setText(nm_kecamatan);
        String nm_kelurahan = intent.getStringExtra("nm_kelurahan");
        edtKeldesss.setText(nm_kelurahan);
        String nm_lat = intent.getStringExtra("nm_lat");
        edtLatt.setText(nm_lat);
        String nm_lng = intent.getStringExtra("nm_lng");
        edtLngg.setText(nm_lng);

        btnUbahLatlng = (Button)findViewById(R.id.btnUbahLatlng);
        btnUbahLatlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_latlng = edtIdLatLng.getText().toString();
                String nm_kabupaten = edtKabbb.getText().toString();
                String nm_kecamatan = edtKeccc.getText().toString();
                String nm_kelurahan = edtKeldesss.getText().toString();
                String nm_lat = edtLatt.getText().toString();
                String nm_lng = edtLngg.getText().toString();

                if (nm_kabupaten.isEmpty()){
                    edtKabbb.setError("Kabupaten/Kota Harus Diisi");
                    edtKabbb.requestFocus();
                    return;
                }
                if (nm_kecamatan.isEmpty()){
                    edtKeccc.setError("Kecamatan Haru diisi");
                    edtKeccc.requestFocus();
                    return;
                }
                if (nm_kelurahan.isEmpty()){
                    edtKeldesss.setError("Kelurahan/Desa harus Diisi");
                    edtKeldesss.requestFocus();
                    return;
                }
                if (nm_lat.isEmpty()){
                    edtLatt.setError("Latitude Harus Diisi");
                    edtLatt.requestFocus();
                    return;
                }
                if (nm_lng.isEmpty()){
                    edtLngg.setError("Longtitude Harus Diisi");
                    edtLngg.requestFocus();
                    return;
                }

                Call<Value> call = RetrofitClient.getmInstance().getApi().ubahLatlng(id_latlng, nm_kabupaten, nm_kecamatan, nm_kelurahan, nm_lat, nm_lng);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(UpdateLatLngActivity.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(UpdateLatLngActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(UpdateLatLngActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnHapusLatLng = (Button)findViewById(R.id.btnHapusLatLng);
        btnHapusLatLng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(UpdateLatLngActivity.this);
                ad.setTitle("Peringatan!!!");
                ad.setMessage("Yakin Ingin Menghapus Data Ini?")
                        .setCancelable(false)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id_latlng = edtIdLatLng.getText().toString();
                                Call<Value> call = RetrofitClient.getmInstance().getApi().hapusLatlng(id_latlng);
                                call.enqueue(new Callback<Value>() {
                                    @Override
                                    public void onResponse(Call<Value> call, Response<Value> response) {
                                        String value = response.body().getValue();
                                        String message = response.body().getMessage();
                                        if (value.equals("1")){
                                            Toast.makeText(UpdateLatLngActivity.this, message, Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else {
                                            Toast.makeText(UpdateLatLngActivity.this, message, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Value> call, Throwable t) {
                                        t.printStackTrace();
                                        Toast.makeText(UpdateLatLngActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
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
