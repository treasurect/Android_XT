package com.treasure_ct.android_xt.minefragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.treasure_ct.android_xt.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MineFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MineFragment";
    private PopupWindow mPopupWindow;
    private static boolean isNight = false;
    private ImageView imageNight;
    private EditText editCode, editPhone;
    private String phoneNumber, user_name1, user_phone;
    private SharedPreferences loginInfo;
    private ImageView mine_login_icon;
    private TextView mine_login_username;
    private static boolean isLogin = false;

    private Tencent tencent;
    public QQAuth mQQAuth;
    private ImageView qqLogin;
    private UserInfo mInfo;
    private String APP_ID = "1105699479";

    private TextView register;
    private ImageView weChatLogin;
    private ImageView sinaLogin;
    private ImageView qqweiboLogin;

    /**
     *                                                      onCreateView
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        imageNight = (ImageView) view.findViewById(R.id.mine_night_icon);
        mine_login_icon = (ImageView) view.findViewById(R.id.mine_login_icon);
        mine_login_username = (TextView) view.findViewById(R.id.mine_login_username);
        imageNight.setOnClickListener(this);
        if (isNight) {
            imageNight.setImageResource(R.mipmap.icon_night);
        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
        }
        mine_login_icon.setOnClickListener(this);
        loginInfo = getActivity().getSharedPreferences("phoneLoginInfo", Context.MODE_PRIVATE);

        if (isLogin) {
            mine_login_icon.setImageResource(R.mipmap.mine_icon);
            mine_login_username.setText("用户：" + user_name1 + "\n手机：" + user_phone);
        } else {
            mine_login_icon.setImageResource(R.mipmap.icon_login1);
            mine_login_username.setText("登录让内容更精彩");
        }
        initQQLogin();
        return view;
    }
    /**
     *                                                      onClick点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_night_icon:
                nightSwitch();
                break;
            case R.id.mine_login_icon:
                if (!isLogin) {
                    showPopupWindow();
                } else {
                    quitAccount();
                }
                break;
            case R.id.mine_popup_quit:
                quitpopupWindow();
                break;
            case R.id.popup_send_message:
                sendMessageCode();
                break;
            case R.id.mine_popup_loginin:
                loginAccount();
                break;
            case R.id.mine_popup_register:
                registerAccount();
                break;
            case R.id.image_qqlogin:
                qqLoginAccount();
                break;
            case R.id.image_wechatlogin:
                break;
            case R.id.image_sinalogin:
                sinaLoginAccount();
                break;
            case R.id.image_qqweibologin:
                break;
        }
    }
    /**
     *                                                      显示 关闭 popupWindow
     */
    public void showPopupWindow() {
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_mine_login, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setAnimationStyle(R.style.popupWindowAnim1);
        mPopupWindow.setOutsideTouchable(false);

        ImageView quit = (ImageView) convertView.findViewById(R.id.mine_popup_quit);
        TextView send_message = (TextView) convertView.findViewById(R.id.popup_send_message);
        editPhone = (EditText) convertView.findViewById(R.id.mine_login_phone);
        editCode = (EditText) convertView.findViewById(R.id.mine_login_verification_code);
        Button login = (Button) convertView.findViewById(R.id.mine_popup_loginin);
        register = (TextView) convertView.findViewById(R.id.mine_popup_register);
        qqLogin = ((ImageView) convertView.findViewById(R.id.image_qqlogin));
        weChatLogin = ((ImageView) convertView.findViewById(R.id.image_wechatlogin));
        sinaLogin = ((ImageView) convertView.findViewById(R.id.image_sinalogin));
        qqweiboLogin = ((ImageView) convertView.findViewById(R.id.image_qqweibologin));

        send_message.setOnClickListener(this);
        quit.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        qqLogin.setOnClickListener(this);
        weChatLogin.setOnClickListener(this);
        sinaLogin.setOnClickListener(this);
        qqweiboLogin.setOnClickListener(this);

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }
    private void quitpopupWindow() {
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
    }
    /**
     *                                                      短信方式 登录注册注销  验证码点击
     */
    public void loginAccount() {
        //                String code = editCode.getText().toString().trim();
//                if (TextUtils.isEmpty(code)){
//                    prodialog = ProgressDialog.show(getContext(),null,"正在验证...",false,true);
//                    SMSSDK.submitVerificationCode("+86",phoneNumber,code);//国家号，手机号，验证码
//                    Toast.makeText(getContext(), "信息已提交", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getContext(), "验证码不可为空", Toast.LENGTH_SHORT).show();
//                }
        String user_name = editPhone.getText().toString().trim();
        String user_pass = editCode.getText().toString().trim();
        user_name1 = loginInfo.getString("userName", "null");
        String user_pass1 = loginInfo.getString("userPass", "null");
        user_phone = loginInfo.getString("userPhone", "null");
        if ((user_name == user_name1 || user_name.equals(user_name1)) && (user_pass == user_pass1 || user_pass.equals(user_pass1))) {
            Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
            mPopupWindow.dismiss();
            mine_login_icon.setImageResource(R.mipmap.mine_icon);
            mine_login_username.setText("用户：" + user_name1 + "\n手机：" + user_phone);
            isLogin = true;
        } else {
            Toast.makeText(getContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
    private void registerAccount() {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
//                    country = (String) phoneMap.get("country");
                    phoneNumber = (String) phoneMap.get("phone");
                    final String code2 = (String) phoneMap.get("code");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());

                    /**
                     * 显示Dialog
                     */
                    LinearLayout linearLayout = new LinearLayout(getContext());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLayout.setGravity(Gravity.CENTER);
                    linearLayout.setPadding(50, 50, 50, 50);
                    linearLayout.setLayoutParams(layoutParams);

                    final EditText editUserName = new EditText(getContext());
                    editUserName.setBackgroundResource(R.drawable.edittext_bg);
                    editUserName.setPadding(50, 0, 0, 50);
                    editUserName.setHint("请输入用户名");
                    final EditText editUserPass = new EditText(getContext());
                    editUserPass.setBackgroundResource(R.drawable.edittext_bg);
                    editUserPass.setPadding(50, 0, 0, 50);
                    editUserPass.setHint("密码");

                    linearLayout.addView(editUserName, layoutParams);
                    linearLayout.addView(editUserPass, layoutParams);
                    builder1.setView(linearLayout);
                    builder1.setPositiveButton("提交", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String userName = editUserName.getText().toString().trim();
                            String userPass = editUserPass.getText().toString().trim();
                            if (userName != null && userPass != null) {
                                SharedPreferences.Editor edit = loginInfo.edit();
                                edit.putString("userName", userName);
                                edit.putString("userPass", userPass);
                                edit.putString("userPhone", phoneNumber);
                                edit.putString("userCode", code2);
                                edit.apply();
                            } else {
                                Toast.makeText(getContext(), "用户名或者密码不可为空", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    AlertDialog dialog1 = builder1.create();
                    dialog1.show();
// 提交用户信息（此方法可以不调用）
//                    registerUser(country, phone);
//                            Toast.makeText(getContext(), "城市："+country+"\n手机号："+phone, Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerPage.show(getContext());
    }
    private void quitAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Are you sure?");
        builder.setMessage("您确定注销账户吗？\n注销的话部分功能就用不了了哦");
        builder.setPositiveButton("注销", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mine_login_icon.setImageResource(R.mipmap.icon_login1);
                mine_login_username.setText("登录让内容更精彩");
                isLogin = false;
                Toast.makeText(getContext(), "退出成功，欢迎下次登录", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("再想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
    private void sendMessageCode() {
        //发送短信，获取验证码
        //                phoneNumber = editPhone.getText().toString().trim();
//                if (TextUtils.isEmpty(phoneNumber)){
//                    Toast.makeText(getContext(), "号码不能为空！！！", Toast.LENGTH_SHORT).show();
//                }else {
//                    SMSSDK.getVerificationCode("+86",phoneNumber);
//                    Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
//                }
        Toast.makeText(getContext(), "请直接注册或者直接登录哦", Toast.LENGTH_SHORT).show();
    }
    /**
     *                                                      夜间模式的切换
     */
    private void nightSwitch() {
        if (!isNight) {
            imageNight.setImageResource(R.mipmap.icon_night);
            isNight = true;
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = 0.001f;
            window.setAttributes(layoutParams);

        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
            isNight = false;
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = -1;
            window.setAttributes(layoutParams);
        }
    }
    /**
     *                                                      QQ第三方登录 注销
     */
    private void initQQLogin() {
        // Tencent类是SDK的主要实现类，通过此访问腾讯开放的OpenAPI。
        mQQAuth = QQAuth.createInstance(APP_ID, getContext().getApplicationContext());
        //实例化
        tencent = Tencent.createInstance(APP_ID, getContext());
    }
    private void qqLoginAccount() {
        if (!mQQAuth.isSessionValid()){
            IUiListener listener = new BaseUIListener(){
                @Override
                public void onComplete(Object o) {
                    updateLoginUser();
                    Toast.makeText(getContext(), "测试1", Toast.LENGTH_SHORT).show();
                }
            };
            Toast.makeText(getContext(), "测试2", Toast.LENGTH_SHORT).show();
//            mQQAuth.login(getActivity(),"all",listener);
            tencent.login(getActivity(),"all",listener);
            mPopupWindow.dismiss();
        }else {
            mQQAuth.logout(getContext());
            updateLoginUser();
        }
    }
    private void updateLoginUser(){
        if (mQQAuth != null && mQQAuth.isSessionValid()){
            IUiListener listener = new IUiListener(){
                @Override
                public void onComplete(final Object response) {
                    Toast.makeText(getContext(), "更新成功", Toast.LENGTH_SHORT).show();
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                    new Thread() {

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject) response;
                            if (json.has("figureurl")) {
                                Bitmap bitmap = null;

                                Message msg = new Message();
                                msg.obj = bitmap;
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                            }
                        }

                    }.start();
                }
                @Override
                public void onError(UiError uiError) {
                    Toast.makeText(getContext(), "更新失败", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancel() {
                    Toast.makeText(getContext(), "更新取消 ", Toast.LENGTH_SHORT).show();
                }
            };
            mInfo = new UserInfo(getContext(),mQQAuth.getQQToken());
            mInfo.getUserInfo(listener);
        }else {
            mine_login_username.setText("");
        }
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    JSONObject object = (JSONObject) msg.obj;
                    if (object.has("nickname")){
                        try {
                            mine_login_username.setText(object.getString("nickname"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    break;
            }
        }
    };
    private class BaseUIListener implements IUiListener{
        @Override
        public void onComplete(Object o) {
            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {
            Toast.makeText(getContext(), "登录取消", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *                                                             微博第三方登录  注销
     */
    private void sinaLoginAccount() {

    }
    @Override
    public void onStop() {
        mPopupWindow.dismiss();
        super.onStop();
    }
}
