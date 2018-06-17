package com.travelfoots.ntitreetravelfoots;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.drew.metadata.Metadata;
import com.travelfoots.ntitreetravelfoots.domain.GPSMetaData;
import com.travelfoots.ntitreetravelfoots.domain.MetaData;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent getintent = getIntent();
//        ArrayList<GPSMetaData> gpsMetaDataList = (ArrayList<GPSMetaData>) getintent.getSerializableExtra("gpslist");
        ArrayList<MetaData> gpsMetaDataList = (ArrayList<MetaData>) getintent.getSerializableExtra("gpslist");
        for (MetaData met : gpsMetaDataList
                ) {
            double lat = met.getFileLat();
            double lng = met.getFileLng();
            Log.i("GPSMetaList", "lat: " + lat + "Lng : " + lng);
        }

        setContentView(R.layout.activity_mypage);

        // 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //drawer_layout 툴바 등 뿌려주는 역할.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //메뉴
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //1= 회원 2=비회원
        int i = 0;
        if (i == 1) {
            navigationView.inflateHeaderView(R.layout.nav_header_main2);
        } else {
            navigationView.inflateHeaderView(R.layout.nav_header_main);
        }
    }


    //메뉴 닫기
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //메뉴 팽창
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //메뉴 선택
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Handle the camera action
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_mypage) {
        } else if (id == R.id.nav_travelrecords) {
            Intent intent = new Intent(this, TravelHistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_infomation) {
//            Intent intent=new Intent(MainActivity.this,.class);
//            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
