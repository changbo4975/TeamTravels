package com.travelfoots.ntitreetravelfoots.Service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.travelfoots.ntitreetravelfoots.domain.Member;
import com.travelfoots.ntitreetravelfoots.network.Get;
import com.travelfoots.ntitreetravelfoots.network.Post;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Response;

public class MemberConnecter {
    private final static String path = "http://192.168.60.51";

    public boolean add(Member member) {
        Post post = null;
        Boolean isSucess = true;

        Gson gson = new Gson();
        String message = gson.toJson(member);

        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("message", message);

            post = new Post(builder, path + "/member/addApp");
            post.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

        return isSucess;
    }

    public boolean edit(Member member){
        Post post = null;
        Boolean isSucess = true;

        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("email", member.getEmail())
                    .add("nickname", member.getNickname())
                    .add("age", Integer.toString(member.getAge()))
                    .add("gender", Integer.toString(member.getGender()))
                    .add("introduction", member.getSelfintroduction())
                    .add("password", member.getPassword());

            post = new Post(builder, path + "/member/addApp");
            post.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

        return isSucess;
    }

    public Member view(String email){
        Get get = null;
        Response response = null;
        Member member = null;

        try {
            get = new Get(path + "/member/viewApp?email=" + email);
            response = get.execute().get();

            String responseBody = response.body().string();
            Gson gson = new Gson();

            LinkedTreeMap m = gson.fromJson(responseBody, LinkedTreeMap.class);

            Log.d("-----", responseBody);

            member = new Member(m);

        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return member;
    }

    public List<Member> list(){
        Get get = null;
        Response response = null;
        List<Member> memberList = new ArrayList<Member>();

        try {
            get = new Get(path + "/member/listApp");
            response = get.execute().get();

            String responseBody = response.body().string();
            Gson gson = new Gson();
            List list = gson.fromJson(responseBody, List.class);

            for(int i = 0; i < list.size(); i++) {
                memberList.add(new Member((LinkedTreeMap)list.get(i)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return memberList;
    }

    public boolean  remove(String email){
        Get get = null;
        Response response = null;

        try {
            get = new Get(path + "/member/viewApp?email=" + email);
            response = get.execute().get();

            String responseBody = response.body().string();
            Gson gson = new Gson();

        } catch (Exception e) {
            e.printStackTrace();
        }

        get.cancel(true);

        return false;
    }
}



