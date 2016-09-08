package com.example.sunnyweather.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sunnyweather.R;
import com.example.sunnyweather.util.HttpCallbackListener;
import com.example.sunnyweather.util.HttpUtil;
import com.example.sunnyweather.util.Utility;

/**
 * Created by admin on 2016/9/7.
 */
public class WeatherActivity extends Activity {
    private LinearLayout weatherInfoLayout;
    /**
     * 显示城市名
     */
    private TextView cityNameText;
    /**
     * 显示发布时间
     */
    private TextView publishText;
    /**
     * 显示天气描述信息
     */
    private TextView weatherDespText;
    /**
     * 显示气温
     */
    private TextView tempText;
    /**
     * 显示当前日期
     */
    private TextView currentDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);
        //初始化各种控件
        weatherInfoLayout = (LinearLayout) findViewById(R.id.weather_info_layout);
        cityNameText = (TextView) findViewById(R.id.city_name);
        publishText = (TextView) findViewById(R.id.publish_text);
        weatherDespText = (TextView) findViewById(R.id.weather_desp);
        tempText = (TextView) findViewById(R.id.temp);
        currentDateText = (TextView) findViewById(R.id.current_date);
        String cityId = getIntent().getStringExtra("city_code");
        if (!TextUtils.isEmpty(cityId)){
            publishText.setText("同步中...");
            weatherInfoLayout.setVisibility(View.INVISIBLE);
            cityNameText.setVisibility(View.INVISIBLE);
            queryCityWeaher(cityId);
        }
    }
    /**
     * 查询城市id对应天气信息
     */
    private  void queryCityWeaher(String cityId){
        String address = "http://api.heweather.com/x3/weather?cityid="+cityId+"&key=2abd648d01c74b73bee1c8e7d6d6be7a";
        queryFromServer(address);
    }
    /**
     * 根据传入地址查询天气信息
     */
    private void queryFromServer(final String address){
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Utility.handleWeatherResponse(WeatherActivity.this, response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showWeather();
                    }
                });
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    /**
     * 从SharedPreferences文件中读取存储的天气信息，并显示在界面
     */
    private void showWeather(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        cityNameText.setText(preferences.getString("city_name",""));
        tempText.setText(preferences.getString("temp","")+"℃");
        weatherDespText.setText(preferences.getString("weather_desp",""));
        publishText.setText("今天"+preferences.getString("publish_time","")+"发布");
        currentDateText.setText(preferences.getString("current_data",""));
        weatherInfoLayout.setVisibility(View.VISIBLE);
        cityNameText.setVisibility(View.VISIBLE);
    }
}
