package com.treasure_ct.android_xt.studyfragment.basedcontrols.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by treasure on 2016.08.25.
 */
public class CalculatorLayout2Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView input;
    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedcontrols_layout_calculator2);
        input = (TextView) findViewById(R.id.input);
        grid = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            TextView childAt = (TextView) grid.getChildAt(i);
            if (!childAt.equals(input)) {
                childAt.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        TextView test = (TextView) view;
        String str = test.getText().toString();
        StringBuilder builder = new StringBuilder(input.getText());
        switch (str) {
            case "C":
                builder.delete(0, builder.length());
                break;
            case "+":
                builder.append("+");
                break;
            case "-":
                builder.append("-");
                break;
            case "X":
                builder.append("*");
                break;
            case "/":
                builder.append("/");
                break;
            case ".":
                if (!builder.toString().contains(".")) {
                    if (builder.length() == 0) {
                        builder.append("0");
                    }
                    builder.append(".");
                }
                break;
            case "~":
                if (builder.length() > 0) {
                    builder.delete(builder.length() - 1, builder.length());
                }
                break;
            case "=":
                StringBuilder builder1 = new StringBuilder();
                Pattern pattern = Pattern.compile("([\\d.]+)([*/])([\\d.]+)");
                builder1.append(str);
                Matcher matcher = pattern.matcher(builder1.toString());
                while (matcher.find()){
                    float f1 = Float.parseFloat(matcher.group(1));
                    float f3 = Float.parseFloat(matcher.group(3));
                    float result = 0;
                    switch (matcher.group(2)){
                        case "*":
                            result = f1 * f3;
                            break;
                        case "/":
                            result = f1 / f3;
                            break;
                    }
                    builder1.replace(matcher.start(),matcher.end(), String.valueOf(result));
                    matcher.reset(builder1.toString());
                }
                pattern = Pattern.compile("([\\d.]+)([+-])([\\d.]+)");
                matcher = pattern.matcher(builder1.toString());
                while (matcher.find()){
                    float f1 = Float.parseFloat(matcher.group(1));
                    float f3 = Float.parseFloat(matcher.group(3));
                    float result = 0;
                    switch (matcher.group(2)){
                        case "+":
                            result = f1 + f3;
                            break;
                        case "-":
                            result = f1 - f3;
                            break;
                    }
                    builder1.replace(matcher.start(),matcher.end(),String.valueOf(result));
                    matcher.reset(builder1.toString());
                }
                input.setText(builder1.toString());
                break;
            default:
                builder.append(str);
                break;
        }
        input.setText(builder.toString());
    }
}
