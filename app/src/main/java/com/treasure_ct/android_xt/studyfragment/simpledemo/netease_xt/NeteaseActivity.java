package com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.entries.Netease_Entry;
import com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.adapter.Netease_Adapter;
import com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.entries.Netease_Result;
import com.treasure_ct.network_xt.NetworkTask;
import com.treasure_ct.network_xt.Tools;

import java.util.ArrayList;

public class NeteaseActivity extends Activity implements NetworkTask.Callback<Netease_Result>, AdapterView.OnItemClickListener {
    private ListView listView;
    private NeteaseServer server;
    private Netease_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_netease);
        listView = (ListView) findViewById(R.id.netease_listview);
        server = Tools.getInstance(NeteaseServer.class);
        server.getHeadline(0,100).execute(this);
        adapter = new Netease_Adapter(this,new ArrayList<Netease_Entry>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onSuccess(Netease_Result text) {
        adapter.addAll(text.getData());
    }

    @Override
    public void onFail(Exception e) {
        e.printStackTrace();
        Toast.makeText(NeteaseActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, NewsInfoActivtiy.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
