package com.treasure_ct.android_xt.studyfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Weather;
import com.treasure_ct.android_xt.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainDialogFragment extends DialogFragment implements View.OnClickListener{
    private Weather mWeatherAPI;
    private EditText edit;
    private TextView textloc,weather_detail,weather_future_detail1,weather_future_detail2,weather_future_detail3,weather_future_detail4,weather_future_detail5;
    private TextView weather_future_detail6,weather_future_detail7,weather_future_detail8,weather_future_detail9,weather_future_detail10;
    private LinearLayout layout;
    private CardView main_weather_future_card1,main_weather_future_card2,main_weather_future_card3,main_weather_future_card4,main_weather_future_card5;
    private CardView main_weather_future_card6,main_weather_future_card7,main_weather_future_card8,main_weather_future_card9,main_weather_future_card10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_main_dialog, container, false);
        Button btnsure = (Button) view.findViewById(R.id.main_dialog_btn_sure);
         textloc = (TextView) view.findViewById(R.id.main_weather_layout_textloc);
        weather_detail = (TextView) view.findViewById(R.id.main_weather_layout_detail);
        weather_future_detail1 = (TextView) view.findViewById(R.id.main_weather_future_detail1);
        weather_future_detail2 = (TextView) view.findViewById(R.id.main_weather_future_detail2);
        weather_future_detail3 = (TextView) view.findViewById(R.id.main_weather_future_detail3);
        weather_future_detail4 = (TextView) view.findViewById(R.id.main_weather_future_detail4);
        weather_future_detail5 = (TextView) view.findViewById(R.id.main_weather_future_detail5);
        weather_future_detail6 = (TextView) view.findViewById(R.id.main_weather_future_detail6);
        weather_future_detail7 = (TextView) view.findViewById(R.id.main_weather_future_detail7);
        weather_future_detail8 = (TextView) view.findViewById(R.id.main_weather_future_detail8);
        weather_future_detail9 = (TextView) view.findViewById(R.id.main_weather_future_detail9);
        weather_future_detail10 = (TextView) view.findViewById(R.id.main_weather_future_detail10);
        main_weather_future_card1 = (CardView) view.findViewById(R.id.main_weather_future_card1);
        main_weather_future_card2 = (CardView) view.findViewById(R.id.main_weather_future_card2);
        main_weather_future_card3 = (CardView) view.findViewById(R.id.main_weather_future_card3);
        main_weather_future_card4 = (CardView) view.findViewById(R.id.main_weather_future_card4);
        main_weather_future_card5 = (CardView) view.findViewById(R.id.main_weather_future_card5);
        main_weather_future_card6 = (CardView) view.findViewById(R.id.main_weather_future_card6);
        main_weather_future_card7 = (CardView) view.findViewById(R.id.main_weather_future_card7);
        main_weather_future_card8 = (CardView) view.findViewById(R.id.main_weather_future_card8);
        main_weather_future_card9 = (CardView) view.findViewById(R.id.main_weather_future_card9);
        main_weather_future_card10 = (CardView) view.findViewById(R.id.main_weather_future_card10);
        main_weather_future_card1.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card2.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card3.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card4.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card5.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card6.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card7.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card8.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card9.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        main_weather_future_card10.setCardBackgroundColor(R.mipmap.pic_weather_overcast);
        layout = (LinearLayout) view.findViewById(R.id.main_weather_layout);
        edit = (EditText) view.findViewById(R.id.main_dialog_edit);
        MobAPI.initSDK(getActivity(),"181d695ec6924");
         mWeatherAPI = (Weather) MobAPI.getAPI(Weather.NAME);
        btnsure.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_dialog_btn_sure:
                mWeatherAPI.queryByCityName(edit.getText().toString().trim(), new APICallback() {
                    @Override
                    public void onSuccess(API api, int i, Map<String, Object> result) {
                        switch (i){
                            case Weather.ACTION_QUERY:
                                List<HashMap<String, Object>> result1 = (List<HashMap<String, Object>>) result.get("result");
                                HashMap<String, Object> weather = result1.get(0);
                                List<HashMap<String, Object>> future = (List<HashMap<String, Object>>) weather.get("future");
//                                for (int j = 0; j < 10; j++) {
                                    HashMap<String, Object> future_detail = future.get(0);
                                    String week = com.mob.tools.utils.R.toString(future_detail.get("week"));
                                    String date = com.mob.tools.utils.R.toString(future_detail.get("date"));
                                    String temperature = com.mob.tools.utils.R.toString(future_detail.get("temperature"));
                                    String windy = com.mob.tools.utils.R.toString(future_detail.get("windy"));
                                    String dayTime = com.mob.tools.utils.R.toString(future_detail.get("dayTime"));
                                    String night = com.mob.tools.utils.R.toString(future_detail.get("night"));
                                weather_future_detail1.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
//                                if (dayTime == "多云" || dayTime.equals("多云")){
//                                    main_weather_future_card1.setBackgroundResource(R.mipmap.pic_weather_cloud);
//                                }else if (dayTime == "阴" || dayTime.equals("阴")){
//                                    main_weather_future_card1.setBackgroundResource(R.mipmap.pic_weather_overcast);
//                                }else if (dayTime == "晴" || dayTime.equals("晴")){
//                                    main_weather_future_card1.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }else if (dayTime.contains("雨")){
//                                    main_weather_future_card1.setBackgroundResource(R.mipmap.pic_weather_rain);
//                                }else if (dayTime.contains("雪")){
//                                    main_weather_future_card1.setBackgroundResource(R.mipmap.pic_weather_snow);
//                                }else {
//                                    main_weather_future_card1.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }
                                HashMap<String, Object> future_detail2 = future.get(1);
                                week = com.mob.tools.utils.R.toString(future_detail2.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail2.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail2.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail2.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail2.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail2.get("night"));
                                weather_future_detail2.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
//                                if (dayTime == "多云" || dayTime.equals("多云")){
//                                    main_weather_future_card2.setBackgroundResource(R.mipmap.pic_weather_cloud);
//                                }else if (dayTime == "阴" || dayTime.equals("阴")){
//                                    main_weather_future_card2.setBackgroundResource(R.mipmap.pic_weather_overcast);
//                                }else if (dayTime == "晴" || dayTime.equals("晴")){
//                                    main_weather_future_card2.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }else if (dayTime.contains("雨")){
//                                    main_weather_future_card2.setBackgroundResource(R.mipmap.pic_weather_rain);
//                                }else if (dayTime.contains("雪")){
//                                    main_weather_future_card2.setBackgroundResource(R.mipmap.pic_weather_snow);
//                                }else {
//                                    main_weather_future_card2.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }
                                HashMap<String, Object> future_detail3 = future.get(2);
                                week = com.mob.tools.utils.R.toString(future_detail3.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail3.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail3.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail3.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail3.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail3.get("night"));
                                weather_future_detail3.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
//                                if (dayTime == "多云" || dayTime.equals("多云")){
//                                    main_weather_future_card3.setBackgroundResource(R.mipmap.pic_weather_cloud);
//                                }else if (dayTime == "阴" || dayTime.equals("阴")){
//                                    main_weather_future_card3.setBackgroundResource(R.mipmap.pic_weather_overcast);
//                                }else if (dayTime == "晴" || dayTime.equals("晴")){
//                                    main_weather_future_card3.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }else if (dayTime.contains("雨")){
//                                    main_weather_future_card3.setBackgroundResource(R.mipmap.pic_weather_rain);
//                                }else if (dayTime.contains("雪")){
//                                    main_weather_future_card3.setBackgroundResource(R.mipmap.pic_weather_snow);
//                                }else {
//                                    main_weather_future_card3.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }
                                HashMap<String, Object> future_detail4 = future.get(3);
                                week = com.mob.tools.utils.R.toString(future_detail4.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail4.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail4.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail4.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail4.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail4.get("night"));
                                weather_future_detail4.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
//                                if (dayTime == "多云" || dayTime.equals("多云")){
//                                    main_weather_future_card4.setBackgroundResource(R.mipmap.pic_weather_cloud);
//                                }else if (dayTime == "阴" || dayTime.equals("阴")){
//                                    main_weather_future_card4.setBackgroundResource(R.mipmap.pic_weather_overcast);
//                                }else if (dayTime == "晴" || dayTime.equals("晴")){
//                                    main_weather_future_card4.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }else if (dayTime.contains("雨")){
//                                    main_weather_future_card4.setBackgroundResource(R.mipmap.pic_weather_rain);
//                                }else if (dayTime.contains("雪")){
//                                    main_weather_future_card4.setBackgroundResource(R.mipmap.pic_weather_snow);
//                                }else {
//                                    main_weather_future_card4.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }
                                HashMap<String, Object> future_detail5 = future.get(4);
                                week = com.mob.tools.utils.R.toString(future_detail5.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail5.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail5.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail5.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail5.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail5.get("night"));
                                weather_future_detail5.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
                                HashMap<String, Object> future_detail6 = future.get(5);
                                week = com.mob.tools.utils.R.toString(future_detail6.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail6.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail6.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail6.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail6.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail6.get("night"));
                                weather_future_detail6.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
                                HashMap<String, Object> future_detail7 = future.get(6);
                                week = com.mob.tools.utils.R.toString(future_detail7.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail7.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail7.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail7.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail7.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail7.get("night"));
                                weather_future_detail7.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
                                HashMap<String, Object> future_detail8 = future.get(7);
                                week = com.mob.tools.utils.R.toString(future_detail8.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail8.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail8.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail8.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail8.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail8.get("night"));
                                weather_future_detail8.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
                                HashMap<String, Object> future_detail9 = future.get(8);
                                week = com.mob.tools.utils.R.toString(future_detail9.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail9.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail9.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail9.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail9.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail9.get("night"));
                                weather_future_detail9.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
                                HashMap<String, Object> future_detail10 = future.get(9);
                                week = com.mob.tools.utils.R.toString(future_detail10.get("week"));
                                date = com.mob.tools.utils.R.toString(future_detail10.get("date"));
                                temperature = com.mob.tools.utils.R.toString(future_detail10.get("temperature"));
                                windy = com.mob.tools.utils.R.toString(future_detail10.get("windy"));
                                dayTime = com.mob.tools.utils.R.toString(future_detail10.get("dayTime"));
                                night = com.mob.tools.utils.R.toString(future_detail10.get("night"));
                                weather_future_detail10.setText(week+"\n"+date+"\n温度变化："+temperature+"\n风力情况："+windy+"\n白天天气："+dayTime +"\n夜晚天气："+night);
//                                if (dayTime == "多云" || dayTime.equals("多云")){
//                                    main_weather_future_card5.setBackgroundResource(R.mipmap.pic_weather_cloud);
//                                }else if (dayTime == "阴" || dayTime.equals("阴")){
//                                    main_weather_future_card5.setBackgroundResource(R.mipmap.pic_weather_overcast);
//                                }else if (dayTime == "晴" || dayTime.equals("晴")){
//                                    main_weather_future_card5.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }else if (dayTime.contains("雨")){
//                                    main_weather_future_card5.setBackgroundResource(R.mipmap.pic_weather_rain);
//                                }else if (dayTime.contains("雪")){
//                                    main_weather_future_card5.setBackgroundResource(R.mipmap.pic_weather_snow);
//                                }else {
//                                    main_weather_future_card5.setBackgroundResource(R.mipmap.pic_weather_fine);
//                                }
//                                }


                                String local_weather = com.mob.tools.utils.R.toString(weather.get("weather"));
                                if (local_weather == "多云" || local_weather.equals("多云")){
                                    layout.setBackgroundResource(R.mipmap.pic_weather_cloud);
                                }else if (local_weather == "阴" || local_weather.equals("阴")){
                                    layout.setBackgroundResource(R.mipmap.pic_weather_overcast);
                                }else if (local_weather == "晴" || local_weather.equals("晴")){
                                    layout.setBackgroundResource(R.mipmap.pic_weather_fine);
                                }else if (local_weather.contains("雨")){
                                    layout.setBackgroundResource(R.mipmap.pic_weather_rain);
                                }else if (local_weather.contains("雪")){
                                    layout.setBackgroundResource(R.mipmap.pic_weather_snow);
                                }else {
                                    layout.setBackgroundResource(R.mipmap.pic_weather_fine);
                                }
                                String local_province = com.mob.tools.utils.R.toString(weather.get("province"));
                                String local_city = com.mob.tools.utils.R.toString(weather.get("city"));
                                String local_humidity = com.mob.tools.utils.R.toString(weather.get("humidity"));
                                String local_temperature = com.mob.tools.utils.R.toString(weather.get("temperature"));
                                String local_airCondition = com.mob.tools.utils.R.toString(weather.get("airCondition"));
                                String local_sunrise = com.mob.tools.utils.R.toString(weather.get("sunrise"));
                                String local_sunset = com.mob.tools.utils.R.toString(weather.get("sunset"));
                                String local_pollutionIndex = com.mob.tools.utils.R.toString(weather.get("pollutionIndex"));
                                String local_dressingIndex = com.mob.tools.utils.R.toString(weather.get("dressingIndex"));
                                String local_wind = com.mob.tools.utils.R.toString(weather.get("wind"));
                                String local_updateTime = com.mob.tools.utils.R.toString(weather.get("updateTime"));
                                String local_coldIndex = com.mob.tools.utils.R.toString(weather.get("coldIndex"));
                                String sub1 = local_updateTime.substring(8, 10);
                                String sub2 = local_updateTime.substring(10, 12);
                                String sub3 = local_updateTime.substring(12, 14);
                                String local_exerciseIndex = com.mob.tools.utils.R.toString(weather.get("exerciseIndex"));
                                String local_washIndex = com.mob.tools.utils.R.toString(weather.get("washIndex"));
                                if (local_city == edit.getText().toString().trim() || local_city.equals(edit.getText().toString().trim())){
                                    textloc.setText(local_province+":"+local_city);
                                    if (local_province == "北京" || local_province.equals("北京")|| local_province == "上海" || local_province.equals("上海")
                                            || local_province == "天津" || local_province.equals("天津") || local_province == "重庆" || local_province.equals("重庆")
                                            || local_province == "香港" || local_province.equals("香港") || local_province == "澳门" || local_province.equals("澳门")){
                                        textloc.setText(local_city);
                                    }
                                }else {
                                    textloc.setText(local_province + ":" + local_city + ":" + edit.getText().toString().trim());
                                    if (local_province == "北京"|| local_province == "上海" || local_province == "天津" || local_province == "重庆"){
                                        textloc.setText(local_city + ":" + edit.getText().toString().trim());
                                    }
                                }
                                weather_detail.setText("更新时间：     "+sub1+":"+sub2+":"+sub3+"\n实时温度：      "+local_temperature+"\n实时湿度：     "+local_humidity+"\n天气情况：     "+local_weather+
                                "\n实时风力：     "+local_wind+"\n空气质量状况：     "+local_airCondition+"\n空气污染指数：     "+local_pollutionIndex+
                                        "\n穿衣建议：     "+local_dressingIndex+"\n外出活动：     "+local_exerciseIndex+"\n感冒系数：     "+local_coldIndex+"\n洗车指数：     "+local_washIndex+"\n日出日落：     "+local_sunrise+"—"+local_sunset);

                                break;
                        }
                    }

                    @Override
                    public void onError(API api, int i, Throwable throwable) {

                    }
                });
                break;
        }
    }
}
