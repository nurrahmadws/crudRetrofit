package com.example.matatabi.crudretrofit;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.matatabi.crudretrofit.api.RetrofitClient;
import com.example.matatabi.crudretrofit.model.Latlng;
import com.example.matatabi.crudretrofit.model.LatlngResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLatlngActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Latlng> latlngList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_latlng);
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

        // Add a marker in Sydney and move the camera
        getDataLocationFromDb();
    }

    private void getDataLocationFromDb(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Menampilkan Lokasi...");
        dialog.show();

        String id_latlng = getIntent().getStringExtra("id_latlng");
        Call<LatlngResponse> call = RetrofitClient.getmInstance().getApi().detailLatlng(id_latlng);
        call.enqueue(new Callback<LatlngResponse>() {
            @Override
            public void onResponse(Call<LatlngResponse> call, Response<LatlngResponse> response) {
                dialog.dismiss();
                latlngList = response.body().getLatlngList();
                initLocation(latlngList);
            }

            @Override
            public void onFailure(Call<LatlngResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(DetailLatlngActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initLocation(List<Latlng> list){
        for (int i=0; i<latlngList.size(); i++){
            LatLng location = new LatLng(Double.parseDouble(latlngList.get(i).getNm_lat()),
                    Double.parseDouble(latlngList.get(i).getNm_lng()));
            mMap.addMarker(new MarkerOptions().position(location).title(latlngList.get(i).getNm_kelurahan()));
            LatLng latLng = new LatLng(Double.parseDouble(latlngList.get(i).getNm_lat()),
                    Double.parseDouble(latlngList.get(i).getNm_lng()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude), 11.0f));
        }
    }
}
