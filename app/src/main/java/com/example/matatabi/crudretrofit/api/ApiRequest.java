package com.example.matatabi.crudretrofit.api;

import com.example.matatabi.crudretrofit.model.KabupatenResponse;
import com.example.matatabi.crudretrofit.model.KecamatanResponse;
import com.example.matatabi.crudretrofit.model.KelurahanResponse;
import com.example.matatabi.crudretrofit.model.LatlngResponse;
import com.example.matatabi.crudretrofit.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("baru.php")
    Call<KecamatanResponse> readKecDet(@Query("id_kabupaten") String id_kabupaten);

    @FormUrlEncoded
    @POST("updatekec.php")
    Call<Value> ubahkec(
            @Field("id_kecamatan") String id_kecamatan,
            @Field("nm_kabupaten") String nm_kabupaten,
            @Field("nm_kecamatan") String nm_kecamatan);

    @FormUrlEncoded
    @POST("deleteKec.php")
    Call<Value> hapusKec(@Field("id_kecamatan") String id_kecamatan);

    @FormUrlEncoded
    @POST("insertKel.php")
    Call<Value> tambahKelurahan(@Field("nm_kabupaten") String nm_kabupaten,
                                @Field("nm_kecamatan") String nm_kecamatan,
                                @Field("nm_kelurahan") String nm_kelurahan);

    @GET("readKel.php")
    Call<KelurahanResponse> readKel();

    @FormUrlEncoded
    @POST("updateKel.php")
    Call<Value> ubahkel(@Field("id_kelurahan") String id_kelurahan,
                        @Field("nm_kabupaten") String nm_kabupaten,
                        @Field("nm_kecamatan") String nm_kecamatan,
                        @Field("nm_kelurahan") String nm_kelurahan);

    @FormUrlEncoded
    @POST("deleteKel.php")
    Call<Value> hapusKel(@Field("id_kelurahan") String id_kelurahan);

    @FormUrlEncoded
    @POST("insertlatlng.php")
    Call<Value> tambahLatlng(@Field("nm_kabupaten") String nm_kabupaten,
                             @Field("nm_kecamatan") String nm_kecamatan,
                             @Field("nm_kelurahan") String nm_kelurahan,
                             @Field("nm_lat") String nm_lat,
                             @Field("nm_lng") String nm_lng);

    @GET("readLatlng.php")
    Call<LatlngResponse> readLatlng();

    @GET("detailLatlng.php")
    Call<LatlngResponse> detailLatlng(@Query("id_latlng") String id_latlng);

    @GET("detailKel.php")
    Call<KelurahanResponse> readKelDet(@Query("id_kecamatan") String id_kecamatan);

    @FormUrlEncoded
    @POST("updateLatlng.php")
    Call<Value> ubahLatlng(@Field("id_latlng") String id_latlng,
                           @Field("nm_kabupaten") String nm_kabupaten,
                           @Field("nm_kecamatan") String nm_kecamatan,
                           @Field("nm_kelurahan") String nm_kelurahan,
                           @Field("nm_lat") String nm_lat,
                           @Field("nm_lng") String nm_lng);

    @FormUrlEncoded
    @POST("deleteLatlng.php")
    Call<Value> hapusLatlng(@Field("id_latlng") String id_latlng);
}
