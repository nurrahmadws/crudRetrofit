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

public class EditMahasiswaActivity extends AppCompatActivity {

    private EditText edtNima, edtNamaa, edtHpa, edtFakultasa, edtProdia, edtProvinsia;
    private RadioGroup radioJka;
    private RadioButton radioBtnLakia, radioBtnPerempuana, radioSexButtona;
    private Spinner spinAngkatana, spinKabupatenMa, spinKecamatanMa, spinKelurahanMa, spinLata, spinLnga;
    private Button btn_edit_mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mahasiswa);

        edtNima = findViewById(R.id.edtNima);
        edtNamaa = findViewById(R.id.edtNamaa);
        edtHpa = findViewById(R.id.edtHpa);
        edtFakultasa = findViewById(R.id.edtFakultasa);
        edtProdia = findViewById(R.id.edtProdia);
        edtProvinsia = findViewById(R.id.edtProvinsia);

        radioJka = findViewById(R.id.radioJka);
        radioBtnLakia = findViewById(R.id.radioBtnLakia);
        radioBtnPerempuana = findViewById(R.id.radioBtnPerempuana);

        spinAngkatana = findViewById(R.id.spinAngkatana);
        spinKabupatenMa = findViewById(R.id.spinKabupatenMa);
        spinKecamatanMa = findViewById(R.id.spinKecamatanMa);
        spinKelurahanMa = findViewById(R.id.spinKelurahanMa);
        spinLata = findViewById(R.id.spinLata);
        spinLnga = findViewById(R.id.spinLnga);

        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        edtNima.setText(nim);
        edtNima.setKeyListener(edtNima.getKeyListener());
        edtNima.setKeyListener(null);

        String nama = intent.getStringExtra("nama");
        edtNamaa.setText(nama);
        String no_hp = intent.getStringExtra("no_hp");
        edtHpa.setText(no_hp);
        String jk = intent.getStringExtra("jk");
        if (jk.equals("Perempuan")){
            radioBtnPerempuana.setChecked(true);
        }else {
            radioBtnLakia.setChecked(true);
        }
        String fakultas = intent.getStringExtra("fakultas");
        edtFakultasa.setText(fakultas);
        edtFakultasa.setKeyListener(edtFakultasa.getKeyListener());
        edtFakultasa.setKeyListener(null);
        String prodi = intent.getStringExtra("prodi");
        edtProdia.setText(prodi);
        edtProdia.setKeyListener(edtProdia.getKeyListener());
        edtProdia.setKeyListener(null);
        String angkatan = intent.getStringExtra("angkatan");
        if (angkatan.equals("2013")){
            spinAngkatana.setClickable(true);
        }else {
            spinAngkatana.setClickable(true);
        }
        String provinsi = intent.getStringExtra("provinsi");
        edtProvinsia.setText(provinsi);
        edtProvinsia.setKeyListener(edtProvinsia.getKeyListener());
        edtProvinsia.setKeyListener(null);

        Call<KabupatenResponse> call = RetrofitClient.getmInstance().getApi().spinKab();
        call.enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                List<Kabupaten> kabupatenList = response.body().getKabupatenList();
                List<String> listSpinKab = new ArrayList<>();
                for (int i = 0; i < kabupatenList.size(); i++){
                    listSpinKab.add(kabupatenList.get(i).getNm_kabupaten());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(EditMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinKab);
                spinKabupatenMa.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        spinKabupatenMa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kabupatenName = parent.getItemAtPosition(position).toString();
                listKecamatan(kabupatenName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKecamatanMa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kecamatanName = parent.getItemAtPosition(position).toString();
                listKelurahan(kecamatanName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKelurahanMa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kelurahanName = parent.getItemAtPosition(position).toString();
                listLatlng(kelurahanName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_edit_mhs = findViewById(R.id.btn_editt_mhs);
        btn_edit_mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMahasiswa();
            }
        });
    }
    private void listKecamatan(String kabupatenName){
        String nm_kabupaten = spinKabupatenMa.getSelectedItem().toString();

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
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EditMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinKec);
                    spinKecamatanMa.setAdapter(arrayAdapter);
                }

                @Override
                public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(EditMahasiswaActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void listKelurahan(String kecamatanName){
        String nm_kecamatan = spinKecamatanMa.getSelectedItem().toString();

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
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EditMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinKel);
                    spinKelurahanMa.setAdapter(arrayAdapter);
                }

                @Override
                public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(EditMahasiswaActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void listLatlng(String kelurahanName){
        String nm_kelurahan = spinKelurahanMa.getSelectedItem().toString();

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(EditMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinLat);
                    spinLata.setAdapter(adapter);

                    List<String> listSpinLng = new ArrayList<>();
                    for (int i = 0; i < latlngList.size(); i++){
                        listSpinLng.add(latlngList.get(i).getNm_lng());
                    }
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(EditMahasiswaActivity.this, android.R.layout.simple_spinner_dropdown_item, listSpinLng);
                    spinLnga.setAdapter(stringArrayAdapter);
                }

                @Override
                public void onFailure(Call<LatlngResponse> call, Throwable t) {

                }
            });
        }
    }
    private void editMahasiswa(){
        AlertDialog.Builder al = new AlertDialog.Builder(EditMahasiswaActivity.this);
        al.setTitle("Peringatan!");
        al.setMessage("Yaking InginMengubah Data Ini?");
        al.setCancelable(false);
        al.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nim = edtNima.getText().toString();
                String nama = edtNamaa.getText().toString();
                String no_hp = edtHpa.getText().toString();

                int selectedId = radioJka.getCheckedRadioButtonId();
                radioSexButtona = findViewById(selectedId);
                String jk = radioSexButtona.getText().toString();

                String fakultas = edtFakultasa.getText().toString();
                String prodi = edtProdia.getText().toString();
                String angkatan = spinAngkatana.getSelectedItem().toString();
                String provinsi = edtProvinsia.getText().toString();
                String nm_kabupaten = spinKabupatenMa.getSelectedItem().toString();
                String nm_kecamatan = spinKecamatanMa.getSelectedItem().toString();
                String nm_kelurahan = spinKelurahanMa.getSelectedItem().toString();
                String nm_lat = spinLata.getSelectedItem().toString();
                String nm_lng = spinLnga.getSelectedItem().toString();

                if (nama.isEmpty()){
                    edtNamaa.setError("Nama Harus Diisi");
                    edtNamaa.requestFocus();
                    return;
                }
                if (no_hp.isEmpty()){
                    edtHpa.setError("No Hp Harus Diisi");
                    edtHpa.requestFocus();
                    return;
                }
                Call<Value> call = RetrofitClient.getmInstance().getApi().updateMhs(nim, nama, no_hp, jk, fakultas, prodi, angkatan, provinsi,
                        nm_kabupaten, nm_kecamatan, nm_kelurahan, nm_lat, nm_lng);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            Toast.makeText(EditMahasiswaActivity.this, message, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(EditMahasiswaActivity.this, DetailMhsActivity.class));
                            finish();
                        }else {
                            Toast.makeText(EditMahasiswaActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(EditMahasiswaActivity.this, "Gagal Merespon", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(EditMahasiswaActivity.this, ReadMhsActivity.class));
            }
        });
        AlertDialog alertDialog = al.create();
        alertDialog.show();


    }
}
