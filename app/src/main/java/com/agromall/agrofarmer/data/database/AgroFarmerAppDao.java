package com.agromall.agrofarmer.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.agromall.agrofarmer.data.models.FarmerDetail;

import java.util.List;

@Dao
public interface AgroFarmerAppDao {

    @Query("SELECT * FROM farmer_data")
    LiveData<List<FarmerDetail>> getFarmerList();

    @Query("SELECT * FROM farmer_data where id=:id")
    LiveData<FarmerDetail> getFarmerWithId(int id);

    @Insert
    void insertFarmerDetails(List<FarmerDetail> details);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFarmerDetail(FarmerDetail farmerDetail);

}
