package com.travelfoots.ntitreetravelfoots.Service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.travelfoots.ntitreetravelfoots.domain.Member;
import com.travelfoots.ntitreetravelfoots.domain.Photo;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;
import com.travelfoots.ntitreetravelfoots.domain.TravelRecord;
import com.travelfoots.ntitreetravelfoots.network.FilePost;
import com.travelfoots.ntitreetravelfoots.network.Get;
import com.travelfoots.ntitreetravelfoots.network.Post;
import com.travelfoots.ntitreetravelfoots.network.PostTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TravelRecordConnecter {
    private final static String path = "http://192.168.60.53";

    public boolean add(TravelRecord travelRecord) {
        Post post = null;
        Boolean isSucess = true;

        Gson gson = new Gson();
        String message = gson.toJson(travelRecord);

        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("message", message);

            post = new Post(builder, path + "/travelRecord/addApp");
            post.execute().get();
            post.cancel(true);

        } catch (Exception e) {
            post.cancel(true);
            e.printStackTrace();
        }



        return isSucess;
    }

    public boolean adda(TravelRecord travelRecord) {
        FilePost post = null;
        Boolean isSucess = true;

        Gson gson = new Gson();
        String message = gson.toJson(travelRecord);

        try {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .addFormDataPart("message", message);

            for(int i = 0; i < travelRecord.getPinpointList().size(); i++) {
                Pinpoint pinpoint = travelRecord.getPinpointList().get(i);

                for(int j = 0; j < pinpoint.getPhotoList().size(); j++) {
                    Photo photo = pinpoint.getPhotoList().get(j);
                    String fileName = travelRecord.getEmail() + '_' + i + '_' + j;
                    builder.addFormDataPart(fileName, fileName,
                            RequestBody.create(MultipartBody.FORM, new File(photo.getFilepath())));


                }
            }

            post = new FilePost(builder, path + "/travelRecord/addAppa");
            post.execute().get();

            post.cancel(true);

        } catch (Exception e) {
            post.cancel(true);
            e.printStackTrace();
        }



        return isSucess;
    }

    public Member view(int no){
        Get get = null;
        Response response = null;
        Member member = null;

        try {
            get = new Get(path + "/travelRecord/viewApp?email=" + Integer.toString(no));
            response = get.execute().get();

            String responseBody = response.body().string();
            Gson gson = new Gson();

            LinkedTreeMap m = gson.fromJson(responseBody, LinkedTreeMap.class);
            TravelRecord travelRecord = new TravelRecord(m);

        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return member;
    }
}
