package com.treasure_ct.android_xt.studyfragment.simplecontrols;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.09.05.
 */
public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private Button btn1,btn2,btn3;
    private String[] hobby ={"足球","篮球","乒乓球"};
    private String[]sexlist ={"男", "女", "保密"};
    private Boolean[] check ={true,false,false};
    StringBuilder builder2 = new StringBuilder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarycontrols_dialog_alertdialog);
        text = (TextView) findViewById(R.id.adialog_sex_select_text);
        btn1 = (Button) findViewById(R.id.adialog_sex_select);
        btn2 = (Button) findViewById(R.id.adialog_multi_sel);
        btn3 = (Button) findViewById(R.id.adialog_progress);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setIcon(R.mipmap.lufei)
                .setTitle("退出确认框")
                .setMessage("您确定要退出吗？");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adialog_sex_select:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.mipmap.icon_main10)
                        .setTitle("相别选择")
                        .setItems(sexlist, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogActivity.this, sexlist[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.adialog_multi_sel:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setIcon(R.mipmap.icon_main6)
                        .setTitle("爱好")
                        .setMultiChoiceItems(hobby, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                for (int i = 0; i < hobby.length; i++) {
                                    if (isChecked){
                                        builder2.append(hobby[i]+" ");
                                    }
                                }
                            }
                        });
            builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    text.setText("爱好："+builder2.toString());
                }
            });
                AlertDialog dialog2 = builder1.create();
                dialog2.show();
            break;
            case R.id.adialog_progress:
                ProgressDialog dialog1 = new ProgressDialog(this);
                dialog1.setTitle("加载中");
                dialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog1.setProgress(50);
                dialog1.setIndeterminate(false);
                dialog1.show();
                break;
        }
    }
}
