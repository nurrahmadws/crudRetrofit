package com.example.matatabi.crudretrofit;

import android.content.Context;
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
import com.example.matatabi.crudretrofit.model.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddKecamatanActivity extends AppCompatActivity {

    private EditText edtKecamatan;
    private Button btnSimpanKec;
    private Spinner spinKabupaten;

    Context mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kecamatan);

        edtKecamatan = (EditText)findViewById(R.id.edtKecamatan);
        btnSimpanKec = (Button)findViewById(R.id.btnSimpanKec);

        spinKabupaten = (Spinner)findViewById(R.id.spinKabupaten);

        initSpinKabupaten();

        spinKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kabupaten = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddKecamatanActivity.this, "Terpilih Kabupaten/Kota " + kabupaten, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSimpanKec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanKecamatan();
            }
        });
    }

    private void initSpinKabupaten(){
        Call<KabupatenResponse> call = RetrofitClient.getmInstance().getApi().spinKab();
        call.enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                if (response.isSuccessful()) {
                    List<Kabupaten> kabupatenList = response.body().getKabupatenList();
                    List<String> listSpinner = new ArrayList<>();
                    List<String> listSpin = new ArrayList<>();
                    for (int i = 0; i < kabupatenList.size(); i++) {
                        listSpinner.add(kabupatenList.get(i).getNm_kabupaten());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddKecamatanActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinner);
                    spinKabupaten.setAdapter(adapter);
                }else{
                    Toast.makeText(AddKecamatanActivity.this, "Gagal Mengambil Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AddKecamatanActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpanKecamatan(){
        String nm_kabupaten = spinKabupaten.getSelectedItem().toString().trim();
        String nm_kecamatan = edtKecamatan.getText().toString().trim();

        if (nm_kecamatan.isEmpty()){
            edtKecamatan.setError("Kecamatan Harus Diisi");
            edtKecamatan.requestFocus();
            return;
        }

        Call<Value> call = RetrofitClient.getmInstance().getApi().tambahKecamatan(nm_kabupaten,nm_kecamatan);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                if (value.equals("1")){
                    Toast.makeText(AddKecamatanActivity.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddKecamatanActivity.this, ReadKecActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }else {
                    Toast.makeText(AddKecamatanActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AddKecamatanActivity.this, "Gagal Merespon", Toast.LENGTH_LONG).show();
            }
        });
    }
}
