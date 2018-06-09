package com.travelfoots.ntitreetravelfoots.Service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.travelfoots.ntitreetravelfoots.domain.Member;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;
import com.travelfoots.ntitreetravelfoots.domain.TravelRecord;
import com.travelfoots.ntitreetravelfoots.network.FilePost;
import com.travelfoots.ntitreetravelfoots.network.Get;
import com.travelfoots.ntitreetravelfoots.network.Post;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TravelRecordConnecter {
    private final static String path = "http://192.168.60.55";

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

        return isSucess;
    }

//    public boolean add(TravelRecord travelRecord) {
//        FilePost filePost = null;
//        Boolean isSucess = true;
//
//        //travelRecord.setPinpoints(pinpointList);
//        Gson g = new Gson();
//
//        try {
//            MultipartBody.Builder builder = new MultipartBody.Builder();
//
//            builder.addFormDataPart("travelRecordNo", Integer.toString(travelRecord.getNo()))
//                    .addFormDataPart("email", travelRecord.getEmail())
//                    .addFormDataPart("startDate", travelRecord.getStartDate())
//                    .addFormDataPart("endDate", travelRecord.getEndDate());
//
//            int cnt = 1;
//            for(Pinpoint pinpoint : travelRecord.getPinpoints()) {
//                builder.addFormDataPart("pinpointNo" + cnt, Integer.toString(pinpoint.getNo()))
//                        .addFormDataPart("latitude" + cnt, Double.toString(pinpoint.getLatitude()))
//                        .addFormDataPart("longitude" + cnt, Double.toString(pinpoint.getLongitude()));
//
//                int filCnt = 1;
//                for(String path : pinpoint.getFilePaths()) {
//                    builder.addFormDataPart(cnt + "_" + filCnt, Double.toString(pinpoint.getLongitude()),
//                            RequestBody.create(MultipartBody.FORM, new File(path)));
//                }
//                cnt++;
//            }
//
//            filePost = new FilePost(builder, path + "/travelRecord/addApp");
//            isSucess = filePost.execute().get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        filePost.cancel(true);
//
//        return isSucess;
//    }

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
