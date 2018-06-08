package com.travelfoots.ntitreetravelfoots.Service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.travelfoots.ntitreetravelfoots.domain.Member;
import com.travelfoots.ntitreetravelfoots.network.Post;

import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Response;

public class MemberConnecter {
    private final static String path = "http://192.168.60.144";

    public boolean add(Member member) {
        Post post = null;
        Boolean isSucess = true;
        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("email", member.getEmail())
                    .add("nickName", member.getNickName())
                    .add("age", Integer.toString(member.getAge()))
                    .add("gender", Integer.toString(member.getGender()))
                    .add("introduction", member.getIntroduction())
                    .add("password", member.getPassword());

            post = new Post(builder, "http://192.168.60.59/member/addApp");
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
                    .add("nickName", member.getNickName())
                    .add("age", Integer.toString(member.getAge()))
                    .add("gender", Integer.toString(member.getGender()))
                    .add("introduction", member.getIntroduction())
                    .add("password", member.getPassword());

            post = new Post(builder, "http://192.168.60.59/member/editApp");
            post.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

        return isSucess;
    }
    public Response  view(String email){
        Post post = null;
        Boolean isSucess = true;
        Response response = null;

        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("email", email);

            post = new Post(builder, path + "/member/viewApp/" + email);
            response = post.execute().get();

            String responseBody = response.body().string();
            Gson gson = new Gson();
            Member m = gson.fromJson(responseBody, Member.class);
            Log.d("dkdkdkdkdjaflsfj_____--", m.getEmail());



        } catch (Exception e) {
            e.printStackTrace();
        }



        post.cancel(true);

        return response;
    }

    public boolean  remove(Member member){
        Post post = null;
        Boolean isSucess = true;
        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("email", member.getEmail());

            post = new Post(builder, "http://192.168.60.59/member/removeApp");
            post.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

        return isSucess;
    }
}



