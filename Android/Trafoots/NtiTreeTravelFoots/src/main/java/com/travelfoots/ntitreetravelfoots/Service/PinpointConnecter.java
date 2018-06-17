package com.travelfoots.ntitreetravelfoots.Service;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.travelfoots.ntitreetravelfoots.domain.Member;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;
import com.travelfoots.ntitreetravelfoots.network.Get;
import com.travelfoots.ntitreetravelfoots.network.Post;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Response;

public class PinpointConnecter {
    private final static String path = "http://192.168.60.56";

    public Pinpoint view(int no){
        Get get = null;
        Boolean isSucess = true;
        Response response = null;
        Pinpoint pinpoint = null;

        try {
            get = new Get(path + "/pinpoint/viewApp");
            response = get.execute().get();

            String responseBody = response.body().string();
            Gson gson = new Gson();
            LinkedTreeMap m = gson.fromJson(responseBody, LinkedTreeMap.class);
            //pinpoint = new Pinpoint(m);

        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return pinpoint;
    }

    public List<Pinpoint> list(String email){
        Get get = null;
        Response response = null;
        List<Pinpoint> pinpointList = new ArrayList<Pinpoint>();

        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("email", email);

            get = new Get(path + "/pinpoint/listApp");
            response = get.execute().get();

            String responseBody = response.body().string();
            Gson gson = new Gson();
            List list = gson.fromJson(responseBody, List.class);

            for(int i = 0; i < list.size(); i++) {
                pinpointList.add(new Pinpoint((LinkedTreeMap)list.get(i)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return pinpointList;
    }

    public boolean  remove(Pinpoint pinpoint){
        Get get = null;
        Boolean isSucess = true;
        try {
            get = new Get("/member/removeApp/" + Integer.toString(pinpoint.getNo()));
            get.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return isSucess;
    }
}

