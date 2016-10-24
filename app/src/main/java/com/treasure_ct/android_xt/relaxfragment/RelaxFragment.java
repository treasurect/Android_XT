package com.treasure_ct.android_xt.relaxfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelaxFragment extends Fragment {
    private SurfaceView surfaceView;
    private ListView listView;
    private List<RelaxModel>list;
    private RelaxBaseAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relax, container, false);
        x.view().inject(getActivity());
        surfaceView = (SurfaceView) view.findViewById(R.id.relax_video_view);
        listView = (ListView) view.findViewById(R.id.relaxVideoList);
        list = new ArrayList<>();
        adapter = new RelaxBaseAdapter(getContext(),list);
        listView.setAdapter(adapter);

        //HTTP工具类的使用
        RequestParams params = new RequestParams("http://ic.snssdk.com/neihan/stream/mix/v1/?content_type=-104&message_cursor=-1&loc_time=1432654641&latitude=40.0522901291784&longitude=116.23490963616668&city=北京&count=30&screen_width=800&iid=2767929313&device_id=2757969807&ac=wifi&channel=baidu2&aid=7&app_name=joke_essay&version_code=400&device_platform=android&device_type=KFTT&os_api=15&os_version=4.0.3&openudid=b90ca6a3a19a78d6");
        //HTTP设置
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                list.clear();
                //解析视频列表
                try {
                    JSONObject outerData = result.getJSONObject("data");
                    JSONArray innerData = outerData.getJSONArray("data");
                    for (int i = 0; i < innerData.length(); i++) {
                        JSONObject jsonObject = innerData.getJSONObject(i);
                        int type = jsonObject.getInt("type");
                        if (type == 1){
                            JSONObject group = jsonObject.getJSONObject("group");
                            JSONObject video = group.getJSONObject("360p_video");
                            RelaxModel info = RelaxModel.createFromJson(video);
                            list.add(info);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getContext(), "获取错误", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(getContext(), "获取取消", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinished() {
                Toast.makeText(getContext(), "获取完成", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        list.clear();
        adapter.notifyDataSetChanged();

        super.onDestroyView();
    }
}
