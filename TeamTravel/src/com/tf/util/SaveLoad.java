package com.tf.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javafx.scene.image.Image;

public class SaveLoad implements Serializable {
	private final static String path = "C:\\Users\\com\\Desktop\\picture";
		
	public boolean save(MultipartFile file, String email, String folder, String fileName) throws Exception {
		
		String filePath = path + "\\" + email + "\\" + folder;
		File todayFolder = new File(filePath);
		if (!todayFolder.exists()) {
			todayFolder.mkdirs();
		}
		
		System.out.println();
		
		Path fileSavePath = FileSystems.getDefault().getPath(filePath + "\\" + fileName);
		File target = new File(fileSavePath.toUri());
		FileCopyUtils.copy(file.getBytes(), target);
		
		return false;
	}
	
	
	public BufferedImage load(String email, String folder, String fileName) throws Exception {
		
		String filePath = path + "\\" + email + "\\" + folder + "\\" + fileName;
		System.out.println(filePath);
		File sourceimage = new File(filePath);
		BufferedImage image = ImageIO.read(sourceimage);
		
		return image;
	}
	

//    public boolean save(Object obj, String email, String path, String fileName){
//
//        ObjectOutputStream out = null;
//
//        try {
//            String filePath = "C:\\Users\\com\\Desktop\\picture\\" + email + "\\" + path;
//
//            File file = new File(filePath);
//            file.mkdirs();
//
//            out = new ObjectOutputStream(new FileOutputStream(filePath + "/" + fileName));
//            out.writeObject(obj);
//
//            //out.flush();
//            out.close();
//
//        } catch (IOException e) {
//            if (out != null) {
//                try {
//                    out.flush();
//                    out.close();
//                } catch (IOException e1) {
//
//                }
//            }
//
//            return false;
//        }
//
//        return true;
//    }

//    public Object load(String email, String path, String fileName) {
//    	ObjectInputStream in = null;
//        Object obj = null;
//        
//        try {
//            String filePath =  "C:\\Users\\com\\Desktop\\picture\\" + email + "\\" + path;
//            
//            in = new ObjectInputStream(new FileInputStream(filePath + "\\" + fileName));
//            obj = in.readObject();
//            
//            in.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//
//            return null;
//        }
//
//        return obj;
//    }
}
