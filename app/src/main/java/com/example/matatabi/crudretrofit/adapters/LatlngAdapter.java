package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.model.Latlng;

import java.util.List;

public class LatlngAdapter extends RecyclerView.Adapter<LatlngAdapter.LatlngViewHolder> {

    private Context mctxt;
    private List<Latlng> latlngList;

    public LatlngAdapter(Context mctxt, List<Latlng> latlngList) {
        this.mctxt = mctxt;
        this.latlngList = latlngList;
    }

    @NonNull
    @Override
    public LatlngViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mctxt).inflate(R.layout.recyclerview_latlng, viewGroup, false);
        return new LatlngViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatlngViewHolder latlngViewHolder, int i) {
        Latlng latlng = latlngList.get(i);
        latlngViewHolder.txtidLatlng.setText(latlng.getId_latlng());
        latlngViewHolder.txtKabupateLatlng.setText(latlng.getNm_kabupaten());
        latlngViewHolder.txtKecamatanLatlng.setText(latlng.getNm_kecamatan());
        latlngViewHolder.txtKelurahanLatlng.setText(latlng.getNm_kelurahan());
        latlngViewHolder.txtLat.setText(latlng.getNm_lat());
        latlngViewHolder.txtLng.setText(latlng.getNm_lng());
    }

    @Override
    public int getItemCount() {
        return latlngList.size();
    }

    class LatlngViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtidLatlng, txtKabupateLatlng, txtKecamatanLatlng, txtKelurahanLatlng, txtLat, txtLng;

        public LatlngViewHolder(@NonNull View itemView) {
            super(itemView);
            txtidLatlng = itemView.findViewById(R.id.txtidLatlng);
            txtKabupateLatlng = itemView.findViewById(R.id.txtKabupateLatlng);
            txtKecamatanLatlng = itemView.findViewById(R.id.txtKecamatanLatlng);
            txtKelurahanLatlng = itemView.findViewById(R.id.txtKelurahanLatlng);
            txtLat = itemView.findViewById(R.id.txtLat);
            txtLng = itemView.findViewById(R.id.txtLng);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id_latlng = txtidLatlng.getText().toString();
            String nm_kabupaten = txtKabupateLatlng.getText().toString();
            String nm_kecamatan = txtKecamatanLatlng.getText().toString();
            String nm_kelurahan = txtKelurahanLatlng.getText().toString();
            String nm_lat = txtLat.getText().toString();
            String nm_lng = txtLng.getText().toString();


        }
    }

}
