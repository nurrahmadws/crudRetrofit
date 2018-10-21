package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.R;
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
        kecamatanViewHolder.txtViewKabupaten.setText(kecamatan.getNm_kabupaten());
        kecamatanViewHolder.txtViewKecamatan.setText(kecamatan.getNm_kecamatan());
    }

    @Override
    public int getItemCount() {
        return kecamatanList.size();
    }

    class KecamatanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtViewKabupaten, txtViewKecamatan;

        public KecamatanViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewKabupaten = itemView.findViewById(R.id.txtKabupatenn);
            txtViewKecamatan = itemView.findViewById(R.id.txtKecamatan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String kabupaten_id = txtViewKabupaten.getText().toString();
            String nm_kecamatan = txtViewKecamatan.getText().toString();
        }
    }
}
