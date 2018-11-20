package com.example.matatabi.crudretrofit.api;

import com.example.matatabi.crudretrofit.model.KabupatenResponse;
import com.example.matatabi.crudretrofit.model.KecamatanResponse;
import com.example.matatabi.crudretrofit.model.KelurahanResponse;
import com.example.matatabi.crudretrofit.model.LatlngResponse;
import com.example.matatabi.crudretrofit.model.LoginResponse;
import com.example.matatabi.crudretrofit.model.MahasiswaResponse;
import com.example.matatabi.crudretrofit.model.UsersResponse;
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

    @GET("spinKecamatan.php")
    Call<KecamatanResponse> spinKec(@Query("nm_kabupaten") String nm_kabupaten);

    @GET("spinKelurahan.php")
    Call<KelurahanResponse> spinKel(@Query("nm_kecamatan") String nm_kecamatan);

    @GET("spinLatlng.php")
    Call<LatlngResponse> spinLatlng(@Query("nm_kelurahan") String nm_kelurahan);

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
    @POST("insertLatlngg.php")
    Call<Value> tambahLatlng(@Field("nm_kabupaten") String nm_kabupaten,
                             @Field("nm_kecamatan") String nm_kecamatan,
                             @Field("nm_kelurahan") String nm_kelurahan,
                             @Field("nm_lat") String nm_lat,
                             @Field("nm_lng") String nm_lng);
//    @Field("nm_lat1") String nm_lat1,
//    @Field("nm_lng1") String nm_lng1,
//    @Field("nm_lat2") String nm_lat2,
//    @Field("nm_lng2") String nm_lng2,
//    @Field("nm_lat3") String nm_lat3,
//    @Field("nm_lng3") String nm_lng3,
//    @Field("nm_lat4") String nm_lat4,
//    @Field("nm_lng4") String nm_lng4,
//    @Field("nm_lat5") String nm_lat5,
//    @Field("nm_lng5") String nm_lng5,
//    @Field("nm_lat6") String nm_lat6,
//    @Field("nm_lng6") String nm_lng6

    @GET("readLatlng.php")
    Call<LatlngResponse> readLatlng();

    @GET("detailLatlngg.php")
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

    @FormUrlEncoded
    @POST("insertMahasiswa.php")
    Call<Value> insertMhs(@Field("nim") String nim,
                          @Field("nama") String nama,
                          @Field("no_hp") String no_hp,
                          @Field("jk") String jk,
                          @Field("fakultas") String fakultas,
                          @Field("prodi") String prodi,
                          @Field("angkatan") String angkatan,
                          @Field("provinsi") String provinsi,
                          @Field("nm_kabupaten") String nm_kabupaten,
                          @Field("nm_kecamatan") String nm_kecamatan,
                          @Field("nm_kelurahan") String nm_kelurahan,
                          @Field("nm_lat") String nm_lat,
                          @Field("nm_lng") String nm_lng);

    @GET("readMhs.php")
    Call<MahasiswaResponse> readMhs();

    @GET("detailMhs.php")
    Call<MahasiswaResponse> detailMhs(@Query("nim") String nim);

    @GET("detail_daerah_mhs.php")
    Call<MahasiswaResponse> detailDaerahMhs(@Query("nm_lat") String nm_lat);

    @FormUrlEncoded
    @POST("updateMhs.php")
    Call<Value> updateMhs(@Field("nim") String nim,
                          @Field("nama") String nama,
                          @Field("no_hp") String no_hp,
                          @Field("jk") String jk,
                          @Field("fakultas") String fakultas,
                          @Field("prodi") String prodi,
                          @Field("angkatan") String angkatan,
                          @Field("provinsi") String provinsi,
                          @Field("nm_kabupaten") String nm_kabupaten,
                          @Field("nm_kecamatan") String nm_kecamatan,
                          @Field("nm_kelurahan") String nm_kelurahan,
                          @Field("nm_lat") String nm_lat,
                          @Field("nm_lng") String nm_lng);

    @FormUrlEncoded
    @POST("registrasi.php")
    Call<Value> registrasi(@Field("username") String username,
                           @Field("password") String password,
                           @Field("level") String field);

    @FormUrlEncoded
    @POST("editUser.php")
    Call<Value> editUser(@Field("id_user") String id_user,
                         @Field("username") String username,
                         @Field("password") String password,
                         @Field("level") String level);

    @GET("readUsers.php")
    Call<UsersResponse> readUsers();

    @FormUrlEncoded
    @POST("deleteUser.php")
    Call<Value> hapusUser(@Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> userLogin(
                            @Field("username") String username,
                            @Field("password") String password);
}
