package com.travelfoots.ntitreetravelfoots;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.travelfoots.ntitreetravelfoots.domain.GPSMetaData;
import com.travelfoots.ntitreetravelfoots.domain.MetaData;
import com.travelfoots.ntitreetravelfoots.domain.Photo;
import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;
import com.travelfoots.ntitreetravelfoots.util.GpsMetaDataSaveLoad;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
    public double Near(List<GPSMetaData> data, MetaData target) {
        double nearLat = 0;
        double nearLng = 0;
        double minLat = Double.MAX_VALUE;
        double minLng = Double.MAX_VALUE;

        for (int i = 0; i < data.size(); i++) {
            if (Abs(data.get(i).getUserLat() - target.getFileLat()) < minLat &&
                    Abs(data.get(i).getUserLng() - target.getFileLng()) < minLng) {
                minLat = Abs(data.get(i).getUserLat() - target.getFileLat());
                nearLat = data.get(i).getUserLng();
                minLng = Abs(data.get(i).getUserLng() - target.getFileLng());
                nearLng = data.get(i).getUserLng();
            }
        }
        double distance = DistanceComparison(nearLat, nearLng, target.getFileLat(), target.getFileLng());

        return distance;

    }

    public static double Abs(double p) {
        return (p < 0) ? -p : p;
    }

    //TODO 거리비교
    double DistanceComparison(double lat, double lng, double nlat, double nlng) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        Ellipsoid reference = Ellipsoid.WGS84;

        GlobalPosition pointA = new GlobalPosition(lat, lng, 0.0); // Point A
        GlobalPosition userPos = new GlobalPosition(nlat, nlng, 0.0); // Point B
        //거리값 m단위
        double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance(); // Distance between Point A and Point B

        return distance;
    }

    //TODO 거리비교

    List<MetaData> DistanceComparison(List<MetaData> metaDataList) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        Ellipsoid reference = Ellipsoid.WGS84;

        for (int i = 0; i < metaDataList.size() - 1; i++) {
            if (i == metaDataList.size() - 1) {
                break;
            }
            double fLat = metaDataList.get(i).getFileLat();
            double fLng = metaDataList.get(i).getFileLng();
            double rLat = metaDataList.get(i + 1).getFileLat();
            double rLng = metaDataList.get(i + 1).getFileLng();
            GlobalPosition pointA = new GlobalPosition(fLat, fLng, 0.0); // Point A
            GlobalPosition userPos = new GlobalPosition(rLat, rLng, 0.0); // Point B
            //거리값 m단위
            double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance(); // Distance between Point A and Point B

            if (distance <= 10) {
                metaDataList.get(i + 1).setFileLat(fLat);
                metaDataList.get(i + 1).setFileLng(fLng);
            }
        }
        return metaDataList;
    }


    //TODO 핀포인트 생성
    public List<Pinpoint> CreatePinpoint(List<MetaData> metaDataArrayList) {
        ArrayList<Pinpoint> pinpointArrayList = new ArrayList<>();
        GpsMetaDataSaveLoad gpsMetaDataSaveLoad = new GpsMetaDataSaveLoad();
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        Date date = calendar.getTime();
        int i = 0;
        metaDataArrayList = DistanceComparison(metaDataArrayList);// -> 사진묶기
        for (MetaData metadata : metaDataArrayList
                ) {
            //TODO 파일패쓰 설정
            if (15 >= (Near(gpsMetaDataSaveLoad.load(), metadata))) { //GPS 근사치 구해 거리비교 시 15m 이내에 있을경우.
                Pinpoint pinpoint = new Pinpoint();
                pinpoint.setLongitude(metadata.getFileLng());
                pinpoint.setLatitude(metadata.getFileLat());
                pinpoint.setPhotoList(getPhotoList(metaDataArrayList,metadata));
                pinpoint.setNo(i);
                pinpointArrayList.add(pinpoint);
                Log.i("제뱔", "CreatePinpoint: " + pinpoint.getPhotoList().toString()+" pinpointSize : " + pinpointArrayList.size());
            }
            i++;
//            }
        }

        return pinpointArrayList;
    }
    List<Photo> getPhotoList(List<MetaData> metaDataList,MetaData metaData){

        double lat = metaData.getFileLat();
        double lng = metaData.getFileLng();

        List<Photo> photoList= new ArrayList<>();
        Photo photo = new Photo();
        photo.setFilepath(metaData.getFilePath());
        photoList.add(photo);
        for (MetaData met: metaDataList
                ) {
            if(lat == met.getFileLat() && lng == met.getFileLng()){
                Photo same_photo = new Photo();
                same_photo.setFilepath(met.getFilePath());
                photoList.add(photo);
            }

        }

        HashSet<Photo> listSet = new HashSet<Photo>(photoList);
        ArrayList<Photo> processedList = new ArrayList<>( listSet);
        return processedList;
    }

}
