package com.treasure_ct.android_xt.slidingfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.treasure_ct.android_xt.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlidingMainRightFragment extends Fragment implements View.OnClickListener {


    public SlidingMainRightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sliding_main_right, container, false);
        ShareSDK.initSDK(getContext(),"1811ccddd1447");
        ImageView image = (ImageView) view.findViewById(R.id.sliding_share_img);
        image.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sliding_share_img:
                showShare();
                break;
        }
    }

    private void showShare() {
            ShareSDK.initSDK(getContext());
            OnekeyShare oks = new OnekeyShare();
//关闭sso授权
            oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
            oks.setTitle("Android_XT软件分享");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
            oks.setTitleUrl("http://www.baidu.com");
// text是分享文本，所有平台都需要这个字段
            oks.setText("Android_XT软件分享");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl("http://www.baidu.com");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("Android_XT软件分享");
// site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl("http://www.baidu.com");

// 启动分享GUI
            oks.show(getContext());
        }
}
