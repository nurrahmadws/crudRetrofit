package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.AddLatlngActivity;
import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.UpdateKecActivity;
import com.example.matatabi.crudretrofit.UpdateKelActivity;
import com.example.matatabi.crudretrofit.model.Kelurahan;

import java.util.List;
import java.util.zip.Inflater;

public class KelurahanAdapter extends RecyclerView.Adapter<KelurahanAdapter.KelurahanViewHolder> {

    private Context mct;
    private List<Kelurahan> kelurahanList;

    public  KelurahanAdapter(Context mct, List<Kelurahan> kelurahanList) {
        this.mct = mct;
        this.kelurahanList = kelurahanList;
    }

    @NonNull
    @Override
    public KelurahanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mct).inflate(R.layout.recyclerview_kelurahan, viewGroup, false);
        return new KelurahanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KelurahanViewHolder kelurahanViewHolder, int i) {
        Kelurahan kelurahan = kelurahanList.get(i);
        kelurahanViewHolder.txtiddd.setText(kelurahan.getId_kelurahan());
        kelurahanViewHolder.txtKabupatennn.setText(kelurahan.getNm_kabupaten());
        kelurahanViewHolder.txtKecamatann.setText(kelurahan.getNm_kecamatan());
        kelurahanViewHolder.txtKelurahan.setText(kelurahan.getNm_kelurahan());
    }

    @Override
    public int getItemCount() {
        return kelurahanList.size();
    }

    class KelurahanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtiddd, txtKabupatennn, txtKecamatann, txtKelurahan;

        public KelurahanViewHolder(@NonNull View itemView) {
            super(itemView);
            txtiddd = itemView.findViewById(R.id.txtiddd);
            txtKabupatennn = itemView.findViewById(R.id.txtKabupatennn);
            txtKecamatann = itemView.findViewById(R.id.txtKecamatann);
            txtKelurahan = itemView.findViewById(R.id.txtKelurahan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id_kelurahan = txtiddd.getText().toString();
            String nm_kabupaten = txtKabupatennn.getText().toString();
            String nm_kecamatan = txtKecamatann.getText().toString();
            String nm_kelurahan = txtKelurahan.getText().toString();

//            Intent intent = new Intent(mct, UpdateKelActivity.class);
            Intent intent = new Intent(mct, AddLatlngActivity.class);
            intent.putExtra("id_kelurahan", id_kelurahan);
            intent.putExtra("nm_kabupaten", nm_kabupaten);
            intent.putExtra("nm_kecamatan", nm_kecamatan);
            intent.putExtra("nm_kelurahan", nm_kelurahan);
            mct.startActivity(intent);
        }
    }
}
