package com.travelfoots.ntitreetravelfoots.network;

import android.os.AsyncTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Get extends AsyncTask<Integer, Response, Response> {
    String url;

    public Get(String url) {
        this.url = url;
    }

    @Override
    protected Response doInBackground(Integer[] objects)  {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            response = client.newCall(request).execute();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
