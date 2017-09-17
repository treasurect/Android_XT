package com.treasure_ct.android_xt.relaxfragment.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.treasure_ct.android_xt.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by treasure on 2016.10.21.
 */

public class RelaxVideoBaseAdapter extends BaseAdapter implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private Context context;
    private List<RelaxVideoModel> list;
    private static MediaPlayer mPlayer;
    private static int isPlayingPosition;

    public RelaxVideoBaseAdapter(Context context, List<RelaxVideoModel> list) {
        this.context = context;
        this.list = list;
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        } else {
            mPlayer.release();
        }
        isPlayingPosition = -1;
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);// 视频播放完时候调用
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            ret = inflater.inflate(R.layout.item_relax_listview, parent, false);
        }
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null) {
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        RelaxVideoModel model = list.get(position);
        holder.setItemPosition(position);
        holder.setModel(model);
        return ret;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //TODO:play Next
    }

    public static class ViewHolder implements View.OnClickListener, SurfaceHolder.Callback {
        private SurfaceView mSurfaceView;
        private ImageView mButton;
        private int itemPosition;
        private RelaxVideoModel mModel;

        public ViewHolder(View view) {
            mButton = (ImageView) view.findViewById(R.id.btn_relax_play);
            mSurfaceView = (SurfaceView) view.findViewById(R.id.relax_surfaceView);
            mSurfaceView.getHolder().addCallback(this);//预置surfaceView
            mButton.setOnClickListener(this);
        }

        public void setModel(RelaxVideoModel model) {
            mModel = model;
        }

        public void setItemPosition(int itemPosition) {
            this.itemPosition = itemPosition;
        }

        @Override
        public void onClick(View v) {
            //TODO:播放视频
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
            mPlayer.reset();
            //1.切换显示的的SurfaceView
            mPlayer.setDisplay(mSurfaceView.getHolder());
            //2.设置视频地址
            try {
                String url = mModel.getFirstUrl();
                if (url != null) {
                    mPlayer.setDataSource(url);
                    mPlayer.prepareAsync();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
