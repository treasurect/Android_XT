package com.treasure_ct.android_xt.studyfragment.simpledemo.contact_backup;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BackupService extends Service implements Runnable{
    private FileWriter writer;
    private static final String TAG = "BackupService";
    public BackupService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: 1111111111111111111");
        Thread thread = new Thread(this);
        thread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void run() {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()){
                int index = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                if (index != -1){
                    String phoneNumber = cursor.getString(index);
                        Log.d(TAG, "run: ----------------------"+phoneNumber);
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)){
                        File directory = Environment.getExternalStorageDirectory();
//                        File directory1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        Log.d(TAG, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+directory.getAbsolutePath());
                        File file = new File(directory, "treasure1.txt");
                        if (file != null) {
                            try {
                                writer = new FileWriter(file, true);
                                writer.write(phoneNumber+"\n");
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
