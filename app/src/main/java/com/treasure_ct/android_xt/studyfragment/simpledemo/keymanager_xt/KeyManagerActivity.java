package com.treasure_ct.android_xt.studyfragment.simpledemo.keymanager_xt;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

public class KeyManagerActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
//    private EditText key_title,key_web,key_username,key_userpass,key_other;
//    private Button key_btn;
    private ListView listView;
    private SQLiteDatabase database;
    private Uri uri;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        key_title = (EditText) findViewById(R.id.key_title);
//        key_web = (EditText) findViewById(R.id.key_web);
//        key_username = (EditText) findViewById(R.id.key_username);
//        key_userpass = (EditText) findViewById(R.id.key_userpass);
//        key_other = (EditText) findViewById(R.id.key_other);
//        key_btn = (Button) findViewById(R.id.key_commit);
        listView = (ListView) findViewById(R.id.key_manager_listView);
         resolver = getContentResolver();
         uri = Uri.parse("content://treasure/keymg");
         cursor = resolver.query(uri, null, null, null, null);

//        DBHelper helper = new DBHelper(this);
//        database = helper.getWritableDatabase();
//        Cursor cursor = database.query("keymg", null, null, null, null, null, "username desc");
        if (cursor!=null){
            adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor
                    ,new String[]{"username"},new int[]{android.R.id.text1},adapter.FLAG_REGISTER_CONTENT_OBSERVER);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
//        listView.setOnCreateContextMenuListener(this);
//        key_btn.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        String title = key_title.getText().toString();
//        String web = key_web.getText().toString();
//        String name = key_username.getText().toString();
//        String pass = key_userpass.getText().toString();
//        String other = key_other.getText().toString();
//        if(title!=null && web!=null && name!=null && pass!=null&& other != null ){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("title",title);
//            contentValues.put("web",web);
//            contentValues.put("username",name);
//            contentValues.put("userpass",pass);
//            contentValues.put("other",other);
//            long l = database.insert("keymg", null, contentValues);
//            Toast.makeText(KeyManagerActivity.this, "添加成功，数据序号："+l, Toast.LENGTH_SHORT).show();
//            Cursor cursor = database.query("keymg", null,null, null, null, null, "username desc");
//            adapter.changeCursor(cursor);
//            adapter.notifyDataSetChanged();
//        }
//
//    }

    @Override
    protected void onResume() {
        cursor = resolver.query(uri, null, null, null, null);
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_simpledemo_keymanager_menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.key_insert:
                Intent intent = new Intent(this, EditTextActivity.class);
//                intent.putExtra("info",(long)1000);
                startActivity(intent);
                break;
            case R.id.reset_password:
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
        }
        return true;
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.context_item,menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.edit_key:
//                Intent intent = new Intent(this, EditTextActivity.class);
//                intent.putExtra("info",Long.toString(item.getItemId()));
//                startActivity(intent);
//                break;
//            case R.id.del_key:
//                break;
//        }
//        return true;
//    }

    @Override
    protected void onDestroy() {
        adapter.changeCursor(null);
        super.onDestroy();
    }
//
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EditTextActivity.class);
        intent.putExtra("info",id);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        resolver.delete(uri, "_id=?",new String[]{Long.toString(id)});
        Cursor cursor = resolver.query(uri, null, null, null, null);
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
        return false;
    }
}























