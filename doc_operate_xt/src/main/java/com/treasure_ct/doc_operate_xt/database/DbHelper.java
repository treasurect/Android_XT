package com.treasure_ct.doc_operate_xt.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by treasure on 2016.09.08.
 */
public class DbHelper extends SQLiteOpenHelper{
    private static String create_table = "CREATE TABLE keymg (_id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", title TEXT NOT NULL, web TEXT, username TEXT NOT NULL UNIQUE, userpass TEXT NOT NULL, other TEXT);";
    public DbHelper(Context context) {
        super(context, "key_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
