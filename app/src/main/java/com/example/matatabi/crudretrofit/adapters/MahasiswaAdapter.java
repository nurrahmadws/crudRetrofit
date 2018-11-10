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

import com.example.matatabi.crudretrofit.DetailMhsActivity;
import com.example.matatabi.crudretrofit.EditMahasiswaActivity;
import com.example.matatabi.crudretrofit.R;
import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Mahasiswa;
import com.example.matatabi.crudretrofit.model.MahasiswaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Mahasiswa mahasiswa = mahasiswaList.get(i);
        mahasiswaViewHolder.txtNim.setText(mahasiswa.getNim());
        mahasiswaViewHolder.txtNama.setText(mahasiswa.getNama());
        mahasiswaViewHolder.txtno_hp.setText(mahasiswa.getNo_hp());
        mahasiswaViewHolder.txtJk.setText(mahasiswa.getJk());
        mahasiswaViewHolder.txtFakultas.setText(mahasiswa.getFakultas());
        mahasiswaViewHolder.txtProdi.setText(mahasiswa.getProdi());
        mahasiswaViewHolder.txtAngkatan.setText(mahasiswa.getAngkatan());
        mahasiswaViewHolder.txtProvinsi.setText(mahasiswa.getProvinsi());
        mahasiswaViewHolder.txtKabupaten.setText(mahasiswa.getNm_kabupaten());
        mahasiswaViewHolder.txtKecamatan.setText(mahasiswa.getNm_kecamatan());
        mahasiswaViewHolder.txtKelurahan.setText(mahasiswa.getNm_kelurahan());
        mahasiswaViewHolder.txtLat.setText(mahasiswa.getNm_lat());
        mahasiswaViewHolder.txtLNg.setText(mahasiswa.getNm_lng());
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtNim, txtNama, txtno_hp, txtJk, txtFakultas, txtProdi, txtAngkatan, txtProvinsi, txtKabupaten, txtKecamatan, txtKelurahan, txtLat, txtLNg;
        Button btnEditMhs;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNim = itemView.findViewById(R.id.txtNim);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtno_hp = itemView.findViewById(R.id.txtnoHpp);
            txtJk = itemView.findViewById(R.id.txtjkk);
            txtFakultas = itemView.findViewById(R.id.txtFakultass);
            txtProdi = itemView.findViewById(R.id.txtProdii);
            txtAngkatan = itemView.findViewById(R.id.txtAngkatann);
            txtProvinsi = itemView.findViewById(R.id.txtProvinsii);
            txtKabupaten = itemView.findViewById(R.id.txtKabupatennnn);
            txtKecamatan = itemView.findViewById(R.id.txtKecamatanna);
            txtKelurahan = itemView.findViewById(R.id.txtKelurahana);
            txtLat = itemView.findViewById(R.id.txtLatt);
            txtLNg = itemView.findViewById(R.id.txtLngg);
            itemView.setOnClickListener(this);

            btnEditMhs = itemView.findViewById(R.id.btnEditMhs);
            btnEditMhs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nim = txtNim.getText().toString();
                    String nama = txtNama.getText().toString();
                    String no_hp = txtno_hp.getText().toString();
                    String jk = txtJk.getText().toString();
                    String fakultas = txtFakultas.getText().toString();
                    String prodi = txtProdi.getText().toString();
                    String angkatan = txtAngkatan.getText().toString();
                    String provinsi = txtProvinsi.getText().toString();
                    String nm_kabupaten = txtKabupaten.getText().toString();
                    String nm_kecamatan = txtKecamatan.getText().toString();
                    String nm_kelurahan = txtKelurahan.getText().toString();
                    String nm_lat = txtLat.getText().toString();
                    String nm_lng = txtLNg.getText().toString();

                    Intent intent = new Intent(mctt, EditMahasiswaActivity.class);
                    intent.putExtra("nim", nim);
                    intent.putExtra("nama", nama);
                    intent.putExtra("no_hp", no_hp);
                    intent.putExtra("jk", jk);
                    intent.putExtra("fakultas", fakultas);
                    intent.putExtra("prodi", prodi);
                    intent.putExtra("angkatan", angkatan);
                    intent.putExtra("provinsi", provinsi);
                    intent.putExtra("nm_kabupaten", nm_kabupaten);
                    intent.putExtra("nm_kecamatan", nm_kecamatan);
                    intent.putExtra("nm_kelurahan", nm_kelurahan);
                    intent.putExtra("nm_lat", nm_lat);
                    intent.putExtra("nm_lng", nm_lng);
                    mctt.startActivity(intent);
                }
            });
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
