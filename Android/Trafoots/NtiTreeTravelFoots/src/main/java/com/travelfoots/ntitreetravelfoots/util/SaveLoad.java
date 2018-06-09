package com.travelfoots.ntitreetravelfoots.util;

import android.os.Environment;
import android.util.Log;

import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SaveLoad implements Serializable {

    public boolean save(Object obj, String path, String fileName){

        ObjectOutputStream out = null;

        try {
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            sdPath += "/travelFoot/";
            File file = new File(sdPath);
            file.mkdirs();

            sdPath += path;
            file = new File(sdPath);
            file.mkdirs();


            sdPath += fileName;

            file = new File(sdPath);
            file.createNewFile();

            out = new ObjectOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/travelFoot/" + path + fileName));
            out.writeObject(obj);
            out.flush();
            out.close();

        } catch (IOException e) {
            if (out != null) {
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

    public Object load(String path, String fileName) {
        ObjectInputStream in = null;
        Object obj = null;

        try {
            in = new ObjectInputStream(new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/travelFoot/" + path + fileName));
            obj = in.readObject();

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            return null;
        }

        return obj;
    }
}
