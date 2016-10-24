package com.treasure_ct.android_xt.studyfragment.loadingways.Loader;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

/**
 * http://ic.snssdk.com/neihan/stream/mix/v1/?content_type=-102&message_cursor=-1&loc_time=1432654641&latitude=40.0522901291784&longitude=116.23490963616668&city=%E5%8C%97%E4%BA%AC&count=30&screen_width=800&iid=2767929313&device_id=2757969807&ac=wifi&channel=baidu2&aid=7&app_name=joke_essay&version_code=400&device_platform=android&device_type=KFTT&os_api=15&os_version=4.0.3&openudid=b90ca6a3a19a78d6
 */
public class LoaderContacts extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingways_loader_main);
        listView = (ListView) findViewById(R.id.loader_listView);
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,
                null,new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);

        LoaderManager loader = getSupportLoaderManager();
        //初始化Loader，如果已存在则不进行创建
        //参数1，将要创建Loader的id，
        //参数2，Loader在创建的时候，传递的参数
        //参数3，回调接口，当Loader需要创建、销毁、数据加载完成时回调
        loader.initLoader(666,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> ret = null;
        switch (id) {
            case 666:
                //创建CursorLoader，直接自动加载内容提供者的数据
                ret = new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                        null,null,null,null);
                break;
        }
        return ret;
    }

    /**
     * 当Loader对象加载完数据之后，回调
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    //TODO:刷新数据
        /**
         * 4
         */
        switch (loader.getId()) {
            case 666:
                adapter.changeCursor(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        /**
         * 5
         */
        //可以根据Loader的ID，进行选定资源的释放
        switch (loader.getId()) {
            case 666:
                adapter.changeCursor(null);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_loader_menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.storage_loader_add:
                ContentResolver resolver = getContentResolver();
                ContentValues values = new ContentValues();
//                values.put();
                Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI,values);
                Toast.makeText(LoaderContacts.this, ""+uri, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
