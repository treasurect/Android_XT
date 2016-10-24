package com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class ToolBarActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
        private SearchView searchView;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedfunction_toolbar);
        //1.查找ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //2.替换Activity的ActionBar
        setSupportActionBar(toolbar);
        //3.要将activity的theme   调整为NoTitleBar

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_toolbar_item,menu);
//        MenuItem item = menu.findItem(R.id.toolbar_search);
//        View view = MenuItemCompat.getActionView(item);
//         actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        if (view != null){
//             searchView = (SearchView) view;
//            searchView.setOnQueryTextListener(this);
//        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(ToolBarActivity.this, "sub == "+query, Toast.LENGTH_SHORT).show();
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(ToolBarActivity.this, "change == "+newText, Toast.LENGTH_SHORT).show();
        return true;
    }
}
