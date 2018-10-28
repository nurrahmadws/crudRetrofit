package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.AddDesaActivity;
import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.UpdateKecActivity;
import com.example.matatabi.crudretrofit.model.Kecamatan;

import java.util.List;

public class KecamatanAdapter extends RecyclerView.Adapter<KecamatanAdapter.KecamatanViewHolder> {

    private Context mctk;
    private List<Kecamatan> kecamatanList;

    public KecamatanAdapter(Context mctk, List<Kecamatan> kecamatanList) {
        this.mctk = mctk;
        this.kecamatanList = kecamatanList;
    }

    @NonNull
    @Override
    public KecamatanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mctk).inflate(R.layout.recyclerview_kecamatan, viewGroup, false);
        return new KecamatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KecamatanViewHolder kecamatanViewHolder, int i) {
        Kecamatan kecamatan = kecamatanList.get(i);
        kecamatanViewHolder.txtViewId.setText(kecamatan.getId_kecamatan());
        kecamatanViewHolder.txtViewKabupaten.setText(kecamatan.getNm_kabupaten());
        kecamatanViewHolder.txtViewKecamatan.setText(kecamatan.getNm_kecamatan());
    }

    @Override
    public int getItemCount() {
        return kecamatanList.size();
    }

    class KecamatanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtViewKabupaten, txtViewKecamatan, txtViewId;

        public KecamatanViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewId = itemView.findViewById(R.id.txtidd);
            txtViewKabupaten = itemView.findViewById(R.id.txtKabupatenn);
            txtViewKecamatan = itemView.findViewById(R.id.txtKecamatan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id_kecamatan = txtViewId.getText().toString();
            String nm_kabupaten = txtViewKabupaten.getText().toString();
            String nm_kecamatan = txtViewKecamatan.getText().toString();

            Intent intent =  new Intent(mctk, UpdateKecActivity.class);
//            Intent intent = new Intent(mctk, AddDesaActivity.class);
            intent.putExtra("id_kecamatan", id_kecamatan);
            intent.putExtra("nm_kabupaten", nm_kabupaten);
            intent.putExtra("nm_kecamatan", nm_kecamatan);
            mctk.startActivity(intent);
        }
    }
}
