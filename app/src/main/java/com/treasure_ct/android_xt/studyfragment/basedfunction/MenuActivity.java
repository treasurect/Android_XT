package com.treasure_ct.android_xt.studyfragment.basedfunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.MainActivity;
import com.treasure_ct.android_xt.R;

import java.util.ArrayList;

/**
 * Created by treasure on 2016.09.05.
 */
public class MenuActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedfuncton_menu);
        listView = (ListView) findViewById(R.id.basedfunction_listview);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("新文本"+(i+1));
        }
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }

    //编写菜单的xml
    //Activity的重写方法，进行菜单加载
    //activity 重写方法 ，实现菜单点击事件处理
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater用于把菜单的xml文件，加再进来，添加到menu中
        getMenuInflater().inflate(R.menu.basedfunction_menu,menu);
        return true;
    }

    /**
     * OptionsMenu菜单项点击时的事件回调
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.basedfunction_action_settings:
                Toast.makeText(MenuActivity.this, "我被点了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.basedfunction_action_icon:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.basedfunction_action_help:
                Toast.makeText(MenuActivity.this, "查看帮助", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
