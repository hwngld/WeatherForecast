package com.weather.weatherforecast.datalocal;

import android.content.Context;
import android.content.SharedPreferences;

public class InitPreferences {
    private static final String MY_PREFERENCES_NAME = "MY_PREFERENCES_NAME";
    private Context context;

    public InitPreferences(Context context) {
        this.context = context;
    }
    public void putString(String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.putString(key, value);
        editor.apply();
    }
    public String getString(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCES_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
    public void putBoolean(String key, boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public boolean getBoolean(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCES_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }
}
