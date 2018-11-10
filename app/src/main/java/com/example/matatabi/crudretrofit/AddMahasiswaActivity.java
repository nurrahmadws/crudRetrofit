package com.example.matatabi.crudretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Kabupaten;
import com.example.matatabi.crudretrofit.model.KabupatenResponse;
import com.example.matatabi.crudretrofit.model.Kecamatan;
import com.example.matatabi.crudretrofit.model.KecamatanResponse;
import com.example.matatabi.crudretrofit.model.Kelurahan;
import com.example.matatabi.crudretrofit.model.KelurahanResponse;
import com.example.matatabi.crudretrofit.model.Latlng;
import com.example.matatabi.crudretrofit.model.LatlngResponse;
import com.example.matatabi.crudretrofit.model.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMahasiswaActivity extends AppCompatActivity {

    private EditText edtNim, edtNama, edtHp, edtFakultas, edtProdi, edtProvinsi;
    private RadioGroup radioJk;
    private RadioButton radioSexButton;
    private Spinner spinAngkatan, spinKabupatenM, spinKecamatanM, spinKelurahanM, spinLat, spinLng;
    private Button btn_simpan_mhs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        edtNim = findViewById(R.id.edtNim);
        edtNama = findViewById(R.id.edtNama);
        edtHp = findViewById(R.id.edtHp);
        edtFakultas = findViewById(R.id.edtFakultas);
        edtProdi = findViewById(R.id.edtProdi);
        edtProvinsi = findViewById(R.id.edtProvinsi);

        radioJk = findViewById(R.id.radioJk);

        spinAngkatan = findViewById(R.id.spinAngkatan);
        spinKabupatenM = findViewById(R.id.spinKabupatenM);
        spinKecamatanM = findViewById(R.id.spinKecamatanM);
        spinKelurahanM = findViewById(R.id.spinKelurahanM);
        spinLat = findViewById(R.id.spinLat);
        spinLng = findViewById(R.id.spinLng);

        edtFakultas.setKeyListener(edtFakultas.getKeyListener());
        edtFakultas.setKeyListener(null);

        edtProdi.setKeyListener(edtProdi.getKeyListener());
        edtProdi.setKeyListener(null);

        edtProvinsi.setKeyListener(edtProvinsi.getKeyListener());
        edtProvinsi.setKeyListener(null);

        Call<KabupatenResponse> call = RetrofitClient.getmInstance().getApi().spinKab();
        call.enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                List<Kabupaten> kabupatenList = response.body().getKabupatenList();
                List<String> listSpinKab = new ArrayList<>();
                for (int i = 0; i < kabupatenList.size(); i++){
                    listSpinKab.add(kabupatenList.get(i).getNm_kabupaten());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AddMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinKab);
                spinKabupatenM.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        spinKabupatenM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kabupatenName = parent.getItemAtPosition(position).toString();
                listKecamatan(kabupatenName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKecamatanM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kecamatanName = parent.getItemAtPosition(position).toString();
                listKelurahan(kecamatanName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKelurahanM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kelurahanName = parent.getItemAtPosition(position).toString();
                listLatlng(kelurahanName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_simpan_mhs = findViewById(R.id.btn_simpan_mhs);
        btn_simpan_mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanMahasiswa();
            }
        });

    }

    private void listKecamatan(String kabupatenName){
        String nm_kabupaten = spinKabupatenM.getSelectedItem().toString();

        if (kabupatenName.equals(nm_kabupaten)){
            Call<KecamatanResponse> calll = RetrofitClient.getmInstance().getApi().spinKec(nm_kabupaten);
            calll.enqueue(new Callback<KecamatanResponse>() {
                @Override
                public void onResponse(Call<KecamatanResponse> call, Response<KecamatanResponse> response) {
                    List<Kecamatan> kecamatanList = response.body().getKecamatanList();
                    List<String> listSpinKec = new ArrayList<>();
                    for (int i = 0; i < kecamatanList.size(); i++){
                        listSpinKec.add(kecamatanList.get(i).getNm_kecamatan());
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinKec);
                    spinKecamatanM.setAdapter(arrayAdapter);
                }

                @Override
                public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(AddMahasiswaActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void listKelurahan(String kecamatanName){
        String nm_kecamatan = spinKecamatanM.getSelectedItem().toString();

        if (kecamatanName.equals(nm_kecamatan)){
            Call<KelurahanResponse> callk = RetrofitClient.getmInstance().getApi().spinKel(nm_kecamatan);
            callk.enqueue(new Callback<KelurahanResponse>() {
                @Override
                public void onResponse(Call<KelurahanResponse> call, Response<KelurahanResponse> response) {
                    List<Kelurahan> kelurahanList = response.body().getKelurahanList();
                    List<String> listSpinKel = new ArrayList<>();
                    for (int i = 0; i < kelurahanList.size(); i++){
                        listSpinKel.add(kelurahanList.get(i).getNm_kelurahan());
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinKel);
                    spinKelurahanM.setAdapter(arrayAdapter);
                }

                @Override
                public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(AddMahasiswaActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void listLatlng(String kelurahanName){
        String nm_kelurahan = spinKelurahanM.getSelectedItem().toString();

        if (kelurahanName.equals(nm_kelurahan)){
            Call<LatlngResponse> latlngResponseCall = RetrofitClient.getmInstance().getApi().spinLatlng(nm_kelurahan);
            latlngResponseCall.enqueue(new Callback<LatlngResponse>() {
                @Override
                public void onResponse(Call<LatlngResponse> call, Response<LatlngResponse> response) {
                    List<Latlng> latlngList = response.body().getLatlngList();
                    List<String> listSpinLat = new ArrayList<>();
                    for (int i = 0; i < latlngList.size(); i++){
                        listSpinLat.add(latlngList.get(i).getNm_lat());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinLat);
                    spinLat.setAdapter(adapter);

                    List<String> listSpinLng = new ArrayList<>();
                    for (int i = 0; i < latlngList.size(); i++){
                        listSpinLng.add(latlngList.get(i).getNm_lng());
                    }
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(AddMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinLng);
                    spinLng.setAdapter(stringArrayAdapter);
                }

                @Override
                public void onFailure(Call<LatlngResponse> call, Throwable t) {

                }
            });
        }
    }

    private void simpanMahasiswa(){
        String nim = edtNim.getText().toString().trim();
        String nama = edtNama.getText().toString().trim();
        String no_hp = edtHp.getText().toString().trim();

        int selectedId = radioJk.getCheckedRadioButtonId();
        radioSexButton = findViewById(selectedId);
        String jk = radioSexButton.getText().toString().trim();

        String fakultas = edtFakultas.getText().toString().trim();
        String prodi = edtProdi.getText().toString().trim();
        String angkatan = spinAngkatan.getSelectedItem().toString().trim();
        String provinsi = edtProvinsi.getText().toString().trim();
        String nm_kabupaten = spinKabupatenM.getSelectedItem().toString().trim();
        String nm_kecamatan = spinKecamatanM.getSelectedItem().toString().trim();
        String nm_kelurahan = spinKelurahanM.getSelectedItem().toString().trim();
        String nm_lat = spinLat.getSelectedItem().toString().trim();
        String nm_lng = spinLng.getSelectedItem().toString().trim();

        if (nim.isEmpty()){
            edtNim.setError("NIM harus Diisi");
            edtNim.requestFocus();
            return;
        }
        if (nama.isEmpty()){
            edtNama.setError("Nama Harus Diisi");
            edtNama.requestFocus();
            return;
        }
        if (no_hp.isEmpty()){
            edtHp.setError("No Hp Harus Diisi");
            edtHp.requestFocus();
            return;
        }

        Call<Value> call = RetrofitClient.getmInstance().getApi().insertMhs(nim, nama, no_hp, jk, fakultas, prodi, angkatan, provinsi, nm_kabupaten,
                nm_kecamatan, nm_kelurahan, nm_lat, nm_lng);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                if (value.equals("1")){
                    Toast.makeText(AddMahasiswaActivity.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddMahasiswaActivity.this, DetailMhsActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }else{
                    Toast.makeText(AddMahasiswaActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AddMahasiswaActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
