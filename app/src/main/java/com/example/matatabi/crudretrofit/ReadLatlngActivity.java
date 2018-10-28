package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.matatabi.crudretrofit.adapters.LatlngAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Latlng;
import com.example.matatabi.crudretrofit.model.LatlngResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadLatlngActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LatlngAdapter latlngAdapter;
    private List<Latlng> latlngList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_latlng);

        recyclerView = (RecyclerView) findViewById(R.id.rv_latlng);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReadLatlngActivity.this));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        Call<LatlngResponse> call = RetrofitClient.getmInstance().getApi().readLatlng();
        call.enqueue(new Callback<LatlngResponse>() {
            @Override
            public void onResponse(Call<LatlngResponse> call, Response<LatlngResponse> response) {
                latlngList = response.body().getLatlngList();
                latlngAdapter = new LatlngAdapter(ReadLatlngActivity.this, latlngList);
                recyclerView.setAdapter(latlngAdapter);
            }

            @Override
            public void onFailure(Call<LatlngResponse> call, Throwable t) {

            }
        });
    }
}
