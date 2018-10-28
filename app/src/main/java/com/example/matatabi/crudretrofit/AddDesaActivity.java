package com.example.matatabi.crudretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Kabupaten;
import com.example.matatabi.crudretrofit.model.KabupatenResponse;
import com.example.matatabi.crudretrofit.model.Kecamatan;
import com.example.matatabi.crudretrofit.model.KecamatanResponse;
import com.example.matatabi.crudretrofit.model.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDesaActivity extends AppCompatActivity {

    private EditText edtidKec, edtkab, edtKec, edtKeldes;
    private Button btnSimpanDes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_desa);

        edtidKec = (EditText) findViewById(R.id.edtidKec);
        edtkab = (EditText) findViewById(R.id.edtkab);
        edtKec = (EditText) findViewById(R.id.edtKec);
        edtKeldes = (EditText) findViewById(R.id.edtKeldes);
        btnSimpanDes = (Button) findViewById(R.id.btnSimpanDes);

        Intent intent = getIntent();
        edtidKec.setText(intent.getStringExtra("id_kecamatan"));
        edtidKec.setKeyListener(edtidKec.getKeyListener());
        edtidKec.setKeyListener(null);

        edtkab.setText(intent.getStringExtra("nm_kabupaten"));
        edtkab.setKeyListener(edtkab.getKeyListener());
        edtkab.setKeyListener(null);

        edtKec.setText(intent.getStringExtra("nm_kecamatan"));
        edtKec.setKeyListener(edtKec.getKeyListener());
        edtKec.setKeyListener(null);

        btnSimpanDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm_kabupaten = edtkab.getText().toString();
                String nm_kecamatan = edtKec.getText().toString();
                String nm_kelurahan = edtKeldes.getText().toString();

                if (nm_kelurahan.isEmpty()){
                    edtKeldes.setError("Kelurahan/Desa Harus Diisi");
                    edtKeldes.requestFocus();
                    return;
                }

                Call<Value> call = RetrofitClient.getmInstance().getApi().tambahKelurahan(nm_kabupaten, nm_kecamatan, nm_kelurahan);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(AddDesaActivity.this, message, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddDesaActivity.this, ReadKelActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }else {
                            Toast.makeText(AddDesaActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(AddDesaActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
