package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.LoginSession.SessionManager;
import com.example.matatabi.crudretrofit.adapters.MahasiswaAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Mahasiswa;
import com.example.matatabi.crudretrofit.model.MahasiswaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadMhsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private List<Mahasiswa> mahasiswaList;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_mhs);

//        session= new SessionManager(getApplicationContext());
//        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
//        session.checkLogin();

        recyclerView = findViewById(R.id.rv_mhs);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReadMhsActivity.this));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        Call<MahasiswaResponse> call = RetrofitClient.getmInstance().getApi().readMhs();
        call.enqueue(new Callback<MahasiswaResponse>() {
            @Override
            public void onResponse(Call<MahasiswaResponse> call, Response<MahasiswaResponse> response) {
                mahasiswaList = response.body().getMahasiswaList();
                mahasiswaAdapter = new MahasiswaAdapter(ReadMhsActivity.this, mahasiswaList);
                recyclerView.setAdapter(mahasiswaAdapter);
            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {

            }
        });
    }
}
