package com.squareboat.secretkeys;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vipul Asri on 02/04/17.
 */

public class LocalStoreUtils {

    private static final String PREF_FILE_NAME = "com.squareboat.secretkeys";
    private static final String KEY_SECRET = "api_secret";

    public static void setAppSecret(String secret, Context context) {
        try {
            SharedPreferences.Editor editor = getSharedEditor(context);
            editor.putString(KEY_SECRET, secret);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAppSecret(Context context) {
        try {
            SharedPreferences pref = getSharedPreference(context);
            return pref.getString(KEY_SECRET, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SharedPreferences.Editor getSharedEditor(Context context)
            throws Exception {
        if (context == null) {
            throw new Exception("Context null Exception");
        }
        return getSharedPreference(context).edit();
    }

    private static SharedPreferences getSharedPreference(Context context)
            throws Exception {
        if (context == null) {
            throw new Exception("Context null Exception");
        }
        return context.getSharedPreferences(PREF_FILE_NAME, 0);
    }

}
