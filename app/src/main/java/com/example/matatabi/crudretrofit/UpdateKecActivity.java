package com.example.matatabi.crudretrofit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class UpdateKecActivity extends AppCompatActivity {

    private EditText edtKecamatanup, edtKecamatanupid;
    private Button btnUbahKec,btnHapusKec;
    private Spinner spinKabup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kec);

        edtKecamatanup = (EditText)findViewById(R.id.edtKecamatanup);
        edtKecamatanupid = (EditText) findViewById(R.id.edtKecamatanupid);
        btnUbahKec = (Button)findViewById(R.id.btnUbahKec);
        spinKabup = (Spinner)findViewById(R.id.spinKabupatenup);

        getDataSpinKab();

        spinKabup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kabupaten = parent.getItemAtPosition(position).toString();
                Toast.makeText(UpdateKecActivity.this, "Terpilih Kabupaten/Kota " + kabupaten, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent = getIntent();
        edtKecamatanupid.setText(intent.getStringExtra("id_kecamatan"));
        edtKecamatanupid.setKeyListener(edtKecamatanupid.getKeyListener());
        edtKecamatanupid.setKeyListener(null);

        String nm_kabupaten = intent.getStringExtra("nm_kabupaten");
        String nm_kecamatan = intent.getStringExtra("nm_kecamatan");
        edtKecamatanup.setText(nm_kecamatan);
        if (nm_kabupaten.equals("nm_kabupaten")){
            spinKabup.setClickable(true);
        }

        btnUbahKec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_kecamatan = edtKecamatanupid.getText().toString();
                String nm_kabupaten = spinKabup.getSelectedItem().toString();
                String nm_kecamatan = edtKecamatanup.getText().toString();

                if (nm_kecamatan.isEmpty()){
                    edtKecamatanup.setError("Kecamatan Harus diisi");
                    edtKecamatanup.requestFocus();
                    return;
                }

                Call<Value> call = RetrofitClient.getmInstance().getApi().ubahkec(id_kecamatan,nm_kabupaten,nm_kecamatan);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(UpdateKecActivity.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(UpdateKecActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(UpdateKecActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnHapusKec = (Button) findViewById(R.id.btnHapusKec);
        btnHapusKec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(UpdateKecActivity.this);
                ad.setTitle("Peringatan!!!");
                ad.setMessage("Yakin Ingin Menghapus Data Ini?");
                ad.setCancelable(false);
                ad.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id_kecamatan = edtKecamatanupid.getText().toString();
                        Call<Value> call = RetrofitClient.getmInstance().getApi().hapusKec(id_kecamatan);
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                if (value.equals("1")){
                                    Toast.makeText(UpdateKecActivity.this, message, Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(UpdateKecActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(UpdateKecActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = ad.create();
                dialog.show();
            }
        });

    }

    private void getDataSpinKab(){
        Call<KabupatenResponse> call = RetrofitClient.getmInstance().getApi().spinKab();
        call.enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                if (response.isSuccessful()){
                    List<Kabupaten> kabupatenList = response.body().getKabupatenList();
                    List<String> listSpin = new ArrayList<>();
                    for (int i = 0; i < kabupatenList.size(); i++){
                        listSpin.add(kabupatenList.get(i).getNm_kabupaten());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateKecActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpin);
                    spinKabup.setAdapter(arrayAdapter);
                }else {
                    Toast.makeText(UpdateKecActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(UpdateKecActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
