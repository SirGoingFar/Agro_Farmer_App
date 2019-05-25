package com.agromall.agrofarmer.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.agromall.agrofarmer.data.models.FarmerData;

@Database(entities = {FarmerData.FarmerDetail.class}, version = 1, exportSchema = false)
public abstract class AgroFarmerAppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AgroFarmerAppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "agro_farmer_app_db";
    private static AgroFarmerAppDatabase sInstance;

    public static AgroFarmerAppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AgroFarmerAppDatabase.class, AgroFarmerAppDatabase.DATABASE_NAME)
                        .build();
            }
        }

        return sInstance;
    }

    public abstract AgroFarmerAppDao getDao();
}
