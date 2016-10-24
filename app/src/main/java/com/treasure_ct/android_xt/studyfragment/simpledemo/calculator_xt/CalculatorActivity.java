package com.treasure_ct.android_xt.studyfragment.simpledemo.calculator_xt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.treasure_ct.android_xt.R;

public class CalculatorActivity extends Activity {
    private EditText result_line;
    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    private Button diot;
    private Button clear;
    private Button delete;
    private Button add;
    private Button subtract;
    private Button multiply;
    private Button divide;
    private Button equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_calculator);
        result_line=(EditText)findViewById(R.id.result_line);
        num0=(Button)findViewById(R.id.num0);
        num1=(Button)findViewById(R.id.num1);
        num2=(Button)findViewById(R.id.num2);
        num3=(Button)findViewById(R.id.num3);
        num4=(Button)findViewById(R.id.num4);
        num5=(Button)findViewById(R.id.num5);
        num6=(Button)findViewById(R.id.num6);
        num7=(Button)findViewById(R.id.num7);
        num8=(Button)findViewById(R.id.num8);
        num9=(Button)findViewById(R.id.num9);
        diot=(Button)findViewById(R.id.diot);
        clear=(Button)findViewById(R.id.clear);
        delete=(Button)findViewById(R.id.delete);
        add=(Button)findViewById(R.id.add);
        subtract=(Button)findViewById(R.id.subtract);
        multiply=(Button)findViewById(R.id.multiply);
        divide=(Button)findViewById(R.id.divide);
        equal=(Button)findViewById(R.id.equal);
        num0.setOnClickListener(listener);
        num1.setOnClickListener(listener);
        num2.setOnClickListener(listener);
        num3.setOnClickListener(listener);
        num4.setOnClickListener(listener);
        num5.setOnClickListener(listener);
        num6.setOnClickListener(listener);
        num7.setOnClickListener(listener);
        num8.setOnClickListener(listener);
        num9.setOnClickListener(listener);
        diot.setOnClickListener(listener);
        clear.setOnClickListener(listener);
        delete.setOnClickListener(listener);
        add.setOnClickListener(listener);
        subtract.setOnClickListener(listener);
        multiply.setOnClickListener(listener);
        divide.setOnClickListener(listener);
        equal.setOnClickListener(listener);
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str=result_line.getText().toString();
            switch (v.getId()) {
                case R.id.num0:
                case R.id.num1:
                case R.id.num2:
                case R.id.num3:
                case R.id.num4:
                case R.id.num5:
                case R.id.num6:
                case R.id.num7:
                case R.id.num8:
                case R.id.num9:
                case R.id.diot:
                    result_line.setText(str+((Button)v).getText());
                    break;
                case R.id.clear:
                    result_line.setText("");
                    break;
                case R.id.delete:
                    if(str.length()!=0){
                        result_line.setText(str.substring(0,str.length()-1));
                    }else{
                        result_line.setText("");
                    }
                    break;
                case R.id.add:
                case R.id.subtract:
                case R.id.multiply:
                case R.id.divide:
                    result_line.setText(str+" "+((Button)v).getText()+" ");
                    break;
                case R.id.equal:
                    getResult();
                    break;
            }

        }

        private void getResult() {
            String abc=result_line.getText().toString();
            if(abc==null||abc.equals("")){
                return;
            }
            if(!abc.contains(" ")){
                return;
            }
            double result=0;
            String s1=abc.substring(0,abc.indexOf(" "));
            String op=abc.substring(abc.indexOf(" ")+1,abc.indexOf(" ")+2);
            String s2=abc.substring(abc.indexOf(" ")+3);
            if(!s1.equals("")&&!s2.equals("")){
                Double d1=Double.parseDouble(s1);
                Double d2=Double.parseDouble(s2);
                if(op.equals("+")){
                    result=d1+d2;
                }else if(op.equals("-")){
                    result=d1-d2;
                }else if(op.equals("*")){
                    result=d1*d2;
                }else if(op.equals("/")){
                    if(d2==0){
                        result=0;
                    }else{
                        result=d1/d2;
                    }
                }
                if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("/")){
                    int r=(int) result;
                    result_line.setText(r+"");
                }else{
                    result_line.setText(result+"");
                }
            }else if(!s1.equals("")&&s2.equals("")){
                result_line.setText(abc);
            }else if(s1.equals("")&&!s2.equals("")){
                Double d2=Double.parseDouble(s2);
                if(op.equals("+")){
                    result=d2;
                }else if(op.equals("-")){
                    result=0-d2;
                }else if(op.equals("*")){
                    result=0;
                }else if(op.equals("/")){
                    result=0;
                }
                if(!s2.contains(".")&&!op.equals("/")){
                    int r=(int) result;
                    result_line.setText(r+"");
                }else{
                    result_line.setText(result+"");
                }
            }
        }
    };
}
