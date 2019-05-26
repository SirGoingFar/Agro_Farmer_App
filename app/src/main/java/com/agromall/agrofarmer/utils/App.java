package com.agromall.agrofarmer.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.agromall.agrofarmer.data.database.AgroFarmerAppDatabase;

import retrofit2.Retrofit;

public class App extends MultiDexApplication {

    private static App sInstance;
    private static Retrofit sRetrofit;
    private static AgroFarmerAppDatabase sDatabase;
    private static AppExecutors mExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        //init class static instance
        if (sInstance == null)
            sInstance = this;

        //init Retrofit instance
        sRetrofit = NetworkIoHelper.getRetrofitInstance(Constants.BASE_URL, false);

        //init the database instance
        sDatabase = AgroFarmerAppDatabase.getInstance(this);

        //init App Executors
        mExecutors = AppExecutors.getInstance();
    }

    public static App getsInstance() {
        return sInstance;
    }

    public Context getAppContext() {
        return this;
    }

    public static Retrofit getRetrofitInstance() {
        return sRetrofit;
    }

    public static AgroFarmerAppDatabase getDatabase() {
        return sDatabase;
    }

    public static AppExecutors getExecutors() {
        return mExecutors;
    }
}
