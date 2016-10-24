package com.treasure_ct.android_xt.studyfragment.seniorcontrols.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.treasure_ct.android_xt.R;

import java.util.List;

public class BaiduMap extends AppCompatActivity implements View.OnClickListener, OnGetPoiSearchResultListener, BDLocationListener {
    private MapView mapView;
    private com.baidu.mapapi.map.BaiduMap mBaiduMap;
    private WindowManager windowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private ImageView image_2D,image_3D,image_Hot,image_Traff,image_Loc;
    private Button search;
    private PoiSearch mPoiSearch;
    private EditText search_city,search_like;
    private LocationClient mLocationClient;
    private Marker mMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//需要调用所有的sak组件，建议放在application初始化中
        setContentView(R.layout.activity_seniorcontrols_map_baidu);
         mapView = (MapView) findViewById(R.id.baidu_mapview);
        image_2D = (ImageView) findViewById(R.id.image_2d);
        image_3D = (ImageView) findViewById(R.id.image_3d);
        image_Hot = (ImageView) findViewById(R.id.image_hot);
        image_Traff = (ImageView) findViewById(R.id.image_traff);
        search = (Button) findViewById(R.id.search_btn);
        search_city = (EditText) findViewById(R.id.search_city);
        search_like = (EditText) findViewById(R.id.search_like);
        image_Loc = (ImageView) findViewById(R.id.image_location);

        mBaiduMap = mapView.getMap();
         mBaiduMap.setMapType(com.baidu.mapapi.map.BaiduMap.MAP_TYPE_NORMAL);
        image_2D.setImageResource(R.mipmap.map_2d2);
        image_2D.setOnClickListener(this);
        image_3D.setOnClickListener(this);
        image_Hot.setOnClickListener(this);
        image_Traff.setOnClickListener(this);
        search.setOnClickListener(this);
        image_Loc.setOnClickListener(this);

        LatLng latLng = new LatLng(40.13, 116.65);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_location);
        MarkerOptions options = new MarkerOptions().position(latLng).icon(bitmap).zIndex(9).draggable(true);
        mBaiduMap.addOverlay(options);

        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
//        initfloatImage();
//        createFloatView();
        initLocation();
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setCoorType("bd0911");
        option.setScanSpan(5000);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(this);
    }


    //    private void initfloatImage(){
//        windowManager = (WindowManager) getApplicationContext().getSystemService("window");
//        mLayoutParams = new WindowManager.LayoutParams();
//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//        mLayoutParams.format = PixelFormat.RGBA_8888;
//        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        mLayoutParams.x = 400;
//        mLayoutParams.y = 400;
//        mLayoutParams.width = 80;
//        mLayoutParams.height = 80;
//    }
//    private void createFloatView(){
//        mImageView = new ImageView(this);
//        mImageView.setBackgroundResource(R.mipmap.icon_main4);
//        mImageView.setAlpha(80);
//        mLayoutParams.gravity = Gravity.LEFT |Gravity.CENTER_VERTICAL;
//        windowManager.addView(mImageView,mLayoutParams);
//    }
    public  void initImage(){
        image_2D.setImageResource(R.mipmap.map_2d);
        image_3D.setImageResource(R.mipmap.map_3d);
        image_Hot.setImageResource(R.mipmap.map_hot);
        image_Traff.setImageResource(R.mipmap.map_traf);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        mLocationClient.startIndoorMode();
        mLocationClient.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        mLocationClient.stopIndoorMode();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        mPoiSearch.destroy();
        mLocationClient.stop();
        mLocationClient.unRegisterLocationListener(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_2d:
                initImage();
                image_2D.setImageResource(R.mipmap.map_2d2);
                mBaiduMap.setMapType(com.baidu.mapapi.map.BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.image_3d:
                initImage();
                image_3D.setImageResource(R.mipmap.map_3d2);
                mBaiduMap.setMapType(com.baidu.mapapi.map.BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.image_hot:
                initImage();
                image_Hot.setImageResource(R.mipmap.map_hot2);
                mBaiduMap.setBaiduHeatMapEnabled(!mBaiduMap.isBaiduHeatMapEnabled());
                break;
            case R.id.image_traff:
                initImage();
                image_Traff.setImageResource(R.mipmap.map_traf2);
                mBaiduMap.setTrafficEnabled(!mBaiduMap.isTrafficEnabled());
                break;
            case R.id.search_btn:
                PoiCitySearchOption option = new PoiCitySearchOption();
                if (search_city.getText().toString() != null && search_like.getText().toString() != null) {
                    option.city(search_city.getText().toString()).keyword(search_like.getText().toString()).pageNum(30);
                    mPoiSearch.searchInCity(option);
                }
                break;
            case R.id.image_location:
                mLocationClient.requestLocation();
                break;
        }
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        List<PoiInfo> allPoi = poiResult.getAllPoi();
        if (allPoi != null){
            int index = 1;
            for (PoiInfo poiInfo : allPoi) {
                LatLng location = poiInfo.location;
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(location);
                if (index <= 30){
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromAsset("Icon_mark" + index + ".png");
                    markerOptions.icon(bitmapDescriptor);
                }else {
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromAsset("Icon_end.png");
                    markerOptions.icon(bitmapDescriptor);
                }
                index ++;
                markerOptions.title(poiInfo.name);
                Bundle bundle = new Bundle();
                bundle.putString("type","poi");
                bundle.putParcelable("poiInfo",poiInfo);
                markerOptions.extraInfo(bundle);
                mBaiduMap.addOverlay(markerOptions);
            }
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        int locType = bdLocation.getLocType();
        switch (locType) {
            case BDLocation.TypeGpsLocation:
            case BDLocation.TypeNetWorkLocation:
            case BDLocation.TypeCacheLocation:
            case BDLocation.TypeOffLineLocation:
                double latitude = bdLocation.getLatitude();
                double longitude = bdLocation.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.setMapStatus(update);
                if (mMarker != null) {
                    MarkerOptions options = new MarkerOptions();
                    options.position(latLng);
                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromAsset("Icon_start.png");
                    options.icon(descriptor);
                    Bundle bundle = new Bundle();
                    bundle.putString("type","self");
                    bundle.putString("address",bdLocation.getAddrStr());
                    options.extraInfo(bundle);
                    mMarker = (Marker) mBaiduMap.addOverlay(options);
                }
                mMarker.setPosition(latLng);
                break;
            default:
                String description = bdLocation.getLocTypeDescription();
                Toast.makeText(this, description, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
