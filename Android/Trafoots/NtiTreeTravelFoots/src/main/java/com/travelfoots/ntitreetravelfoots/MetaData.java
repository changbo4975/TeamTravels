package com.travelfoots.ntitreetravelfoots;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.drew.lang.GeoLocation;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MetaData {

    public MetaData() {
        super();
    }

    private String filePath;
    private double fileLat;
    private double fileLng;
    private Date fileDate;

    public MetaData(String filePath, double fileLat, double fileLng, Date fileDate) {
        this.filePath = filePath;
        this.fileLat = fileLat;
        this.fileLng = fileLng;
        this.fileDate = fileDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getFileLat() {
        return fileLat;
    }

    public void setFileLat(double fileLat) {
        this.fileLat = fileLat;
    }

    public double getFileLng() {
        return fileLng;
    }

    public void setFileLng(double fileLng) {
        this.fileLng = fileLng;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

//    void GetMetaData(ArrayList<String> filepaths) {
//        File file;
//        Iterable<Directory> directories;
//        Collection<PhotoLocation> photoLocations = new ArrayList<PhotoLocation>();
//        Collection<GpsDirectory> gpsDirectories;
//        Collection<Mp4Directory> mp4Directories;
//        ExifSubIFDDirectory subIFDDirectory;
//
//        try {
//            for (String filepath : filepaths) {
//                //TODO 갤러리 모든 목록 불러오기. + 여행날짜 시작 부분 안함.
//                //파일 불러오기 (하나만 해놓기.)
//                file = new File(filepath);
////            file = new File(filePath);
//
//                // 파일의 메타데이터
//                Metadata metadata = ImageMetadataReader.readMetadata(file);
//
//                //GPS 메타데이터 불러오기
//
//                subIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
//                gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);
//                for (GpsDirectory gpsDirectory : gpsDirectories) {
//                    // Try to read out the location, making sure it's non-zero
//                    GeoLocation geoLocation = gpsDirectory.getGeoLocation();
//                    Date photo_time = subIFDDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
//
//                    //GPS값이 없는건 빼고....
//                    if (geoLocation != null && !geoLocation.isZero()) {
//                        //사진 경로와 gps값, 사진 시간정보 photoLocations 에 젖앙.
////                        Log.d(TAG, "GetMetaData: " + geoLocation + " " + photo_time);
//                        photoLocations.add(new PhotoLocation(geoLocation, file, photo_time));
//                        break;
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.getStackTrace();
//            System.out.println(e);
//
//        }
//    }


    //TODO 모든 경로.
    public static ArrayList<MetaData> ContentsMetadata(final Context context) {
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
            long addIDate =Long.parseLong(imageDate);
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
            long addvDate =Long.parseLong(videoDate);
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

    public static class PhotoLocation {
        public final GeoLocation location;
        public final File file;
        public final Date photo_time;

        public PhotoLocation(final GeoLocation location, final File file, Date photo_time) {
            this.location = location;
            this.file = file;
            this.photo_time = photo_time;
        }
    }

}



