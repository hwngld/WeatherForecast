package com.weather.weatherforecast.datalocal;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.weather.weatherforecast.model.ItemLocation;
import com.weather.weatherforecast.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String TODAY_KEY = "TODAY_KEY";
    private static final String FIVE_DAY_KEY = "FIVE_DAY_KEY";
    private static final String FIRST_INSTALL_KEY = "FIRST_INSTALL_KEY";
    private static final String LOCATION_KEY = "LOCATION_KEY";
    private static final String ALL_LOCATION_KEY = "ALL_LOCATION_KEY";
    private static DataManager instance;
    private InitPreferences initPreferences;

    public static void initManager(Context context){
        instance = new DataManager();
        instance.initPreferences = new InitPreferences(context);
    }
    public static DataManager getInstance(){
        if(instance==null){
            instance = new DataManager();
        }
        return instance;
    }
    public static void setTodayWeather(Weather weather){
        String data = new Gson().toJson(weather);
        DataManager.getInstance().initPreferences.putString(TODAY_KEY,data);

    }
    public static Weather getTodayWeather(){
        String data = DataManager.getInstance().initPreferences.getString(TODAY_KEY);
        Weather weather = new Gson().fromJson(data, Weather.class);
        return weather;
    }
    public static void setFiveDayWeather(List<Weather> list){
        JsonArray jsonArray = new Gson().toJsonTree(list).getAsJsonArray();
        String strJsonArr = jsonArray.toString();

        DataManager.getInstance().initPreferences.putString(FIVE_DAY_KEY,strJsonArr);
    }
    public static List<Weather> getFiveDayWeather(){
        List<Weather> list = new ArrayList<>();
        String strJsonArr = DataManager.getInstance().initPreferences.getString(FIVE_DAY_KEY);
        try {
            JSONArray jsonArray = new JSONArray(strJsonArr);
            JSONObject object;
            Weather weather;
            for(int i = 0; i < jsonArray.length();i++){
                object = jsonArray.getJSONObject(i);
                weather = new Gson().fromJson(object.toString(),Weather.class);
                list.add(weather);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void setFirstInstall(boolean value){
        DataManager.getInstance().initPreferences.putBoolean(FIRST_INSTALL_KEY, value);
    }
    public static boolean getFirstInstall(){
        return DataManager.getInstance().initPreferences.getBoolean(FIRST_INSTALL_KEY);
    }
    public static void setListLocation(List<ItemLocation> value){
        JsonArray jsonArray = new Gson().toJsonTree(value).getAsJsonArray();
        String str = jsonArray.toString();
        DataManager.getInstance().initPreferences.putString(LOCATION_KEY,str);
    }
    public static List<ItemLocation> getListLocation(){
        List<ItemLocation> list = new ArrayList<>();
        String str = DataManager.getInstance().initPreferences.getString(LOCATION_KEY);

        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject object;
            ItemLocation location;
            for(int i = 0; i < jsonArray.length(); i++){
                object = jsonArray.getJSONObject(i);
                location = new Gson().fromJson(object.toString(), ItemLocation.class);
                list.add(location);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void setListAllLocation(List<ItemLocation> value){
        JsonArray jsonArray = new Gson().toJsonTree(value).getAsJsonArray();
        String str = jsonArray.toString();
        DataManager.getInstance().initPreferences.putString(ALL_LOCATION_KEY,str);
    }
    public static List<ItemLocation> getListAllLocation(){
        List<ItemLocation> list = new ArrayList<>();
        String str = DataManager.getInstance().initPreferences.getString(ALL_LOCATION_KEY);

        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject object;
            ItemLocation location;
            for(int i = 0; i < jsonArray.length(); i++){
                object = jsonArray.getJSONObject(i);
                location = new Gson().fromJson(object.toString(), ItemLocation.class);
                list.add(location);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
