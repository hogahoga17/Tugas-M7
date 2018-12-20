package com.hoga.sessionmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {

    private SharedPreferences mSharedPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    int PRIVATE_MODE;

    private static final String PREF_NAME = "SharedPreferenceLatihan";

    private static final String IS_LOGIN = "is_login";
    public static final String KEY_USER = "username";
    public static final String KEY_PASS = "password";

    public SessionManagement(Context mContext) {
        this.mContext = mContext;
        mSharedPref =this.mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPref.edit();
    }

    //fungsi untuk simpan shared preferences
    public void createLoginSession(String user, String pass) {
        //Storing login value as TRUE
        mEditor.putBoolean(IS_LOGIN, true);

        mEditor.putString(KEY_USER, user);
        mEditor.putString(KEY_PASS, pass);
        mEditor.commit();
    }

    //fungsi untuk mendapatkan informasi user
    public HashMap<String, String> getUserInformation() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_USER, mSharedPref.getString(KEY_USER, null));
        user.put(KEY_PASS, mSharedPref.getString(KEY_PASS, null));
        return user;
    }

    public boolean isLoggedIn() { return mSharedPref.getBoolean(IS_LOGIN, false); }

    public void checkIsLogin()  {
        //jika belum login
        if (!isLoggedIn())  {
            Intent i = new Intent(mContext, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        }
    }

    public void  logoutUser(){
        mEditor.clear();
        mEditor.commit();

        Intent i = new Intent(mContext, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }

}