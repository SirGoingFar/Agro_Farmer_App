package com.agromall.agrofarmer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.agromall.agrofarmer.fragments.FarmerListFragment;
import com.agromall.agrofarmer.R;

public class FarmersCatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, FarmerListFragment.newInstance(), FarmerListFragment.class.getSimpleName())
                .addToBackStack(FarmerListFragment.class.getSimpleName())
                .commit();
    }
}
