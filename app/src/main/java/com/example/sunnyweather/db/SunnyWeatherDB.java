package com.example.sunnyweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sunnyweather.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/31.
 */
public class SunnyWeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "sunny_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static SunnyWeatherDB sunnyWeatherDB;
    private SQLiteDatabase db;
    /**
     * 将构造方法私有化
     */
    private SunnyWeatherDB(Context context){
        SunnyWeatherOpenHelper dbHelper = new SunnyWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    /**
     * 获得SunnyWeatherDB的实例     -保证全局范围只有一个SunnyWeather实例 避免多线程同时访问
     */
    public synchronized static SunnyWeatherDB getInstance(Context context){
        if (sunnyWeatherDB == null){
            sunnyWeatherDB = new SunnyWeatherDB(context);
        }
        return sunnyWeatherDB;
    }
    /**
     * 将City实例存储到数据库
     */
    public void saveCity(City city){
        if (city != null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("lat",city.getLat());
            values.put("lon",city.getLon());
            values.put("prov",city.getProvince());
            db.insert("City", null, values);
        }
    }
    /**
     * 从数据库读取所有城市信息
     */
    public List<City> loadCites(){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setLat(cursor.getString(cursor.getColumnIndex("lat")));
                city.setLon(cursor.getString(cursor.getColumnIndex("lon")));
                city.setProvince(cursor.getString(cursor.getColumnIndex("prov")));
                list.add(city);
            }while (cursor.moveToNext());
        }
        if (cursor != null){
            cursor.close();
        }
        return list;
    }
}
