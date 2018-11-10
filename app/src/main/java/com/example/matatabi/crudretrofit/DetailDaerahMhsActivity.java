package com.example.matatabi.crudretrofit;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Mahasiswa;
import com.example.matatabi.crudretrofit.model.MahasiswaResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDaerahMhsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Mahasiswa> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_daerah_mhs);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getDataLocationFromDb();
    }

    private void getDataLocationFromDb(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Manmpilkan Lokasi...");
        progressDialog.show();

        String nm_lat = getIntent().getStringExtra("nm_lat");
        Call<MahasiswaResponse> call = RetrofitClient.getmInstance().getApi().detailDaerahMhs(nm_lat);
        call.enqueue(new Callback<MahasiswaResponse>() {
            @Override
            public void onResponse(Call<MahasiswaResponse> call, Response<MahasiswaResponse> response) {
                progressDialog.dismiss();
                mahasiswaList = response.body().getMahasiswaList();
                initLocation(mahasiswaList);

            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void initLocation(List<Mahasiswa> list){
        for (int i = 0; i < mahasiswaList.size(); i++){
            LatLng location = new LatLng(Double.parseDouble(mahasiswaList.get(i).getNm_lat()),
                    Double.parseDouble(mahasiswaList.get(i).getNm_lng()));
            mMap.addMarker(new MarkerOptions().position(location).title(mahasiswaList.get(i).getNm_kelurahan()));
            LatLng latLng = new LatLng(Double.parseDouble(mahasiswaList.get(i).getNm_lat()),
                    Double.parseDouble(mahasiswaList.get(i).getNm_lng()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude), 15.0f));
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CircleOptions circleOptions = new CircleOptions().center(new LatLng(Double.parseDouble(mahasiswaList.get(i).getNm_lat()),
                    Double.parseDouble(mahasiswaList.get(i).getNm_lng()))).radius(1090).strokeColor(Color.RED).
                    fillColor(Color.argb(128, 255, 0, 0));
            Circle circle = mMap.addCircle(circleOptions);
        }
    }
}
