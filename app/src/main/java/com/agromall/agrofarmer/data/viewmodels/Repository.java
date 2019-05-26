package com.agromall.agrofarmer.data.viewmodels;

import android.support.annotation.Nullable;

public interface Repository<T> {

    T getLocalData(@Nullable Object... varArgs);

    void getApiData(@Nullable Object... varArgs);
}
