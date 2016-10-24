package com.treasure_ct.android_xt.studyfragment.loadingways.Loader;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.network_xt.NetWorkLoader;

import org.json.JSONObject;

import java.util.ArrayList;

public class LoaderDuanzi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<JSONObject>{
//    private Button btn1;
    private ListView listView;
    private SwipeRefreshLayout swipelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_duanzi__loader);
        listView = (ListView) findViewById(R.id.duanzi_listview);
        ArrayList<String> list = new ArrayList<>();
//        btn1 = (Button) findViewById(R.id.duanzi_loader_btn);
        for (int i = 0; i < 100; i++) {
            list.add("-------------"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        swipelayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipelayout.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
        Bundle bundle = new Bundle();getSupportLoaderManager().initLoader(555,bundle,this);
//        btn1.setOnClickListener(this);
    }

    @Override
    public Loader<JSONObject> onCreateLoader(int id, Bundle args) {
        String url = null;
        if (args != null) {
            url = args.getString("url");
        }
        return new NetWorkLoader(this,url);
    }

    @Override
    public void onLoadFinished(Loader<JSONObject> loader, JSONObject data) {
        if (data != null){
            Log.d("----------------------", "onLoadFinished: "+data.toString());
        }
    }

    @Override
    public void onLoaderReset(Loader<JSONObject> loader) {

    }
}
