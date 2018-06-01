package com.travelfoots.ntitreetravelfoots.network;

import android.os.AsyncTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Post extends AsyncTask<Boolean, Boolean, Boolean> {
    FormBody.Builder formBuilder;
    String url;

    public Post(FormBody.Builder formBuilder, String url) {
        this.formBuilder = formBuilder;
        this.url = url;
    }

    @Override
    protected Boolean doInBackground(Boolean[] objects)  {
        Boolean isSucess = true;
        OkHttpClient client = new OkHttpClient();

        RequestBody body = formBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            isSucess = client.newCall(request).execute().isSuccessful();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return isSucess;
    }
}
