package com.example.sunnyweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sunnyweather.db.SunnyWeatherDB;
import com.example.sunnyweather.model.Bean;
import com.example.sunnyweather.model.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by admin on 2016/9/1.
 */
public class Utility {
    /**
     * 解析和处理服务器返回的城市数据
     */
    public synchronized static boolean handleCityResponse(SunnyWeatherDB sunnyWeatherDB,String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("city_info");
            for (int i = 0;i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String city = jsonObject1.getString("city");
                String id = jsonObject1.getString("id");
                String lat = jsonObject1.getString("lat");
                String lon = jsonObject1.getString("lon");
                String prov = jsonObject1.getString("prov");
                City city0 = new City();
                city0.setCityCode(id);
                city0.setCityName(city);
                city0.setLat(lat);
                city0.setLon(lon);
                city0.setProvince(prov);
                sunnyWeatherDB.saveCity(city0);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的天气信息的JSON数据
     */
    public static void handleWeatherResponse(Context context,String response){
        String result = response.replace("HeWeather data service 3.0", "HeWeather_data_service");
        Gson gson = new Gson();
        Bean basicBean = gson.fromJson(result, Bean.class);
        String cityName = basicBean.getHeWeather_data_service().get(0).getBasic().getCity();
        String cityId = basicBean.getHeWeather_data_service().get(0).getBasic().getId();
        //天气状况描述
        String weatherDesp = basicBean.getHeWeather_data_service().get(0).getNow().getCond().getTxt();
        List<Bean.HeWeatherDataServiceBean.HourlyForecastBean> list = basicBean.getHeWeather_data_service().get(0).getHourly_forecast();
        int i = list.size()-1;
        //气温
        String temp = list.get(i).getTmp();
        //更新时间
        String publishTime = list.get(i).getDate();
        saveWeatherInfo(context,cityName,cityId,temp,weatherDesp,publishTime);
    }
    /**
     * 将服务器返回的所有天气信息存储到SharedPreFerences文件中
     */
    public static void saveWeatherInfo(Context context,String cityName,String cityId,String temp,String weatherDesp,String publishTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected",true);
        editor.putString("city_name",cityName);
        editor.putString("city_id",cityId);
        editor.putString("temp",temp);
        editor.putString("weather_desp",weatherDesp);
        editor.putString("publish_time",publishTime);
        editor.putString("current_date",sdf.format(new Date()));
        editor.commit();
    }
}
