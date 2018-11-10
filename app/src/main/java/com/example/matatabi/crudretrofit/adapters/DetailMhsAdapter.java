package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.model.Mahasiswa;

import java.util.List;

public class DetailMhsAdapter extends RecyclerView.Adapter<DetailMhsAdapter.MahasiswaViewHolder> {

    private Context mcs;
    private List<Mahasiswa> mahasiswaList;

    public DetailMhsAdapter(Context mcs, List<Mahasiswa> mahasiswaList) {
        this.mcs = mcs;
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcs).inflate(R.layout.recyclerview_detail_mahasiswa, viewGroup, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder mahasiswaViewHolder, int i) {
        Mahasiswa  mahasiswa = mahasiswaList.get(i);
        mahasiswaViewHolder.txtNimm.setText(mahasiswa.getNim());
        mahasiswaViewHolder.txtNama.setText(mahasiswa.getNama());
        mahasiswaViewHolder.txtJk.setText(mahasiswa.getJk());
        mahasiswaViewHolder.txtNoHp.setText(mahasiswa.getNo_hp());
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder{

        private TextView txtNimm, txtNama, txtJk, txtNoHp;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNimm = itemView.findViewById(R.id.txtNimm);
            txtNama = itemView.findViewById(R.id.txtNamaa);
            txtJk = itemView.findViewById(R.id.txtJk);
            txtNoHp = itemView.findViewById(R.id.txtNoHp);
        }
    }
}
