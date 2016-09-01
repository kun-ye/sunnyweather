package com.example.sunnyweather.util;

/**
 * Created by admin on 2016/9/1.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
