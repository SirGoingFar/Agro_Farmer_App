package com.agromall.agrofarmer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import static android.content.Context.MODE_PRIVATE;

public class Prefs {
    private static String PREFS = "prefs";
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor editor;
    private static Prefs sInstance;

    public static final String PREF_IMAGE_BASE_URL = "pref_image_base_url";
    public static final String PREF_TOTAL_RECORD = "pref_total_rec";
    public static final String PREF_LAST_FETCHED_PAGE_NUMBER = "pref_last_fetched_page_number";
    public static final String PREF_LAST_PAGE_NUMBER = "pref_last_page_number";

    private Prefs(Context context) {
        sInstance = this;
        mPrefs = context.getSharedPreferences(PREFS, MODE_PRIVATE);
        editor = mPrefs.edit();
    }

    public static Prefs getInstance() {
        if (sInstance == null) {
            sInstance = new Prefs(App.getsInstance().getAppContext());
        }
        return sInstance;
    }

    public void setTotalRecord(int totalRecord) {
        editor.putInt(PREF_TOTAL_RECORD, totalRecord).apply();
    }

    public int getTotalRecord() {
        return mPrefs.getInt(PREF_TOTAL_RECORD, -1);
    }

    public void setLastFetchedPageNumber(int pageNumber) {
        editor.putInt(PREF_LAST_FETCHED_PAGE_NUMBER, pageNumber).apply();
    }

    public int getLastFetchedPageNumber() {
        return mPrefs.getInt(PREF_LAST_FETCHED_PAGE_NUMBER, -1);
    }

    public void setImageBaseUrl(String imageBaseUrl) {
        editor.putString(PREF_IMAGE_BASE_URL, imageBaseUrl).apply();
    }

    public String getImageBaseUrl() {
        return mPrefs.getString(PREF_IMAGE_BASE_URL, null);
    }

    public boolean hasImageBaseUrl() {
        return !TextUtils.isEmpty(getImageBaseUrl());
    }

    public void setLastPageNumber(int lastPageNumber) {
        editor.putInt(PREF_LAST_PAGE_NUMBER, lastPageNumber).apply();
    }

    public int getLastPAgeNumber() {
        return mPrefs.getInt(PREF_LAST_PAGE_NUMBER, -1);
    }
}