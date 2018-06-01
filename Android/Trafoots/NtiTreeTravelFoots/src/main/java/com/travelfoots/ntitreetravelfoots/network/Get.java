package com.travelfoots.ntitreetravelfoots.network;

import android.os.AsyncTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Get extends AsyncTask {
    String url;

    public Get(String url) {
        this.url = url;
    }

    @Override
    protected Object doInBackground(Object[] objects)  {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            client.newCall(request).execute();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
