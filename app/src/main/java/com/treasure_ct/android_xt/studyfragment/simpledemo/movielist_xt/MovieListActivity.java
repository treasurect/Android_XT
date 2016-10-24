package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.adapter.ML_BaseAdapter;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.ML_Entry;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.ML_Result;
import com.treasure_ct.network_xt.NetworkTask;
import com.treasure_ct.network_xt.Tools;

import java.util.ArrayList;

public class MovieListActivity extends Activity implements AbsListView.OnScrollListener, View.OnTouchListener, AdapterView.OnItemClickListener,NetworkTask.Callback<ML_Result> {
    private static final String TAG = "aaaa";
    private ListView listView;
    private ML_BaseAdapter adapter;
    private int firstVisibleItem;
    private float fingerY;
    private ML_Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_movielist);
        listView = (ListView) findViewById(R.id.ml_listview);
        adapter = new ML_BaseAdapter(this,new ArrayList<ML_Entry>());
        server = Tools.getInstance(ML_Server.class);
        server.getList().execute(this);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
        listView.setOnTouchListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onSuccess(ML_Result text) {
        adapter.addAll(text.getList());
    }

    @Override
    public void onFail(Exception e) {
        e.printStackTrace();
        Toast.makeText(MovieListActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        if (firstVisibleItem == 0){
            View view1 = view.getChildAt(0);
            int paddingTop = view.getPaddingTop();
            if (view1 != null){
                if (view1.getTop() == paddingTop){
                    Log.d(TAG, "onScroll: "+"到顶部了");
                }
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        View childAt = ((ViewGroup) v).getChildAt(0);
        if (childAt != null) {
            if(firstVisibleItem == 0 && childAt.getTop() == v.getPaddingTop()){
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_DOWN:
                        fingerY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (event.getY() > fingerY){
                            Toast.makeText(MovieListActivity.this, "开始刷新", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Minfo_Activity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
