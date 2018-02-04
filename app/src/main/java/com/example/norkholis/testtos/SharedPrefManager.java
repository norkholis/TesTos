package com.example.norkholis.testtos;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by norkholis on 03/02/18.
 */

public class SharedPrefManager {
    public static final String SP_LOGIN_APP = "spLoginApp";

    public static final String SP_USERNAME = "spUsername";
    public static final String SP_NAME = "spName";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_TOKEN = "spToken";

    public static final String SP_IS_LOGIN = "belum";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        this.sp = context.getSharedPreferences(SP_LOGIN_APP, context.MODE_PRIVATE);
        this.spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpUsername(){
        return sp.getString(SP_USERNAME,"");
    }

    public  String getSpEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpToken(){
        return sp.getString(SP_TOKEN, "");
    }

    public String getSpName(){
        return sp.getString(SP_NAME, "");
    }

    public boolean getSpIsLogin(){
        return sp.getBoolean(SP_IS_LOGIN, false);
    }
}
