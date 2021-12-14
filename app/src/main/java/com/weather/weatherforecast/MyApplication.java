package com.weather.weatherforecast;

import android.app.Application;

import com.weather.weatherforecast.datalocal.DataManager;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.initManager(getApplicationContext());
    }
}
