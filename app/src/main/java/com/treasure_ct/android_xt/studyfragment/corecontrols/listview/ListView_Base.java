package com.treasure_ct.android_xt.studyfragment.corecontrols.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.adapter.MyBaseAdapter;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.model.Base_Entry;
import com.treasure_ct.android_xt.studyfragment.loadingways.asynctask.AsyncTask_NetWorkTask;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.model.Base_Result;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.server.Base_Server;
import com.treasure_ct.network_xt.Tools;

import java.util.ArrayList;

/**
 * Created by treasure on 2016.09.02.
 */

public class ListView_Base extends AppCompatActivity implements AdapterView.OnItemClickListener,AsyncTask_NetWorkTask.CallBack<Base_Result> {
    private ListView listView;
    private MyBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarycontrols_listview_base);
        listView = (ListView) findViewById(R.id.baselistview);
//        List<Base_Entry>list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            list.add(new Base_Entry("有一条数据"+i,"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1394704243,2048269785&fm=111&gp=0.jpg"));
//        }
        adapter = new MyBaseAdapter(this, new ArrayList<Base_Entry>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        Base_Server server = Tools.getInstance(Base_Server.class);
        server.getList(0,1,20).execute(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getAdapter().getItem(position);
        if (item instanceof Base_Entry){
            Base_Entry entry = (Base_Entry) item;
            Toast.makeText(ListView_Base.this, entry.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Base_Result base_result) {
        adapter.addAll(base_result.getList());
    }

    @Override
    public void onFail(Exception e) {
        e.printStackTrace();
        Toast.makeText(ListView_Base.this, "服务器错误", Toast.LENGTH_SHORT).show();
    }
}

