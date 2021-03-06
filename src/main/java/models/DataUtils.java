/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.image.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.*;
import javax.servlet.http.*;
import javax.xml.bind.*;

/**
 *
 * @author bayan
 */
public class DataUtils {

    public static Long convertDataToLong(String res) {
        long startDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(res);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }

    public static Long convertDataToLong(int day, int month, int year) {
        String res = day + "/" + month + "/" + year;
        long startDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(res);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }

    public static Long convertDataToLongWithRawString(String str) {
        String day = str.substring(8, 10);
        String month = str.substring(5, 7);
        String year = str.substring(0, 4);
        String res = year + "-" + month + "-" + day;
        long startDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(res);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }

    public static String convertLongToDataString(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(new Date(date));
        return dateString;
    }

    public static String getCurrentDateInString() {
        Date date = new Date();
        String mDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return mDate;
    }

    public static Long getCurentDateInLong() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateobj = new Date();
        String data = df.format(dateobj);
        Long l = convertDataToLongWithRawString(data);
        return l;
    }

    public static String PATH = "/Aualmaty/";

    public static String generateRandomString(int length) {
        String source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            int randSynb = rand.nextInt(source.length());
            result += source.charAt(randSynb);

        }
        return result;
    }

    public static void savePhoto(Part filePart, String path, String fileName) throws Exception {
        OutputStream out = null;
        InputStream fileContent = null;

        try {
            File mFile = new File(path + File.separator + fileName);
            mFile.createNewFile();

            //  out = new java.io.FileOutputStream(new File(path + File.separator + fileName));
            out = new java.io.FileOutputStream(mFile);
            fileContent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
    }

    public static void savePhotoByBytes(byte[] bytes2, String path, String fileName) throws Exception {
        // считываем полученный массив в объект BufferedImage
        BufferedImage resultImage = ImageIO.read(new ByteArrayInputStream(bytes2));

        // сохраняем объект BufferedImage в виде нового изображения
        ImageIO.write(resultImage, "jpg", new File(path, fileName));
    }

    public static byte[] getPhotoByName(String path, String fileName) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        BufferedImage image = ImageIO.read(new File(path, fileName));

        // явно указываем расширение файла для простоты реализации
        ImageIO.write(image, "jpg", baos);
        baos.flush();
/*
        String base64String = DatatypeConverter.printBase64Binary(baos.toByteArray());
        baos.close();

        // декодируем полученную строку в массив байт
        byte[] resByteArray = DatatypeConverter.parseBase64Binary(base64String);
        return  resByteArray;*/
  return baos.toByteArray();
        
    }

    // /app/src/main/webapp/Content
    // /app/target/tomcat.11978/webapps/expanded/Content
    // "C:\\Users\\bayan\\OneDrive\\Документы\\NetBeansProjects\\Aualmaty\\src\\main\\webapp\\Content"; 
    public static String getPersonPhotoPath(Person p) {
        if (p.getPhoto() == null || p.getPhoto().equals("")) {
            return "Content/executors_default_image.png";

        }
        else {
            return "Content/" + p.getPhoto();

        }
    }

    public static boolean deletePersonImage(String imageName) {
        String name = imageName + ".jpg";
        boolean b = false;
        try {
            String path = "C:\\Users\\bayan\\OneDrive\\Документы\\NetBeansProjects\\Aualmaty\\src\\main\\webapp\\Content";

            File file = new File(path + File.separator + name);
            b = file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
