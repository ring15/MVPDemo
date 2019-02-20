package com.example.founq.okhttpdemo.mvp;

import android.os.SystemClock;
import android.util.JsonReader;
import android.util.Log;

import com.example.founq.okhttpdemo.Interfaces.InterCon;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Model implements InterCon.modelInter {

    private static String str = "request:\n";
    private static String strPost = "";
    @Override
    public String getPostData(final String string) {
        String url="http://devapi.sns.founq.com/main?sign=9543decbff29ed849ba0c9d34f1d1268&token=417d72c0777a7a0a52849b4ad55b7464&v=i2.9.2&vc=0&vd=1e779e00-de03-4ee1-98db-84cd04b561d2" ;
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.
        RequestBody body = RequestBody.create(JSON, jsonStr);
//        RequestBody requestBody = new FormBody.Builder()
//                .add("key",string)
//                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            strPost += response.code()+"  "+response.message()+"\n"+response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                strPost = response.body().string();
//            }
//        });
        return strPost;
    }

    @Override
    public String getGetData() {
        String url = "http://devapi.sns.founq.com/main?sign=9543decbff29ed849ba0c9d34f1d1268&token=417d72c0777a7a0a52849b4ad55b7464&v=i2.9.2&vc=0&vd=1e779e00-de03-4ee1-98db-84cd04b561d2";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String respond = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(respond);
                    String data = jsonObject.getString("data");
                    JSONObject jsonObject1 = new JSONObject((data));
                    JSONArray jsonArray = jsonObject1.getJSONArray("shumen_star");
                    for (int i =0;i<jsonArray.length();i++){
                         JSONObject jsonObject2 =jsonArray.getJSONObject(i);
                         str +=  "shumen_star_name"+i+":  "+jsonObject2.getString("name")+"\n";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


}
