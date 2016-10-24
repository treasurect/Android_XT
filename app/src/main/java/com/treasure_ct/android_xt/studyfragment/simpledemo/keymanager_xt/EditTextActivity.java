package com.treasure_ct.android_xt.studyfragment.simpledemo.keymanager_xt;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class EditTextActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText key_title,key_web,key_username,key_userpass,key_other;
    private SQLiteDatabase database;
    private Button commit,cancel;
    private long info;
    private ContentResolver resolver;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpeldemo_keymanager_edit_text);
        key_title = (EditText) findViewById(R.id.key_title_edit);
        key_web = (EditText) findViewById(R.id.key_web_edit);
        key_username = (EditText) findViewById(R.id.key_username_edit);
        key_userpass = (EditText) findViewById(R.id.key_userpass_edit);
        key_other = (EditText) findViewById(R.id.key_other_edit);
        commit = (Button) findViewById(R.id.key_edit_commit);
        cancel = (Button) findViewById(R.id.key_edit_cancel);
        commit.setText("保存");
        commit.setTag(true);

//        DBHelper helper = new DBHelper(this);
//        database = helper.getWritableDatabase();
         resolver = getContentResolver();
         uri = Uri.parse("content://treasure/keymg");
        final Intent intent = getIntent();
        info = intent.getLongExtra("info", -1);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Boolean) commit.getTag())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditTextActivity.this);
                    builder.setTitle("确认框")
                            .setIcon(R.mipmap.loading)
                            .setMessage("您确定不保存内容吗");
                    builder.setPositiveButton("不用了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("再看看", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        if (info != -1){
            key_title.setFocusableInTouchMode(false);
            key_web.setFocusableInTouchMode(false);
            key_username.setFocusableInTouchMode(false);
            key_userpass.setFocusableInTouchMode(false);
            key_other.setFocusableInTouchMode(false);
            commit.setText("编辑");
            commit.setTag(false);
            cancel.setVisibility(View.GONE);
            key_web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((Boolean) commit.getTag())) {
                        String url = key_web.getText().toString();
                        Uri uri = Uri.parse(url);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));

                    } else {
                        Toast.makeText(EditTextActivity.this, "请在查看状态下再进行点击", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Cursor cursor = resolver.query(uri,null, "_id=?", new String[]{Long.toString(info)}, null);
            if (cursor!=null) {
                if (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex("title");
                    if (index!=-1) {
                        String title = cursor.getString(index);
                        key_title.setText(title);
                    }
                    index = cursor.getColumnIndex("web");
                    if (index!=-1) {
                        String web = cursor.getString(index);
                        key_web.setText(web);
                    }
                    index = cursor.getColumnIndex("username");
                    if (index!=-1) {
                        String username = cursor.getString(index);
                        key_username.setText(username);
                    }
                    index = cursor.getColumnIndex("userpass");
                    if (index!=-1) {
                        String userpass = cursor.getString(index);
                        key_userpass.setText(userpass);
                    }
                    index = cursor.getColumnIndex("other");
                    if (index!=-1) {
                        String other = cursor.getString(index);
                        key_other.setText(other);
                    }
                }
                cursor.close();
            }
        }
        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!(Boolean) commit.getTag()) {
            cancel.setVisibility(View.VISIBLE);
            commit.setText("保存");
            commit.setTag(true);
            key_title.setFocusableInTouchMode(true);
            key_web.setFocusableInTouchMode(true);
            key_username.setFocusableInTouchMode(true);
            key_userpass.setFocusableInTouchMode(true);
            key_other.setFocusableInTouchMode(true);
        } else {
//         title = null;
            String title = key_title.getText().toString();
            String web = key_web.getText().toString();
            String name = key_username.getText().toString();
            String pass = key_userpass.getText().toString();
            String other = key_other.getText().toString();
            if ((title != null && (!title.equals(""))) && (web != null && (!web.equals(""))) && (name != null &&
                    (!name.equals(""))) && (pass != null && (!pass.equals(""))) && (other != null && (!other.equals("")))) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("title", title);
                contentValues.put("web", web);
                contentValues.put("username", name);
                contentValues.put("userpass", pass);
                contentValues.put("other", other);
                if (info != -1) {
                    int update = resolver.update(uri, contentValues, "_id=?", new String[]{Long.toString(info)});
//                    int update = database.update("keymg", contentValues, "_id=?", new String[]{Long.toString(info)});
                    if (update > 0) {
                        Toast.makeText(EditTextActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditTextActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Uri insert = resolver.insert(uri, contentValues);
//                    long l = database.insert("keymg", null, contentValues);
//                    if (l != -1) {
//                        Toast.makeText(EditTextActivity.this, "添加成功，数据序号：" + l, Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(EditTextActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
//                    }
                    Toast.makeText(EditTextActivity.this, String.valueOf(insert), Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(EditTextActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
