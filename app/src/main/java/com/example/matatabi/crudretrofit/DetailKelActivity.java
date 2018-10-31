package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.adapters.KelurahanAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Kelurahan;
import com.example.matatabi.crudretrofit.model.KelurahanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KelurahanAdapter kelurahanAdapter;
    private List<Kelurahan> kelurahanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kel);

        recyclerView = (RecyclerView) findViewById(R.id.rvvv);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailKelActivity.this));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        String id_kecamatan = getIntent().getStringExtra("id_kecamatan");
        Call<KelurahanResponse> call = RetrofitClient.getmInstance().getApi().readKelDet(id_kecamatan);
        call.enqueue(new Callback<KelurahanResponse>() {
            @Override
            public void onResponse(Call<KelurahanResponse> call, Response<KelurahanResponse> response) {
                kelurahanList = response.body().getKelurahanList();
                kelurahanAdapter = new KelurahanAdapter(DetailKelActivity.this, kelurahanList);
                recyclerView.setAdapter(kelurahanAdapter);
            }

            @Override
            public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailKelActivity.this, "Gagal Merespon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

