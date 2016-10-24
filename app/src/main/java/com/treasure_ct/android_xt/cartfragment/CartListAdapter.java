package com.treasure_ct.android_xt.cartfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.platform.comapi.map.E;
import com.treasure_ct.android_xt.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by treasure on 2016.10.22.
 */

public class CartListAdapter extends BaseAdapter {
    private Context context;
    private List<CartModel> list;
    private boolean isEdit = false;

    public CartListAdapter(Context context, List<CartModel> list) {
        this.context = context;
        this.list = list;
    }

    public void cartEditChange() {
        isEdit = !isEdit;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getProduceId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            ret = inflater.inflate(R.layout.item_cart_listview, parent, false);
        }
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null) {
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        CartModel model = list.get(position);
        holder.setCartModel(model);
        holder.mImageIcon.setImageResource(R.mipmap.icon_main1);
        holder.mTextName.setText(model.getProductName());
        holder.mTextPrice.setText(String.format("￥ %.2f", model.getProductPrice()));
        int count = model.getCount();
        holder.mTextCount.setText(Integer.toString(count));
        if (count == 1) {
            holder.mButtonDec.setEnabled(false);
        } else {
            holder.mButtonDec.setEnabled(true);
        }
        holder.mCheckBox.setChecked(model.isChecked());
        if (isEdit) {
            holder.mButtonDel.setVisibility(View.VISIBLE);
        } else {
            holder.mButtonDel.setVisibility(View.INVISIBLE);
        }
        return ret;
    }

    private static class ViewHolder implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
        private CheckBox mCheckBox;
        private ImageView mImageIcon;
        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mTextCount;
        private Button mButtonDec;
        private Button mButtonInc;
        private TextView mButtonDel;
        private CartModel mCartModel;

        public ViewHolder(View view) {
            mCheckBox = (CheckBox) view.findViewById(R.id.item_cart_check);
            mImageIcon = (ImageView) view.findViewById(R.id.item_cart_icon);
            mTextName = (TextView) view.findViewById(R.id.item_cart_title);
            mTextPrice = (TextView) view.findViewById(R.id.item_cart_price);
            mTextCount = (TextView) view.findViewById(R.id.item_cart_count);
            mButtonInc = (Button) view.findViewById(R.id.item_cart_inc);
            mButtonDec = (Button) view.findViewById(R.id.item_cart_dec);
            mButtonDel = (TextView) view.findViewById(R.id.item_cart_del);
            mCheckBox.setOnCheckedChangeListener(this);
            mButtonInc.setOnClickListener(this);
            mButtonDec.setOnClickListener(this);
            mButtonDel.setOnClickListener(this);

        }

        public CartModel getCartModel() {
            return mCartModel;
        }

        public void setCartModel(CartModel cartModel) {
            mCartModel = cartModel;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (mCartModel != null) {
                mCartModel.setChecked(isChecked);
            }
            CartOperationEvent event = new CartOperationEvent();
            event.id = buttonView.getId();
            event.mModel = mCartModel;
            EventBus.getDefault().post(event);
        }

        @Override
        public void onClick(View v) {
//            int count;
//            switch (v.getId()) {
//                case R.id.item_cart_inc:
//                    count = mCartModel.getCount();
//                    count++;
//                    mCartModel.setCount(count);
//                    break;
//                case R.id.item_cart_dec:
//                    count = mCartModel.getCount();
//                    if (count != 1){
//                        count --;
//                        mCartModel.setCount(count);
//                    }
//                    break;
//                case R.id.item_cart_del:
//                    
//                    break;
//            }
            CartOperationEvent event = new CartOperationEvent();
            event.id = v.getId();
            event.mModel = mCartModel;
            EventBus.getDefault().post(event);
        }
    }
}
