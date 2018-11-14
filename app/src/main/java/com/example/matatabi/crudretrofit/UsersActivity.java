package com.example.matatabi.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.matatabi.crudretrofit.adapters.UsersAdapter;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Users;
import com.example.matatabi.crudretrofit.model.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private List<Users> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerView = findViewById(R.id.rv_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(UsersActivity.this));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        Call<UsersResponse> call = RetrofitClient.getmInstance().getApi().readUsers();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                usersList = response.body().getUsersList();
                usersAdapter = new UsersAdapter(UsersActivity.this, usersList);
                recyclerView.setAdapter(usersAdapter);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }
}
