package com.stateunion.eatshop;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by ZHANGGUOZHENG on 2017/11/28.
 */

public class DataStore {


    public static final String TAG = "DataStore";

    private static Context context;


    public static void init(Context context) {
        DataStore.context = context;
    }

    /**
     * 读取序列化对象
     *
     * @param name 存储名字
     * @return 返回对象   为null则没有这个序列化保存对象
     */
    public static Object readObject(String name) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        Object object = null;
        try {
            fis = context.openFileInput(name);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.closeInputStream(fis);
            FileUtil.closeInputStream(ois);
        }

        return object;

    }


    /**
     * 储存序列化对象
     *
     * @param name   存储名字
     * @param object 要保存序列化对象
     */
    public static void writeObject(String name, Object object) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            FileUtil.closeOutputStream(fos);
            FileUtil.closeOutputStream(oos);

        }
    }


    public static SharedPreferences getSharedPreference() {

        return context.getSharedPreferences(TAG, Context.MODE_PRIVATE);

    }

    public static void put(String key, String value) {
        getSharedPreference().edit().putString(key, value).commit();
    }

    public static void put(String key, int value) {
        getSharedPreference().edit().putInt(key, value).commit();
    }

    public static void put(String key, long value) {
        getSharedPreference().edit().putLong(key, value).commit();
    }

    public static void put(String key, boolean value) {
        getSharedPreference().edit().putBoolean(key, value).commit();
    }

    public static void put(String key, float value) {
        getSharedPreference().edit().putFloat(key, value).commit();
    }

    public static int getInt(String key) {
        return getSharedPreference().getInt(key, -1);
    }

    public static boolean getBoolean(String key) {

        return getSharedPreference().getBoolean(key, false);

    }

    public static long getLong(String key) {
        return getSharedPreference().getLong(key, -1);
    }

    public static String getString(String key) {
        return getSharedPreference().getString(key, "");
    }

    public static float getFloat(String key) {
        return getSharedPreference().getFloat(key, -1);
    }

}
