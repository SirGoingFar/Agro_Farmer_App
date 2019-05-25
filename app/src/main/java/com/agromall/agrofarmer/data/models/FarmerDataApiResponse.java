package com.agromall.agrofarmer.data.models;

import com.google.gson.annotations.SerializedName;

public class FarmerDataApiResponse {

    @SerializedName("data")
    private FarmerData.Data data;

    @SerializedName("status")
    private String status;

    public void setData(FarmerData.Data data) {
        this.data = data;
    }

    public FarmerData.Data getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}