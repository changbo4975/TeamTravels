package com.travelfoots.ntitreetravelfoots.network;

import android.os.AsyncTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Post extends AsyncTask<Integer, Response, Response>{
    Response response = null;
    FormBody.Builder formBuilder;
    String url;

    public Post(FormBody.Builder formBuilder, String url) {
        this.formBuilder = formBuilder;
        this.url = url;
    }

    @Override
    protected Response doInBackground(Integer[] list)  {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = formBuilder.build();

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
