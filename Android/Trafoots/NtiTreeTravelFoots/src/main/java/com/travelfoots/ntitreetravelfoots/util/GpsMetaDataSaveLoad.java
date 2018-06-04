package com.travelfoots.ntitreetravelfoots.util;

import android.os.Environment;
import android.util.Log;

import com.travelfoots.ntitreetravelfoots.domain.GPSMetaData;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class GpsMetaDataSaveLoad {
    public boolean save(List<GPSMetaData> gpsMetaDataList) {
        ObjectOutputStream out = null;

        Log.i("완각이는 못생김", Environment.getExternalStorageDirectory().getAbsolutePath() + "/gpsRecord.dat");

        try {
            out = new ObjectOutputStream(new FileOutputStream( Environment.getExternalStorageDirectory().getAbsolutePath() + "/gpsRecord.dat" ));
            out.writeObject(gpsMetaDataList);

            out.flush();
            out.close();

        } catch (IOException e) {
            if(out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e1) {

                }
            }

            return false;
        }

        return true;
    }

    public List<GPSMetaData> load() {
        ObjectInputStream in = null;
        List<GPSMetaData> gpsMetaDataList = null;

        try {
            in = new ObjectInputStream(new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gpsRecord"));
            gpsMetaDataList = (List<GPSMetaData>) in.readObject();

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            return null;
        }

        return gpsMetaDataList;
    }
}
