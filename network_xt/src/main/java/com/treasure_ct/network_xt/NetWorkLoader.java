package com.treasure_ct.network_xt;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by treasure on 2016.09.10.
 */

/**
 * 1.继承ASyncTaskLoader,确认好 返回的数据类型，泛型是结果类型 doinbackground的返回值
 */
public class NetWorkLoader extends AsyncTaskLoader<JSONObject>{
    private String url;
    public NetWorkLoader(Context context,String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {

        super.onStartLoading();
    }

    /**
     * 子线程加载数据的部分
     * @return
     */
    @Override
    public JSONObject loadInBackground() {
        JSONObject ret = null;
        byte[] bytes = HttpTool.doGet(url);
        //网络连接完成之后，考虑cancel reset abort的处理
        if (isLoadInBackgroundCanceled()){

        }else {
            if (isReset()) {
                    //不返回数据，但是可以考虑,把数据保存到本地
            } else {
                if (bytes != null) {
                    try {
                        String s = new String(bytes, "UTF-8");
                        ret = new JSONObject(s);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ret;
    }
}
