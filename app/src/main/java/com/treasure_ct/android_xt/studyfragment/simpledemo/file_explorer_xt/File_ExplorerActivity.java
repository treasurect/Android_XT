package com.treasure_ct.android_xt.studyfragment.simpledemo.file_explorer_xt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simpledemo.file_explorer_xt.adapter.FileAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class File_ExplorerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView listView;
    private List<File> list;
    private List<File> list2;
    private FileAdapter adapter;
    private TextView re;
    private File parentFile;
    private int i;

    //    private File file1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo_fileexplorer_main);
        listView = (ListView) findViewById(R.id.ex_listview);
        re = (TextView) findViewById(R.id.return_parent);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        //获取 手机 / 目录下面的所有内容
        parentFile = new File("/");
        File[] files = getSubFiles(parentFile);
//        file1 = new File("..");
//        list.add(file1);
        for (File file : files) {
            if (file.isDirectory()){
                list.add(file);
                Collections.sort(list);
            }
//            list.add(file);
        }
        for (i = files.length - 1;i>=0;i--) {
            if (!files[i].isDirectory()){
                if (files[i]!=null) {
                    list2.add(files[i]);
                }
            }
//            list.add(file);
        }
        if (list2!=null) {
            list.addAll(list2);
        }
        adapter = new FileAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        re.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simpledemo_fileexplorer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.file_register:
                startActivity(new Intent(this,Register_Activity.class));
                finish();
                break;
        }
        return true;
    }

    private static File[] getSubFiles(File dir){
        File[] ret = null;
        if (dir != null && dir.canRead() && dir.isDirectory()){
            ret = dir.listFiles();
        }
        return ret;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File file = list.get(position);
        parentFile = file;
        if (file.isDirectory()) {
            File[] files = getSubFiles(file);
            if (files != null) {
                list.clear();
//                list.add(file1);

                for (File file2 : files) {
                    if (file2.isDirectory()){
                        list.add(file2);
                        Collections.sort(list);
                    }
//            list.add(file);
                }
                for (i = files.length - 1;i>=0;i--) {
                    if (!files[i].isDirectory()){
                        if (files[i]!=null) {
                            list2.add(files[i]);
                        }
                    }
//            list.add(file);
                }
                if (list2!=null) {
                    list.addAll(list2);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_parent:
                File parentFile1 = parentFile.getParentFile();
                File[] subFiles = getSubFiles(parentFile1);
                if (subFiles!=null){
                    parentFile = parentFile.getParentFile();
                    list.clear();
                    for (File subFile : subFiles) {
                        if (subFile.isDirectory()){
                            list.add(subFile);
                            Collections.sort(list);
                        }
//            list.add(file);
                    }
                    for (i = subFiles.length - 1;i>=0;i--) {
                        if (!subFiles[i].isDirectory()){
                            if (subFiles[i]!=null) {
                                list2.add(subFiles[i]);
                            }
                        }
//            list.add(file);
                    }
                    if (list2!=null) {
                        list.addAll(list2);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        File parentFile1 = parentFile.getParentFile();
        File[] subFiles = getSubFiles(parentFile1);
        if (subFiles!=null){
            parentFile = parentFile.getParentFile();
            list.clear();
            for (File subFile : subFiles) {
                if (subFile.isDirectory()){
                    list.add(subFile);
                    Collections.sort(list);
                }
//            list.add(file);
            }
            for (i = subFiles.length - 1;i>=0;i--) {
                if (!subFiles[i].isDirectory()){
                    if (subFiles[i]!=null) {
                        list2.add(subFiles[i]);
                    }
                }
//            list.add(file);
            }
            if (list2!=null) {
                list.addAll(list2);
            }
            adapter.notifyDataSetChanged();
        }
        if (parentFile.getParentFile() == null){
            finish();
        }
    }
}
