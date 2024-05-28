package com.example.doanhunnyfood.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.Food;

import java.util.List;

public class FoodManagerRecycAdapter extends RecyclerView.Adapter<FoodManagerRecycAdapter.FoodManagerViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Food> mList;

    public FoodManagerRecycAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FoodManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyc_food_manager_item,parent,false);
        return new FoodManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodManagerViewHolder holder, int position) {
        if (mList != null){
            holder.tvFoodM.setText(mList.get(position).name);
        }

    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public void setList(List<Food> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class FoodManagerViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFoodM;
        public ImageView ivView, ivEdit;
        public CardView cv;
        public FoodManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodM = itemView.findViewById(R.id.tvFoodM);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;
        }
    }
}
