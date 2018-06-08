package com.travelfoots.ntitreetravelfoots;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerOptions;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.OnCameraTrackingChangedListener;
import com.mapbox.mapboxsdk.plugins.locationlayer.OnLocationLayerClickListener;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;
import com.travelfoots.ntitreetravelfoots.Service.TravelRecordConnecter;
import com.travelfoots.ntitreetravelfoots.domain.GPSMetaData;
import com.travelfoots.ntitreetravelfoots.domain.MetaData;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;
import com.travelfoots.ntitreetravelfoots.domain.TravelRecord;
import com.travelfoots.ntitreetravelfoots.util.GpsMetaDataSaveLoad;
import com.travelfoots.ntitreetravelfoots.util.SaveLoad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        LocationEngineListener, OnLocationLayerClickListener, OnCameraTrackingChangedListener, MapboxMap.OnMarkerClickListener {

    int M = 23;//minsdkversion
    MapView mapView;
    TextView modeText;
    TextView trackingText;
    Button locationModeBtn;
    Button locationTrackingBtn;
    Button createPinpointBtn;
    Button startEndBtn;
    boolean check_records = false;
    private int file_count=0;
    //그리기
    List<LatLng> latLngs = new ArrayList<>();

    private LocationLayerPlugin locationLayerPlugin;
    private LocationEngine locationEngine;
    private MapboxMap mapboxMap;
    private boolean customStyle;

    private static final String SAVED_STATE_CAMERA = "saved_state_camera";
    private static final String SAVED_STATE_RENDER = "saved_state_render";

    @CameraMode.Mode
    private int cameraMode = CameraMode.NONE;

    @RenderMode.Mode
    private int renderMode = RenderMode.NORMAL;


    Pinpoint_AutoGeneration pinpoint_autoGeneration = new Pinpoint_AutoGeneration();
    ArrayList<MetaData> metaDataArrayList = new ArrayList<>();
    List<Pinpoint> pinpointArrayList;
    ArrayList<GPSMetaData> gpsMetaDataArrayList;

    GpsMetaDataSaveLoad gpsMetaDataSaveLoad = new GpsMetaDataSaveLoad();
    SaveLoad saveLoad = new SaveLoad();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); super.onCreate(savedInstanceState);

        checkPermissionF();
        gpsMetaDataArrayList = new ArrayList<>();
        pinpointArrayList = new ArrayList<>();
        setContentView(R.layout.activity_main);
        // 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mapView = findViewById(R.id.map_view);
        modeText = findViewById(R.id.tv_mode);
        trackingText = findViewById(R.id.tv_tracking);
        locationModeBtn = findViewById(R.id.button_location_mode);
        locationModeBtn.setOnClickListener(v -> locationMode(locationModeBtn));
        locationTrackingBtn = findViewById(R.id.button_location_tracking);
        locationTrackingBtn.setOnClickListener(v -> locationModeCompass(locationTrackingBtn));
        createPinpointBtn = findViewById(R.id.button);
        createPinpointBtn.setOnClickListener(v -> CreatePinpoint(createPinpointBtn));


        startEndBtn = findViewById(R.id.startEndBut);

//        LottieAnimationView animationView = findViewById(R.id.startEndBut);
//        animationView.setAnimation("menu.json");
//        animationView.setOnClickListener(v -> animationView.playAnimation());
        startEndBtn.setOnClickListener(view -> TravelRecordStartEnd(startEndBtn));
        // 여행기록 서버 전송 확인
        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(view -> {
            TravelRecordConnecter t = new TravelRecordConnecter();
            t.add(new TravelRecord());
        });


        //drawer_layout 툴바 등 뿌려주는 역할.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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


        //지도
        Mapbox.getInstance(this, getString(R.string.access_token));
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        if (savedInstanceState != null) {
            cameraMode = savedInstanceState.getInt(SAVED_STATE_CAMERA);
            renderMode = savedInstanceState.getInt(SAVED_STATE_RENDER);
        }

    }


    public void CreatePinpoint(View view) {
        pinpointArrayList = new ArrayList<>();
        metaDataArrayList = pinpoint_autoGeneration.MetaDataExtract(getApplicationContext());

        pinpointArrayList = pinpoint_autoGeneration.CreatePinpoint(metaDataArrayList);
        IconFactory iconFactory = IconFactory.getInstance(MainActivity.this);
        Icon icon = iconFactory.fromResource(R.drawable.custom_marker);


        for (Pinpoint pinpoint : pinpointArrayList) {
            double lat = pinpoint.getLatitude();
            double lng = pinpoint.getLongitude();
            Log.i("pinpoint", "pinpoint_count: " + pinpoint.getNo());

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(icon));
        }
        mapboxMap.setOnMarkerClickListener(this);


    }

    //시작 정지버튼
    void TravelRecordStartEnd(View view) {
        if (check_records) {
            startEndBtn.setText("▶");
            saveLoad.save(gpsMetaDataArrayList,"gpsdata/","gpsData"+file_count+".dat");
            check_records = false;
            for (GPSMetaData m : gpsMetaDataArrayList
                    ) {
                Log.i("gps data", "lng : " + m.getUserLng() + " lat:  " + m.getUserLat()+m.getUserDate());

            }
        } else {
            startEndBtn.setText("■");
            gpsMetaDataArrayList.clear();
            check_records = true;
        }
    }


    //TODO 지도 관련

    @SuppressWarnings({"MissingPermission"})
    public void locationMode(View view) {
        if (locationLayerPlugin == null) {
            return;
        }
        showModeListDialog();
    }

    public void locationModeCompass(View view) {
        if (locationLayerPlugin == null) {
            return;
        }
        showTrackingListDialog();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;

        locationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        locationEngine.setFastestInterval(1000);
        locationEngine.addLocationEngineListener(this);
        locationEngine.activate();


        int[] padding;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            padding = new int[]{0, 750, 0, 0};
        } else {
            padding = new int[]{0, 250, 0, 0};
        }
        LocationLayerOptions options = LocationLayerOptions.builder(this)
                .padding(padding)
                .build();
        locationLayerPlugin = new LocationLayerPlugin(mapView, mapboxMap, locationEngine, options);
        locationLayerPlugin.addOnLocationClickListener(this);
        locationLayerPlugin.addOnCameraTrackingChangedListener(this);
        locationLayerPlugin.setCameraMode(cameraMode);
        setRendererMode(renderMode);

        getLifecycle().addObserver(locationLayerPlugin);
    }


    @SuppressLint("MissingPermission")


    @VisibleForTesting
    public void toggleMapStyle() {
        String styleUrl = mapboxMap.getStyleUrl().contentEquals(Style.DARK) ? Style.LIGHT : Style.DARK;
        mapboxMap.setStyle(styleUrl);
    }

    @VisibleForTesting
    public LocationLayerPlugin getLocationLayerPlugin() {
        return locationLayerPlugin;
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        if (locationEngine != null) {
            locationEngine.requestLocationUpdates();
            locationEngine.addLocationEngineListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);

        outState.putInt(SAVED_STATE_CAMERA, cameraMode);
        outState.putInt(SAVED_STATE_RENDER, renderMode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (locationEngine != null) {
            locationEngine.deactivate();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onConnected() {
        locationEngine.requestLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        //mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
        //      new LatLng(location.getLatitude(), location.getLongitude()), 16));
        double lat;
        double lng;
        lat = location.getLatitude();
        lng = location.getLongitude();


        GPSMetaData gpsMetaData = new GPSMetaData();


        Date date = new Date();
        gpsMetaData.setUserDate(date);
        gpsMetaData.setUserLat(lat);
        gpsMetaData.setUserLng(lng);
        LatLng latLng = new LatLng(lat, lng);

        latLngs.add(latLng);
        gpsMetaDataArrayList.add(gpsMetaData);
        mapboxMap.addPolyline(new PolylineOptions()
                .addAll(latLngs)
                .color(Color.parseColor("#3bb2d0"))
                .width(2));

    }

    @Override
    public void onLocationLayerClick() {
        Toast.makeText(this, "OnLocationLayerClick", Toast.LENGTH_LONG).show();
    }

    private void showModeListDialog() {
        List<String> modes = new ArrayList<>();
        modes.add("Normal");
        modes.add("Compass");
        modes.add("GPS");
        ArrayAdapter<String> profileAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, modes);
        ListPopupWindow listPopup = new ListPopupWindow(this);
        listPopup.setAdapter(profileAdapter);
        listPopup.setAnchorView(locationModeBtn);
        listPopup.setOnItemClickListener((parent, itemView, position, id) -> {
            String selectedMode = modes.get(position);
            locationModeBtn.setText(selectedMode);
            if (selectedMode.contentEquals("Normal")) {
                setRendererMode(RenderMode.NORMAL);
            } else if (selectedMode.contentEquals("Compass")) {
                setRendererMode(RenderMode.COMPASS);
            } else if (selectedMode.contentEquals("GPS")) {
                setRendererMode(RenderMode.GPS);
            }
            listPopup.dismiss();
        });
        listPopup.show();
    }

    private void setRendererMode(@RenderMode.Mode int mode) {
        renderMode = mode;
        locationLayerPlugin.setRenderMode(mode);
        if (mode == RenderMode.NORMAL) {
            locationModeBtn.setText("Normal");
        } else if (mode == RenderMode.COMPASS) {
            locationModeBtn.setText("Compass");
        } else if (mode == RenderMode.GPS) {
            locationModeBtn.setText("Gps");
        }
    }

    private void showTrackingListDialog() {
        List<String> trackingTypes = new ArrayList<>();
        trackingTypes.add("None");
        trackingTypes.add("Tracking");
        trackingTypes.add("Tracking Compass");
        trackingTypes.add("Tracking GPS");
        trackingTypes.add("Tracking GPS North");
        ArrayAdapter<String> profileAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, trackingTypes);
        ListPopupWindow listPopup = new ListPopupWindow(this);
        listPopup.setAdapter(profileAdapter);
        listPopup.setAnchorView(locationTrackingBtn);
        listPopup.setOnItemClickListener((parent, itemView, position, id) -> {
            String selectedTrackingType = trackingTypes.get(position);
            locationTrackingBtn.setText(selectedTrackingType);
            if (selectedTrackingType.contentEquals("None")) {
                locationLayerPlugin.setCameraMode(CameraMode.NONE);
            } else if (selectedTrackingType.contentEquals("Tracking")) {
                locationLayerPlugin.setCameraMode(CameraMode.TRACKING);
            } else if (selectedTrackingType.contentEquals("Tracking Compass")) {
                locationLayerPlugin.setCameraMode(CameraMode.TRACKING_COMPASS);
            } else if (selectedTrackingType.contentEquals("Tracking GPS")) {
                locationLayerPlugin.setCameraMode(CameraMode.TRACKING_GPS);
            } else if (selectedTrackingType.contentEquals("Tracking GPS North")) {
                locationLayerPlugin.setCameraMode(CameraMode.TRACKING_GPS_NORTH);
            }
            listPopup.dismiss();
        });
        listPopup.show();
    }

    @Override
    public void onCameraTrackingDismissed() {
        locationTrackingBtn.setText("None");
    }

    @Override
    public void onCameraTrackingChanged(int currentMode) {
        this.cameraMode = currentMode;

        if (cameraMode == CameraMode.NONE) {
            locationTrackingBtn.setText("None");
        } else if (cameraMode == CameraMode.TRACKING) {
            locationTrackingBtn.setText("Tracking");
        } else if (cameraMode == CameraMode.TRACKING_COMPASS) {
            locationTrackingBtn.setText("Tracking Compass");
        } else if (cameraMode == CameraMode.TRACKING_GPS) {
            locationTrackingBtn.setText("Tracking GPS");
        } else if (cameraMode == CameraMode.TRACKING_GPS_NORTH) {
            locationTrackingBtn.setText("Tracking GPS North");
        }
    }


    //메뉴 열기
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
        } else if (id == R.id.nav_mypage) {
            Intent intent=new Intent(MainActivity.this,MypageActivity.class);
            intent.putExtra("gpslist",(Serializable)metaDataArrayList);
            startActivity(intent);
        } else if (id == R.id.nav_travelrecords) {
            Intent intent=new Intent(MainActivity.this,TravelHistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            Intent intent=new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_infomation) {
//            Intent intent=new Intent(MainActivity.this,.class);
//            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this, marker.getTitle() + "\n" + marker.getPosition(), Toast.LENGTH_LONG).show();
        return true;
    }


    private void checkPermissionF() {

        if (android.os.Build.VERSION.SDK_INT >= M) {
            // only for LOLLIPOP and newer versions
            System.out.println("Hello Marshmallow (마시멜로우)");
            int permissionResult = getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permissionResult == PackageManager.PERMISSION_DENIED) {
                //요청한 권한( WRITE_EXTERNAL_STORAGE )이 없을 때..거부일때...
                /* 사용자가 WRITE_EXTERNAL_STORAGE 권한을 한번이라도 거부한 적이 있는 지 조사한다.
                 * 거부한 이력이 한번이라도 있다면, true를 리턴한다.
                 */
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


                    AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
                    dialog.setTitle("권한이 필요합니다.")
                            .setMessage("단말기의 파일쓰기 권한이 필요합니다.\\n계속하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if (Build.VERSION.SDK_INT >= M) {

                                        System.out.println("감사합니다. 권한을 허락했네요");

                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, 1);
                                    }

                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "권한 요청 취소", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create()
                            .show();

                    //최초로 권한을 요청할 때.
                } else {
                    System.out.println("최초로 권한을 요청할 때. (마시멜로우)");
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, 1);
                    //        getThumbInfo();
                }
            } else {
                //권한이 있을 때.
                //       getThumbInfo();
            }

        } else {
            System.out.println("(마시멜로우 이하 버전입니다.)");
            //   getThumbInfo();
        }

    }

    /**
     * 사용자가 권한을 허용했는지 거부했는지 체크
     *
     * @param requestCode  1번
     * @param permissions  개발자가 요청한 권한들
     * @param grantResults 권한에 대한 응답들
     *                     permissions와 grantResults는 인덱스 별로 매칭된다.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            /* 요청한 권한을 사용자가 "허용"했다면 인텐트를 띄워라
                내가 요청한 게 하나밖에 없기 때문에. 원래 같으면 for문을 돈다.*/
/*            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);*/
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("onRequestPermissionsResult WRITE_EXTERNAL_STORAGE ( 권한 성공 ) ");
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("onRequestPermissionsResult ACCESS_FINE_LOCATION ( 권한 성공 ) ");
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("onRequestPermissionsResult ACCESS_COARSE_LOCATION ( 권한 성공 ) ");
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("onRequestPermissionsResult INTERNET ( 권한 성공 ) ");
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("onRequestPermissionsResult READ_EXTERNAL_STORAGE ( 권한 성공 ) ");
                    }
                }


            }

        } else {
            System.out.println("onRequestPermissionsResult ( 권한 거부) ");
            Toast.makeText(getApplicationContext(), "요청 권한 거부", Toast.LENGTH_SHORT).show();
        }

    }
}
