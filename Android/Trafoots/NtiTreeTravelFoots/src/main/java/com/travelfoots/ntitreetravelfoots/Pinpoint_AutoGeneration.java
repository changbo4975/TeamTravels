package com.travelfoots.ntitreetravelfoots;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.travelfoots.ntitreetravelfoots.domain.GPSMetaData;
import com.travelfoots.ntitreetravelfoots.domain.MetaData;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Pinpoint_AutoGeneration {
    public Pinpoint_AutoGeneration() {
        super();
    }

    //TODO Metadata 추출
    public ArrayList<MetaData> MetaDataExtract(final Context context) {
        ArrayList<MetaData> result = new ArrayList<>();

        //Images...
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] imageProjection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.LATITUDE, MediaStore.Images.ImageColumns.LONGITUDE,
                MediaStore.Images.ImageColumns.DATE_TAKEN};
        Cursor Image_cursor = context.getContentResolver().query(imageUri, imageProjection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " desc");

        //데이터
        int columnImageData = Image_cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnImageLat = Image_cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.LATITUDE);
        int columnImageLong = Image_cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.LONGITUDE);
        int columnImageDate = Image_cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN);
        int columnDisplayname = Image_cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int imageLastIndex;

        while (Image_cursor.moveToNext()) {
            String imagePath = Image_cursor.getString(columnImageData);
            double imagesLat = Image_cursor.getDouble(columnImageLat);
            double imageLong = Image_cursor.getDouble(columnImageLong);
            String imageDate = Image_cursor.getString(columnImageDate);
            String nameOfFile = Image_cursor.getString(columnDisplayname);
            //Date 바꾸기
            long addIDate = Long.parseLong(imageDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(addIDate);

            Date iDate = calendar.getTime();

            imageLastIndex = imagePath.lastIndexOf(nameOfFile);
            imageLastIndex = imageLastIndex >= 0 ? imageLastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(imagePath) && Double.compare(imagesLat, 0.0) != 0) {
                //생성자 사용.
                MetaData metaData = new MetaData(imagePath, imagesLat, imageLong, iDate);
                result.add(metaData);
            }
        }
        //Videos....
        Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] videoProjection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.Video.VideoColumns.LATITUDE, MediaStore.Video.VideoColumns.LONGITUDE,
                MediaStore.Video.VideoColumns.DATE_TAKEN};
        Cursor videoCursor = context.getContentResolver().query(videoUri, videoProjection, null, null, MediaStore.Video.VideoColumns.DATE_TAKEN + " desc");


        //데이터
        int columnVideoData = videoCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);//파일
        int columnVideoLat = videoCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.LATITUDE);//위도
        int columnVideoLng = videoCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.LONGITUDE);//경도
        int columnVideoDate = videoCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATE_TAKEN);//촬영날짜
        int columnDisplayVideoName = videoCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);// 파일이름


        int videoLastIndex;
        //넣어버리기~
        while (videoCursor.moveToNext()) {
            String videoPath = videoCursor.getString(columnVideoData);
            double videosLat = videoCursor.getDouble(columnVideoLat);
            double videoLong = videoCursor.getDouble(columnVideoLng);
            String videoDate = videoCursor.getString(columnVideoDate);
            String videoNameOfFile = videoCursor.getString(columnVideoData);

            //Date 바꾸기
            long addvDate = Long.parseLong(videoDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(addvDate);
            Date vDate = calendar.getTime();

            videoLastIndex = videoPath.lastIndexOf(videoNameOfFile);
            videoLastIndex = videoLastIndex >= 0 ? videoLastIndex : videoNameOfFile.length() - 1;

            if (!TextUtils.isEmpty(videoPath) && Double.compare(videoLong, 0.0) != 0) {
                //생성자 사용.
                MetaData metaData = new MetaData(videoPath, videosLat, videoLong, vDate);
                result.add(metaData);
            }

        }


        return result;
    }


    //TODO 근사값 찾기
    public MetaData Near(ArrayList<GPSMetaData> gpsMetaDataArrayList, MetaData contentMetaData) {
        ArrayList<GPSMetaData> tempGpsMetaDataArrayList = new ArrayList<>();
        Date date = contentMetaData.getFileDate();

//        for (int k = 0; k < gpsMetaDataArrayList.size(); k++) {
//            if (gpsMetaDataArrayList.get(k).getUserDate() == date)
//                tempGpsMetaDataArrayList.add(gpsMetaDataArrayList.get(k)); // temp 에 GPS데이터와 메타데이터와 같은 날짜 값을 넣음.
//        }
        double resultLat = 0;
        double resultLng = 0;
        int Listnum = 0;
        for (int i = 0; i < tempGpsMetaDataArrayList.size(); i++) {
            //list에 있는 숫자 - 검색하고자 하는 숫자
            //뺀 값의 절대값이 낮을수록 유사한 숫자
            double tempLng = Math.abs(tempGpsMetaDataArrayList.get(i).getUserLng() - contentMetaData.getFileLng());
            double tempLat = Math.abs(tempGpsMetaDataArrayList.get(i).getUserLat() - contentMetaData.getFileLat());
            if (resultLng > tempLng && resultLat > tempLat) {  // 근사치 검사
                resultLng = tempLng;
                resultLat = tempLat;
                Listnum = i;


            } else if (tempLng == 0 && tempLat == 0) {    // 같은 값 (일치)
                resultLng = tempLng;
                resultLat = tempLat;
                Listnum = i;
                break;
            }
        }
        contentMetaData.setFileLat(resultLat);
        contentMetaData.setFileLng(resultLng);


        return contentMetaData;
    }

    //TODO 거리비교
    /**
     * Mean radius.
     */
    private static double EARTH_RADIUS = 6371;

    /**
     * Returns the distance between two sets of latitudes and longitudes in meters.
     * <p/>
     * Based from the following JavaScript SO answer:
     * http://.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula,
     * which is based on https://en.wikipedia.org/wiki/Haversine_formula (error rate: ~0.55%).
     */
    public double DisTanceComperison(double lat1, double lon1, double lat2, double lon2) {
        double dLat = toRadians(lat2 - lat1);
        double dLon = toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = EARTH_RADIUS * c;

        return d;
    }

    public double toRadians(double degrees) {
        return degrees * (Math.PI / 180);
    }

    //TODO 핀포인트 생성
    public ArrayList<Pinpoint> CreatePinpoint(ArrayList<MetaData> metaDataArrayList) {
        ArrayList<Pinpoint> pinpointArrayList = new ArrayList<>();
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        Date date = calendar.getTime();
        int i = 0;
        for (MetaData metadata : metaDataArrayList
                ) {
            //TODO 시간 잡아주는곳.
//            if (date.compareTo(metadata.getFileDate()) > 0) {
                Pinpoint pinpoint = new Pinpoint();
                Log.i("제발", "CreatePinpoint: "+metadata.getFileDate());
                Log.i("제발", "CreatePinpoint: "+date);
                pinpoint.setLongitude(metadata.getFileLng());
                pinpoint.setLatitude(metadata.getFileLat());
                //TODO 파일패쓰 설정
//                pinpoint.setFilePaths(metadata.getFilePath());
                pinpoint.setNo(i);
                pinpointArrayList.add(pinpoint);
                i++;
//            }
        }

        return pinpointArrayList;
    }


}
