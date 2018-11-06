package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.DetailLatlngActivity;
import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.UpdateLatLngActivity;
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
//        latlngViewHolder.txtLat1.setText(latlng.getNm_lat1());
//        latlngViewHolder.txtLng1.setText(latlng.getNm_lng1());
//        latlngViewHolder.txtLat2.setText(latlng.getNm_lat2());
//        latlngViewHolder.txtLng2.setText(latlng.getNm_lng2());
//        latlngViewHolder.txtLat3.setText(latlng.getNm_lat3());
//        latlngViewHolder.txtLng3.setText(latlng.getNm_lng3());
//        latlngViewHolder.txtLat4.setText(latlng.getNm_lat4());
//        latlngViewHolder.txtLng4.setText(latlng.getNm_lng4());
//        latlngViewHolder.txtLat5.setText(latlng.getNm_lat5());
//        latlngViewHolder.txtLng5.setText(latlng.getNm_lng5());
    }

    @Override
    public int getItemCount() {
        return latlngList.size();
    }

    class LatlngViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtidLatlng, txtKabupateLatlng, txtKecamatanLatlng, txtKelurahanLatlng, txtLat, txtLng, txtLat1, txtLng1, txtLat2, txtLng2, txtLat3, txtLng3, txtLat4, txtLng4, txtLat5, txtLng5;

        public LatlngViewHolder(@NonNull View itemView) {
            super(itemView);
            txtidLatlng = itemView.findViewById(R.id.txtidLatlng);
            txtKabupateLatlng = itemView.findViewById(R.id.txtKabupateLatlng);
            txtKecamatanLatlng = itemView.findViewById(R.id.txtKecamatanLatlng);
            txtKelurahanLatlng = itemView.findViewById(R.id.txtKelurahanLatlng);
            txtLat = itemView.findViewById(R.id.txtLat);
            txtLng = itemView.findViewById(R.id.txtLng);
//            txtLat1 = itemView.findViewById(R.id.txtLat1);
//            txtLng1 = itemView.findViewById(R.id.txtLng1);
//            txtLat2 = itemView.findViewById(R.id.txtLat2);
//            txtLng2 = itemView.findViewById(R.id.txtLng2);
//            txtLat3 = itemView.findViewById(R.id.txtLat3);
//            txtLng3 = itemView.findViewById(R.id.txtLng3);
//            txtLat4 = itemView.findViewById(R.id.txtLat4);
//            txtLng4 = itemView.findViewById(R.id.txtLng4);
//            txtLat5 = itemView.findViewById(R.id.txtLat5);
//            txtLng5 = itemView.findViewById(R.id.txtLng5);
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
//            String nm_lat1 = txtLat1.getText().toString();
//            String nm_lng1 = txtLng1.getText().toString();
//            String nm_lat2 = txtLat2.getText().toString();
//            String nm_lng2 = txtLng2.getText().toString();
//            String nm_lat3 = txtLat3.getText().toString();
//            String nm_lng3 = txtLng3.getText().toString();
//            String nm_lat4 = txtLat4.getText().toString();
//            String nm_lng4 = txtLng4.getText().toString();
//            String nm_lat5 = txtLat5.getText().toString();
//            String nm_lng5 = txtLng5.getText().toString();

//            Intent intent = new Intent(mctxt, UpdateLatLngActivity.class);
            Intent intent = new Intent(mctxt, DetailLatlngActivity.class);
            intent.putExtra("id_latlng", id_latlng);
            intent.putExtra("nm_kabupaten", nm_kabupaten);
            intent.putExtra("nm_kecamatan", nm_kecamatan);
            intent.putExtra("nm_kelurahan", nm_kelurahan);
            intent.putExtra("nm_lat", nm_lat);
            intent.putExtra("nm_lng", nm_lng);
//            intent.putExtra("nm_lat1", nm_lat1);
//            intent.putExtra("nm_lng1", nm_lng1);
//            intent.putExtra("nm_lat2", nm_lat2);
//            intent.putExtra("nm_lng2", nm_lng2);
//            intent.putExtra("nm_lat3", nm_lat3);
//            intent.putExtra("nm_lng3", nm_lng3);
//            intent.putExtra("nm_lat4", nm_lat4);
//            intent.putExtra("nm_lng4", nm_lng4);
//            intent.putExtra("nm_lat5", nm_lat5);
//            intent.putExtra("nm_lng5", nm_lng5);
            mctxt.startActivity(intent);


        }
    }

}
