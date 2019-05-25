package com.agromall.agrofarmer.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FarmerData {

    @SerializedName("data")
    private Data data;

    @SerializedName("status")
    private String status;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public class Data {

        @SerializedName("imageBaseUrl")
        private String imageBaseUrl;

        @SerializedName("farmers")
        private List<FarmerDetail> farmers;

        @SerializedName("totalRec")
        private int totalRec;

        public void setImageBaseUrl(String imageBaseUrl) {
            this.imageBaseUrl = imageBaseUrl;
        }

        public String getImageBaseUrl() {
            return imageBaseUrl;
        }

        public void setFarmers(List<FarmerDetail> farmers) {
            this.farmers = farmers;
        }

        public List<FarmerDetail> getFarmers() {
            return farmers;
        }

        public void setTotalRec(int totalRec) {
            this.totalRec = totalRec;
        }

        public int getTotalRec() {
            return totalRec;
        }
    }
}