package com.treasure_ct.android_xt.studyfragment.simplecontrols;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by treasure on 2016.08.26.
 */
public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner,spinner_1,spinner_2;
    private Button btn;
    private ArrayAdapter<String> adapter,adapter_1,adapter_2;
    private ArrayList<String> list,list_1,list_2;
    private static final String[] PROVINCES = {"北京","山东","河南"};
    private static final String[][] CITIES = {{"北京"},{"烟台","青岛"},{"郑州","洛阳"}};
    private static final String[][][] AREAS = {{{"海淀区","朝阳区"}},{{"莱阳市","莱山区"},{"崂山区","莱西市"}},{{"A","B"},{}}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecontrols_spinner);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner_1 = (Spinner)findViewById(R.id.spinner_1);
        spinner_2 = (Spinner)findViewById(R.id.spinner_2);
        btn = ((Button) findViewById(R.id.btn));
         list = new ArrayList<>(Arrays.asList(PROVINCES));
        list_1 = new ArrayList<>(Arrays.asList(CITIES[0]));
        list_2 = new ArrayList<>(Arrays.asList(AREAS[0][0]));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter_1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list_1);
        adapter_2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list_2);
        spinner.setAdapter(adapter);
        spinner_1.setAdapter(adapter_1);
        spinner_2.setAdapter(adapter_2);
        spinner.setOnItemSelectedListener(this);
        spinner_1.setOnItemSelectedListener(this);
        spinner_2.setOnItemSelectedListener(this);
    }

//    @Override
//    public void onClick(View view) {
//        adapter.add("新增数据");
////        list.add("hello");
////        adapter.notifyDataSetChanged();
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spinner:
                adapter_1.clear();
                adapter_1.addAll(CITIES[i]);
                adapter_2.clear();
                adapter_2.addAll(AREAS[i][0]);
                if (adapter_2.isEmpty()){
                    spinner_2.setVisibility(View.GONE);
                }else {
                    spinner_2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.spinner_1:
                adapter_2.clear();
                adapter_2.addAll(AREAS[spinner.getSelectedItemPosition()][i]);
                if (adapter_2.isEmpty()){
                    spinner_2.setVisibility(View.GONE);
                }else {
                    spinner_2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.spinner_2:
                StringBuilder builder = new StringBuilder();
                builder.append(spinner.getSelectedItem())
                        .append(":")
                        .append(spinner_1.getSelectedItem())
                        .append(":")
                        .append(spinner_2.getSelectedItem());
                Toast.makeText(SpinnerActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
