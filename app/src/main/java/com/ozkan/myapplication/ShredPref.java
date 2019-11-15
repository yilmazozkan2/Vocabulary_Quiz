package com.ozkan.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class ShredPref {
    private static String PREF_NAME = "Skor";
    private static String PREF_KEY = "Key";

    public void degerEkle(Context context, int deger){
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(PREF_KEY,deger);
        editor.commit();
    }

    public int degerGöster(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        int i = settings.getInt(PREF_KEY,0);
        return i;
    }

}
