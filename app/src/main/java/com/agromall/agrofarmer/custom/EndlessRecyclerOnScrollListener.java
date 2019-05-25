package com.agromall.agrofarmer.custom;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int mPreviousTotal = 0;
    private boolean mLoading = true;
    private int currentPage;
    private boolean isLastPage = false;
    private int lastPage = -1;

    public EndlessRecyclerOnScrollListener(int currentPage, boolean isLastPage) {
        this.currentPage = currentPage;
        this.isLastPage = isLastPage;
    }

    public EndlessRecyclerOnScrollListener(int currentPage, int lastPage) {
        this.currentPage = currentPage;
        this.lastPage = lastPage;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = totalItemCount;
            }
        }
        int visibleThreshold = 3;
        boolean shouldLoadMore = lastPage == -1 ? !isLastPage : currentPage < lastPage;
        if (!mLoading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold) && shouldLoadMore) {
            // End has been reached
            onLoadMore(++currentPage);

            mLoading = true;
        }
    }

    public abstract void onLoadMore(int nextPage);

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        this.mPreviousTotal = 0;
    }
}