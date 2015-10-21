package com.fdu.socialapp.custom;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;


/**
 * Created by mao on 2015/10/12 0012.
 * A class for users
 */
public class User extends Application{
    private boolean isLogin;
    private String userName;

    private static User myUser;

    public static User getMyUser(){
        return myUser;
    }
    public boolean isLogin() {
        return isLogin;
    }
    public String getUserName() {
        if (isLogin) return userName;
        else return null;
    }
    public void setUserName(String name) {
        userName = name;
    }

    public void login() {
        isLogin = true;
    }

    public void logout() {
        isLogin = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "zFeMVYB4tMuBVvjcAWt8uBOh", "IInViyO81sNlBNj4TUSoXyQH");
        isLogin = false;
        userName = null;
        myUser = this;
    }
}
