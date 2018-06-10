package com.travelfoots.ntitreetravelfoots;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.travelfoots.ntitreetravelfoots.domain.MetaData;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import java.util.ArrayList;

public class PinpointDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinpoint_detail);
        Intent getintent = getIntent();
        ArrayList<Pinpoint> PinpointList = (ArrayList<Pinpoint>) getintent.getSerializableExtra("pinpoint");



    }


}
