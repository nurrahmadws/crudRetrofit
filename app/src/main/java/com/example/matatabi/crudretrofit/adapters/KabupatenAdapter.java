package com.example.matatabi.crudretrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matatabi.crudretrofit.DetailActivity;
import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.UpdateActivity;
import com.example.matatabi.crudretrofit.model.Kabupaten;

import java.util.List;

public class KabupatenAdapter extends RecyclerView.Adapter<KabupatenAdapter.KabupatenViewHolder> {

    private Context mctx;
    private List<Kabupaten> kabupatenList;

    public KabupatenAdapter(Context mctx, List<Kabupaten> kabupatenList) {
        this.mctx = mctx;
        this.kabupatenList = kabupatenList;
    }

    @NonNull
    @Override
    public KabupatenViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mctx).inflate(R.layout.recyclerview_kabupaten, viewGroup, false);
        return new KabupatenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KabupatenViewHolder kabupatenViewHolder, int i) {
        Kabupaten kabupaten = kabupatenList.get(i);
        kabupatenViewHolder.txtViewId.setText(kabupaten.getId_kabupaten());
        kabupatenViewHolder.txtViewKabupaten.setText(kabupaten.getNm_kabupaten());
    }

    @Override
    public int getItemCount() {
        return kabupatenList.size();
    }

    class KabupatenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtViewKabupaten, txtViewId;

        public KabupatenViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewId = itemView.findViewById(R.id.txtid);
            txtViewKabupaten = itemView.findViewById(R.id.txtKabupaten);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id_kabupaten = txtViewId.getText().toString();
            String nm_kabupaten = txtViewKabupaten.getText().toString();

            Intent intent = new Intent(mctx, DetailActivity.class);
//            Intent intent = new Intent(mctx, UpdateActivity.class);
            intent.putExtra("id_kabupaten", id_kabupaten);
            intent.putExtra("nm_kabupaten", nm_kabupaten);
            mctx.startActivity(intent);
        }
    }

}
