package com.treasure_ct.android_xt.studyfragment.components.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.treasure_ct.doc_operate_xt.database.DbHelper;

public class MyContentProvider extends ContentProvider {
    private static final UriMatcher uriMatcher;
    public static final int CODE_COST = 1;
    public static final int CODE_INCOME= 2;
    static {
        uriMatcher = new UriMatcher(0);
        //添加Uri的匹配規則，實現uri對應判斷是哪個表的操作
        uriMatcher.addURI("*","/cost",CODE_COST);
        uriMatcher.addURI("*","/income",CODE_INCOME);
    }
    private DbHelper mHelper;
    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        // 数据库的初始化
        mHelper = new DbHelper(getContext());
        //必须返回true
        return true;
    }

    /**
     * 添加方法，对外提供接口，可以让其他程序向当前程序的数据库添加数据内容
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri ret = null;
        int match = uriMatcher.match(uri);
        //所有的數據庫打開操作都應該在增刪改查中完成，不允許在onCreate中操作
        SQLiteDatabase database = mHelper.getWritableDatabase();
        mHelper.getWritableDatabase();
        switch (match) {
            case CODE_COST:
                long cost = database.insert("cost", null, values);
                //數據庫添加完成之後返回的ID，必須和Uri參數拼接在一起在返回
                ret = ContentUris.withAppendedId(uri,cost);
                break;
            case CODE_INCOME:
                break;
        }
        return ret;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int ret = 0;
        int match = uriMatcher.match(uri);
        //所有的數據庫打開操作都應該在增刪改查中完成，不允許在onCreate中操作
        SQLiteDatabase database = mHelper.getWritableDatabase();
        mHelper.getWritableDatabase();
        switch (match) {
//            case CODE_COST:
//                long cost = database.insert("cost", selection, selectionArgs);
//                //數據庫添加完成之後返回的ID，必須和Uri參數拼接在一起在返回
//                ret = ContentUris.withAppendedId(uri,cost);
//                break;
//            case CODE_INCOME:
//                break;
        }
        return ret;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
       Cursor ret = null;
        SQLiteDatabase database = mHelper.getReadableDatabase();
        return ret;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
