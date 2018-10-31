package com.example.matatabi.crudretrofit;

import android.content.Intent;
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

public class AddLatlngActivity extends AppCompatActivity {

    private EditText edtidKelLatlng, edtkablatlng, edtKecLatlng, edtKeldesLatlng, edtLat, edtLng;
    private Button btnSimpanLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_latlng);

        edtidKelLatlng = (EditText) findViewById(R.id.edtidKelLatlng);
        edtkablatlng = (EditText) findViewById(R.id.edtkablatlng);
        edtKecLatlng = (EditText) findViewById(R.id.edtKecLatlng);
        edtKeldesLatlng = (EditText) findViewById(R.id.edtKeldesLatlng);
        edtLat = (EditText) findViewById(R.id.edtLat);
        edtLng = (EditText) findViewById(R.id.edtLng);
        btnSimpanLatLng = (Button) findViewById(R.id.btnSimpanLatLng);

        Intent intent = getIntent();
        edtidKelLatlng.setText(intent.getStringExtra("id_kelurahan"));
        edtidKelLatlng.setKeyListener(edtidKelLatlng.getKeyListener());
        edtidKelLatlng.setKeyListener(null);

        edtkablatlng.setText(intent.getStringExtra("nm_kabupaten"));
        edtkablatlng.setKeyListener(edtkablatlng.getKeyListener());
        edtkablatlng.setKeyListener(null);

        edtKecLatlng.setText(intent.getStringExtra("nm_kecamatan"));
        edtKecLatlng.setKeyListener(edtKecLatlng.getKeyListener());
        edtKecLatlng.setKeyListener(null);

        edtKeldesLatlng.setText(intent.getStringExtra("nm_kelurahan"));
        edtKeldesLatlng.setKeyListener(edtKeldesLatlng.getKeyListener());
        edtKeldesLatlng.setKeyListener(null);

        btnSimpanLatLng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm_kabupaten = edtkablatlng.getText().toString();
                String nm_kecamatan = edtKecLatlng.getText().toString();
                String nm_kelurahan = edtKeldesLatlng.getText().toString();
                String nm_lat = edtLat.getText().toString().trim();
                String nm_lng = edtLng.getText().toString().trim();

                if (nm_lat.isEmpty()){
                    edtLat.setError("Latitude Harus diisi");
                    edtLat.requestFocus();
                    return;
                }
                if (nm_lng.isEmpty()){
                    edtLng.setError("Longtitude Harus Diisi");
                    edtLng.requestFocus();
                    return;
                }

                Call<Value> call = RetrofitClient.getmInstance().getApi().tambahLatlng(nm_kabupaten, nm_kecamatan, nm_kelurahan, nm_lat, nm_lng);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();

                        if (value.equals("1")){
                            Toast.makeText(AddLatlngActivity.this, message, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddLatlngActivity.this, ReadLatlngActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }else{
                            Toast.makeText(AddLatlngActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(AddLatlngActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
