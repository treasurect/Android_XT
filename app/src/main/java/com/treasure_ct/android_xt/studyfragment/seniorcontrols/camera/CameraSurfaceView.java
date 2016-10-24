package com.treasure_ct.android_xt.studyfragment.seniorcontrols.camera;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.treasure_ct.android_xt.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraSurfaceView extends AppCompatActivity implements SurfaceHolder.Callback, Camera.PreviewCallback, Camera.PictureCallback {
    private SurfaceView surfaceView;
    private Camera camera;
    private int mCameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_camera_surfaceview);
        surfaceView = (SurfaceView) findViewById(R.id.camera_surfaceview);
        if (surfaceView != null){
            surfaceView.getHolder().addCallback(this);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (camera == null){
            initCamera();
            if (camera != null){
                try {
                    /*
                          ！！！！！
                        * 照相机的设置
                        * 1.预览设置
                        * 2.拍照设置
                        * */
                    // 预览设置
                    //mCamera.setDisplayOrientation(90);
                // 根据手机屏幕方向来设置显示预览的显示方向
                setCameraDisplayOrientation(this,mCameraId,camera);
                // 1.2 设置预览回调接口 通常是视频录制 二维码扫描 都是用这个接口
                camera.setPreviewCallbackWithBuffer(this);
                //2. 拍照设置 使用 Parameters 来设置
                Camera.Parameters parameters = camera.getParameters();
                parameters.setColorEffect(Camera.Parameters.EFFECT_NEGATIVE);
                //1)设置闪光灯
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                //2)设置的 拍照的 图片格式 针对拍照接口
                parameters.setPictureFormat(ImageFormat.JPEG);
                parameters.setJpegQuality(100);
                camera.setParameters(parameters);
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //        Canvas canvas = holder.lockCanvas();
//        canvas.drawColor(Color.BLUE);
//        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null){
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
    /*
    *
    *根据手机屏幕方向 来设置摄像头的预览方向
    *
    * */
    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
    public void initCamera(){
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i,cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                camera = Camera.open(i);//只会找到后置摄像头
                mCameraId = i;
                break;
            }
        }
    }
    // ============================
    //  摄像头预览出来的头像 会回传到当前方法
    // 预览的图片格式 通常是YUV格式 YCbCr YPbPr ==> RGB
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        //TODO:二维码扫描
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size size = parameters.getPreviewSize();
        int format = parameters.getPreviewFormat();
        YuvImage image = new YuvImage(data,format,size.width,size.height,null);
        Rect rect = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = rect.left + size.width;
        rect.bottom = rect.top + size.height;
//        image.compressToJpeg();
    }

    public void btnSurface(View view) {
        if (camera != null){
            // 拍照 默认有声音 拍照完成 调用回调接口
            // 回调接口调用那个是根据摄像头的配置来完成的
            // 三个参数
            // 参数 1 快门接口
            // 参数二 如果拍照格式RAW 那么调用这个接口
            // 参数3
            camera.takePicture(null,null,this);
        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        String state = Environment.getExternalStorageState();
        File dir = getFilesDir();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            dir = Environment.getExternalStorageDirectory();
        }
        if (!dir.exists()){
            dir.mkdir();
        }
        File file = new File(dir, "img-" + System.currentTimeMillis() + ".jpg");
        if (!file.exists()){
            try {
                file.createNewFile();
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(data);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.stopPreview();
        }
    }
}