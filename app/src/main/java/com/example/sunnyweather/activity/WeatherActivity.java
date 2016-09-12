package com.example.sunnyweather.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sunnyweather.R;
import com.example.sunnyweather.service.AutoUpdateService;
import com.example.sunnyweather.util.HttpCallbackListener;
import com.example.sunnyweather.util.HttpUtil;
import com.example.sunnyweather.util.Utility;

import java.util.Calendar;

/**
 * Created by admin on 2016/9/7.
 */
public class WeatherActivity extends Activity implements View.OnClickListener {
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

    /**
     *切换城市按钮
     */
    private Button switchCity;

    /**
     *更新天气按钮
     */
    private Button refreshWeather;

    /**
     *显示湿度风力
     */
    private TextView nowCond;

    /**
     *天气图片
     */
    private ImageView weather_pic1;
    private ImageView weather_pic2;
    private ImageView weather_pic3;

    /**
     *三天天气描述
     */
    private TextView max1;
    private TextView min1;
    private TextView daily_forecast_desp1;
    private TextView max2;
    private TextView min2;
    private TextView daily_forecast_desp2;
    private TextView max3;
    private TextView min3;
    private TextView daily_forecast_desp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);
        View v = findViewById(R.id.title_top);
        v.getBackground().setAlpha(120);
        View v1 = findViewById(R.id.first_day_l);
        v1.getBackground().setAlpha(80);
        View v2 = findViewById(R.id.second_day_l);
        v2.getBackground().setAlpha(100);
        View v3 = findViewById(R.id.third_day_l);
        v3.getBackground().setAlpha(80);
        //初始化各种控件
        weatherInfoLayout = (LinearLayout) findViewById(R.id.weather_info_layout);
        cityNameText = (TextView) findViewById(R.id.city_name);
        publishText = (TextView) findViewById(R.id.publish_text);
        weatherDespText = (TextView) findViewById(R.id.weather_desp);
        tempText = (TextView) findViewById(R.id.temp);
        currentDateText = (TextView) findViewById(R.id.current_date);
        switchCity = (Button) findViewById(R.id.switch_city);
        refreshWeather = (Button) findViewById(R.id.refresh_weather);
        nowCond = (TextView) findViewById(R.id.now_cond);
        weather_pic1 = (ImageView)findViewById(R.id.weather_pic1);
        weather_pic2 = (ImageView)findViewById(R.id.weather_pic2);
        weather_pic3 = (ImageView)findViewById(R.id.weather_pic3);
        max1 = (TextView) findViewById(R.id.max1);
        min1 = (TextView) findViewById(R.id.min1);
        daily_forecast_desp1 = (TextView) findViewById(R.id.daily_forecast_desp1);
        max2 = (TextView) findViewById(R.id.max2);
        min2 = (TextView) findViewById(R.id.min2);
        daily_forecast_desp2 = (TextView) findViewById(R.id.daily_forecast_desp2);
        max3 = (TextView) findViewById(R.id.max3);
        min3 = (TextView) findViewById(R.id.min3);
        daily_forecast_desp3 = (TextView) findViewById(R.id.daily_forecast_desp3);
        switchCity.setOnClickListener(this);
        refreshWeather.setOnClickListener(this);
        String cityId = getIntent().getStringExtra("city_code");
        if (!TextUtils.isEmpty(cityId)){
            publishText.setText("同步中...");
            weatherInfoLayout.setVisibility(View.INVISIBLE);
            cityNameText.setVisibility(View.INVISIBLE);
            queryCityWeaher(cityId);
        }else{
            showWeather();
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
                publishText.setText("同步失败...");
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
        currentDateText.setText(preferences.getString("current_date", ""));
        nowCond.setText(preferences.getString("nowCond",""));
        max1.setText(preferences.getString("daily1_temp_max","")+"℃");
        max2.setText(preferences.getString("daily2_temp_max","")+"℃");
        max3.setText(preferences.getString("daily3_temp_max","")+"℃");
        min1.setText(preferences.getString("daily1_temp_min","")+"℃");
        min2.setText(preferences.getString("daily2_temp_min","")+"℃");
        min3.setText(preferences.getString("daily3_temp_min","")+"℃");
        //判断系统是12小时制还是24小时制
        ContentResolver cv = getBaseContext().getContentResolver();
        String strTimeFormat = android.provider.Settings.System.getString(cv, android.provider.Settings.System.TIME_12_24);
        Calendar mCalendar=Calendar.getInstance();
        long time=System.currentTimeMillis();
        mCalendar.setTimeInMillis(time);
        if(strTimeFormat!=null && strTimeFormat.equals("24"))
        {
            int mHour=mCalendar.get(Calendar.HOUR)+12;
            if(19<=mHour&&mHour<=24||0<=mHour&&mHour<=5){
                String desp_n = preferences.getString("daily1_cond_n","");
                String desp_n2 = preferences.getString("daily2_cond_n","");
                String desp_n3 = preferences.getString("daily3_cond_n","");
                switch (desp_n){
                    case "晴":
                        weather_pic1.setImageResource(R.drawable.sunny_night);
                        break;
                    case "多云":
                        weather_pic1.setImageResource(R.drawable.cloudy_night);
                        break;
                    case "阵雨":
                        weather_pic1.setImageResource(R.drawable.shower_night);
                        break;
                    case "雷阵雨":
                        weather_pic1.setImageResource(R.drawable.tstorm_night);
                        break;
                    case "小雨":
                        weather_pic1.setImageResource(R.drawable.light_rain);
                        break;
                }
                switch (desp_n2){
                    case "晴":
                        weather_pic2.setImageResource(R.drawable.sunny_night);
                        break;
                    case "多云":
                        weather_pic2.setImageResource(R.drawable.cloudy_night);
                        break;
                    case "阵雨":
                        weather_pic2.setImageResource(R.drawable.shower_night);
                        break;
                    case "雷阵雨":
                        weather_pic2.setImageResource(R.drawable.tstorm_night);
                        break;
                    case "小雨":
                        weather_pic2.setImageResource(R.drawable.light_rain);
                        break;
                }
                switch (desp_n3){
                    case "晴":
                        weather_pic3.setImageResource(R.drawable.sunny_night);
                        break;
                    case "多云":
                        weather_pic3.setImageResource(R.drawable.cloudy_night);
                        break;
                    case "阵雨":
                        weather_pic3.setImageResource(R.drawable.shower_night);
                        break;
                    case "雷阵雨":
                        weather_pic3.setImageResource(R.drawable.tstorm_night);
                        break;
                    case "小雨":
                        weather_pic3.setImageResource(R.drawable.light_rain);
                        break;
                }
                daily_forecast_desp1.setText(desp_n);
                daily_forecast_desp2.setText(desp_n2);
                daily_forecast_desp3.setText(desp_n3);
            }else {
                String desp_d = preferences.getString("daily1_cond_d","");
                String desp_d2 = preferences.getString("daily2_cond_d","");
                String desp_d3 = preferences.getString("daily3_cond_d","");
                switch (desp_d){
                    case "晴":
                        weather_pic1.setImageResource(R.drawable.sunny);
                        break;
                    case "多云":
                        weather_pic1.setImageResource(R.drawable.cloudy);
                        break;
                    case "阵雨":
                        weather_pic1.setImageResource(R.drawable.shower);
                        break;
                    case "雷阵雨":
                        weather_pic1.setImageResource(R.drawable.tstorm);
                        break;
                    case "小雨":
                        weather_pic1.setImageResource(R.drawable.light_rain);
                        break;
                }
                switch (desp_d2){
                    case "晴":
                        weather_pic2.setImageResource(R.drawable.sunny);
                        break;
                    case "多云":
                        weather_pic2.setImageResource(R.drawable.cloudy);
                        break;
                    case "阵雨":
                        weather_pic2.setImageResource(R.drawable.shower);
                        break;
                    case "雷阵雨":
                        weather_pic2.setImageResource(R.drawable.tstorm);
                        break;
                    case "小雨":
                        weather_pic2.setImageResource(R.drawable.light_rain);
                        break;
                }
                switch (desp_d3){
                    case "晴":
                        weather_pic3.setImageResource(R.drawable.sunny);
                        break;
                    case "多云":
                        weather_pic3.setImageResource(R.drawable.cloudy);
                        break;
                    case "阵雨":
                        weather_pic3.setImageResource(R.drawable.shower);
                        break;
                    case "雷阵雨":
                        weather_pic3.setImageResource(R.drawable.tstorm);
                        break;
                    case "小雨":
                        weather_pic3.setImageResource(R.drawable.light_rain);
                        break;
                }
                daily_forecast_desp1.setText(desp_d);
                daily_forecast_desp2.setText(desp_d2);
                daily_forecast_desp3.setText(desp_d3);
            }
        }
        //12小时制暂时不写
        else
        {
            String amPmValues;
            Calendar c = Calendar.getInstance();
            if(c.get(Calendar.AM_PM) == 0)
            {
                amPmValues = "AM";
            }
            else
            {
                amPmValues = "PM";
            }
        }
        weatherInfoLayout.setVisibility(View.VISIBLE);
        cityNameText.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.switch_city:
                Intent intent = new Intent(this,ChooseAreaActivity.class);
                intent.putExtra("from_weather_activity", true);
                startActivity(intent);
                finish();
                break;
            case R.id.refresh_weather:
                publishText.setText("同步中..");
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                String cityId = prefs.getString("city_id","");
                if (!TextUtils.isEmpty(cityId)){
                    queryCityWeaher(cityId);
                }
                break;
            default:
                break;
        }
    }
}
