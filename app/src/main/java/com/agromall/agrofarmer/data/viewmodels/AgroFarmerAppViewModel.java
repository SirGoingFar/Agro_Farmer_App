package com.agromall.agrofarmer.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.agromall.agrofarmer.data.api.GetFarmersData;
import com.agromall.agrofarmer.data.database.AgroFarmerAppDatabase;
import com.agromall.agrofarmer.data.models.FarmerData;
import com.agromall.agrofarmer.data.models.FarmerDetail;
import com.agromall.agrofarmer.utils.App;
import com.agromall.agrofarmer.utils.AppExecutors;
import com.agromall.agrofarmer.utils.Constants;
import com.agromall.agrofarmer.utils.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AgroFarmerAppViewModel extends AndroidViewModel {

    private LifecycleOwner mLifecycleOwner;
    private FarmerListRepository repository;
    private MutableLiveData<List<FarmerDetail>> mFarmerListMutableLiveData;
    private MutableLiveData<FarmerDetail> mFarmerDetailMutableLiveData;
    private Retrofit retrofit;
    private Prefs prefs;
    private AgroFarmerAppDatabase mDb;
    private AppExecutors mExecutors;


    public AgroFarmerAppViewModel(Application application, LifecycleOwner lifecycleOwner) {
        super(application);
        this.mLifecycleOwner = lifecycleOwner;
        repository = new FarmerListRepository();
        mFarmerListMutableLiveData = new MutableLiveData<>();
        mFarmerDetailMutableLiveData = new MutableLiveData<>();
        mDb = App.getDatabase();
        mExecutors = App.getExecutors();
    }

    public LiveData<List<FarmerDetail>> getFarmerListObserver() {
        return mFarmerListMutableLiveData;
    }

    public LiveData<FarmerDetail> getFarmerObserver() {
        return mFarmerDetailMutableLiveData;
    }

    private void setFarmerListMutableLiveData(List<FarmerDetail> farmerList) {
        mFarmerListMutableLiveData.postValue(farmerList);
    }

    private void setFarmerDetailMutableLiveData(FarmerDetail farmerDetail) {
        mFarmerDetailMutableLiveData.postValue(farmerDetail);
    }

    public void getFarmerList(int page) {
        repository.getFarmerData(page);
    }

    public void getDataForFarmerWith(int farmerId) {
        repository.getFarmerWith(farmerId);
    }


    class FarmerListRepository implements Repository {
        @Override
        public Object getLocalData(@Nullable Object... varArgs) {

            int page = -1;

            if (varArgs != null) {
                page = (Integer) varArgs[0];
            }

            if (prefs.getLastFetchedPageNumber() < page)
                return null;

            mExecutors.diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.getDao().getFarmerList().observe(mLifecycleOwner, new Observer<List<FarmerDetail>>() {
                        @Override
                        public void onChanged(@Nullable List<FarmerDetail> farmerDetails) {
                            if (farmerDetails == null)
                                return;

                            setFarmerListMutableLiveData(farmerDetails);
                        }
                    });
                }
            });

            return new Object();
        }

        @Override
        public void getApiData(@Nullable Object... varArgs) {
            int page = prefs.getLastFetchedPageNumber() + 1;
            int limit = Constants.MAX_DATA_FETCH_LIMIT;

            if (retrofit == null)
                retrofit = App.getRetrofitInstance();

            if (prefs == null)
                prefs = Prefs.getInstance();

            Call<FarmerData> call = retrofit.create(GetFarmersData.class).getFarmersDataAt(page, limit);

            final int finalPage = page;

            call.enqueue(new Callback<FarmerData>() {
                @Override
                public void onResponse(Call<FarmerData> call, Response<FarmerData> response) {
                    if (response.isSuccessful()) {

                        FarmerData data = response.body();

                        List<FarmerDetail> farmerDetails = data.getData().getFarmers();
                        saveFarmerDataToDatabase(farmerDetails);

                        //Save Image Base Url to cache
                        if (!prefs.hasImageBaseUrl())
                            prefs.setImageBaseUrl(data.getData().getImageBaseUrl());

                        //save the last fetched page number
                        prefs.setLastFetchedPageNumber(finalPage);
                    }
                }

                @Override
                public void onFailure(Call<FarmerData> call, Throwable t) {

                }
            });
        }

        public void getFarmerData(int page) {

            Object dataObject = getLocalData(page);
            if (dataObject != null) {

                try {
                    List<FarmerDetail> farmerList = (List<FarmerDetail>) dataObject;
                    setFarmerListMutableLiveData(farmerList);
                }catch (Exception exception){
                    exception.printStackTrace();
                }

                return;
            }

            getApiData();
        }

        private void saveFarmerDataToDatabase(final List<FarmerDetail> farmerDetails) {
            mExecutors.diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.getDao().insertFarmerDetails(farmerDetails);

                    //query for data
                    mDb.getDao().getFarmerList().observe(mLifecycleOwner, new Observer<List<FarmerDetail>>() {
                        @Override
                        public void onChanged(@Nullable List<FarmerDetail> farmerList) {

                            if (farmerList == null)
                                return;

                            setFarmerListMutableLiveData(farmerList);
                        }
                    });
                }
            });
        }

        private void getFarmerWith(final int farmerId) {
            mExecutors.diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.getDao().getFarmerWithId(farmerId).observe(mLifecycleOwner, new Observer<FarmerDetail>() {
                        @Override
                        public void onChanged(@Nullable FarmerDetail farmerDetail) {
                            if (farmerDetail == null)
                                return;

                            setFarmerDetailMutableLiveData(farmerDetail);
                        }
                    });
                }
            });
        }
    }
}
