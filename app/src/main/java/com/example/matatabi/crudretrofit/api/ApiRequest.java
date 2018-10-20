package com.example.matatabi.crudretrofit.api;

import com.example.matatabi.crudretrofit.model.KabupatenResponse;
import com.example.matatabi.crudretrofit.model.KecamatanResponse;
import com.example.matatabi.crudretrofit.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> tambahKabupaten(@Field("nm_kabupaten") String nm_kabupaten);

    @GET("read.php")
    Call<KabupatenResponse> read();

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> ubah(
            @Field("id_kabupaten") String id_kabupaten,
            @Field("nm_kabupaten") String nm_kabupaten);

    @FormUrlEncoded
    @POST("delete.php")
    Call<Value> hapus(@Field("id_kabupaten") String id_kabupaten);

    @FormUrlEncoded
    @POST("search.php")
    Call<KabupatenResponse> search(@Field("search") String search);

    @GET("spinnerKabupaten.php")
    Call<KabupatenResponse> spinKab();

    @FormUrlEncoded
    @POST("insertKec.php")
    Call<Value> tambahKecamatan(@Field("nm_kabupaten") String nm_kabupaten,
                                @Field("nm_kecamatan") String nm_kecamatan);

    @GET("readKec.php")
    Call<KecamatanResponse> readKec();
}
