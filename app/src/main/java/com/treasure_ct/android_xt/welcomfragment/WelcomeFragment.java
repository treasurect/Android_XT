package com.treasure_ct.android_xt.welcomfragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.camera.BitmapUtil;
import com.treasure_ct.android_xt.welcomfragment.chat.ChatOnlineActivity;
import com.treasure_ct.zxing.decoding.Intents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private ViewPager viewPager;
    private File mFile;
    private ImageView imageCamera;
    private List<Integer> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ImageView imgChat = (ImageView) view.findViewById(R.id.welcome_chat);
        ImageView imgQRcode = (ImageView) view.findViewById(R.id.welcome_qrcode);
        Button search = (Button) view.findViewById(R.id.welcome_search);
        ImageView imgCamera = (ImageView) view.findViewById(R.id.welcome_camera);
        viewPager = ((ViewPager) view.findViewById(R.id.welcome_viewPager));
        imageCamera = (ImageView) view.findViewById(R.id.welcome_camera_img);

        list = new ArrayList<>();
        list.add(R.mipmap.icon_suolong1);
        list.add(R.mipmap.icon_suolong2);
        list.add(R.mipmap.icon_suolong3);
        WelPagerAdapter adapter = new WelPagerAdapter(getContext(), list);
        viewPager.setAdapter(adapter);
        imgChat.setOnClickListener(this);
        imgQRcode.setOnClickListener(this);
        search.setOnClickListener(this);
        imgCamera.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_chat:
                loadChat();
                break;
            case R.id.welcome_qrcode:
                loadQRcode();
                break;
            case R.id.welcome_search:
                break;
            case R.id.welcome_camera:
                loadCamera();
                break;
        }
    }

    /**
     * 记载照相机
     */
    private void loadCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String state = Environment.getExternalStorageState();
        File file = getActivity().getFilesDir();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            file = Environment.getExternalStorageDirectory();
        }
        if (!file.exists()){
            file.mkdir();
        }
        mFile = new File(file,System.currentTimeMillis()+"_ct.jpg");
        Uri uri = Uri.fromFile(mFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,200);
    }

    /**
     * 加载二维码
     */
    private void loadQRcode() {
        Intent intent = new Intent(Intents.Scan.ACTION);
        startActivityForResult(intent,201);
    }

    /**
     *  加载即时通讯
     */
    private void loadChat() {
        startActivity(new Intent(getContext(),ChatOnlineActivity.class));
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200){
            if (resultCode == Activity.RESULT_OK){
                if (mFile.exists()){
                    Bitmap bitmap = BitmapUtil.loadBitmapWithScale(mFile, 500, 500);
                    imageCamera.setImageBitmap(bitmap);
                }
            }
        }else if (requestCode == 201){
            if (data != null){
                String result = data.getStringExtra("result");
                if (result != null){
                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

