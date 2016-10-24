package com.treasure_ct.android_xt.studyfragment.simplecontrols;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simplecontrols.autocomplete.AutoStartWithAdapter;

import java.util.ArrayList;

/**
 * Created by treasure on 2016.08.27.
 */
public class AutoCompleteActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecontrols_autocomplete);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiautoComplete);

//        List<String> list = new ArrayList<>();
//        List<AutoEntry> list = new ArrayList<>();
//        list.add(new AutoEntry("北京","beijing"));
//        list.add(new AutoEntry("山东","shandong"));
//        list.add(new AutoEntry("上海","shanghai"));
//        list.add(new AutoEntry("广东","guangdong"));
//        list.add(new AutoEntry("浙江","zhejiang"));
//        list.add(new AutoEntry("江苏","jiangsu"));
//        list.add(new AutoEntry("天津","tianjin"));
//        list.add(new AutoEntry("辽宁","liaoning"));
//        list.add(new AutoEntry("山西","shanxi"));
//        list.add(new AutoEntry("河南","henan"));
//        list.add(new AutoEntry("河北","hebei"));
//        list.add(new AutoEntry("四川","sichuan"));
        ArrayList<String> list = new ArrayList<>();
        list.add("北京");
        list.add("上海");
        list.add("山东");
        list.add("广东");
        list.add("浙江");
        list.add("江苏");
        list.add("天津");
        list.add("辽宁");
        list.add("山西");
        list.add("河南");
        list.add("河北");
        list.add("四川");
//        AutoAdapter autoAdapter = new AutoAdapter(this,list);
        AutoStartWithAdapter autoStartWithAdapter = new AutoStartWithAdapter(this,list);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        autoCompleteTextView.setAdapter(autoStartWithAdapter);
        multiAutoCompleteTextView.setAdapter(autoStartWithAdapter);
        MultiAutoCompleteTextView.CommaTokenizer commaTokenizer = new MultiAutoCompleteTextView.CommaTokenizer();
        commaTokenizer.terminateToken(",");
        multiAutoCompleteTextView.setTokenizer(commaTokenizer);
    }
}
