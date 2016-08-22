/*
package com.syscraft.caremeds.sharedPrefrns;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefrnceCareMeds {

    public static final String PREFRENCE_NAME = "CareMedsAppPrefrnce";

    public static String getSharedPrefData(Context activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences(PREFRENCE_NAME,
                Context.MODE_PRIVATE);
        String value = null;
        if (prefs != null && prefs.contains(key)) {
            value = prefs.getString(key, "");
        }
        return value;
    }

    public static void setDataInSharedPrefrence(Context activity, String key,
                                                String value) {
        SharedPreferences prefs = activity.getSharedPreferences(PREFRENCE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void deletePrefrenceData(Context activity) {
        SharedPreferences prefs = activity.getSharedPreferences(PREFRENCE_NAME,
                Context.MODE_PRIVATE);
        prefs.edit().clear().commit();
    }
}
*/
