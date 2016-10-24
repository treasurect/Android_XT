package com.treasure_ct.android_xt.cartfragment;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    private List<CartModel> list;
    private CartListAdapter adapter;
    private CartDataObserver mObserver;
    private TextView textTotalMoney, textTotalMoney2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ListView listView = (ListView) view.findViewById(R.id.cart_listView);
        TextView textEdit = (TextView) view.findViewById(R.id.cart_edit);
        textTotalMoney = (TextView) view.findViewById(R.id.cart_totalMoney);
        textTotalMoney2 = (TextView) view.findViewById(R.id.cart_totalMoney2);
        CheckBox selectAll = (CheckBox) view.findViewById(R.id.cart_allSelect);
        Button cartCast = (Button) view.findViewById(R.id.cart_cast);

        if (listView != null) {
            list = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                CartModel model = new CartModel();
                model.setCount(1);
                model.setProduceId(i);
                model.setProductName("这是个什么东西 - " + i);
                model.setProductPrice(30 + i);
                list.add(model);
            }
            adapter = new CartListAdapter(getContext(), list);
            //Adapter 可以指定数据观察者   每次notifyDataSetChange 观察者自动调用
            mObserver = new CartDataObserver();
            adapter.registerDataSetObserver(mObserver);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }
        textEdit.setOnClickListener(this);
        selectAll.setOnCheckedChangeListener(this);
        cartCast.setOnClickListener(this);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_edit:
                for (CartModel cartModel : list) {
                    cartModel.setChecked(false);
                }
                adapter.cartEditChange();
                break;
            case R.id.cart_cast:
                String castMoney = textTotalMoney.getText().toString();
                if (castMoney == "￥0.00" || castMoney.equals("￥0.00")){
                    Toast.makeText(getContext(), "请选择对应的商品", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "正在跳转", Toast.LENGTH_SHORT).show();
//                    new Intent(getContext(),PayDemoActivity)
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCartOperation(CartOperationEvent event) {
        int count = event.mModel.getCount();
        switch (event.id) {
            case R.id.item_cart_inc:
                count++;
                event.mModel.setCount(count);
                break;
            case R.id.item_cart_dec:
                count--;
                if (count > 0) {
                    event.mModel.setCount(count);
                }
                break;
            case R.id.item_cart_del:
                list.remove(event.mModel);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        adapter.unregisterDataSetObserver(mObserver);
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击条目，查看商品详情
        Snackbar.make(parent, "点击条目" + position, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (CartModel cartModel : list) {
            cartModel.setChecked(isChecked);
        }
        adapter.notifyDataSetChanged();
    }

    private class CartDataObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            //计算金额
            float total = 0;
            float total2 = 0;
            for (CartModel cartModel : list) {
                boolean checked = cartModel.isChecked();
                if (checked == true) {
                    total = total + cartModel.getCount() * cartModel.getProductPrice();
                }
                total2 = total2 + cartModel.getCount() * cartModel.getProductPrice();
            }
            textTotalMoney.setText(String.format("￥%.2f", total));
            textTotalMoney2.setText(String.format("￥%.2f", total2));
        }
    }
}
