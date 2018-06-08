package com.travelfoots.ntitreetravelfoots;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.travelfoots.ntitreetravelfoots.domain.MetaData;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MyPinpointActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Pinpoint_AutoGeneration pinpoint_autoGeneration = new Pinpoint_AutoGeneration();
    List<String> filepath;
    String TAG = "씨발";

    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면이 생성되는 시점
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // 화면을 보여줌
        ImageView imageView=null;

        Intent intent = new Intent(this.getIntent());
        Pinpoint pinpoint= (Pinpoint) intent.getSerializableExtra("pinpoint");
        Log.i(TAG, "onCreate: "+pinpoint.getLongitude());
        Log.i(TAG, "Path: "+pinpoint.getFilePaths());

        for(int j=0; j<pinpoint.getFilePaths().size(); j++) {
            String p = pinpoint.getFilePaths().get(0);
            Log.i(TAG, "FilePath: "+p);
            imageView.setImageURI(Uri.parse(p));
        }




/*
        File file = new File(pinpoint.getFilePaths());
        if(file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(pinpoint.getFilePaths());
            ImageView myImage = (ImageView) findView;

            myImage.setImageBitmap(myBitmap);

        }*/





        Toolbar toolbar = findViewById(R.id.toolbar); //툴바객체를 생성하고 link를 이어줌
        setSupportActionBar(toolbar); //액션바에 툴바를 생성함.


//drawer_layout 툴바 등 뿌려주는 역할.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //메뉴
        NavigationView navigationView = findViewById(R.id.nav_view_pinpoint);
        navigationView.setNavigationItemSelectedListener(this);

        //1= 회원 2=비회원
        int i = 0;
        if (i == 1) {
            navigationView.inflateHeaderView(R.layout.nav_header_main2);
        } else {
            navigationView.inflateHeaderView(R.layout.nav_header_main);
        }

        navigationView.inflateHeaderView(R.layout.nav_header_main2);
        navigationView.inflateHeaderView(R.layout.nav_header_main);


        Button button = (Button) findViewById(R.id.btn_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이전 액티비티로 돌아갑니다.", Toast.LENGTH_LONG).show();
                finish();
            }

        });

    }

    //메뉴 팽창
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //메뉴 선택
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //옵션 아이템을 선택했을 시
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); //아이템을 선택했을 시 들어오는 아이템의 id 값

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) { //id 값이 action settings일 때 실행되는 필드
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //네비게이션뷰 아이템 선택시
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {        //위와 동일.
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);  //아이템을 선택했을 시 이뤄지는 필드 후 drawer로 뿌려준 메뉴를 닫아줌.
        return true;
    }

    /*public void viewpicture(View view) {
        pinpointArrayList = new ArrayList<>();
        ArrayList<MetaData> metaDataArrayList = pinpoint_autoGeneration.MetaDataExtract(getApplicationContext());

    }*/
}


