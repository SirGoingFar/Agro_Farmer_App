package com.agromall.agrofarmer.data.database;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.agromall.agrofarmer.data.viewmodels.AgroFarmerAppViewModel;

public class AgroFarmerAppViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private LifecycleOwner mOwner;

    public AgroFarmerAppViewModelFactory(Application application, LifecycleOwner mOwner) {
        this.application = application;
        this.mOwner = mOwner;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AgroFarmerAppViewModel(application, mOwner);
    }
}
