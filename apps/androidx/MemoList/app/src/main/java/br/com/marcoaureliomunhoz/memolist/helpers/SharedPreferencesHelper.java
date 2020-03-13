package br.com.marcoaureliomunhoz.memolist.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesHelper {

    private static SharedPreferences getPreferences(Context context) {
        SharedPreferences preferences = null;
        try {
            preferences = context.getSharedPreferences("memolist_pref", Context.MODE_PRIVATE);
        }catch (Exception ex){}
        return preferences;
    }

    public static String getString(Context context, String key, String defaultValue) {
        try {
            return getPreferences(context).getString(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int getInt(Context context, String key, int defaultValue) {
        try {
            return getPreferences(context).getInt(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static void putString(Context context, String key, String value) {
        try {
            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {
        }
    }

    public static void putInt(Context context, String key, int value) {
        try {
            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(key, value);
            editor.commit();
        } catch (Exception e) {
        }
    }



}
