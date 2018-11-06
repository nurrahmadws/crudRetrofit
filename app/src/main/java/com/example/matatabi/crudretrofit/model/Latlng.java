package com.example.matatabi.crudretrofit.model;

public class Latlng {

    private String id_latlng;
    private String nm_kabupaten;
    private String nm_kecamatan;
    private String nm_kelurahan;
    private String nm_lat;
    private String nm_lng;
//    private String nm_lat1;
//    private String nm_lng1;
//    private String nm_lat2;
//    private String nm_lng2;
//    private String nm_lat3;
//    private String nm_lng3;
//    private String nm_lat4;
//    private String nm_lng4;
//    private String nm_lat5;
//    private String nm_lng5;
//    private String nm_lat6;
//    private String nm_lng6;


    public Latlng(String id_latlng, String nm_kabupaten, String nm_kecamatan, String nm_kelurahan, String nm_lat, String nm_lng) {
        this.id_latlng = id_latlng;
        this.nm_kabupaten = nm_kabupaten;
        this.nm_kecamatan = nm_kecamatan;
        this.nm_kelurahan = nm_kelurahan;
        this.nm_lat = nm_lat;
        this.nm_lng = nm_lng;
    }

    public String getId_latlng() {
        return id_latlng;
    }

    public String getNm_kabupaten() {
        return nm_kabupaten;
    }

    public String getNm_kecamatan() {
        return nm_kecamatan;
    }

    public String getNm_kelurahan() {
        return nm_kelurahan;
    }

    public String getNm_lat() {
        return nm_lat;
    }

    public String getNm_lng() {
        return nm_lng;
    }
}
