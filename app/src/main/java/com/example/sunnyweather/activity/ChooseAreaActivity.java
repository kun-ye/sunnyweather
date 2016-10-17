package com.example.sunnyweather.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunnyweather.R;
import com.example.sunnyweather.db.SunnyWeatherDB;
import com.example.sunnyweather.model.City;
import com.example.sunnyweather.util.HttpCallbackListener;
import com.example.sunnyweather.util.HttpUtil;
import com.example.sunnyweather.util.Utility;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2016/9/1.
 */
public class ChooseAreaActivity extends Activity {
    private ProgressDialog progressDialog;
    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private SunnyWeatherDB sunnyWeatherDB;
    private List<String> dataList = new ArrayList<String>();
    private String cityCode;
    /**
     * 判断是否WeatherActivity中跳转
     */
    private boolean isFromWeatherActivity;
    /**
     * City列表
     */
    private List<City> cityList;
    /**
     * 选中的城市
     */
    private City selectedCity;

    /**
     * AutoCompleteTextView
     */
    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFromWeatherActivity = getIntent().getBooleanExtra("from_weather_activity",false);
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        if (preference.getBoolean("city_selected",false)&& !isFromWeatherActivity){
            Intent intent = new Intent(this,WeatherActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoText);
        listView = (ListView) findViewById(R.id.list_view);
        titleText = (TextView) findViewById(R.id.title_text);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        sunnyWeatherDB = SunnyWeatherDB.getInstance(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityId = cityList.get(position).getCityCode();
                Intent intent = new Intent(ChooseAreaActivity.this, WeatherActivity.class);
                intent.putExtra("city_code", cityId);
                startActivity(intent);
                finish();
            }
        });
        queryCities();//加载城市数据
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ChooseAreaActivity.this,android.R.layout.simple_dropdown_item_1line,dataList);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(ChooseAreaActivity.this, WeatherActivity.class);
                intent.putExtra("city_code", getCityCodeFromCityName(str));
                startActivity(intent);
                finish();
            }
        });
    }

    private String getCityCodeFromCityName(String cityName){
        cityList = sunnyWeatherDB.loadCites();
        if (cityList.size() > 0) {
            for (City city : cityList) {
                if (city.getCityName().equals(cityName)) {
                    cityCode = city.getCityCode();
                }
            }
        }
        return cityCode;
    }

    private void queryCities(){
        cityList = sunnyWeatherDB.loadCites();
        if (cityList.size() > 0){
            dataList.clear();
            for (City city : cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();//动态刷新列表
            listView.setSelection(0);
            titleText.setText("全国城市列表");
        }else {
            queryFromServer();
        }
    }
    /**
     * 从服务器上查询城市数据
     */
    private void queryFromServer(){
        String address = "https://api.heweather.com/x3/citylist?search=allchina&key=2abd648d01c74b73bee1c8e7d6d6be7a";
        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                result = Utility.handleCityResponse(sunnyWeatherDB,response);
                if(result){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            queryCities();
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(ChooseAreaActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    /**
     * 显示进度对话框
     */
    private void showProgressDialog(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
    }
    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isFromWeatherActivity){
            Intent intent = new Intent(this,WeatherActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
