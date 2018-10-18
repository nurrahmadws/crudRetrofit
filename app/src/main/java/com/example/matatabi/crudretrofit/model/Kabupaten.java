package com.example.matatabi.crudretrofit.model;

public class Kabupaten {
    private String id_kabupaten;
    private String nm_kabupaten;

    public Kabupaten(String id_kabupaten, String nm_kabupaten) {
        this.id_kabupaten = id_kabupaten;
        this.nm_kabupaten = nm_kabupaten;
    }

    public String getId_kabupaten() {
        return id_kabupaten;
    }

    public String getNm_kabupaten() {
        return nm_kabupaten;
    }
}
