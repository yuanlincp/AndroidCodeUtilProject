package com.android.yl.androidcodeproject.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author YuanLin
 * @create 2019/8/19
 * @Describe 文件管理类
 */
public class FileUtils {

    private static double MAX_LENGTH = 0.5 * 1024 * 1024;

    /**
     * 图片压缩-质量压缩
     *
     * @param filePath 源图片路径
     * @return 压缩后的路径
     */

    public static String compressImage(String filePath) {
        //原文件
        File oldFile = new File(filePath);
        if (oldFile.length() > MAX_LENGTH) {
            //压缩文件路径 照片路径/
            String targetPath = oldFile.getPath();
            String startPath = targetPath.substring(0, targetPath.lastIndexOf(".")) + "image";
            String endPath = targetPath.substring(targetPath.lastIndexOf("."));
            targetPath = startPath + endPath;
            int quality = 50;//压缩比例0-100
            Bitmap bm = getSmallBitmap(filePath);//获取一定尺寸的图片
            int degree = getRotateAngle(filePath);//获取相片拍摄角度
            if (degree != 0) {//旋转照片角度，防止头像横着显示
                bm = setRotateAngle(degree, bm);
            }
            File outputFile = new File(targetPath);
            try {
                if (!outputFile.exists()) {
                    outputFile.getParentFile().mkdirs();
                    //outputFile.createNewFile();
                } else {
                    outputFile.delete();
                }
                FileOutputStream out = new FileOutputStream(outputFile);
                bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                return filePath;
            }
            if (outputFile.length() > MAX_LENGTH) {
                compressImage(outputFile.getPath());
            }
            return outputFile.getPath();
        }
        return filePath;
    }

    /**
     * 获取图片的旋转角度
     *
     * @param filePath
     * @return
     */
    public static int getRotateAngle(String filePath) {
        int rotate_angle = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate_angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate_angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate_angle = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate_angle;
    }

    /**
     * 旋转图片角度
     *
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap setRotateAngle(int angle, Bitmap bitmap) {

        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(angle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;

    }

    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


}
