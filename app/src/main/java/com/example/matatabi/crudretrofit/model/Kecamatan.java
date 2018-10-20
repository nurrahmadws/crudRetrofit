package com.example.matatabi.crudretrofit.model;

public class Kecamatan {
    private String nm_kabupaten;
    private String nm_kecamatan;

    public Kecamatan(String nm_kabupaten, String nm_kecamatan) {
        this.nm_kabupaten = nm_kabupaten;
        this.nm_kecamatan = nm_kecamatan;
    }

    public String getNm_kabupaten() {
        return nm_kabupaten;
    }

    public String getNm_kecamatan() {
        return nm_kecamatan;
    }
}
