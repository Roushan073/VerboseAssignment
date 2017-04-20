package com.roushan.verboseassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    public static final String IS_USER_LOGGED_IN = "isUserLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";


    public Session(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("my_app", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedIn(boolean loggedIn) {
        editor.putBoolean(IS_USER_LOGGED_IN, loggedIn);
        editor.commit();
    }

    public void setUserDetails(String name, String phone, String address) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_ADDRESS, address);
        editor.commit();
    }

    public boolean checkLogin() {
        return prefs.getBoolean(IS_USER_LOGGED_IN, false);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, prefs.getString(KEY_NAME, null));
        user.put(KEY_PHONE, prefs.getString(KEY_PHONE, null));
        user.put(KEY_ADDRESS, prefs.getString(KEY_ADDRESS, null));

        return user;
    }

    public void log_out_user() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(ctx, SelectBuyerSeller.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
}
