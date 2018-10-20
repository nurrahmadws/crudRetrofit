package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.matatabi.crudretrofit.adapters.KecamatanAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Kecamatan;
import com.example.matatabi.crudretrofit.model.KecamatanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadKecActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KecamatanAdapter kecamatanAdapter;
    private List<Kecamatan> kecamatanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_kec);

        recyclerView = (RecyclerView) findViewById(R.id.rv_kecamatan);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReadKecActivity.this));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        Call<KecamatanResponse> call = RetrofitClient.getmInstance().getApi().readKec();
        call.enqueue(new Callback<KecamatanResponse>() {
            @Override
            public void onResponse(Call<KecamatanResponse> call, Response<KecamatanResponse> response) {
                kecamatanList = response.body().getKecamatanList();
                kecamatanAdapter = new KecamatanAdapter(ReadKecActivity.this, kecamatanList);
                recyclerView.setAdapter(kecamatanAdapter);

            }

            @Override
            public void onFailure(Call<KecamatanResponse> call, Throwable t) {

            }
        });
    }
}
