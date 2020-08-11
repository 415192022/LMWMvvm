package com.lmw.common.mmkv;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tencent.mmkv.MMKV;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MmkvHelper {
    private static MMKV mmkv;

    private MmkvHelper() {
        mmkv = MMKV.defaultMMKV();
    }

    public static MmkvHelper getInstance() {
        return MmkvHolder.INSTANCE;
    }


    public MMKV getMmkv() {
        return mmkv;
    }

    /**
     * 存入map集合
     *
     * @param key 标识
     * @param map 数据集合
     */
    public void saveInfo(String key, Map<String, Object> map) {
        Gson gson = new Gson();
        JSONArray mJsonArray = new JSONArray();
        JSONObject object = null;
        try {
            object = new JSONObject(gson.toJson(map));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonArray.put(object);
        encode(key, mJsonArray.toString());
    }

    /**
     * 获取map集合
     *
     * @param key 标识
     */
    public Map<String, String> getInfo(String key) {
        Map<String, String> itemMap = new HashMap<>();
        String result = decodeString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);

                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
            }
        } catch (JSONException e) {

        }
        return itemMap;
    }

    /**
     * 扩展MMKV类使其支持对象存储
     */

    public <T> T getObject(String key, Class<T> t) {
        String str = decodeString(key, null);
        if (!TextUtils.isEmpty(str)) {
            return new GsonBuilder().create().fromJson(str, t);
        } else {
            return null;
        }
    }

    public void putObject(String key, Object object) {
        String jsonString = new GsonBuilder().create().toJson(object);
        encode(key, jsonString);
    }

    public boolean encode(String key, boolean value) {
        return mmkv.encode(key, value);
    }

    public boolean decodeBool(String key) {
        return mmkv.decodeBool(key);
    }

    public boolean decodeBool(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    public boolean encode(String key, int value) {
        return mmkv.encode(key, value);
    }

    public int decodeInt(String key) {
        return mmkv.decodeInt(key);
    }

    public int decodeInt(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    public boolean encode(String key, long value) {
        return mmkv.encode(key, value);
    }

    public long decodeLong(String key) {
        return mmkv.decodeLong(key);
    }

    public long decodeLong(String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    public boolean encode(String key, float value) {
        return mmkv.encode(key, value);
    }

    public float decodeFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    public float decodeFloat(String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    public boolean encode(String key, double value) {
        return mmkv.encode(key, value);
    }

    public double decodeDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    public double decodeDouble(String key, double defaultValue) {
        return mmkv.decodeDouble(key, defaultValue);
    }

    public boolean encode(String key, String value) {
        return mmkv.encode(key, value);
    }

    public String decodeString(String key) {
        return mmkv.decodeString(key);
    }

    public String decodeString(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    public boolean encode(String key, Set<String> value) {
        return mmkv.encode(key, value);
    }

    private static class MmkvHolder {
        private static final MmkvHelper INSTANCE = new MmkvHelper();
    }

}
