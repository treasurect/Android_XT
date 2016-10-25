package com.treasure_ct.android_xt.minefragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
    private String phoneNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        SMSSDK.initSDK(getContext(),"1847114ba1735","786941ca8790a16335b0ba1b824b8d64");
        SMSSDK.registerEventHandler(eh);//注册短信回调监听
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
    public void showPopupWindow(){
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_mine_login, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        mPopupWindow.setAnimationStyle(R.style.popupWindowAnim1);
        mPopupWindow.setOutsideTouchable(false);

        ImageView quit = (ImageView) convertView.findViewById(R.id.mine_popup_quit);
        TextView send_message = (TextView) convertView.findViewById(R.id.popup_send_message);
         editPhone = (EditText) convertView.findViewById(R.id.mine_login_phone);
         editCode = (EditText) convertView.findViewById(R.id.mine_login_verification_code);
        send_message.setOnClickListener(this);
        quit.setOnClickListener(this);


        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);
    }
    //    private void textSendMessage() {
//        //打开注册页面
//        RegisterPage registerPage = new RegisterPage();
//        registerPage.setRegisterCallback(new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//// 解析注册结果
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    @SuppressWarnings("unchecked")
//                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                    String country = (String) phoneMap.get("country");
//                    String phone = (String) phoneMap.get("phone");
//
//// 提交用户信息（此方法可以不调用）
////                    registerUser(country, phone);
//                    Toast.makeText(getContext(), "城市："+country+"\n手机号："+phone, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        registerPage.show(getContext());
//    }

    private EventHandler eh= new EventHandler(){
                @Override
                public void afterEvent(int event, int result, Object data) {

                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                            HashMap<String, Object> data1 = (HashMap<String, Object>) data;
                            String country = (String) data1.get("country");
                            String phone = (String) data1.get("phone");
                            if (phone.equals(phoneNumber)){
//                                runO
                            }
                        }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            //获取验证码成功
                        }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //返回支持发送验证码的国家列表
                        }
                    }else{
                        ((Throwable)data).printStackTrace();
                    }
                }
            };


    /**
     * 获取验证码
     * @param
     */
    public void getCode(View view){
        phoneNumber = editPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(getContext(), "号码不能为空！！！", Toast.LENGTH_SHORT).show();
        }else {
            SMSSDK.getVerificationCode("+86",phoneNumber);
            Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 提交验证码、
     * @param v
     */
//    private void sendCode(View view){
//        String phoneNum = editPhone.getText().toString().trim();
//        if (!TextUtils.isEmpty(phoneNum)){
//            ProgressDialog.show(getContext(),null,"",false,true);
//        }
//    }
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
            case R.id.popup_send_message:
//                textSendMessage();

                SMSSDK.registerEventHandler(eh); //注册短信回调
                break;
        }
    }
    @Override
    public void onDestroyView() {
        SMSSDK.unregisterAllEventHandler();//取消监听，防止内存泄露
        super.onDestroyView();
    }
    public interface OnSendMessageHandler {

        //#if def{lang} == cn
        /**
         * 此方法在发送验证短信前被调用，传入参数为接收者号码
         * 返回true表示此号码无须实际接收短信
         */
        //#elif def{lang} == en
        /**
         * This method will be called before verification message being to sent,
         * input params are the message receiver
         * return true means this number won't actually receive the message
         */
        //#endif
        public boolean onSendMessage(String country, String phone);

    }
}
