package com.treasure_ct.android_xt.studyfragment.components.service.downloadfile;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DownloadfileIntentService extends IntentService {
    private static final String TAG = "Download";
    public DownloadfileIntentService() {
        super("DownloadfileIntentService");
    }

    /**
     * IntentService 内部包含一个线程，线程会一直处理Intent参数
     * 每一次startService，传递的Intent，都自动传递给线程，队列中保存，依次处理
     * 这个方法在子线程处理
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        if (url != null){
            //TODO:下载文件，保存文件
            Log.d(TAG, "onHandleIntent: "+url);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
