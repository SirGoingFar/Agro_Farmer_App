package com.agromall.agrofarmer.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import java.util.ArrayList;

public class App extends MultiDexApplication {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        //init class static instance
        if(sInstance == null)
            sInstance = this;

    }

    public static App getsInstance() {
        return sInstance;
    }

    public Context getAppContext(){
        return this;
    }
}
