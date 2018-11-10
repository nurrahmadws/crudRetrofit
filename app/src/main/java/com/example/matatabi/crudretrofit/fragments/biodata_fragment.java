package com.example.matatabi.crudretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.adapters.DetailMhsAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Mahasiswa;
import com.example.matatabi.crudretrofit.model.MahasiswaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class biodata_fragment extends Fragment {
    private RecyclerView recyclerView;
    private DetailMhsAdapter detailMhsAdapter;
    private List<Mahasiswa> mahasiswaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.biodata_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_detail_mhs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();
    }

    private void loadData(){
        String nim = getActivity().getIntent().getStringExtra("nim");

        Call<MahasiswaResponse> call = RetrofitClient.getmInstance().getApi().detailMhs(nim);
        call.enqueue(new Callback<MahasiswaResponse>() {
            @Override
            public void onResponse(Call<MahasiswaResponse> call, Response<MahasiswaResponse> response) {
                mahasiswaList = response.body().getMahasiswaList();
                detailMhsAdapter = new DetailMhsAdapter(getContext(), mahasiswaList);
                recyclerView.setAdapter(detailMhsAdapter);
            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Gagal Merespon", Toast.LENGTH_LONG).show();
            }
        });
    }
}