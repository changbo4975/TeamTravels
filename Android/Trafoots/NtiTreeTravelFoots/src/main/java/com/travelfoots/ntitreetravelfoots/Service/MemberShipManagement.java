package com.travelfoots.ntitreetravelfoots.Service;

import android.os.AsyncTask;

import com.travelfoots.ntitreetravelfoots.domain.Member;
import com.travelfoots.ntitreetravelfoots.network.Post;

import okhttp3.FormBody;

public class MemberShipManagement {
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
            isSucess = post.execute().get();
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
            isSucess = post.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

        return isSucess;
    }
    public boolean  view(Member member){
        Post post = null;
        Boolean isSucess = true;
        try {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("email", member.getEmail());

            post = new Post(builder, "http://192.168.60.59/member/viewApp");
            isSucess = post.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.cancel(true);

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



