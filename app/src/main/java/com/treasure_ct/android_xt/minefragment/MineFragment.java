package com.treasure_ct.android_xt.minefragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private PopupWindow mPopupWindow;
    private static boolean isNight = false;
    private ImageView imageNight;
    private EditText editCode,editPhone;
    private String phoneNumber,country;
    private static String passWord;
    private ProgressDialog prodialog;
    private SharedPreferences loginInfo;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 200:
//                    Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
//                    break;
//                case 404:
//                    Toast.makeText(getContext(), "登录失败", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
//        SMSSDK.registerEventHandler(eh);//注册短信回调监听
         imageNight = (ImageView) view.findViewById(R.id.mine_night_icon);
        ImageView imgLogin = (ImageView) view.findViewById(R.id.mine_login_icon);
        imageNight.setOnClickListener(this);
        if (isNight){
            imageNight.setImageResource(R.mipmap.icon_night);
        }else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
        }
        imgLogin.setOnClickListener(this);
         loginInfo = getActivity().getSharedPreferences("phoneLoginInfo", Context.MODE_PRIVATE);
        return view;
    }

    public void showPopupWindow(){
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_mine_login, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        mPopupWindow.setAnimationStyle(R.style.popupWindowAnim1);
        mPopupWindow.setOutsideTouchable(false);

        ImageView quit = (ImageView) convertView.findViewById(R.id.mine_popup_quit);
        TextView send_message = (TextView) convertView.findViewById(R.id.popup_send_message);
         editPhone = (EditText) convertView.findViewById(R.id.mine_login_phone);
         editCode = (EditText) convertView.findViewById(R.id.mine_login_verification_code);
        Button login = (Button) convertView.findViewById(R.id.mine_popup_in);
        TextView register = (TextView) convertView.findViewById(R.id.mine_popup_register);

        send_message.setOnClickListener(this);
        quit.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);
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
                final AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.popup_send_message:  //发送短信，获取验证码
//                phoneNumber = editPhone.getText().toString().trim();
//                if (TextUtils.isEmpty(phoneNumber)){
//                    Toast.makeText(getContext(), "号码不能为空！！！", Toast.LENGTH_SHORT).show();
//                }else {
//                    SMSSDK.getVerificationCode("+86",phoneNumber);
//                    Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.mine_popup_in:
//                String code = editCode.getText().toString().trim();
//                if (TextUtils.isEmpty(code)){
//                    prodialog = ProgressDialog.show(getContext(),null,"正在验证...",false,true);
//                    SMSSDK.submitVerificationCode("+86",phoneNumber,code);//国家号，手机号，验证码
//                    Toast.makeText(getContext(), "信息已提交", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getContext(), "验证码不可为空", Toast.LENGTH_SHORT).show();
//                }
                String phone = editPhone.getText().toString().trim();
                String code = editCode.getText().toString().trim();
                SharedPreferences phoneLoginInfo = getActivity().getSharedPreferences("phoneLoginInfo", Context.MODE_PRIVATE);
                String phone1 = phoneLoginInfo.getString("phoneNumber", "null");
                String code1 = phoneLoginInfo.getString("phonePWD", "null");
                if (phone == phone1 && phone.equals(phone1) && code == code1 && code.equals(code1)){
                    Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "code1"+code1+"\ncode:"+code+"\nphone1"+phone1, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mine_popup_register:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                             country = (String) phoneMap.get("country");
                             phoneNumber = (String) phoneMap.get("phone");
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                            final EditText editText = new EditText(getContext());
                            builder1.setView(editText);
                            builder1.setPositiveButton("提交", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    passWord = editText.getText().toString().trim();
                                    Toast.makeText(getContext(), "qqqqqq"+passWord, Toast.LENGTH_SHORT).show();
                                }
                            });
                            AlertDialog dialog1 = builder1.create();
                            dialog1.show();
                            SharedPreferences.Editor edit = loginInfo.edit();
                            edit.putString("phoneNumber",phoneNumber);
                            edit.putString("phonePWD",passWord);
                            edit.apply();
// 提交用户信息（此方法可以不调用）
//                    registerUser(country, phone);
//                            Toast.makeText(getContext(), "城市："+country+"\n手机号："+phone, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                registerPage.show(getContext());
                break;
        }
    }

//    private EventHandler eh= new EventHandler(){
//        @Override
//        public void afterEvent(int event, int result, Object data) {
//
//            if (result == SMSSDK.RESULT_COMPLETE) {
//                //回调完成
//                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                    //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
//                    HashMap<String, Object> data1 = (HashMap<String, Object>) data;
//                    String country = (String) data1.get("country");
//                    String phone = (String) data1.get("phone");
//                    Toast.makeText(getContext(), "城市："+country+"\n手机号："+phone, Toast.LENGTH_SHORT).show();
//                    if (phone.equals(phoneNumber)){
//                        Message message = handler.obtainMessage(200);
//                        handler.sendMessage(message);
//                    }else {
//                        Message message = handler.obtainMessage(404);
//                        handler.sendMessage(message);
//                    }
//                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
//                    //获取验证码成功
//                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
//                    //返回支持发送验证码的国家列表
//                }
//            }else{
//                ((Throwable)data).printStackTrace();
//            }
//        }
//    };

//    @Override
//    public void onDestroyView() {
//        SMSSDK.unregisterAllEventHandler();//取消监听，防止内存泄露
//        super.onDestroyView();
//    }
}
