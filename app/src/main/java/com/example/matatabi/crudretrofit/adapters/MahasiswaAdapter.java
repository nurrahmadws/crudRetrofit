package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.DetailMhsActivity;
import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.model.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {

    private Context mctt;
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(Context mctt, List<Mahasiswa> mahasiswaList) {
        this.mctt = mctt;
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mctt).inflate(R.layout.recyclerview_mhs, viewGroup, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder mahasiswaViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtNim, txtNama;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNim = itemView.findViewById(R.id.txtNim);
            txtNama = itemView.findViewById(R.id.txtNama);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String nim = txtNim.getText().toString();
            String nama = txtNama.getText().toString();

            Intent intent = new Intent(mctt, DetailMhsActivity.class);
            intent.putExtra("nim", nim);
            intent.putExtra("nama", nama);
            mctt.startActivity(intent);
        }
    }
}
