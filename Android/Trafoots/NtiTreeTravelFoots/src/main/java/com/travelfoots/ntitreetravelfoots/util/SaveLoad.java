package com.travelfoots.ntitreetravelfoots.util;

import android.os.Environment;

import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveLoad {

    public boolean save(Object obj, String path, String fileName) {
                    ObjectOutputStream out = null;

                    try {
                        out = new ObjectOutputStream(new FileOutputStream( Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/travelFoot/" + path + fileName));
                        out.writeObject(obj);

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
            if(in != null) {
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
