package com.travelfoots.ntitreetravelfoots;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        LocationEngineListener, OnLocationLayerClickListener, OnCameraTrackingChangedListener,MapboxMap.OnMarkerClickListener {

    MapView mapView;
    TextView modeText;
    TextView trackingText;
    Button locationModeBtn;
    Button locationTrackingBtn;
    Button createPinpointBtn;

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

    List<Pinpoint> pinpointArrayList;
    ArrayList<GPSMetaData> gpsMetaDataArrayList;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == 0) {
                Toast.makeText(this, "카메라 권한이 승인됨", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "카메라 권한이 거절됨", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        // 여행기록 서버 전송 확인
        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelRecordConnecter t = new TravelRecordConnecter();
                t.add(new TravelRecord());
            }
        });


        //fab 버튼
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {

            }

            int permissionCheck5 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck5 == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {

            }
            int permissionCheck2 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionCheck2 == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            } else {

            }

            List<GPSMetaData> list = null;
            GpsMetaDataSaveLoad gpsMetaDataSaveLoad = new GpsMetaDataSaveLoad();
            gpsMetaDataSaveLoad.save(gpsMetaDataArrayList);

            list = gpsMetaDataSaveLoad.load();


            for (GPSMetaData g : list) {
                Log.i("sia", "lat : " + Double.toString(g.getUserLat()) + " lng : " + Double.toString(g.getUserLng()) + " " + g.getUserDate());
            }
            ArrayList<MetaData> metaDataArrayList = pinpoint_autoGeneration.MetaDataExtract(getApplicationContext());
            pinpointArrayList = pinpoint_autoGeneration.CreatePinpoint(metaDataArrayList);

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
        int i=0;
        if(i==1){
            navigationView.inflateHeaderView(R.layout.nav_header_main2);
        }else {
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
        ArrayList<MetaData> metaDataArrayList = pinpoint_autoGeneration.MetaDataExtract(getApplicationContext());

        pinpointArrayList = pinpoint_autoGeneration.CreatePinpoint(metaDataArrayList);
        IconFactory iconFactory = IconFactory.getInstance(MainActivity.this);
        Icon icon = iconFactory.fromResource(R.drawable.custom_marker);


        for (Pinpoint pinpoint: pinpointArrayList) {
            double lat = pinpoint.getLatitude();
            double lng = pinpoint.getLongitude();
            Log.i("pinpoint", "pinpoint_count: " + pinpoint.getNo());

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat,lng))
                    .icon(icon));
        }
        mapboxMap.setOnMarkerClickListener(this);


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
    protected void onStop() {
        super.onStop();
        mapView.onStop();
        if (locationEngine != null) {
            locationEngine.removeLocationEngineListener(this);
            locationEngine.removeLocationUpdates();
        }
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

        gpsMetaDataArrayList.add(gpsMetaData);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this,marker.getTitle() + "\n" + marker.getPosition(),Toast.LENGTH_LONG).show();
        return true;
    }
}
