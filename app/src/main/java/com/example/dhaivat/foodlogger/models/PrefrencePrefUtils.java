package com.example.dhaivat.foodlogger.models;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by one on 31/10/15.
 */
public class PrefrencePrefUtils {

    // Set Login Id

    public static void setLLoginId(String loginId, Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("loginId", loginId);
        editor.commit();
    }

    public static String getLoginId(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String loginId = preferences.getString("loginId", "");
        return loginId;
    }


    public static void clearLoginId(Context ctx){

        SharedPreferences preferences = ctx.getSharedPreferences("loginId", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


    // Set Login Id

    public static void setName(String name, Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.commit();
    }

    public static String getname(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String name = preferences.getString("name", "");
        return name;
    }







}
