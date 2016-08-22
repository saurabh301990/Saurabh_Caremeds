package com.syscraft.caremeds.sharedPrefrns;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("deprecation")
public class PreferenceConnector {
    public static final String PREF_NAME = "CAREMEDS_APP";

    public static final int MODE = Context.MODE_WORLD_WRITEABLE;

//    public static final String LOGIN_TIME = "LOGIN_TIME";
    public static final String LAST_ACTIVITY_TIME = "LAST_ACTIVITY_TIME";
    //	public static final String TIMEOUT_TIME ="TIMEOUT_TIME" ;

    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String LOGIN_ACCOUNT_NAME = "LOGIN_ACCOUNT_NAME";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";
    public static final String USERID = "USERID";
    public static final String USERNAME = "USERNAME";
    public static final String FULLNAME = "FULLNAME";
    public static final String CAN_AUDIT = "CAN_AUDIT";
    public static final String SESSION_TIMEOUT = "SESSION_TIMEOUT";
    public static final String WARNING_TIME = "WARNING_TIME";
    public static final String CACHE_PATIENT_LIST = "CACHE_PATIENT_LIST";
    public static final String SETTING_ALLOWABLE_DELAY_MINUTES = "SETTING_ALLOWABLE_DELAY_MINUTES";
    public static final String SETTING_WITNESS_CHECKIN = "SETTING_WITNESS_CHECKIN";
    public static final String SETTING_BASE_URL = "SETTING_BASE_URL";
    public static final String SECONDRY_USERID = "SECONDRY_USERID";
    public static final String SECONDRY_USERFULLNAME = "SECONDRY_USERFULLNAME";
    public static final String IS_USER_DONE_LOGIN_ONCE = "IS_USER_DONE_LOGIN_ONCE";
    public static final String LAST_LOGIN_USERID = "LAST_LOGIN_USERID";
    public static final String LAST_LOGIN_USERPASSWORD = "LAST_LOGIN_USERPASSWORD";
    public static final String LAST_LOGIN_CARETAKER_ID = "LAST_LOGIN_CARETAKER_ID";

    public static void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static boolean readBoolean(Context context, String key,
                                      boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void writeFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public static float readFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();

    }

    public static void writeArraylist(Context context, String key,
                                      List<String> arryid) {
        Set<String> set = new HashSet<String>(arryid);
        getEditor(context).putStringSet(key, set).commit();

    }

    public static List<String> ReadArraylist(Context context, String key) {
        Set<String> stock_Set = getPreferences(context).getStringSet(key,
                new HashSet<String>());
        List<String> demo = new ArrayList<String>(stock_Set);

        return demo;
    }

    public static long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);

    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void remove(Context context, String key) {
        getEditor(context).remove(key);

    }

}
