package com.agromall.agrofarmer.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.agromall.agrofarmer.R;
import com.agromall.agrofarmer.activities.FarmersCatalogActivity;
import com.agromall.agrofarmer.adapters.FarmerListRecyclerViewAdapter;
import com.agromall.agrofarmer.custom.EndlessRecyclerOnScrollListener;
import com.agromall.agrofarmer.data.database.AgroFarmerAppViewModelFactory;
import com.agromall.agrofarmer.data.models.FarmerDetail;
import com.agromall.agrofarmer.data.viewmodels.AgroFarmerAppViewModel;
import com.agromall.agrofarmer.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FarmerListFragment extends Fragment {

    class ViewHolder {

        private EndlessRecyclerOnScrollListener scrollListener;
        private FarmerListRecyclerViewAdapter mAdapter;
        private RecyclerView farmerList;
        private ProgressBar loadingProgressBar;

        ViewHolder(View view) {
            init(view);
        }

        private void init(View view) {

            scrollListener = new EndlessRecyclerOnScrollListener(currentPage, false) {
                @Override
                public void onLoadMore(int nextPage) {
                    currentPage++;

                    if (currentPage < mPrefs.getLastPageNumber()) {
                        loadMore();
                        setLoading();
                    }
                }
            };

            loadingProgressBar = view.findViewById(R.id.pb_loading);
            farmerList = view.findViewById(R.id.rv_farmer_data);
            mAdapter = new FarmerListRecyclerViewAdapter(catalogActivity);
            farmerList.setHasFixedSize(true);
            farmerList.addOnScrollListener(scrollListener);
            farmerList.setLayoutManager(new LinearLayoutManager(catalogActivity, LinearLayoutManager.VERTICAL, false));
            farmerList.setAdapter(mAdapter);
        }

        private void setLoading() {
            loadingProgressBar.setVisibility(View.VISIBLE);
        }

        private void setLoaded() {
            loadingProgressBar.setVisibility(View.GONE);
        }

        void refreshData(List<FarmerDetail> farmerList) {
            mAdapter.refreshData(farmerList);
            setLoaded();
            scrollListener.setIsLastPage(currentPage > mPrefs.getLastPageNumber());
        }
    }

    private void loadMore() {
        model.loadMore();
    }

    public class Model {

        private AgroFarmerAppViewModel mViewModel;
        private List<FarmerDetail> mFarmerList;

        Model() {
            AgroFarmerAppViewModelFactory factory = new AgroFarmerAppViewModelFactory(catalogActivity.getApplication(), FarmerListFragment.this);
            mViewModel = ViewModelProviders.of(FarmerListFragment.this, factory).get(AgroFarmerAppViewModel.class);

            mFarmerList = new ArrayList<>();
        }

        void init() {

            //observe Farmer List change
            mViewModel.getFarmerListObserver().observe(FarmerListFragment.this, new Observer<List<FarmerDetail>>() {
                @Override
                public void onChanged(@Nullable List<FarmerDetail> farmerDetails) {
                    if (farmerDetails == null)
                        return;

                    mFarmerList = farmerDetails;
                    refreshView(mFarmerList);
                }
            });

            loadMore();
        }

        void loadMore() {
            mViewModel.getFarmerList(currentPage);
            setLoading();
        }
    }

    private FarmersCatalogActivity catalogActivity;
    private Prefs mPrefs;
    private int currentPage;
    private int lastPage;

    private Model model;
    private ViewHolder viewHolder;

    public static FarmerListFragment newInstance() {
        Bundle args = new Bundle();
        FarmerListFragment fragment = new FarmerListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FarmersCatalogActivity)
            catalogActivity = (FarmersCatalogActivity) context;

        mPrefs = Prefs.getInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new Model();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_farmer_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        viewHolder = new ViewHolder(view);
        model.init();
    }

    private void refreshView(List<FarmerDetail> farmerList) {
        viewHolder.refreshData(farmerList);
    }

    private void setLoading() {
        viewHolder.setLoading();
    }
}
