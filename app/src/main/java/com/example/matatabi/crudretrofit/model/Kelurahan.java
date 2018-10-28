package com.example.matatabi.crudretrofit.model;

public class Kelurahan {

    private String id_kelurahan;
    private String nm_kabupaten;
    private String nm_kecamatan;
    private String nm_kelurahan;

    public Kelurahan(String id_kelurahan, String nm_kabupaten, String nm_kecamatan, String nm_kelurahan) {
        this.id_kelurahan = id_kelurahan;
        this.nm_kabupaten = nm_kabupaten;
        this.nm_kecamatan = nm_kecamatan;
        this.nm_kelurahan = nm_kelurahan;
    }

    public String getId_kelurahan() {
        return id_kelurahan;
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
}
