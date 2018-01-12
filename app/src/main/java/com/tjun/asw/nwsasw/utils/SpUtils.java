package com.tjun.asw.nwsasw.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asw on 2018/1/12.
 * Sp tool utils
 */

public class SpUtils {
    private static SharedPreferences sp;
    private static String mSharedPreferences = "shared_preferences_asw_default";

    private static SharedPreferences getSp(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(mSharedPreferences, Context.MODE_PRIVATE);
        }
        return sp;
    }

    /**
     * Boolean类型sp操作
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context,String key,boolean value){
        getSp(context).edit().putBoolean(key,value).apply();
    }

    public static boolean getBoolean(Context context,String key,boolean defValue){
        return getSp(context).getBoolean(key,defValue);
    }

    /**
     * String类型sp操作
     * @param context
     * @param key
     * @param value
     */

    public static void putString(Context context,String key,String value){
        getSp(context).edit().putString(key,value).apply();
    }

    public static String getString(Context context,String key,String defValue){
        return getSp(context).getString(key,defValue);
    }

    /**
     * Int类型sp操作
     * @param context
     * @param key
     * @param value
     */

    public static void putInt(Context context,String key,int value){
        getSp(context).edit().putInt(key,value).apply();
    }

    public static int getInt(Context context,String key,int defValue){
        return getSp(context).getInt(key,defValue);
    }

    /**
     * List类型sp操作
     * @param context
     * @param key
     * @param dataList
     * @param <T>
     */
    public static <T> void putDataList(Context context, String key, List<T> dataList){
        if (dataList == null || dataList.size() <= 0){
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(dataList);
        SpUtils.putString(context,key,json);
    }

    public static <T> List<T> getDataList(Context context,String key,Class<T> cls){
        List<T> dataList = new ArrayList<T>();
        String strJson = SpUtils.getString(context,key,null);
        if (strJson == null){
            return dataList;
        }
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
        for (final JsonElement elem : array) {
            dataList.add(gson.fromJson(elem, cls));
        }
        return dataList;
    }

    /**
     * 对theme的索引
     * @param context
     * @param index
     */
    public static void putThemeIndex(Context context,int index){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("ThemeIndex", index).apply();
    }

    public static int getThemeIndex(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt("ThemeIndex",5);
    }

    /**
     * model的存储获取封装
     * @param context
     * @return
     */
    public static boolean getNightModel(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pNightMode", false);
    }

    public static void setNightModel(Context context, boolean nightModel) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("pNightMode", nightModel).apply();
    }
}
