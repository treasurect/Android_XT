package com.treasure_ct.android_xt.studyfragment.components.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用：
 * 1） ContentResolver,类似Socket的客户端
 * 2） 需要提供Uri对象，可以看做网址，Android提供很多常量类
 * 3)访问Android内部的内容提供者，需要声明权限
 * 4)API23以上，所有内容提供者都需要动态权限检查，否者执行不了
 */
public class CallRecordsActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String>mAdapter;
    private List<String>mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_contentprovider_callrecords);
        listView = (ListView) findViewById(R.id.call_records_listView);
        mList = new ArrayList<>();
//        ContentResolver resolver = this.getContentResolver();
        /**\
         * 动态权限检查
         */
        if (Build.VERSION.SDK_INT >=16) {//API版本判断
            int state = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
            if(state == PackageManager.PERMISSION_GRANTED){
                //所有常量类，类中的CONTENT_URI就可以用来内容提供者的访问
                //2.需要返回的列
                //3.where语句
                //4.where条件参数
                //
//                int state = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
                //TODO:获取通话记录
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
                if (cursor!=null) {
                    while (cursor.moveToNext()){
                        int index = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                        if (index != -1) {
                            String number = cursor.getString(index);
                            Log.d("ContentProviderActivity", "number:: "+number);
                            mList.add(number);
                        }
                    }
                    cursor.close();
                }
            }else {
                //需要申请,会回调当前activity的方法
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},998);
            }
        }

        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mList);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 998) {
            //参数2  每一个元素代表一个权限，对应参数3中的权限设置，是否允许
            if (permissions[0].equals(Manifest.permission.READ_CALL_LOG)){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    int state = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
                    //TODO:获取通话记录
                    ContentResolver resolver = getContentResolver();
                    Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
                    if (cursor!=null) {
                        while (cursor.moveToNext()){
                            int index = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                            if (index != -1) {
                                String number = cursor.getString(index);
                                Log.d("ContentProviderActivity", "number:: "+number);
                            }
                        }
                        cursor.close();
                    }
                }
            }
        }
    }
}
