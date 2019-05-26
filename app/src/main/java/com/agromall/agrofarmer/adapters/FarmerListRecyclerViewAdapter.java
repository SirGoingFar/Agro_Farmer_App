package com.agromall.agrofarmer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agromall.agrofarmer.R;
import com.agromall.agrofarmer.data.models.FarmerData;
import com.agromall.agrofarmer.data.models.FarmerDetail;
import com.agromall.agrofarmer.utils.Constants;
import com.agromall.agrofarmer.utils.Prefs;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FarmerListRecyclerViewAdapter extends RecyclerView.Adapter<FarmerListRecyclerViewAdapter.FarmerViewHolder> {

    private final Context mContext;
    private List<FarmerDetail> mDataList = new ArrayList<>();

    public FarmerListRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public FarmerListRecyclerViewAdapter.FarmerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FarmerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_farmer_detail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FarmerListRecyclerViewAdapter.FarmerViewHolder viewHolder, int i) {
        FarmerDetail farmerDetail = viewHolder.getCurrentItem();
        String imageUrl = Constants.BASE_IMAGE_URL.concat(farmerDetail.getPassportPhoto());
        viewHolder.farmerName.setText(farmerDetail.getFirstName());
        viewHolder.farmerContact.setText(farmerDetail.getMobileNo());
        viewHolder.farmerState.setText(farmerDetail.getCity());
        Glide.with(mContext).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.ivUserImage);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void refreshData(List<FarmerDetail> dataList) {
        if (dataList == null)
            return;

        mDataList = dataList;
        notifyDataSetChanged();
    }

    class FarmerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.container)
        ConstraintLayout viewContainer;

        @BindView(R.id.civ_farmer_image)
        ImageView ivUserImage;

        @BindView(R.id.tv_farmer_name)
        TextView farmerName;

        @BindView(R.id.tv_farmer_number)
        TextView farmerContact;

        @BindView(R.id.tv_farmer_state)
        TextView farmerState;

        FarmerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        FarmerDetail getCurrentItem() {
            return mDataList.get(getAdapterPosition());
        }
    }
}
