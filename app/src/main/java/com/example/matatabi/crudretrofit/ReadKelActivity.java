package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.matatabi.crudretrofit.adapters.KelurahanAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Kelurahan;
import com.example.matatabi.crudretrofit.model.KelurahanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadKelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KelurahanAdapter kelurahanAdapter;
    private List<Kelurahan> kelurahanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_kel);

        recyclerView = (RecyclerView) findViewById(R.id.rv_kelurahan);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReadKelActivity.this));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        Call<KelurahanResponse> call = RetrofitClient.getmInstance().getApi().readKel();
        call.enqueue(new Callback<KelurahanResponse>() {
            @Override
            public void onResponse(Call<KelurahanResponse> call, Response<KelurahanResponse> response) {
                kelurahanList = response.body().getKelurahanList();
                kelurahanAdapter = new KelurahanAdapter(ReadKelActivity.this, kelurahanList);
                recyclerView.setAdapter(kelurahanAdapter);
            }

            @Override
            public void onFailure(Call<KelurahanResponse> call, Throwable t) {

            }
        });
    }
}
