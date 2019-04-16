package com.example.founq.gaitrecognition.mvp.model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface TestServer {

    @GET("test")
    Call<ResponseBody> getTest();

    @POST("post_gait_info")
    @Multipart
    Call<ResponseBody> postGaitInfo(@Part MultipartBody.Part file);
}
