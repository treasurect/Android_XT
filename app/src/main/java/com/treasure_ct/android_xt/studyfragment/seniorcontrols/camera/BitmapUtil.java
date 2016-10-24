package com.treasure_ct.android_xt.studyfragment.seniorcontrols.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by treasure on 2016.10.18.
 */

public class BitmapUtil {
    public BitmapUtil() {
    }
    public static Bitmap loadBitmapWithScale(File imageFile,int reqWidth,int reqHeight){
        Bitmap ret = null;
        if (imageFile != null && imageFile.exists() && imageFile.canRead()){
            if (reqWidth > 0 && reqHeight > 0){
                //1.第一次采样
                BitmapFactory.Options options = new BitmapFactory.Options();
                //只获取尺寸
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imageFile.getAbsolutePath(),options);

                //获取图片源时尺寸,激素那采样数值
                options.inSampleSize = calcInSampleSize(options,reqWidth,reqHeight);

                //再次设置，加载图像到内存
                options.inJustDecodeBounds = false ;
                //TODO:可以用inPreferredConfig来进一步降低图片像素内存】】
                //所有JPG图片可以支持透明
                //所有JPG图片 都不支持透明

                String type = options.outMimeType;
                if (type != null){
                    //根据MIME type来判断图片的文件格式
                    //image/png
                    //image/jpeg
                    if (type.endsWith("png")) {
                        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                    }else if (type.endsWith("jpeg")){
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                    }
                }
                ret = BitmapFactory.decodeFile(imageFile.getAbsolutePath(),options);
            }
        }
        return ret;
    }
    private static int calcInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int inSampleSize = 1;
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        if (reqHeight > 0 && reqWidth > 0){
            int halfW = outWidth >> 1;
            int halfH = outHeight >> 1;
            while (((halfH / inSampleSize) >= reqHeight) && ((halfW / inSampleSize) >= reqWidth)){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
