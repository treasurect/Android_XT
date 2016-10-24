package com.treasure_ct.android_xt.minefragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private PopupWindow mPopupWindow;
    private static boolean isNight = false;
    private ImageView imageNight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
         imageNight = (ImageView) view.findViewById(R.id.mine_night_icon);
        ImageView imgLogin = (ImageView) view.findViewById(R.id.mine_login_icon);
        imageNight.setOnClickListener(this);
        if (isNight){
            imageNight.setImageResource(R.mipmap.icon_night);
        }else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
        }
        imgLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_night_icon:
                if (!isNight){
                    imageNight.setImageResource(R.mipmap.icon_night);
                    isNight = true;
                    Window window = getActivity().getWindow();
                    WindowManager.LayoutParams layoutParams = window.getAttributes();
                    layoutParams.screenBrightness = 0.001f;
                    window.setAttributes(layoutParams);

                }else {
                    imageNight.setImageResource(R.mipmap.icon_daytime);
                    isNight = false;
                    Window window = getActivity().getWindow();
                    WindowManager.LayoutParams layoutParams = window.getAttributes();
                    layoutParams.screenBrightness = -1;
                    window.setAttributes(layoutParams);
                }
                break;
            case R.id.mine_login_icon:
                showPopupWindow();
                break;
            case R.id.mine_popup_quit:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("您确认放弃登录吗？");
                builder.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPopupWindow.dismiss();
                    }
                });
                builder.setNegativeButton("继续登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
    }
    public void showPopupWindow(){
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_mine_login, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        mPopupWindow.setAnimationStyle(R.style.popupWindowAnim1);
        mPopupWindow.setOutsideTouchable(false);
        ImageView quit = (ImageView) convertView.findViewById(R.id.mine_popup_quit);
        quit.setOnClickListener(this);


        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);
    }
}
