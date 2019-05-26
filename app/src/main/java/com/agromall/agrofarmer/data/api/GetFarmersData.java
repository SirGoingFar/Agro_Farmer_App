package com.agromall.agrofarmer.data.api;

import com.agromall.agrofarmer.data.models.FarmerData;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetFarmersData {

    @POST("get-sample-farmers")
    Call<FarmerData> getFarmersDataAt(
            @Query("page") int page,
            @Query("limit") int limit
    );

}
