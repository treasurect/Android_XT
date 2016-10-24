package com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class ActionBar_Search_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedfunction_actionbar_search);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_search_item,menu);
        //设置搜索输入框的步骤
        //1.查找指定的MenuItem
        MenuItem item = menu.findItem(R.id.actionbar_search);
        //2.设置SearchView
//     2   View view = SearchViewCompat.newSearchView(this);
//      1  item.setActionView(view);
//     2   MenuItemCompat.setActionView(item, view);
        View view = MenuItemCompat.getActionView(item);
        if (view != null){
             searchView = (SearchView) view;

            //设置 SearchView 的查询回调接口
            searchView.setOnQueryTextListener(this);

//            //在搜索输入框没有显示的时候，点击Action，回调这个接口，冰洁显示输入框
//            searchView.setOnSearchClickListener();

            //档自动补全的 的内容被选中，回调接口
//            searchView.setOnSuggestionListener();

            //可以设置搜索的自动补全功能，也可以搜索历史数据
//            searchView.setSuggestionsAdapter();
        }
        return true;
    }

    /**
     * 当用户在输入法中点击搜索按钮时，调用这个方法，发起实际的搜索功能。
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(ActionBar_Search_Activity.this, "su= b   "+query, Toast.LENGTH_SHORT).show();
        searchView.clearFocus();
        return true;
    }

    /**
     * 每一次输入字符都会回调这个方法，联想功能
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(ActionBar_Search_Activity.this, "chhhhhhhh:  "+newText, Toast.LENGTH_SHORT).show();
        return true;
    }

}
