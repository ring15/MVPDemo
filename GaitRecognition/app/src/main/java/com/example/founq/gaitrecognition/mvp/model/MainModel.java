package com.example.founq.gaitrecognition.mvp.model;

import android.util.Log;

import com.example.founq.gaitrecognition.base.BaseRequst;
import com.example.founq.gaitrecognition.base.Listener;
import com.example.founq.gaitrecognition.mvp.Contract;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements Contract.Model {


    @Override
    public void postGait(File file, final Listener listener) {
//        TestServer server = BaseRequst.getInstance(TestServer.class);
//        Call<ResponseBody> call = server.getTest();
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                String result = null;
//                try {
//                    result = response.body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                listener.Success(result);
//                Log.e("result", result);
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                listener.Failed(t.getMessage());
//            }
//        });
        TestServer server = BaseRequst.getInstance(TestServer.class);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        String descriptionString = "This is a description";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        Call<ResponseBody> call = server.postGaitInfo(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listener.Success(result);
                Log.e("result", result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                listener.Failed(t.getMessage());
            }
        });
    }
}
