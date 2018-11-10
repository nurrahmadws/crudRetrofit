package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.DetailDaerahMhsActivity;
import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.model.Mahasiswa;

import java.util.List;

public class DetailDaerahAdapter extends RecyclerView.Adapter<DetailDaerahAdapter.DaerahViewHolder> {
    private Context mctr;
    private List<Mahasiswa> mahasiswaList;

    public DetailDaerahAdapter(Context mctr, List<Mahasiswa> mahasiswaList) {
        this.mctr = mctr;
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public DaerahViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mctr).inflate(R.layout.recyclerview_detail_daerah, viewGroup, false);
        return new DaerahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaerahViewHolder daerahViewHolder, int i) {
        Mahasiswa mahasiswa = mahasiswaList.get(i);
        daerahViewHolder.txtProvinsi.setText(mahasiswa.getProvinsi());
        daerahViewHolder.txtKotaKab.setText(mahasiswa.getNm_kabupaten());
        daerahViewHolder.txtKecamatana.setText(mahasiswa.getNm_kecamatan());
        daerahViewHolder.txtDesaKel.setText(mahasiswa.getNm_kelurahan());
        daerahViewHolder.txtLata.setText(mahasiswa.getNm_lat());
        daerahViewHolder.txtLnga.setText(mahasiswa.getNm_lng());
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    class DaerahViewHolder extends RecyclerView.ViewHolder{

        TextView txtProvinsi, txtKotaKab, txtKecamatana, txtDesaKel, txtLata, txtLnga;
        Button btn_tampilkan_map;

        public DaerahViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProvinsi = itemView.findViewById(R.id.txtProvinsi);
            txtKotaKab = itemView.findViewById(R.id.txtKotaKab);
            txtKecamatana = itemView.findViewById(R.id.txtKecamatana);
            txtDesaKel = itemView.findViewById(R.id.txtDesaKel);
            txtLata = itemView.findViewById(R.id.txtlata);
            txtLnga = itemView.findViewById(R.id.txtLnga);

            btn_tampilkan_map = itemView.findViewById(R.id.btn_tampilka_map);
            btn_tampilkan_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nm_kelurahan = txtDesaKel.getText().toString();
                    String nm_lat = txtLata.getText().toString();
                    String nm_lng = txtLnga.getText().toString();

                    Intent intent = new Intent(mctr, DetailDaerahMhsActivity.class);
                    intent.putExtra("nm_kelurahan", nm_kelurahan);
                    intent.putExtra("nm_lat", nm_lat);
                    intent.putExtra("nm_lng", nm_lng);
                    mctr.startActivity(intent);
                }
            });
        }
    }
}
