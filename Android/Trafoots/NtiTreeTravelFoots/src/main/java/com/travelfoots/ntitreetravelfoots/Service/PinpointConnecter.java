package com.travelfoots.ntitreetravelfoots.Service;

import com.travelfoots.ntitreetravelfoots.domain.Member;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;
import com.travelfoots.ntitreetravelfoots.network.Get;
import com.travelfoots.ntitreetravelfoots.network.Post;

import okhttp3.FormBody;

public class PinpointConnecter {
    private final static String path = "http://192.168.60.56";

    public boolean  list(double latitude1, double latitude2, double longitude1, double longgitude2){
        Get get = null;
        Boolean isSucess = true;
        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("latitude1", Double.toString(latitude1))
                    .add("latitude2", Double.toString(latitude2))
                    .add("longitude1", Double.toString(longitude1))
                    .add("longgitude2", Double.toString(longgitude2));

            get = new Get(path + "/pinpoint/list");
            get.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return isSucess;
    }

    public boolean  view(int no){
        Get get = null;
        Boolean isSucess = true;
        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("latitude1", "");

            get = new Get(path + "/pinpoint/list");
            get.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return isSucess;
    }

    public boolean  remove(Member member){
        Post post = null;
        Boolean isSucess = true;
        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("email", member.getEmail());

            post = new Post(builder, "http://192.168.60.59/member/removeApp");
            isSucess = post.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

        return isSucess;
    }
}

