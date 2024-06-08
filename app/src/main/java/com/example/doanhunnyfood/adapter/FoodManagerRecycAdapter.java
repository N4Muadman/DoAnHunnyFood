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
    public static ItemClickListener itemEditClickListener;

    public FoodManagerRecycAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public  void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        FoodManagerRecycAdapter.itemEditClickListener = itemEditClickListener;
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
            holder.tvNameFood.setText(mList.get(position).name);
            holder.ivFood.setImageResource(mList.get(position).image);
            holder.tvPrice.setText(String.format("%.3f", mList.get(position).price) + " đồng");
        } else {
            holder.tvNameFood.setText("No Food");
            holder.ivFood.setImageResource(R.drawable.img_coffee); // Thay bằng hình ảnh mặc định nếu không có
            holder.tvPrice.setText("0.000 đồng");
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public Food getItem(int position){
        if (mList == null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Food> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class FoodManagerViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameFood, tvPrice;
        public ImageView  ivEdit,ivFood;
        public CardView cv;
        public FoodManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameFood = itemView.findViewById(R.id.tvNameFood);
            tvPrice = itemView.findViewById(R.id.txtPrice);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivFood = itemView.findViewById(R.id.imgFood);
            cv = (CardView) itemView;

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemEditClickListener != null){
                        itemEditClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
