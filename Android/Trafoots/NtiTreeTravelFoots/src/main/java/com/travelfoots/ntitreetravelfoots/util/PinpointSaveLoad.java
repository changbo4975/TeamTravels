package com.travelfoots.ntitreetravelfoots.util;

import android.os.Environment;

import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PinpointSaveLoad {




    public boolean save(Pinpoint pinpoint) {
                    ObjectOutputStream out = null;

                    try {
                        out = new ObjectOutputStream(new FileOutputStream( Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                                + Integer.toString(pinpoint.getNo())));
                        out.writeObject(pinpoint);

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

    public Pinpoint load(int no) {
        ObjectInputStream in = null;
        Pinpoint pinpoint = null;

        try {
            in = new ObjectInputStream(new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                    + Integer.toString(no)));
            pinpoint = (Pinpoint) in.readObject();

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

        return pinpoint;
    }


}
