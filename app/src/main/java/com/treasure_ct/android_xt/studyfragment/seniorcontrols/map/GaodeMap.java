package com.treasure_ct.android_xt.studyfragment.seniorcontrols.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.treasure_ct.android_xt.R;

public class GaodeMap extends AppCompatActivity implements View.OnClickListener {
    private MapView gaode;
    private AMap mMap;
    private ImageView map_2D,map_3D,map_Night,map_Traff,map_Hot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_map_gaode);
        gaode = (MapView) findViewById(R.id.gaode_mapView);
        gaode.onCreate(savedInstanceState);
        map_2D = (ImageView) findViewById(R.id.gaode_2d);
        map_3D = (ImageView) findViewById(R.id.gaode_3d);
        map_Night = (ImageView) findViewById(R.id.gaode_night);
        map_Traff = (ImageView) findViewById(R.id.gaode_traff);
        map_Hot = (ImageView) findViewById(R.id.gaode_hot);
        mMap  = gaode.getMap();
        mMap.setMapType(AMap.MAP_TYPE_NORMAL);
        map_2D.setImageResource(R.mipmap.map_2d2);

        map_2D.setOnClickListener(this);
        map_3D.setOnClickListener(this);
        map_Night.setOnClickListener(this);
        map_Traff.setOnClickListener(this);
        map_Hot.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gaode.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gaode.onPause();
    }

    @Override
    protected void onDestroy() {
        gaode.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        gaode.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gaode_2d:
                initBackImage();
                map_2D.setImageResource(R.mipmap.map_2d2);
                mMap.setMapType(AMap.MAP_TYPE_NORMAL);
                break;
            case R.id.gaode_3d:
                initBackImage();
                map_3D.setImageResource(R.mipmap.map_3d2);
                mMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.gaode_night:
                initBackImage();
                map_Night.setImageResource(R.mipmap.map_night2);
                mMap.setMapType(AMap.MAP_TYPE_NIGHT);
                break;
            case R.id.gaode_traff:
                initBackImage();
                map_Traff.setImageResource(R.mipmap.map_traf2);
                mMap.setTrafficEnabled(!mMap.isTrafficEnabled());
                break;
            case R.id.gaode_hot:
                initBackImage();
                map_Hot.setImageResource(R.mipmap.map_hot2);
                mMap.setMapType(AMap.MAP_TYPE_NAVI);
                break;
        }
    }
    public  void initBackImage(){
        map_2D.setImageResource(R.mipmap.map_2d);
        map_3D.setImageResource(R.mipmap.map_3d);
        map_Night.setImageResource(R.mipmap.map_night);
        map_Traff.setImageResource(R.mipmap.map_traf);
        map_Hot.setImageResource(R.mipmap.map_hot);
    }
}
