package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.adapters.KabupatenAdapter;
import com.example.matatabi.crudretrofit.adapters.KecamatanAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Kabupaten;
import com.example.matatabi.crudretrofit.model.KabupatenResponse;
import com.example.matatabi.crudretrofit.model.Kecamatan;
import com.example.matatabi.crudretrofit.model.KecamatanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KecamatanAdapter kecamatanAdapter;
    private List<Kecamatan> kecamatanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = (RecyclerView) findViewById(R.id.rvv);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        String id_kabupaten = getIntent().getStringExtra("id_kabupaten");
        Call<KecamatanResponse> call = RetrofitClient.getmInstance().getApi().readKecDet(id_kabupaten);
        call.enqueue(new Callback<KecamatanResponse>() {
            @Override
            public void onResponse(Call<KecamatanResponse> call, Response<KecamatanResponse> response) {
                kecamatanList = response.body().getKecamatanList();
                kecamatanAdapter = new KecamatanAdapter(DetailActivity.this, kecamatanList);
                recyclerView.setAdapter(kecamatanAdapter);
            }

            @Override
            public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
