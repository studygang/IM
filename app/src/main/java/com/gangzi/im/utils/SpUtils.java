package com.gangzi.im.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.gangzi.im.IMApplication;

/**
 * Created by Administrator on 2017/6/1.
 */

public class SpUtils {
    public static final String IS_NEW_INVITE = "is_new_invite";//新的邀请标记
    private  static SpUtils mSpUtils=new SpUtils();
    private static SharedPreferences sharedPreferences;;
    public SpUtils() {
    }
    public static SpUtils getmSpUtils(){
        if (sharedPreferences==null){
            sharedPreferences = IMApplication.getGlobalApplication().getSharedPreferences("im", Context.MODE_PRIVATE);
        }
        return  mSpUtils;
    }
    public void saveData(String key,Object value){
        if (value instanceof String){
            sharedPreferences.edit().putString(key, (String) value).commit();
        }else if (value instanceof Boolean){
            sharedPreferences.edit().putBoolean(key, (Boolean) value).commit();
        }else if (value instanceof Integer){
            sharedPreferences.edit().putInt(key, (Integer) value).commit();
        }
    }
    public String getString(String key,String defValue){
        return  sharedPreferences.getString(key,defValue);
    }
    public boolean getBoolean(String key,boolean defValue){
        return sharedPreferences.getBoolean(key,defValue);
    }
    public int getInt(String key,int defVaule){
        return sharedPreferences.getInt(key,defVaule);
    }
}
