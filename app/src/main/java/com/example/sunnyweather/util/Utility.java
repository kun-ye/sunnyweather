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
import java.util.Calendar;
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
        //气温
        String temp = basicBean.getHeWeather_data_service().get(0).getNow().getTmp();
        //更新时间
        String publishTime = basicBean.getHeWeather_data_service().get(0).getBasic().getUpdate().getLoc();
        //湿度风力
        String nowHum = basicBean.getHeWeather_data_service().get(0).getNow().getHum();
        String nowWind = basicBean.getHeWeather_data_service().get(0).getNow().getWind().getDir()+basicBean.getHeWeather_data_service().get(0).getNow().getWind().getSc();
        String nowCond = "湿度"+nowHum+"%"+" "+nowWind+"级";
        /**
         * 三天天气
         */
        //白天天气状况
        String daily1_cond_d = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(0).getCond().getTxt_d();
        String daily2_cond_d = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(1).getCond().getTxt_d();
        String daily3_cond_d = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(2).getCond().getTxt_d();
        //夜晚天气状况
        String daily1_cond_n = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(0).getCond().getTxt_n();
        String daily2_cond_n = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(1).getCond().getTxt_n();
        String daily3_cond_n = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(2).getCond().getTxt_n();
        //未来三天气温描述
        String daily1_temp_max = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(0).getTmp().getMax();
        String daily1_temp_min = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(0).getTmp().getMin();
        String daily2_temp_max = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(1).getTmp().getMax();
        String daily2_temp_min = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(1).getTmp().getMin();
        String daily3_temp_max = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(2).getTmp().getMax();
        String daily3_temp_min = basicBean.getHeWeather_data_service().get(0).getDaily_forecast().get(2).getTmp().getMin();
        saveWeatherInfo(context,cityName,cityId,temp,weatherDesp,publishTime,nowCond,daily1_cond_d,daily1_cond_n,daily1_temp_max,daily1_temp_min,daily2_cond_d,daily2_cond_n,daily2_temp_max,daily2_temp_min,daily3_cond_d,daily3_cond_n,daily3_temp_max,daily3_temp_min);
    }
    /**
     * 将服务器返回的所有天气信息存储到SharedPreFerences文件中
     */
    public static void saveWeatherInfo(Context context,String cityName,String cityId,String temp,String weatherDesp,String publishTime,String nowCond,String daily1_cond_d,String daily1_cond_n,String daily1_temp_max,String daily1_temp_min,String daily2_cond_d,String daily2_cond_n,String daily2_temp_max,String daily2_temp_min,String daily3_cond_d,String daily3_cond_n,String daily3_temp_max,String daily3_temp_min){
        SimpleDateFormat sdf = new SimpleDateFormat("M/d", Locale.CHINA);
        final Calendar c = Calendar.getInstance();
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected", true);
        editor.putString("city_name", cityName);
        editor.putString("city_id",cityId);
        editor.putString("temp",temp);
        editor.putString("weather_desp", weatherDesp);
        editor.putString("publish_time",publishTime);
        editor.putString("nowCond",nowCond);
        editor.putString("current_date",(sdf.format(new Date())+"|周"+mWay));
        editor.putString("daily1_cond_d",daily1_cond_d);
        editor.putString("daily1_cond_n",daily1_cond_n);
        editor.putString("daily1_temp_max",daily1_temp_max);
        editor.putString("daily1_temp_min",daily1_temp_min);
        editor.putString("daily2_cond_d",daily2_cond_d);
        editor.putString("daily2_cond_n",daily2_cond_n);
        editor.putString("daily2_temp_max",daily2_temp_max);
        editor.putString("daily2_temp_min",daily2_temp_min);
        editor.putString("daily3_cond_d",daily3_cond_d);
        editor.putString("daily3_cond_n",daily3_cond_n);
        editor.putString("daily3_temp_max",daily3_temp_max);
        editor.putString("daily3_temp_min",daily3_temp_min);
        editor.commit();
    }
}
