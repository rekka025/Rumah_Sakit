package com.reka.rumahsakit.retrofit;

import com.reka.rumahsakit.model.Pasien;
import com.reka.rumahsakit.model.ResponServer;
import com.reka.rumahsakit.model.ValueDokter;
import com.reka.rumahsakit.model.ValueUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by reka on 3/19/18.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("Login.php")
    Call<Pasien> login(@Field("username") String username,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("Register.php")
    Call<Pasien> register(@Field("username") String username,
                         @Field("password") String password,
                         @Field("nama")String nama,
                         @Field("alamat")String alamat,
                         @Field("nohp") String nohp,
                         @Field("noktp") String noktp,
                         @Field("email") String email
                         );
    @GET("ReadDokter.php")
    Call<ValueDokter> view();

    @GET("ReadUser.php")
    //mengGet data dengan yang di jadikan patokan adalah username
    Call<ValueUser> ambil(@Query("username") String username);

    @FormUrlEncoded
    @POST("Update.php")
    Call<ResponServer> editData(@Field("username") String username,
                                @Field("nama")String nama,
                                @Field("noktp") String noktp,
                                @Field("alamat")String alamat,
                                @Field("nohp") String nohp,
                                @Field("password") String password,
                                @Field("email") String email );


}
