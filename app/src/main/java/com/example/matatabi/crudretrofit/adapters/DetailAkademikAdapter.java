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

public class DetailAkademikAdapter extends RecyclerView.Adapter<DetailAkademikAdapter.AkademikViewHolder> {

    private Context mctr;
    private List<Mahasiswa> mahasiswaList;

    public DetailAkademikAdapter(Context mctr, List<Mahasiswa> mahasiswaList) {
        this.mctr = mctr;
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public AkademikViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mctr).inflate(R.layout.recyclerview_detail_akademik, viewGroup, false);
        return new AkademikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AkademikViewHolder akademikViewHolder, int i) {
        Mahasiswa mahasiswa =  mahasiswaList.get(i);
        akademikViewHolder.txtFakultas.setText(mahasiswa.getFakultas());
        akademikViewHolder.txtProdi.setText(mahasiswa.getProdi());
        akademikViewHolder.txtAngkatan.setText(mahasiswa.getAngkatan());

    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    class AkademikViewHolder extends RecyclerView.ViewHolder{

        TextView txtFakultas, txtProdi, txtAngkatan;

        public AkademikViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFakultas = itemView.findViewById(R.id.txtFakultas);
            txtProdi = itemView.findViewById(R.id.txtProdi);
            txtAngkatan = itemView.findViewById(R.id.txtAngkatan);
        }
    }
}

