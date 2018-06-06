package com.travelfoots.ntitreetravelfoots.Service;

import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;
import com.travelfoots.ntitreetravelfoots.domain.TravelRecord;
import com.travelfoots.ntitreetravelfoots.network.FilePost;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TravelRecordConnecter {
    private final static String path = "http://192.168.60.61";

    private boolean add(TravelRecord travelRecord) {
        FilePost filePost = null;
        Boolean isSucess = true;

        travelRecord = new TravelRecord();

        travelRecord.setNo(1);
        travelRecord.setEmail("ee@email.com");
        travelRecord.setStartDate("2018/06/02");
        travelRecord.setEndDate("2018/06/05");

        List<Pinpoint> pinpointList = new ArrayList<Pinpoint>();

        for(int i = 0; i < 3; i++) {
            Pinpoint pinpoint = new Pinpoint();
            pinpoint.setNo(i);
        }

        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();

            builder.addFormDataPart("travelRecordNo", Integer.toString(travelRecord.getNo()))
                    .addFormDataPart("email", travelRecord.getEmail())
                    .addFormDataPart("startDate", travelRecord.getStartDate())
                    .addFormDataPart("endDate", travelRecord.getEndDate());

            int cnt = 1;
//            for(Pinpoint pinpoint : travelRecord.getPinpoints()) {
<<<<<<< Updated upstream
////                builder.addFormDataPart("pinpointNo" + cnt, Integer.toString(pinpoint.getNo()))
////                        .addFormDataPart("latitude" + cnt, Double.toString(pinpoint.getLatitude()))
////                        .addFormDataPart("longitude" + cnt, Double.toString(pinpoint.getLongitude()))
//////                        .addFormDataPart("file" + cnt++, Double.toString(pinpoint.getLongitude()),
//////                                RequestBody.create(MultipartBody.FORM, pinpoint.getFilePaths()));
////            }
=======
//                builder.addFormDataPart("pinpointNo" + cnt, Integer.toString(pinpoint.getNo()))
//                        .addFormDataPart("latitude" + cnt, Double.toString(pinpoint.getLatitude()))
//                        .addFormDataPart("longitude" + cnt, Double.toString(pinpoint.getLongitude()))
//                        .addFormDataPart("file" + cnt++, Double.toString(pinpoint.getLongitude()),
//                                RequestBody.create(MultipartBody.FORM, pinpoint.getFilePaths()));
//            }
>>>>>>> Stashed changes

            filePost = new FilePost(builder, path + "/travelRecord/addApp");
            isSucess = filePost.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        filePost.cancel(true);

        return isSucess;
    }

}
