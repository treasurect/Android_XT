package com.treasure_ct.android_xt.studyfragment.simpledemo.keymanager_xt.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.treasure_ct.doc_operate_xt.database.DbHelper;

public class MyContentProvider extends ContentProvider {
    public static final UriMatcher matcher;
    public static final int CODE_KEY = 1;
    static {
        matcher = new UriMatcher(0);
        matcher.addURI("*","/keymg",CODE_KEY);
    }
    private DbHelper mDBHelper;
    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri ret = null;
        int match = matcher.match(uri);
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        switch (match) {
            case CODE_KEY:
                long insert = database.insert("keymg", null, values);
                ret = ContentUris.withAppendedId(uri,insert);
        }
        database.close();
        return ret;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int ret = 0;
        int match = matcher.match(uri);
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        switch (match) {
            case CODE_KEY:
                ret = database.delete("keymg",selection,selectionArgs);
                break;
        }
        database.close();
        return ret;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
       int ret = 0;
        int match = matcher.match(uri);
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        switch (match) {
            case CODE_KEY:
                ret = database.update("keymg",values,selection,selectionArgs);
                break;
        }
        return ret;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor ret = null;
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        int match = matcher.match(uri);
        switch (match){
            case CODE_KEY:
                ret = database.query("keymg",projection,selection,selectionArgs,null,null,sortOrder);
                break;
        }
        return ret;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
