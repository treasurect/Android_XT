package com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.List;

/**
 * Created by treasure on 2016.10.05.
 */

public class MyCyclerViewListAdapter extends RecyclerView.Adapter<MyCyclerViewListAdapter.MyViewHolder>{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public MyCyclerViewListAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }
    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyCyclerViewListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_corecontrols_recyclerview_list_item, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyCyclerViewListAdapter.MyViewHolder holder, final int position) {
        holder.textView.setText(mList.get(position));
        if ( mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recyclerview_item_title);
        }
    }
    public void addData(int position){
        mList.add(position,"新增加了一个");
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mList.size());
    }
    public void removeData(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mList.size());
    }
    /**
     * RecyclerView.Adapter中刷新数据的几个方法，一共有这么几个方法
     * @1 public final void notifyDataSetChanged()
     *    与ListView用法相同
     *
     * @2 public final void notifyItemChanged(int position)
     * 当position位置的数据发生了改变时就会调用这个方法，就会回调对应position的onBindViewHolder()方法了，
     * 当然，因为ViewHolder是复用的，所以如果position在当前屏幕以外，也就不会回调了，因为没有意义，
     * 下次position滚动会当前屏幕以内的时候同样会调用onBindViewHolder()方法刷新数据了。
     *
     * @3 public final void notifyItemRangeChanged(int positionStart, int itemCount)
     * 可以刷新从positionStart开始itemCount数量的item了（这里的刷新指回调onBindViewHolder()方法）。
     *
     *@4  public final void notifyItemInserted(int position)
     * 这个方法是在第position位置被插入了一条数据的时候可以使用这个方法刷新，注意这个方法调用后会有插入的动画，
     * 这个动画可以使用默认的，也可以自己定义。
     *
     * @5 public final void notifyItemMoved(int fromPosition, int toPosition)
     * 这个方法是从fromPosition移动到toPosition为止的时候可以使用这个方法刷新
     *
     * @6 public final void notifyItemRangeInserted(int positionStart, int itemCount)
     * 显然是批量添加
     *
     * @7 public final void notifyItemRemoved(int position)
     * 第position个被删除的时候刷新，同样会有动画。
     *
     * @8 public final void notifyItemRangeRemoved(int positionStart, int itemCount)
     * 批量删除
     *
     */

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
