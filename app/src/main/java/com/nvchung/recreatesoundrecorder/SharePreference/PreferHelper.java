package com.nvchung.recreatesoundrecorder.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferHelper {
    private static String PREF_HIGH_QUALITY = "pref_high_quality";

    public static void setQuality(Context ct, boolean high) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ct);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_HIGH_QUALITY, high);
        editor.apply();

    }

    public static boolean getQuality(Context ct) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ct);
        return sharedPreferences.getBoolean(PREF_HIGH_QUALITY, false);
    }
}
