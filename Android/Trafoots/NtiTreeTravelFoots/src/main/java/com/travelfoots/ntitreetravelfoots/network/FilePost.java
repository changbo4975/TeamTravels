package com.travelfoots.ntitreetravelfoots.network;

import android.os.AsyncTask;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FilePost extends AsyncTask<Integer, Response, Response>{
    Response response = null;
    MultipartBody.Builder builder;
    String url;

    public FilePost(MultipartBody.Builder builder, String url) {
        this.builder = builder;
        this.url = url;
    }

    @Override
    protected Response doInBackground(Integer[] list)  {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .url(url)
                .post(body)
                .build();
        try {
            response = client.newCall(request).execute();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
