package com.example.sunnyweather.util;

import com.example.sunnyweather.db.SunnyWeatherDB;
import com.example.sunnyweather.model.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by admin on 2016/9/1.
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
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
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
