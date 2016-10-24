package com.treasure_ct.network_xt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 16/9/6
 * Email: vhly@163.com
 */
public class ImageTask extends AsyncTask<String, Integer, Bitmap> {

    private SoftReference<ImageView> mReference;

    private Context mContext;

    public ImageTask(ImageView imageView){
        mReference = new SoftReference<ImageView>(imageView);
        mContext = imageView.getContext();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap ret = null;

        if(strings != null && strings.length > 0){
            String url = strings[0];

            /////////////////////////////////
            // 检查缓存，如果文件存在，直接形成图片

            // 先检查文件缓存
            // TODO: 将图片的数据保存到手机的内部存储区中，以文件的形式
            // Context getFilesDir() 获取内部存储区 /data/data/包名/files
            //         getCacheDir() 缓存目录，当手机内部存储满了，那么系统自动删除这个目录

            File cacheDir = mContext.getExternalCacheDir();
            // 缓存目录需要监测，是否存在，不存在创建

            if(cacheDir == null){
                cacheDir = mContext.getCacheDir();
            }

            if(!cacheDir.exists()){
                cacheDir.mkdirs();
            }

            File picFile = new File(cacheDir, EncryptUtil.mapUrl(url));
            if(picFile.exists()){

                ret = BitmapFactory.decodeFile(picFile.getAbsolutePath());

            }else {

                // 文件不存在的情况
                // 联网下载图片
                byte[] data = HttpTool.doGet(url);
                if (data != null) {

                    // https://www.baidu.com/img/bd_logo1.png -> File
                    // http://www.sina.com.cn/img/bd_logo1.png
                    // String -> MD5 -> Hex

                    // 图片创建
                    ret = BitmapFactory.decodeByteArray(data, 0, data.length);

                    // TODO: 将图片的数据保存到手机的内部存储区中，以文件的形式
                    // Context getFilesDir() 获取内部存储区 /data/data/包名/files
                    //         getCacheDir() 缓存目录，当手机内部存储满了，那么系统自动删除这个目录

                    //File cacheDir = mContext.getCacheDir();
                    // 缓存目录需要监测，是否存在，不存在创建
                    //
                    //if(!cacheDir.exists()){
                    //    cacheDir.mkdirs();
                    //}


                    File saveFile = new File(cacheDir, EncryptUtil.mapUrl(url));

                    FileOutputStream fout = null;
                    try {
                        fout = new FileOutputStream(saveFile);

                        fout.write(data);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    } finally {
                        StreamUtil.close(fout);
                    }

                    data = null;
                }
            }
        }

        return ret;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            if(mReference != null){
                ImageView imageView = mReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
