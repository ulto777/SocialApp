package com.fdu.socialapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.fdu.socialapp.User;

/**
 * Created by mh on 2015/10/14.
 */
public class UserInfo {

    private String username;
    private String password;

    private static UserInfo instance = null;

    private UserInfo(){}

    public static UserInfo getInstance(){
        if (instance == null){
            synchronized (UserInfo.class){
                if (instance == null){
                    instance = new UserInfo();
                }
            }
        }
        return instance;
    }

    public String getUsername() {
        if (username == null) return "";
        return username;
    }

    public String getPassword() {
        if (password == null) return "";
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void persist(Context context){
        UserInfo userInfo = getInstance();
        SharedPreferences sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("username",userInfo.getUsername());
        editor.putString("password",userInfo.getPassword());

        editor.apply();
    }

    public static void load(Context context){
        UserInfo userInfo = getInstance();
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        userInfo.setUsername(sp.getString("username", ""));
        userInfo.setPassword(sp.getString("password", ""));
    }

    public static void clear(Context context){
        instance = null;
        SharedPreferences sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
