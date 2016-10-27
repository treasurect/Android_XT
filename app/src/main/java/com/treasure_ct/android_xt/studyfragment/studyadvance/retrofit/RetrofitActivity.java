package com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.ApiListModel;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.ApiModel;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.ClassifyListModel;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.ClassifyModel;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.model.InfoModel;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.services.SuperService;
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.services.TngouService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RetrofitActivity";

    //http://www.tngou.net/tnfs/api/classify
    private TngouService mTngouService;

    private SuperService mSuperService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyadavance_retrofit);
        Button getClassify = (Button) findViewById(R.id.getClassify);
        Button getApiList = (Button) findViewById(R.id.getApiList);
        Button version = (Button) findViewById(R.id.getServerVersion);
        Button btnPostLogin = (Button) findViewById(R.id.btnPostLogin);
        Button btnPutJson = (Button) findViewById(R.id.btnPutJson);
        Button btnUploadFile = (Button) findViewById(R.id.btnUploadFile);

        getClassify.setOnClickListener(this);
        getApiList.setOnClickListener(this);
        version.setOnClickListener(this);
        btnPostLogin.setOnClickListener(this);
        btnPutJson.setOnClickListener(this);
        btnUploadFile.setOnClickListener(this);

        if (mTngouService == null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("http://www.tngou.net/");
            //支持服务器返回的数据转换为字符串
            builder.addConverterFactory(ScalarsConverterFactory.create());
            //添加 GSON 转换对象的支持
            builder.addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            //创建TngouService 接口的实现类，用于发起实际的网络请求
            mTngouService = retrofit.create(TngouService.class);
        }
        if (mSuperService == null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("http://10.0.153.115:8080/rest/api/");
            builder.addConverterFactory(ScalarsConverterFactory.create());
            builder.addConverterFactory(GsonConverterFactory.create());
            Retrofit build = builder.build();
            mSuperService = build.create(SuperService.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getClassify:
                //调用Service的方法
                //方法返回 Call 对象  用于实际的网络请求  并且返回结果
                Call<ClassifyListModel> call = mTngouService.getClassifyList();
                //同步代码
//                call.execute();
                call.enqueue(new Callback<ClassifyListModel>() {
                    @Override
                    public void onResponse(Call<ClassifyListModel> call, Response<ClassifyListModel> response) {
                        //请求成功 返回数据  注意：！！！ 不代表状态码为200，也包含 405， 400 等其他状态码
                        if (response.isSuccessful()){
                            ClassifyListModel body = response.body();
                            List<ClassifyModel> tngou = body.getTngou();
                            for (ClassifyModel classifyModel : tngou) {
                                String title = classifyModel.getTitle();
                                Log.d(TAG, "onResponse: getClassify " + title);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassifyListModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
                break;
            case R.id.getApiList:
                //服务对象
                Call<ApiListModel> apiCall = mTngouService.getApiListById("1");
                apiCall.enqueue(new Callback<ApiListModel>() {
                    @Override
                    public void onResponse(Call<ApiListModel> call, Response<ApiListModel> response) {
                        if (response.isSuccessful()){
                            ApiListModel body = response.body();
                            List<ApiModel> tngou = body.getTngou();
                            for (ApiModel apiModel : tngou) {
                                String title = apiModel.getTitle();
                                Log.d(TAG, "onResponse: getApiList"+title);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiListModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
                break;
            case R.id.getServerVersion:
                Call<String> name = mSuperService.getVersionItem("name");
                name.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            String body = response.body();
                            Log.d(TAG, "onResponse: getServerVersion" + body);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
                break;
            case R.id.btnPostLogin:
                Call<String> login = mSuperService.login("admin", "123");
                login.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        int code = response.code();
                        String message = response.message();
                        Log.d(TAG, "code: " + code);
                        Log.d(TAG, "message: " + message);
                        if (response.isSuccessful()){
                            String body = response.body();
                            Log.d(TAG, "onResponse: body" + body);
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
                break;
            case R.id.btnPutJson:
                InfoModel info = new InfoModel();
                info.setAuthor("vhly");
                info.setName("超级服务器");
                info.setUpdateTime(Long.toString(System.currentTimeMillis()));
                info.setVersion("1.0");
                Call<String> updateInfo = mSuperService.updateInfo(info);
                updateInfo.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            String body = response.body();
                            Log.d(TAG, "onResponse: body"+body);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
                break;
            case R.id.btnUploadFile:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200){
            if (resultCode == Activity.RESULT_OK){
                Bitmap bmp = data.getParcelableExtra("data");
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG,100,bos);
                byte[] byteArray = bos.toByteArray();
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //准备RequestBody
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), byteArray);
                //提交上传文件
                Call<String> call = mSuperService.uploadFile(requestBody);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            String body = response.body();
                            Log.d(TAG, "onResponse: body" + body);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
            }
        }
    }
}
